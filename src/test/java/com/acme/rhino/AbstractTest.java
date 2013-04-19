package com.acme.rhino;

import static java.nio.file.Files.readAllBytes;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;

public class AbstractTest {

	protected Engine engine;
	protected HashMap<String, String> p;

	protected DynamicRule parse(final String path) throws IOException {
		return DynamicRule.parse(engine, new String(
				readAllBytes(java.nio.file.Paths.get(toURI("/" + path))),
				Charset.forName("UTF-8")));

	}

	protected URI toURI(final String path) {
		return new File(this.getClass().getResource(path).getFile()).toURI();
	}
}
