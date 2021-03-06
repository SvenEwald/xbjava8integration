package org.xmlbeam.tests.java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.xmlbeam.XBProjector;

public class TestJava8Projections {

    private final static String XML = "<root><somevalue>foo</somevalue></root>";

    final SimpleJava8Projection simpleProjection = new XBProjector().projectXMLString(XML, SimpleJava8Projection.class);

    final ExtendedJava8Projection extendedProjection = new XBProjector().projectXMLString(XML, ExtendedJava8Projection.class);

    @Test
    public void testSimpleDefaultMethodInvocation() {
        final List<String> strings = new LinkedList<String>();
        simpleProjection.noOperationMethod(strings);
        assertEquals("bar", strings.get(0));
    }

    @Test
    public void testSimpleDelegateToProjectionMethod() {
        assertTrue(simpleProjection.validate());
    }

    @Test
    public void testExtendedInterfaceDelegateToMethodWithParameter() {
        assertEquals("foo", extendedProjection.delegateToMethodWithParameter());
    }

    @Test
    public void testInterfaceExtension() {
        assertEquals("foo", extendedProjection.delegateToSuperInterface());
    }

    @Test(expected = IOException.class)
    public void testExceptionHandling() throws IOException {
        simpleProjection.throwSomething();
    }

    @Test
    public void testPassBoolean() {
        assertEquals(false, simpleProjection.passBoolean(false));
    }

    @Test
    public void testPassInt() {
        assertEquals(12, simpleProjection.passInt(12));
    }

    @Test
    public void testDefaultMethodUsingLambda() {
        assertEquals("Huhu", simpleProjection.defaultMethodUsingLambda().get());
    }

    @Test
    public void testWrapResultInStream() {
        assertEquals(1, simpleProjection.getValuesAsStream().count());
        assertEquals("foo", simpleProjection.getValuesAsStream().findFirst().get());
    }

    public void testToString() {
        assertEquals("foobar", simpleProjection.toString());
    }
}
