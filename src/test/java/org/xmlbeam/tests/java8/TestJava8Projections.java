package org.xmlbeam.tests.java8;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.xmlbeam.XBProjector;

public class TestJava8Projections {
	
	private final static String XML="<root><somevalue>foo</somevalue></root>";
	
	final SimpleJava8Projection simpleProjection = new XBProjector().projectXMLString(XML,SimpleJava8Projection.class);
	
	final ExtendedJava8Projection extendedProjection = new XBProjector().projectXMLString(XML, ExtendedJava8Projection.class);
	
	@Test
	public void testSimpleDefaultMethodInvocation() {
	    List<String> strings= new LinkedList<String>();
		simpleProjection.noOperationMethod(strings);
		assertEquals("bar",strings.get(0));
	}
	
	@Test
	public void testSimpleDelegateToProjectionMethod() {
		assertTrue(simpleProjection.validate());
	}
	
	@Test
	public void testExtendedInterfaceDelegateToMethodWithParameter() {
		assertEquals("foo",extendedProjection.delegateToMethodWithParameter());		
	}
	
	@Test
	public void testInterfaceExtension() {
		assertEquals("foo",extendedProjection.delegateToSuperInterface());
	}
	
	@Test(expected=IOException.class)
	public void testExceptionHandling() throws IOException {
		simpleProjection.throwSomething();
	}

}
