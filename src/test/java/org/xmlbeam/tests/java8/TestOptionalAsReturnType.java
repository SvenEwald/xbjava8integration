package org.xmlbeam.tests.java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.annotation.XBRead;
import org.xmlbeam.util.intern.ReflectionHelper;

public class TestOptionalAsReturnType {

	public interface OptionalReturningProjection {
		@XBRead("/foo/bar")
		Optional<String> getSomeValue();
	}

	@Test
	public void testValueIsPresent() throws NoSuchMethodException,
			SecurityException {
		Method method = OptionalReturningProjection.class.getMethod(
				"getSomeValue", null);
		ParameterizedType type = (ParameterizedType) method
				.getGenericReturnType();
		ReflectionHelper.isOptional(type);
		System.out.println(ReflectionHelper.isOptional(type));

		OptionalReturningProjection projection = new XBProjector()
				.projectXMLString("<foo><bar>hereValue</bar></foo>",
						OptionalReturningProjection.class);
		Optional<String> value = projection.getSomeValue();
		assertTrue(value.isPresent());
		assertEquals("hereValue", value.get());
	}
}
