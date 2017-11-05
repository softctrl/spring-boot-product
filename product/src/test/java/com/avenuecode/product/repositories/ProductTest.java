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

/**
 * @author timoshenko
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
