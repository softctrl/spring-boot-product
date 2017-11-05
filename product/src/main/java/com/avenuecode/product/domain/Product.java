/**
 * 
 */
package com.avenuecode.product.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.LockModeType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author timoshenko
 *
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NamedQueries(value = {
		@NamedQuery(name = Product.Constants.FIND_ALL_PRODUCTS, lockMode = LockModeType.NONE, query = "SELECT new Product(p.id, p.name, p.description) FROM Product p ORDER BY p.id"),
		@NamedQuery(name = Product.Constants.FIND_PRODUCT_BY_ID, lockMode = LockModeType.NONE, query = "SELECT new Product(p.id, p.name, p.description) FROM Product p WHERE p.id = ?1 ORDER BY p.id"),
		@NamedQuery(name = Product.Constants.FIND_PRODUCT_CHIELDS_BY_ID, lockMode = LockModeType.NONE, query = "SELECT new Product(p.id, p.name, p.description) FROM Product p WHERE p.parentProduct.id = ?1 ORDER BY p.id")
})
public class Product implements Serializable {

	public static final class Constants {

		public static final String FIND_ALL_PRODUCTS = "FIND_ALL_PRODUCTS";
		public static final String FIND_PRODUCT_BY_ID = "FIND_PRODUCT_BY_ID";
		public static final String FIND_PRODUCT_CHIELDS_BY_ID = "FIND_PRODUCT_CHIELDS_BY_ID";
		public static final String FIND_PRODUCT_IMAGES_BY_ID = "FIND_PRODUCT_IMAGES_BY_ID";

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 290450148769915285L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Image> images;

	@JsonBackReference
	@JoinColumn(name = "parent_product_id")
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Product parentProduct;

	@OneToMany(mappedBy = "parentProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Product.class)
	private List<Product> products;

	/**
	 * 
	 */
	public Product() {
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param description
	 */
	public Product(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * @return the parentProduct
	 */
	public Product getParentProduct() {
		return parentProduct;
	}

	/**
	 * @param parentProduct
	 *            the parentProduct to set
	 */
	public void setParentProduct(Product parentProduct) {
		this.parentProduct = parentProduct;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
