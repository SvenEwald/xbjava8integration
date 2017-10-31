package org.xmlbeam.tests.java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.annotation.XBRead;

public class TestOptionalAsReturnType {

	public interface OptionalReturningProjection {
		@XBRead("/foo/bar")
		Optional<Integer> getSomeValue();

		@XBRead("/foo/bar")
		Optional<String> getSomeValueAsString();
	}

	@Test
	public void testValueIsPresent() {
		OptionalReturningProjection projection = new XBProjector().projectXMLString("<foo><bar>1</bar></foo>", OptionalReturningProjection.class);
		Optional<Integer> value = projection.getSomeValue();
		assertTrue(value.isPresent());
		assertEquals(Integer.valueOf(1), value.get());
	}

	@Test
	public void testValueIsNotPresent() {
		OptionalReturningProjection projection = new XBProjector().projectXMLString("<foo><bar2>2</bar2></foo>", OptionalReturningProjection.class);
		Optional<Integer> value = projection.getSomeValue();
		assertFalse(value.isPresent());
	}

	@Test
	public void testStringValueIsPresent() {
		OptionalReturningProjection projection = new XBProjector().projectXMLString("<foo><bar>1</bar></foo>", OptionalReturningProjection.class);
		Optional<String> value = projection.getSomeValueAsString();
		assertTrue(value.isPresent());
		assertEquals("1", value.get());
	}
}
