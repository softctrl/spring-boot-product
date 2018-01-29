/**
 * 
 */
package br.com.softctrl.springboot.product.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softctrl.springboot.product.domain.Image;
import br.com.softctrl.springboot.product.domain.Product;
import br.com.softctrl.springboot.product.repositories.ImageRepository;
import br.com.softctrl.springboot.product.repositories.ProductRepository;
import br.com.softctrl.springboot.product.services.BaseService;

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
public class ImageServiceImpl implements BaseService<Image, Long> {

	private ImageRepository imageRepository;
	private ProductRepository productRepository;

	/**
	 * 
	 * @param imageRepository
	 * @param productRepository
	 */
	@Autowired
	public ImageServiceImpl(ImageRepository imageRepository, ProductRepository productRepository) {
		this.imageRepository = imageRepository;
		this.productRepository = productRepository;
	}

	/**
	 * 
	 * @return
	 */
	protected ImageRepository getRepo() {
		return Objects.requireNonNull(this.imageRepository, "ERROR: Image Repository is null!!");
	}

	/**
	 * 
	 * @return
	 */
	protected ProductRepository getProdRepo() {
		return Objects.requireNonNull(this.productRepository, "ERROR: Product Repository is null!!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.springboot.product.services.BaseService#insert(java.lang.Object)
	 */
	@Override
	public Long insert(Image entity) {
		if (Objects.nonNull(entity.getProduct())) {
			entity.setProduct(this.getProdRepo().findOne(entity.getProduct().getId()));
		}
		return this.getRepo().save(entity).getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.springboot.product.services.BaseService#findById(java.lang.Object)
	 */
	@Override
	public Image findById(Long id) {
		return this.getRepo().findOne(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Image> findByProductId(Long id) {
		return this.getRepo().findAllImagesByProductId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.springboot.product.services.BaseService#findAll()
	 */
	@Override
	public List<Image> findAll() {
		List<Image> result = new ArrayList<>();
		this.getRepo().findAll().forEach(result::add);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.springboot.product.services.BaseService#update(java.lang.Object)
	 */
	@Override
	public void update(Image entity) {

		Objects.requireNonNull(entity, "ERROR: Null Image informed.");
		Objects.requireNonNull(entity.getId(), "ERROR: Null Image ID informed.");

		if (this.getRepo().exists(entity.getId())) {
			if (Objects.nonNull(entity.getProduct())) {
				entity.setProduct(this.getProdRepo().findOne(entity.getProduct().getId()));
			}
			this.getRepo().save(entity);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.springboot.product.services.BaseService#remove(java.lang.Object)
	 */
	@Override
	public void remove(Long id) {

		if (this.getRepo().exists(Objects.requireNonNull(id, "ERROR: Null Id informed."))) {
			Image img = this.getRepo().findOne(id);
			Product prod = img.getProduct();
			if (Objects.nonNull(prod) && Objects.nonNull(prod.getImages())) {
				prod.getImages().remove(img);
				this.getProdRepo().save(prod);
			}
			this.getRepo().delete(id);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.springboot.product.services.BaseService#count()
	 */
	@Override
	public long count() {
		return this.getRepo().count();
	}

}
