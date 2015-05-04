package org.xmlbeam.tests.java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Ignore;
import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.XBProjector.Flags;
import org.xmlbeam.annotation.XBRead;
import org.xmlbeam.annotation.XBWrite;

public class TestSupplierInterfaceAsParam {

	public interface Projection {
		@XBRead("'{0}'")
		String getSomeValue(Supplier<String> param);

		@XBWrite("/foo/bar")
		Projection setSomeValues(Collection<Supplier<String>> param);

		@XBRead("/foo/bar")
		String[] checkValues();
	}

	@Test
	public void testUnWrappSupplier() throws Exception {
		Supplier<String> supplier = new Supplier<String>() {

			@Override
			public String get() {
				return "Nothing but a String";
			}
		};

		String result = new XBProjector()
				.projectEmptyDocument(Projection.class).getSomeValue(supplier);

		assertEquals(supplier.get(), result);
	}

	@Ignore //Maybe not so good idea
	public void testUnwrappInCollection() {
		List<Supplier<String>> collection = Arrays.asList(
				new Supplier<String>() {

					@Override
					public String get() {
						return "A";
					}

				}, new Supplier<String>() {

					@Override
					public String get() {
						return "B";
					}

				}, new Supplier<String>() {

					@Override
					public String get() {
						return "C";
					}

				});

		Projection projection = new XBProjector(Flags.TO_STRING_RENDERS_XML)
				.projectEmptyDocument(Projection.class);
		final String[] result = projection.setSomeValues(collection)
				.checkValues();
		assertEquals("A", result[0]);
		assertEquals("B", result[1]);
		assertEquals("C", result[2]);
	}
}
