var HashMap = function () {
  let map = {};
  return {
    put: function (key, value) {
      map[key] = value;
    },
    keys: function () {
      return Object.keys(map);
    },
    contains: function (key) {
      return key in map;
    },
    get: function (key) {
      return map[key];
    },
    clear: function () {
      return (map = {});
    },
  };
};



