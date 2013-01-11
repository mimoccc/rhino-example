package com.acme.rhino;

import java.io.File;

public class AbstractTest {

	protected File getFile(final String s) {
		return new File(this.getClass().getResource(s).getFile());
	}

}
