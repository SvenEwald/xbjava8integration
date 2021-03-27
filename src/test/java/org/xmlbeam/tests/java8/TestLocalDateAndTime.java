package org.xmlbeam.tests.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.XBProjector.Flags;
import org.xmlbeam.annotation.XBRead;
import org.xmlbeam.annotation.XBWrite;

public class TestLocalDateAndTime {

	interface Prj {
		@XBRead("/date")
		LocalDate read();
		
		@XBRead("/date")
		LocalDateTime readTime();
		
		@XBRead("/date using ddMMyyyy")
		LocalDate readPattern();
		
		@XBRead("/date using ddMMyyyy HHmmss")
		LocalDateTime readTimePattern();
		
		@XBWrite("/date")
		Prj write(LocalDate date);
		
		@XBWrite("/date using ddMMyyyy")
		Prj writePattern (LocalDate date);

		@XBWrite("/date")
		Prj writeTime(LocalDateTime date);
		
		@XBWrite("/date using ddMMyyyy HHmmss")
		Prj writeTimePattern (LocalDateTime date);
	}
	@Test
	public void testLocalDate() {
		Prj createProjection = new XBProjector().onXMLString("<date>2007-12-03</date>").createProjection(Prj.class);
		LocalDate read = createProjection.read();
		System.out.println(read);
	}
	
	@Test
	public void testLocalDateTime() {
		Prj createProjection = new XBProjector().onXMLString("<date>2011-12-03T10:15:30</date>").createProjection(Prj.class);
		LocalDateTime read = createProjection.readTime();
		System.out.println(read);
	}
	@Test
	public void testLocalDatePattern() {
		Prj createProjection = new XBProjector().onXMLString("<date>03122007</date>").createProjection(Prj.class);
		LocalDate read = createProjection.readPattern();
		System.out.println(read);
	}
	
	@Test
	public void testLocalDateTimePattern() {
		Prj createProjection = new XBProjector().onXMLString("<date>03122007 101530</date>").createProjection(Prj.class);
		LocalDateTime read = createProjection.readTimePattern();
		System.out.println(read);
	}
	
	@Test
	public void testWriteLocalDate() {
		Prj createProjection = new XBProjector(Flags.TO_STRING_RENDERS_XML).onXMLString("<date>03122007 101530</date>").createProjection(Prj.class);
		createProjection.write(LocalDate.now());
		System.out.println(createProjection.toString());
	}

	@Test
	public void testWriteLocalDatePattern() {
		Prj createProjection = new XBProjector(Flags.TO_STRING_RENDERS_XML).onXMLString("<date>03122007 101530</date>").createProjection(Prj.class);
		createProjection.writePattern(LocalDate.now());
		System.out.println(createProjection.toString());
	}
	@Test
	public void testWriteLocalDateTime() {
		Prj createProjection = new XBProjector(Flags.TO_STRING_RENDERS_XML).onXMLString("<date>03122007 101530</date>").createProjection(Prj.class);
		createProjection.writeTime(LocalDateTime.now());
		System.out.println(createProjection.toString());
	}

	@Test
	public void testWriteLocalDateTimePattern() {
		Prj createProjection = new XBProjector(Flags.TO_STRING_RENDERS_XML).onXMLString("<date>03122007 101530</date>").createProjection(Prj.class);
		createProjection.writeTimePattern(LocalDateTime.now());
		System.out.println(createProjection.toString());
	}


}
