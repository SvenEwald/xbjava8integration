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
 *    Created on  14.04.2014
 *
 ************************************************************************/
package org.xmlbeam.tests.java8;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.annotation.XBRead;
import org.xmlbeam.util.intern.ReflectionHelper;

public class TestAutoVariableResolver {
    
    public interface Projection {
        @XBRead("$bar")
        String read(String bar);
        
        @XBRead("/foo/{node}")
        String readNode(String node);
        
        @XBRead("/{first}/{second}")
        String readMultipleParams(String first, String second);
    }

        @Test
    public void testGetMethodParameterNames() {
        assertTrue(ReflectionHelper.mayProvideParameterNames());        
        List<String> methodParameterNames = ReflectionHelper.getMethodParameterNames(ReflectionHelper.findMethodByName(Projection.class, "read"));
        assertEquals(1,methodParameterNames.size());
        assertEquals("bar",methodParameterNames.get(0));        
    }
    
    @Test
    public void testSingleParam() {
        //Projection.class.getMethods()[0].getParameters()[0].getName()
        Projection projection = new XBProjector().projectXMLString("<foo><a>1</a><b>2</b></foo>", Projection.class);
        assertEquals("a",projection.read("a"));
    }
    
    @Test
    public void test2() {
        Projection projection = new XBProjector().projectXMLString("<foo><a>1</a><b>2</b></foo>", Projection.class);
        assertEquals("1",projection.readNode("a"));
        assertEquals("2",projection.readNode("b"));
    }
    
    @Test
    public void testMultipleParams() {
        Projection projection = new XBProjector().projectXMLString("<foo><a>1</a><b>2</b></foo>", Projection.class);
        assertEquals("1",projection.readMultipleParams("foo","a"));
        assertEquals("2",projection.readMultipleParams("foo","b"));
    }

}
