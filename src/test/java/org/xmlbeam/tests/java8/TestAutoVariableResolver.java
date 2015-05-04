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

import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.XBProjector.Flags;
import org.xmlbeam.annotation.XBRead;
import org.xmlbeam.annotation.XBWrite;
import org.xmlbeam.config.DefaultXMLFactoriesConfig;
import org.xmlbeam.util.intern.ReflectionHelper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeTrue;
//github.com/SvenEwald/xbjava8integration.git
import java.util.Locale;
import java.util.Map;

public class TestAutoVariableResolver {

    public interface Projection {
        @XBRead("/foo/{node}")
        String readNode(String node);

        @XBRead("/{first}/{second}")
        String readMultipleParams(String first, String second);

        @XBWrite("/foo/bar[@id=$id(:000:)]")
        Projection writeWithFormat(int id);

        @XBRead("/foo/bar[@id=$id(:000:)]")
        int readWithFormat(int id);
    }

    @Test
    public void testFormatwithVariableBound() {
        XBProjector xbProjector = new XBProjector(Flags.TO_STRING_RENDERS_XML);
        xbProjector.config().as(DefaultXMLFactoriesConfig.class).setPrettyPrinting(false);
        Projection projection = xbProjector.projectEmptyDocument(Projection.class);
        assertEquals("<foo><bar id=\"004\">4</bar></foo>",projection.writeWithFormat(4).toString());
        assertEquals(4,projection.readWithFormat(4));
    }

    @Test
    public void testGetMethodParameterNames() throws Exception {
        assumeTrue(ReflectionHelper.mayProvideParameterNames());
        assumeThat(Projection.class.getMethod("readNode", String.class).getParameters()[0].getName(), is("node"));
        Map<String, Integer> map = ReflectionHelper.getMethodParameterIndexes(ReflectionHelper.findMethodByName(Projection.class, "readNode"));
        assertEquals(1, map.size());
        assertTrue(map.containsKey("node".toUpperCase(Locale.ENGLISH)));
    }

    @Test
    public void test2() throws Exception {
        assumeTrue(ReflectionHelper.mayProvideParameterNames());
        assumeThat(Projection.class.getMethod("readNode", String.class).getParameters()[0].getName(), is("node"));
        Projection projection = new XBProjector().projectXMLString("<foo><a>1</a><b>2</b></foo>", Projection.class);
        assertEquals("1", projection.readNode("a"));
        assertEquals("2", projection.readNode("b"));
    }

    @Test
    public void testMultipleParams() throws Exception {
        assumeTrue(ReflectionHelper.mayProvideParameterNames());
        assumeThat(Projection.class.getMethod("readNode", String.class).getParameters()[0].getName(), is("node"));
        Projection projection = new XBProjector().projectXMLString("<foo><a>1</a><b>2</b></foo>", Projection.class);
        assertEquals("1", projection.readMultipleParams("foo", "a"));
        assertEquals("2", projection.readMultipleParams("foo", "b"));
    }

}
