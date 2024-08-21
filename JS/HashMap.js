var HashMap = function () {
  let map = {};
  return {
    put: function (k, v) {
      map[k] = v;
    },
    keys: function () {
      return Object.keys(map);
    },
    contains: function (k) {
      return k in map;
    },
    get: function (k) {
      return map[k];
    },
    clear: function () {
      return (map = {});
    },
  };
};

let hashMap = new HashMap();
let resultStr = '';
document.getElementById("buttonPut").addEventListener('click', function () {
  let key = document.getElementById('inputBoxKey').value;
  let value = document.getElementById('inputBoxValue').value;
 
  console.log("Key:", key); // Debugging output
  console.log("Value:", value); // Debugging output

  if (key === null || key === "") {
    alert("請輸入KEY");

    return;
  } else if (hashMap.contains(key)) {
    alert("KEY已存在!");
    return;
  } else {
    hashMap.put(key, value);
    resultStr += (key + " : " + hashMap.get(key) + '<br>');
    document.getElementById('resultContent').innerHTML = resultStr;
  }
});


document.getElementById('buttonClear').addEventListener('click', function(){
    resultStr = '';
    document.getElementById('resultContent').innerHTML = resultStr;
})