package org.xmlbeam.tests.java8;

import org.xmlbeam.annotation.XBRead;

public interface SimpleJava8Projection {

	@XBRead("/root/somevalue")
	String getSomeValue();

	default boolean validate() {
		final String value = getSomeValue();
		return "foo".equals(value);
	}

	default void noOperationMethod() {
		System.out.println("No operation.");
	}

}
