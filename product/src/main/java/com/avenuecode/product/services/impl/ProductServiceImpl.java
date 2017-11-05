/**
 * 
 */
package com.avenuecode.product.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.product.domain.Product;
import com.avenuecode.product.repositories.ProductRepository;
import com.avenuecode.product.services.BaseService;

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
@Service
public class ProductServiceImpl implements BaseService<Product, Long> {

	private ProductRepository productRepository;

	/**
	 * 
	 * @param productRepository
	 */
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * 
	 * @return
	 */
	protected ProductRepository getRepo() {
		return Objects.requireNonNull(this.productRepository, "ERROR: Product Repository is null!!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.services.BaseService#insert(java.lang.Object)
	 */
	@Override
	public Long insert(Product product) {

		Objects.requireNonNull(product, "ERROR: Product is Null.").getImages().forEach((i) -> {
			i.setProduct(product);
		});
		if (Objects.nonNull(product.getParentProduct())) {
			Product parent = Objects.requireNonNull(this.getRepo().findOne(product.getParentProduct().getId()),
					"ERROR: The parent product dont exists.");
			if (Objects.isNull(parent.getProducts())) {
				parent.setProducts(new ArrayList<>());
			}
			parent.getProducts().add(product);
			product.setParentProduct(this.getRepo().findOne(product.getParentProduct().getId()));
			final Long id = this.getRepo().save(product).getId();
			this.getRepo().save(parent);
			return id;
		} else {
			return this.getRepo().save(product).getId();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.services.BaseService#findById(java.lang.Object)
	 */
	@Override
	public Product findById(Long id) {
		return this.getRepo().findOne(id);
	}

	/**
	 * 
	 * @param id
	 * @param all
	 * @return
	 */
	public Product findById(Long id, boolean all) {

		if (all) {
			return this.getRepo().findOne(id);
		} else {
			return this.getRepo().findProductById(id).stream().findFirst().orElse(null);
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Product> findProductChildsById(Long id) {

		return this.getRepo().findProductChildsById(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.services.BaseService#findAll()
	 */
	@Override
	public List<Product> findAll() {

		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;

	}

	/**
	 * 
	 * @param all
	 * @return
	 */
	public List<Product> findAll(boolean all) {

		List<Product> products = new ArrayList<>();
		if (all) {
			productRepository.findAll().forEach(products::add);
		} else {
			productRepository.findAllProducts().forEach(products::add);
		}
		return products;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.services.BaseService#update(java.lang.Object)
	 */
	@Override
	public void update(Product product) {

		if (this.getRepo().exists(Objects.requireNonNull(product, "ERROR: Null Product informed.").getId())) {
			product.getImages().forEach((i) -> {
				i.setProduct(product);
			});
			if (Objects.nonNull(product.getParentProduct())) {
				product.setParentProduct(this.getRepo().findOne(product.getParentProduct().getId()));
			}
			this.getRepo().save(product);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.services.BaseService#remove(java.lang.Object)
	 */
	@Override
	public void remove(Long id) {

		if (this.getRepo().exists(Objects.requireNonNull(id, "ERROR: Null Id informed."))) {
			Product prd = this.getRepo().findOne(id);
			Product prod2 = prd.getParentProduct();
			if (Objects.nonNull(prod2.getProducts())) {
				prod2.getProducts().remove(prd);
				this.getRepo().save(prod2);
			}
			this.getRepo().delete(id);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.services.BaseService#count()
	 */
	@Override
	public long count() {
		return this.getRepo().count();
	}

}
