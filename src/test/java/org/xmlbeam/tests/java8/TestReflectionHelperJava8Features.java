/************************************************************************
 *                                                                      *
 *  DDDD     SSSS    AAA        Daten- und Systemtechnik Aachen GmbH    *
 *  D   D   SS      A   A       Pascalstrasse 28                        *
 *  D   D    SSS    AAAAA       52076 Aachen-Oberforstbach, Germany     *
 *  D   D      SS   A   A       Telefon: +49 (0)2408 / 9492-0           *
 *  DDDD    SSSS    A   A       Telefax: +49 (0)2408 / 9492-92          *
 *                                                                      *
 *                                                                      *
 *  (c) Copyright by DSA - all rights reserved                          *
 *                                                                      *
 ************************************************************************
 *
 * Initial Creation:
 *    Author      se
 *    Created on  09.09.2014
 *
 ************************************************************************/
package org.xmlbeam.tests.java8;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.Test;
import org.xmlbeam.util.intern.ReflectionHelper;

public class TestReflectionHelperJava8Features {

    public interface SomeInterface {

        Optional<String> methodWithOptionalString();

        @SuppressWarnings("rawtypes")
        Optional methodWithRawOptional();

        String methodWithStringReturnType();

    }

    @Test
    public void testIsOptional() throws Exception {
        Method method = SomeInterface.class.getMethod("methodWithOptionalString", (Class[])null);
        assertTrue(ReflectionHelper.isOptional(method.getGenericReturnType()));

        assertEquals(String.class, ReflectionHelper.getParameterType(method.getGenericReturnType()));
    }

    @Test
    public void testIsRawOptional() throws Exception {
        Method method = SomeInterface.class.getMethod("methodWithRawOptional", (Class[])null);
        assertFalse(ReflectionHelper.isOptional(method.getGenericReturnType()));
    }

    @Test
    public void testIsNoOptional() throws Exception {
        Method method = SomeInterface.class.getMethod("methodWithStringReturnType", (Class[])null);
        assertFalse(ReflectionHelper.isOptional(method.getGenericReturnType()));
    }

    @Test
    public void testVoidIsNoOptional() throws Exception {
        Method method = TestReflectionHelperJava8Features.class.getMethod("testVoidIsNoOptional", (Class[])null);
        assertFalse(ReflectionHelper.isOptional(method.getGenericReturnType()));
    }

    @Test
    public void testCreateOptional() {
        assertTrue(ReflectionHelper.createOptional("Huhu") instanceof Optional);
        assertSame("huhu", ((Optional<?>)ReflectionHelper.createOptional("huhu")).get());
    }

    @Test
    public void testCreateOptionalWithNull() {
        assertTrue(ReflectionHelper.createOptional(null) instanceof Optional);
        assertFalse(((Optional<?>)ReflectionHelper.createOptional(null)).isPresent());
    }

}
