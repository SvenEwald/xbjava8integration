package org.xmlbeam.tests.java8;

import java.io.IOException;
import java.util.List;

import org.xmlbeam.annotation.XBRead;

public interface SimpleJava8Projection {
	
	@XBRead("/root/somevalue")
	String getSomeValue();

	@XBRead("''{0}''")
	int getInt(int i);
	
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
	
	default int passInt(int i) {		
		return getInt(i);
	}
}
