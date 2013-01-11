Result = Packages.com.acme.rhino.Result;

function evaluate(a) {
	if (a.foo === "true") {
		return Result("static hello", 1);
	}
	
	if (a.bar === "true") {
		return Result("static hello", 2);
	}
	
	return Result("static hello", 0)
}