var math = require("acme.math.js");

function getRequiredValues() { return [sum(2,3), product(2,3)];  }

function sum(a,b) {
	return math.sum(a,b);
}

function product(a,b) {
	return math.product(a,b);
}