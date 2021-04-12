package org.xmlbeam.tests.java8;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.dom.DOMAccess;
import org.xmlbeam.util.intern.DOMHelper;

public class TestXMLRepresentation {

	private static String XML="<foo>\n  <bar>\n    <fitz>x</fitz>\n  </bar>\n</foo>\n";
	private static Path XMLFileInput;
	private static Path XMLFileOutput;
	
	@Before
	public void createFile() throws IOException {
		Path tempDirectory = Files.createTempDirectory("xbtest");
		tempDirectory.toFile().deleteOnExit();
		Path file = Files.createFile(tempDirectory.resolve(Path.of("testxml.xml")));
		file.toFile().deleteOnExit();
		Files.writeString(file, XML);
		XMLFileInput=file;
		XMLFileOutput=tempDirectory.resolve("XMLoutput.xml");
	}
	
	@Test
	public void testWriteDoesNotChangeRepresentation() throws IOException {
		XBProjector projector = new XBProjector();
		
		DOMAccess read = projector.io().file(XMLFileInput.toFile()).read(DOMAccess.class);
		//DOMHelper.trim(read.getDOMBaseElement());
		projector.io().file(XMLFileOutput.toFile()).write(read);
		assertEquals(Files.readString(XMLFileInput),Files.readString(XMLFileOutput));
		
	}
}
