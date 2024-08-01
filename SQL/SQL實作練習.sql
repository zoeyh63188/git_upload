/*實作練習2 - 資料定義*/
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
    

/*實作練習3 - 資料操控*/

insert into CATEGORY values('K01','公寓');
insert into CATEGORY values('K02','大樓');
insert into CATEGORY values('K03','公共設施');
insert into CATEGORY values('K04','私營單位');

insert into VILLAGE values('C001','大埔里','竹南鎮公義路1035號','03-758 1072');
insert into VILLAGE values('C002','竹南里','竹南鎮竹南里中山路 103 號','037-472735');
insert into VILLAGE values('C003','山佳里','竹南鎮山佳里國光街 14 號','037-614186');
insert into VILLAGE values('C004','埔頂里','後龍鎮埔頂里中興路136-1號','037-724839');
insert into VILLAGE values('C005','綠苗里','苗栗市綠苗里中正路 766 號','037-333240');
insert into VILLAGE values('C006','民族里','民族里民族路96號','037-660001');
insert into VILLAGE values('C007','忠孝里','忠孝里光大街82號','037-661145');
insert into VILLAGE values('C008','信義里','信義里信義路53巷1號','037-616072');


insert into COUNTRY values('B01','苗栗縣','竹南鎮');
insert into COUNTRY values('B02','苗栗縣','後龍鎮');
insert into COUNTRY values('B03','苗栗縣','苗栗市');
insert into COUNTRY values('B04','苗栗縣','頭份市');


insert into SHELTER values('A01','K01','C001','B01','中埔街20號','100','1','M001');
insert into SHELTER values('A02','K02','C002','B01','和平街79號','3142','1','M001');
insert into SHELTER values('A03','K02','C003','B01','龍山路三段142號','1072','1','M001');
insert into SHELTER values('A04','K03','C004','B02','中華路1498號','32','1','M001');
insert into SHELTER values('A05','K01','C005','B03','米市街80號','106','1','M002');
insert into SHELTER values('A06','K02','C005','B03','博愛街109號','2038','2','M002');
insert into SHELTER values('A07','K03','C006','B04','民族里和平路102號','353','1','M003');
insert into SHELTER values('A08','K04','C007','B04','忠孝忠孝一路69號','501','1','M003');
insert into SHELTER values('A09','K01','C008','B04','信義里中正路65號','194','1','M003');
insert into SHELTER values('A10','K04','C008','B04','信義里中正路116號','78','1','M003');



/*實作練習4 - 資料查詢*/
/*4-1. 列出 轄管 區域內有單一 避難設施大於 1000 容人數量的 轄管分局 及 分局連絡電話 。*/

select distinct p.NAME as 轄管分局, p.TEL as 分局連絡電話
 from STUDENT.SHELTER s
 inner join STUDENT.POLICE p
 on s.POLICE_OFFICE = p.POLICE_ID
 where s.CAPACITY > 1000;

/*4-2. 列出 轄管 區域內有單一 避難設施大於 1000 容人數量的 轄管分局 及 分局連絡電話 並計算出 避難設施大於1000容人數量的設施數量 。 （關鍵字 partition)*/

select p.NAME as 轄管分局, p.TEL as 分局連絡電話, count(s.SHELTER_ID) as 避難設施數量
 from STUDENT.SHELTER s
 inner join STUDENT.POLICE p
 on s.POLICE_OFFICE = p.POLICE_ID
 where s.CAPACITY > 1000
 group by s.POLICE_OFFICE,  p.NAME, p.TEL;


/*4-3. 承上題， 再補上 避難設施地址 、 類型 。*/
select p.NAME as 轄管分局, p.TEL as 分局連絡電話, count(s.SHELTER_ID) over (partition by s.POLICE_OFFICE) as 避難設施數量, (cou.CITY || cou.DISTRICT || s.ADDRESS ) as 避難設施地址, c.CATEGORY as 類型
 from STUDENT.SHELTER s
 inner join STUDENT.POLICE p on s.POLICE_OFFICE = p.POLICE_ID
 left join STUDENT.CATEGORY c on s.CATEGORY = c.CATEGORY_ID
 left join STUDENT.COUNTRY cou on s.COUNTRY = cou.COUNTRY_ID    
 where s.CAPACITY > 1000;


/*4-4. 查詢設施地址包含「中」字的避難設施，列出資料必須含 村里別 、 避難設施地址 、 容人數量 、 轄管分局 及 分局連絡電話 。*/
select v.NAME as 村里別, (cou.CITY || cou.DISTRICT || s.ADDRESS ) as 避難設施地址, s.CAPACITY as 容人數量, p.NAME as 轄管分局, p.TEL as 分局連絡電話
 from STUDENT.SHELTER s
 left join STUDENT.VILLAGE v on s.VILLAGE = v.VILLAGE_ID
 left join STUDENT.COUNTRY cou on s.COUNTRY = cou.COUNTRY_ID
 left join STUDENT.POLICE p on s.POLICE_OFFICE = p.POLICE_ID
 where (cou.CITY || cou.DISTRICT || s.ADDRESS ) like '%中%';
 
/*4-5. 查詢 所有 類別 為 公寓及大樓 的 避難設施 列出 資料必須包含 村里別 、 村里辦公室位置 、 避難設施地址 、 容人數量 。*/
select v.NAME as 村里別, v.ADDRESS as 村里辦公室位置, (cou.CITY || cou.DISTRICT || s.ADDRESS ) as 避難設施地址, s.CAPACITY as 容人數量
 from STUDENT.SHELTER s
 left join STUDENT.VILLAGE v on s.VILLAGE = v.VILLAGE_ID
 left join STUDENT.COUNTRY cou on s.COUNTRY = cou.COUNTRY_ID
 left join STUDENT.CATEGORY c on s.CATEGORY = c.CATEGORY_ID
 where c.CATEGORY in ('公寓', '大樓');
    

/*實作練習5 - 資料操控*/
/*5-1. 更新避難設施地址 是 「苗栗縣 竹南鎮和平街 79 號 」的容人數量為 5000 人 。*/

create table SHELTER1 as select * from SHELTER;

update STUDENT.SHELTER1
set CAPACITY = '5000'
where ADDRESS = '和平街79號';

/*5-2. 刪除避難設施小 於 1000 容人數量的 資料 。*/

delete from STUDENT.SHELTER
where CAPACITY < 1000;

commit;