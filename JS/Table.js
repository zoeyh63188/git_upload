let count = 0;
let resultTable = document.getElementById('result');
document.getElementById('btnInsert').addEventListener('click', function () {
  let manufacturer = document.getElementById('manufacturerInput').value;
  let type = document.getElementById('typeInput').value;
  let minPrice = document.getElementById('minPriceInput').value;
  let price = document.getElementById('priceInput').value;
  if (!manufacturer.trim() || !type.trim()) {
    alert('請輸入製造商和類別!');
    return;
  }
  count += 1;
  let resultRow = document.createElement('tr');
  let radioFrame = document.createElement('td');
  let radioLabel = document.createElement('label');
  let radio = document.createElement('input');
  let indexFrame = document.createElement('td');
  let index = document.createElement('p');
  let resultManufacturer = document.createElement('td');
  let resultType = document.createElement('td');
  let resultMinPrice = document.createElement('td');
  let resultPrice = document.createElement('td');
  let deleteFrame = document.createElement('td');
  let deleteBtn = document.createElement('button');

  resultRow.setAttribute('class', 'resultRow');
  radio.setAttribute('type', 'radio');
  radio.setAttribute('name', 'radioOption');
  radio.setAttribute('class', 'radioBtn');
  radio.onclick = function () {
    let row = radio.parentElement.parentElement.parentElement;

    document.getElementById('manufacturerInput').value =
      row.childNodes[2].value;
    document.getElementById('typeInput').value = row.childNodes[3].value;
    document.getElementById('priceInput').value = row.childNodes[4].value;
    document.getElementById('minPriceInput').value = row.childNodes[5].value;
  };
  resultRow.appendChild(radioFrame).appendChild(radioLabel).appendChild(radio);
  index.setAttribute('class', 'index');
  index.innerHTML = count;
  resultRow.appendChild(indexFrame).appendChild(index);

  resultManufacturer.value = manufacturer;
  resultManufacturer.innerHTML = manufacturer;
  resultManufacturer.setAttribute('class', 'resultManufacturer');
  resultRow.appendChild(resultManufacturer);

  resultType.value = type;
  resultType.innerHTML = type;
  resultType.setAttribute('class', 'resultType');
  resultRow.appendChild(resultType);

  resultMinPrice.value = minPrice;
  resultMinPrice.innerHTML = minPrice;
  resultMinPrice.setAttribute('class', 'resultMinPrice');
  resultRow.appendChild(resultMinPrice);

  resultPrice.value = price;
  resultPrice.innerHTML = price;
  resultPrice.setAttribute('class', 'resultPrice');
  resultRow.appendChild(resultPrice);

  deleteBtn.setAttribute('id', 'deleteBtn');
  resultRow.appendChild(deleteFrame).appendChild(deleteBtn);
  deleteBtn.onclick = function () {
    resultRow.remove();
    updateIndex();
  };

  deleteBtn.innerHTML = '刪除';
  resultTable.appendChild(resultRow);
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
  let rows = document.getElementsByClassName('resultRow');
  let selectedRow = document.querySelector('input[name="radioOption"]:checked')
    .parentElement.parentElement;

  if (document.querySelector('input[name="radioOption"]:checked') === null) {
    alert('請選擇欲修改的欄位');
    return;
  }
  if (!manufacturer.trim() || !type.trim()) {
    alert('請輸入製造商和類別!');
    return;
  }

  selectedRow.childNodes[2].innerHTML = manufacturer;
  selectedRow.childNodes[3].innerHTML = type;
  selectedRow.childNodes[4].innerHTML = minPrice;
  selectedRow.childNodes[5].innerHTML = price;
});
