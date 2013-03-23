package com.acme.rhino;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.junit.Before;

public class AbstractTest {

	protected HashMap<String, String> p;

	@Before
	public void setup() {
		p = new HashMap<>();
	}

	protected String read(final String path) throws IOException {
		final URI uri = new File(this.getClass().getResource("/" + path)
				.getFile()).toURI();

		return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths
				.get(uri)), Charset.forName("UTF-8"));
	}
}
