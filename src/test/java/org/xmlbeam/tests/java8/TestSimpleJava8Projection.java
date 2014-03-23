package org.xmlbeam.tests.java8;

import org.junit.Test;
import org.xmlbeam.XBProjector;

public class TestSimpleJava8Projection {

	@Test
	public void testDefaultMethodInvocation() {
		final SimpleJava8Projection projection = new XBProjector()
				.projectXMLString("<root><somevalue>foo</somevalue></root>",
						SimpleJava8Projection.class);

		projection.noOperationMethod();
		System.out.println(projection.validate());
	}
}
