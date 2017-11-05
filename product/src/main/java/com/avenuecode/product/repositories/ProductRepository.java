/**
 * 
 */
package com.avenuecode.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.avenuecode.product.domain.Product;

/**
 * @author timoshenko
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

	/**
	 * 
	 * @return
	 */
	@Query(name = Product.Constants.FIND_ALL_PRODUCTS)
	List<Product> findAllProducts();

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Query(name = Product.Constants.FIND_PRODUCT_BY_ID)
	List<Product> findProductById(Long id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Query(name = Product.Constants.FIND_PRODUCT_CHIELDS_BY_ID)
	List<Product> findProductChieldsById(Long id);

}
