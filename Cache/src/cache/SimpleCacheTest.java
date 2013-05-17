/*
 * SimpleCache
 * Copyright (C) 2008 Christian Schenk
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */
package cache;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SimpleCacheTest {

	private SimpleCache<Object> cache;

	@Before
	public void setup() {
		this.cache = new SimpleCache<Object>(2);
	}

	@Test
	public void perf() throws InterruptedException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			this.cache.put("" + i, new Integer(i));
		}
		long end = System.currentTimeMillis() - start;
		System.out.println("Putting took: " + end + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			assertEquals(new Integer(i), this.cache.get("" + i, Integer.class));
		}
		end = System.currentTimeMillis() - start;
		System.out.println("Getting took: " + end + "ms");
	}

	@Test
	public void putAndGet() throws InterruptedException {
		this.cache.put("one", 1);
		assertEquals(1, this.cache.get("one", Integer.class).intValue());
		this.cache.put("hurz", "hurz");
		assertEquals("hurz", this.cache.get("hurz", String.class));
		this.cache.put("long", new Long(2));
		assertEquals(2, this.cache.get("long", Long.class).longValue());

		// overwrite
		this.cache.put("test", 1);
		this.cache.put("test", 2);
		assertEquals(2, this.cache.get("test", Integer.class).intValue());
	}

	@Test
	public void expire() throws InterruptedException {
		this.cache.put("test", "test-string");
		assertEquals("test-string", this.cache.get("test", String.class));
		// wait until this object is expired
		Thread.sleep(this.cache.getExpire() * 1010);
		assertNull(this.cache.get("test", String.class));

		// check if a custom time works
		assertTrue("Expiration time should be greater than or equal to 2", this.cache.getExpire() >= 2);
		this.cache.put("test", "test-string", this.cache.getExpire() / 2);
		assertEquals("test-string", this.cache.get("test", String.class));
		Thread.sleep((this.cache.getExpire() / 2) * 1100);
		assertNull(this.cache.get("test", String.class));
	}

	@Test
	public void typeSafe() {
		final SimpleCache<Integer> intCache = new SimpleCache<Integer>();
		intCache.put("one", 1);
		assertEquals(new Integer(1), intCache.get("one"));

		// this doesn't work
		// intCache.get("one", String.class);

		// this seems to be okay during compile time, but ...
		final SimpleCache<Object> objectCache = new SimpleCache<Object>();
		objectCache.put("int", 1);
		try {
			@SuppressWarnings("unused")
			final String str = objectCache.get("int", String.class);
			fail("Should throw exception");
		} catch (ClassCastException ignore) {
		}
	}
}