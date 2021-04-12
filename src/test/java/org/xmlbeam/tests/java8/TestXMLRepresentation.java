package org.xmlbeam.tests.java8;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.dom.DOMAccess;

public class TestXMLRepresentation {

	private static String XML="<foo>\n  <bar>\n    <fitz>x</fitz>\n  </bar>\n</foo>\n";
	private static Path XMLFileInput;
	private static Path XMLFileOutput;
	
	@Before
	public void createFile() throws IOException {
		Path tempDirectory = Files.createTempDirectory("xbtest");
		tempDirectory.toFile().deleteOnExit();
		Path file = Files.createFile(tempDirectory.resolve(Paths.get("testxml.xml")));
		file.toFile().deleteOnExit();
		Files.write(file, XML.getBytes(StandardCharsets.UTF_8));
		XMLFileInput=file;
		XMLFileOutput=tempDirectory.resolve("XMLoutput.xml");
	}
	
	@Test
	public void testWriteDoesNotChangeRepresentation() throws IOException {
		XBProjector projector = new XBProjector();
		
		DOMAccess read = projector.io().file(XMLFileInput.toFile()).read(DOMAccess.class);
		//DOMHelper.trim(read.getDOMBaseElement());
		projector.io().file(XMLFileOutput.toFile()).write(read);
		
		assertEquals(Files.readAllLines(XMLFileInput).stream().collect(Collectors.joining()),Files.readAllLines(XMLFileOutput).stream().collect(Collectors.joining()));
		
	}
}
