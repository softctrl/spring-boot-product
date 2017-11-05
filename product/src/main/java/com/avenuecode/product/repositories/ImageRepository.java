/**
 * 
 */
package com.avenuecode.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.avenuecode.product.domain.Image;

/**
 * @author timoshenko
 *
 */
public interface ImageRepository extends CrudRepository<Image, Long> {

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Query(name = Image.Constants.FIND_IMAGES_BY_PRODUCT_ID)
	List<Image> findAllImagesByProductId(Long id);

}
