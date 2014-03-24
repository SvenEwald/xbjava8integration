package org.xmlbeam.tests.java8;

import java.io.IOException;
import java.util.List;

import org.xmlbeam.annotation.XBRead;

public interface SimpleJava8Projection {
	
	@XBRead("/root/somevalue")
	String getSomeValue();

	default boolean validate() {
		final String value = getSomeValue();
		return "foo".equals(value);
	}

	default void noOperationMethod(List<String> strings) {
		strings.add("bar");
	}

	default void throwSomething() throws IOException {
		throw new IOException();
	}
}
