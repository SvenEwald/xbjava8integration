package org.xmlbeam.tests.java8;

import java.io.IOException;

import org.junit.Test;
import org.xmlbeam.util.intern.ASMHelper;

public class TestASMHelper {
	@Test(expected = IOException.class)
	public void testASMHelper() throws IOException {
		SimpleJava8Projection proxy = ASMHelper.create(SimpleJava8Projection.class, null);
		proxy.throwSomething();
	}

}
