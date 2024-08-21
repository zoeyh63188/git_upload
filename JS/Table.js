let count = 0;
let resultTable = document.getElementById('result');
document.getElementById('btnInsert').addEventListener('click', function () {
  let manufacturer = document.getElementById('manufacturerInput').value;
  let type = document.getElementById('typeInput').value;
  let minPrice = document.getElementById('minPriceInput').value;
  let price = document.getElementById('priceInput').value;
  if (manufacturer === '' || type === '') {
    alert('請輸入製造商和類別!');
  } else {
    count += 1;
    let resultRow = document.createElement('tr');
    resultRow.setAttribute('class', 'resultRow');

    let radioFrame = document.createElement('td');
    let radioLabel = document.createElement('label');
    let radio = document.createElement('input');
    radio.setAttribute('type', 'radio');
    radio.setAttribute('name', 'radioOption');
    radio.setAttribute('class', 'radioBtn');
    radio.onclick = function () {
      manufacturer = '';
      type = '';
      minPrice = '';
      price = '';
      let row = radio.parentElement.parentElement.parentElement;

      document.getElementById('manufacturerInput').value = row.childNodes[2].value;
      document.getElementById('typeInput').value = row.childNodes[3].value;
      document.getElementById('priceInput').value = row.childNodes[4].value;
      document.getElementById('minPriceInput').value = row.childNodes[5].value;
    };
    resultRow.appendChild(radioFrame).appendChild(radioLabel).appendChild(radio);

    let indexFrame = document.createElement('td');
    let index = document.createElement('p');
    index.setAttribute('class', 'index');
    index.innerHTML = count;
    resultRow.appendChild(indexFrame).appendChild(index);

    let resultManufacturer = document.createElement('td');
    resultManufacturer.value = manufacturer;
    resultManufacturer.innerHTML = manufacturer;
    resultManufacturer.setAttribute('class', 'resultManufacturer');
    resultRow.appendChild(resultManufacturer);

    let resultType = document.createElement('td');
    resultType.value = type;
    resultType.innerHTML = type;
    resultType.setAttribute('class', 'resultType');
    resultRow.appendChild(resultType);

    let resultMinPrice = document.createElement('td');
    resultMinPrice.value = minPrice;
    resultMinPrice.innerHTML = minPrice;
    resultMinPrice.setAttribute('class', 'resultMinPrice');
    resultRow.appendChild(resultMinPrice);

    let resultPrice = document.createElement('td');
    resultPrice.value = price;
    resultPrice.innerHTML = price;
    resultPrice.setAttribute('class', 'resultPrice');
    resultRow.appendChild(resultPrice);

    let deleteFrame = document.createElement('td');
    let deleteBtn = document.createElement('button');
    deleteBtn.setAttribute('id', 'deleteBtn');
    resultRow.appendChild(deleteFrame).appendChild(deleteBtn);
    deleteBtn.onclick = function () {
      resultRow.remove();
      updateIndex();
    };

    deleteBtn.innerHTML = '刪除';

    resultTable.appendChild(resultRow);
  }
});

document.getElementById('btnDelete').addEventListener('click', function () {
  count = 0;
  while (resultTable.children.length > 1) {
    resultTable.lastChild.remove();
  }
});

function updateIndex() {
  let rows = document.getElementsByClassName('resultRow');
  for (let i = 0; i < rows.length; i++) {
    let index = rows[i].querySelector('.index');
    index.innerHTML = i + 1;
  }
  count = rows.length;
}

document.getElementById('btnUpdate').addEventListener('click', function () {
  let manufacturer = document.getElementById('manufacturerInput').value;
  let type = document.getElementById('typeInput').value;
  let minPrice = document.getElementById('minPriceInput').value;
  let price = document.getElementById('priceInput').value;

  if (document.querySelector('input[name="radioOption"]:checked') === null) {
    alert('請選擇欲修改的欄位');
    return;
  } else if (manufacturer === '' || type === '') {
    alert('請輸入製造商和類別!');
    return;
  }
  let rows = document.getElementsByClassName('resultRow');
  for (let i = 0; i < rows.length; i++) {
    if (rows[i].querySelector('.radioBtn').checked) {
      let newManufacturer = document.createElement('td');
      newManufacturer.value = manufacturer;
      newManufacturer.innerHTML = manufacturer;
      rows[i].replaceChild(newManufacturer, rows[i].childNodes[2]);
  

      let newType = document.createElement('td');
      newType.value = type;
      newType.innerHTML = type;
      rows[i].replaceChild(newType, rows[i].childNodes[3]);


      let newMinPrice = document.createElement('td');
      newMinPrice.value = minPrice;
      newMinPrice.innerHTML = minPrice;
      rows[i].replaceChild(newMinPrice, rows[i].childNodes[4]);
 

      let newPrice = document.createElement('td');
      newPrice.value = price;
      newPrice.innerHTML = price;
      rows[i].replaceChild(newPrice, rows[i].childNodes[5]);

    } else {
      continue;
    }
  }
});
