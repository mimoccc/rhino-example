function build(a) {
	if (a.foo === "true") {
		return new Target("static hello", 1);
	}

	if (a.bar === "true") {
		return new Target("static hello", 2);
	}

	return new Target("static hello", 3)
}