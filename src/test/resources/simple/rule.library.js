require('./rule.library.js');

function build(p) {
	if (p.foo === "true") {
		return new Target("static hello", 1);
	}

	if (p.bar === "true") {
		return new Target("static hello", 2);
	}

	return new Target("static hello", 3)
}