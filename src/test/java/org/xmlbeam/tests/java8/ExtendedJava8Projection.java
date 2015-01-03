package org.xmlbeam.tests.java8;

import org.xmlbeam.annotation.XBRead;

public interface ExtendedJava8Projection extends SimpleJava8Projection {

	@XBRead("//somevalue")
	String findSomeValue(String unusedParameter);

	default String delegateToMethodWithParameter() {
		return findSomeValue("xxx");
	}

	default String delegateToSuperInterface() {
		return getSomeValue();
	}
}
