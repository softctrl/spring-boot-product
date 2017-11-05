/**
 * 
 */
package com.avenuecode.product.repositories;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.avenuecode.product.domain.Image;
import com.avenuecode.product.domain.Product;
import com.avenuecode.product.services.impl.ProductServiceImpl;

/*
The MIT License (MIT)

Copyright (c) 2017 Carlos Timoshenko Rodrigues Lopes
http://www.0x09.com.br

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

	@Autowired()
	private ProductServiceImpl productService;

	@Before
	public void setUp() throws Exception {

	}

	private List<Product> mockProducts() {

		List<Product> list = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			Product p = new Product();
			p.setName("Name-" + i);
			p.setDescription("Description-" + i);
			p.setImages(new ArrayList<>());
			for (int j = 0; j < 10; j++) {
				Image img = new Image();
				img.setType("Type-" + i + "-" + j);
				p.getImages().add(img);
			}
			list.add(p);
		}
		return list;

	}

	int c = 0;

	@Test
	public void testPersistence() {
		// given

		mockProducts().forEach((p) -> {
			Assert.assertNotNull(productService.insert(p));
			Assert.assertEquals(++c, productService.count());
		});

	}

}
