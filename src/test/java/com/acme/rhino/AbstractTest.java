package com.acme.rhino;

import java.io.File;

public class AbstractTest {

	protected File getFile(final String path) {
		return new File(this.getClass().getResource("/" + path).getFile());
	}

}
