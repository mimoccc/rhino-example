var sum = require("./acme.math.sum.js");
var product = require("./acme.math.product.js");

exports.sum = function(a, b) {
	return sum.eval(a, b);
};

exports.product = function(a, b) {
	return product.eval(a, b);
};