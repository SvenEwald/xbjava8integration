package org.xmlbeam.tests.java8;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.xmlbeam.annotation.XBRead;

public interface SimpleJava8Projection {

	@XBRead("/root/somevalue")
	String getSomeValue();

	@XBRead("'{0}'")
	int getInt(int i);

	@XBRead("'{0}'")
	boolean getBoolean(boolean i);

	default boolean validate() {
		final String value = getSomeValue();
		return "foo".equals(value);
	}

	default void noOperationMethod(final List<String> strings) {
		strings.add("bar");
	}

	default void throwSomething() throws IOException {
		throw new IOException();
	}

	default boolean passBoolean(final boolean i) {
		return getBoolean(i);
	}

	default int passInt(final int i) {
		return getInt(i);
	}
	
	default Supplier<String> defaultMethodUsingLambda() {
	    return ()->"Huhu";
	}
	
	@XBRead("/root/somevalue")
	Stream<String> getValuesAsStream();
}
