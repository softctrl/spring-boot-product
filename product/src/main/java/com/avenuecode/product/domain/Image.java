/**
 * 
 */
package com.avenuecode.product.domain;

import java.io.Serializable;

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
		@NamedQuery(name = Image.Constants.FIND_IMAGES_BY_PRODUCT_ID, lockMode = LockModeType.NONE, query = "SELECT i FROM Image i WHERE i.product.id = ?1 ORDER BY i.id")
})

public class Image implements Serializable {
	
	public static final class Constants {

		public static final String FIND_IMAGES_BY_PRODUCT_ID = "FIND_IMAGES_BY_PRODUCT_ID";

	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1649630879980974808L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String type;

	@JsonBackReference
	@JoinColumn(name = "product_id")
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private Product product;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

}
