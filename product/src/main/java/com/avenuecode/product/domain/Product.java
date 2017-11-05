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
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NamedQueries(value = {
		@NamedQuery(name = Product.Constants.FIND_ALL_PRODUCTS, lockMode = LockModeType.NONE, query = "SELECT new Product(p.id, p.name, p.description) FROM Product p ORDER BY p.id"),
		@NamedQuery(name = Product.Constants.FIND_PRODUCT_BY_ID, lockMode = LockModeType.NONE, query = "SELECT new Product(p.id, p.name, p.description) FROM Product p WHERE p.id = ?1 ORDER BY p.id"),
		@NamedQuery(name = Product.Constants.FIND_PRODUCT_CHILDS_BY_ID, lockMode = LockModeType.NONE, query = "SELECT new Product(p.id, p.name, p.description) FROM Product p WHERE p.parentProduct.id = ?1 ORDER BY p.id")
})
public class Product implements Serializable {

	public static final class Constants {

		public static final String FIND_ALL_PRODUCTS = "FIND_ALL_PRODUCTS";
		public static final String FIND_PRODUCT_BY_ID = "FIND_PRODUCT_BY_ID";
		public static final String FIND_PRODUCT_CHILDS_BY_ID = "FIND_PRODUCT_CHILDS_BY_ID";
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
	@ManyToOne(optional = true, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentProduct == null) ? 0 : parentProduct.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentProduct == null) {
			if (other.parentProduct != null)
				return false;
		} else if (!parentProduct.equals(other.parentProduct))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", images=" + images
				+ ", parentProduct=" + parentProduct + ", products=" + products + "]";
	}

}
