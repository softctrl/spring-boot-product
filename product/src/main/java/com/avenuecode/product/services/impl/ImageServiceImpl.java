/**
 * 
 */
package com.avenuecode.product.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.product.domain.Image;
import com.avenuecode.product.domain.Product;
import com.avenuecode.product.repositories.ImageRepository;
import com.avenuecode.product.repositories.ProductRepository;
import com.avenuecode.product.services.BaseService;

/**
 * @author timoshenko
 *
 */
@Service
public class ImageServiceImpl implements BaseService<Image, Long> {

	private ImageRepository imageRepository;
	private ProductRepository productRepository;

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

	protected ProductRepository getProdRepo() {
		return Objects.requireNonNull(this.productRepository, "ERROR: Product Repository is null!!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.services.BaseService#insert(java.lang.Object)
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
	 * @see com.avenuecode.product.services.BaseService#findById(java.lang.Object)
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
	 * @see com.avenuecode.product.services.BaseService#findAll()
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
	 * @see com.avenuecode.product.services.BaseService#update(java.lang.Object)
	 */
	@Override
	public void update(Image entity) {

		if (this.getRepo().exists(Objects.requireNonNull(entity, "ERROR: Null Image informed.").getId())) {
			if (Objects.nonNull(entity.getProduct())) {
				entity.setProduct(this.getProdRepo().findOne(entity.getProduct().getId()));
			}
			this.getRepo().save(entity);
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
			Image img = this.getRepo().findOne(id);
			Product prod = img.getProduct();
			prod.getImages().remove(img);
			this.getProdRepo().save(prod);
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
