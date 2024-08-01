/*��@�m��2 - ��Ʃw�q*/
create table SHELTER 
   (	
    SHELTER_ID  VARCHAR2(4) primary key, 
    CATEGORY VARCHAR2(4) references CATEGORY(CATEGORY_ID), 
	VILLAGE VARCHAR2(4) references VILLAGE(VILLAGE_ID), 
	COUNTRY VARCHAR2(4)  references COUNTRY(COUNTRY_ID) , 
	ADDRESS NVARCHAR2(50), 
	CAPACITY INTEGER, 
	FLOOR INTEGER,
    POLICE_STATION VARCHAR2(4) references POLICE(POLICE_ID)
	);   
    
create table CATEGORY 
   (	
    CATEGORY_ID  VARCHAR2(4) primary key, 
    CATEGORY NVARCHAR2(10)
	); 

create table POLICE 
   (	
    POLICE_ID VARCHAR2(4) primary key, 
	NAME NVARCHAR2(20),
    ADDRESS NVARCHAR2(20),
    TEL VARCHAR2(10)
	);
  
create table VILLAGE 
   (	
    VILLAGE_ID VARCHAR2(4) primary key, 
	NAME NVARCHAR2(20),
    ADDRESS NVARCHAR2(20),
    TEL VARCHAR2(10)
	);
    
create table COUNTRY 
   (	
    COUNTRY_ID VARCHAR2(4) primary key, 
	CITY NVARCHAR2(20),
    DISTRICT NVARCHAR2(20)
	);
    

/*��@�m��3 - ��ƾޱ�*/

insert into CATEGORY values('K01','���J');
insert into CATEGORY values('K02','�j��');
insert into CATEGORY values('K03','���@�]�I');
insert into CATEGORY values('K04','�p����');

insert into VILLAGE values('C001','�j�H��','�˫n���q��1035��','03-758 1072');
insert into VILLAGE values('C002','�˫n��','�˫n��˫n�����s�� 103 ��','037-472735');
insert into VILLAGE values('C003','�s�Ψ�','�˫n��s�Ψ������ 14 ��','037-614186');
insert into VILLAGE values('C004','�H����','���s��H����������136-1��','037-724839');
insert into VILLAGE values('C005','��]��','�]�ߥ���]�������� 766 ��','037-333240');
insert into VILLAGE values('C006','���ڨ�','���ڨ����ڸ�96��','037-660001');
insert into VILLAGE values('C007','������','���������j��82��','037-661145');
insert into VILLAGE values('C008','�H�q��','�H�q���H�q��53��1��','037-616072');


insert into COUNTRY values('B01','�]�߿�','�˫n��');
insert into COUNTRY values('B02','�]�߿�','���s��');
insert into COUNTRY values('B03','�]�߿�','�]�ߥ�');
insert into COUNTRY values('B04','�]�߿�','�Y����');


insert into SHELTER values('A01','K01','C001','B01','���H��20��','100','1','M001');
insert into SHELTER values('A02','K02','C002','B01','�M����79��','3142','1','M001');
insert into SHELTER values('A03','K02','C003','B01','�s�s���T�q142��','1072','1','M001');
insert into SHELTER values('A04','K03','C004','B02','���ظ�1498��','32','1','M001');
insert into SHELTER values('A05','K01','C005','B03','�̥���80��','106','1','M002');
insert into SHELTER values('A06','K02','C005','B03','�շR��109��','2038','2','M002');
insert into SHELTER values('A07','K03','C006','B04','���ڨ��M����102��','353','1','M003');
insert into SHELTER values('A08','K04','C007','B04','���������@��69��','501','1','M003');
insert into SHELTER values('A09','K01','C008','B04','�H�q��������65��','194','1','M003');
insert into SHELTER values('A10','K04','C008','B04','�H�q��������116��','78','1','M003');



/*��@�m��4 - ��Ƭd��*/
/*4-1. �C�X �Һ� �ϰ줺����@ �����]�I�j�� 1000 �e�H�ƶq�� �Һޤ��� �� �����s���q�� �C*/

select distinct p.NAME as �Һޤ���, p.TEL as �����s���q��
 from STUDENT.SHELTER s
 inner join STUDENT.POLICE p
 on s.POLICE_OFFICE = p.POLICE_ID
 where s.CAPACITY > 1000;

/*4-2. �C�X �Һ� �ϰ줺����@ �����]�I�j�� 1000 �e�H�ƶq�� �Һޤ��� �� �����s���q�� �íp��X �����]�I�j��1000�e�H�ƶq���]�I�ƶq �C �]����r partition)*/

select p.NAME as �Һޤ���, p.TEL as �����s���q��, count(s.SHELTER_ID) as �����]�I�ƶq
 from STUDENT.SHELTER s
 inner join STUDENT.POLICE p
 on s.POLICE_OFFICE = p.POLICE_ID
 where s.CAPACITY > 1000
 group by s.POLICE_OFFICE,  p.NAME, p.TEL;


/*4-3. �ӤW�D�A �A�ɤW �����]�I�a�} �B ���� �C*/
select p.NAME as �Һޤ���, p.TEL as �����s���q��, count(s.SHELTER_ID) over (partition by s.POLICE_OFFICE) as �����]�I�ƶq, (cou.CITY || cou.DISTRICT || s.ADDRESS ) as �����]�I�a�}, c.CATEGORY as ����
 from STUDENT.SHELTER s
 inner join STUDENT.POLICE p on s.POLICE_OFFICE = p.POLICE_ID
 left join STUDENT.CATEGORY c on s.CATEGORY = c.CATEGORY_ID
 left join STUDENT.COUNTRY cou on s.COUNTRY = cou.COUNTRY_ID    
 where s.CAPACITY > 1000;


/*4-4. �d�߳]�I�a�}�]�t�u���v�r�������]�I�A�C�X��ƥ����t �����O �B �����]�I�a�} �B �e�H�ƶq �B �Һޤ��� �� �����s���q�� �C*/
select v.NAME as �����O, (cou.CITY || cou.DISTRICT || s.ADDRESS ) as �����]�I�a�}, s.CAPACITY as �e�H�ƶq, p.NAME as �Һޤ���, p.TEL as �����s���q��
 from STUDENT.SHELTER s
 left join STUDENT.VILLAGE v on s.VILLAGE = v.VILLAGE_ID
 left join STUDENT.COUNTRY cou on s.COUNTRY = cou.COUNTRY_ID
 left join STUDENT.POLICE p on s.POLICE_OFFICE = p.POLICE_ID
 where (cou.CITY || cou.DISTRICT || s.ADDRESS ) like '%��%';
 
/*4-5. �d�� �Ҧ� ���O �� ���J�Τj�� �� �����]�I �C�X ��ƥ����]�t �����O �B �����줽�Ǧ�m �B �����]�I�a�} �B �e�H�ƶq �C*/
select v.NAME as �����O, v.ADDRESS as �����줽�Ǧ�m, (cou.CITY || cou.DISTRICT || s.ADDRESS ) as �����]�I�a�}, s.CAPACITY as �e�H�ƶq
 from STUDENT.SHELTER s
 left join STUDENT.VILLAGE v on s.VILLAGE = v.VILLAGE_ID
 left join STUDENT.COUNTRY cou on s.COUNTRY = cou.COUNTRY_ID
 left join STUDENT.CATEGORY c on s.CATEGORY = c.CATEGORY_ID
 where c.CATEGORY in ('���J', '�j��');
    

/*��@�m��5 - ��ƾޱ�*/
/*5-1. ��s�����]�I�a�} �O �u�]�߿� �˫n��M���� 79 �� �v���e�H�ƶq�� 5000 �H �C*/

create table SHELTER1 as select * from SHELTER;

update STUDENT.SHELTER1
set CAPACITY = '5000'
where ADDRESS = '�M����79��';

/*5-2. �R�������]�I�p �� 1000 �e�H�ƶq�� ��� �C*/

delete from STUDENT.SHELTER
where CAPACITY < 1000;

commit;