/**
 * 
 */
package com.avenuecode.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avenuecode.product.domain.Image;
import com.avenuecode.product.domain.Product;
import com.avenuecode.product.services.BaseService;
import com.avenuecode.product.services.impl.ImageServiceImpl;
import com.avenuecode.product.services.impl.ProductServiceImpl;

/**
 * @author timoshenko
 *
 */
@Controller
public class ProductController extends BaseController<Product, Long> {

	private BaseService<Image, Long> imageService;

	/**
	 * 
	 * @param imageService
	 */
	@Autowired
	public void setImageService(BaseService<Image, Long> imageService) {
		this.imageService = imageService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avenuecode.product.controllers.BaseController#getService()
	 */
	@Override
	protected ProductServiceImpl getService() {
		return (ProductServiceImpl) super.getService();
	}

	/**
	 * 
	 * @return
	 */
	protected ImageServiceImpl getImageService() {
		return (ImageServiceImpl) this.imageService;
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product", produces = { "application/json" }, method = { RequestMethod.POST })
	public @ResponseBody Response<Long> insertProduct(@RequestBody Product product) {
		return new Response<Long>(SUCCESS, null, this.getService().insert(product));

	}

	/**
	 * 
	 * @param all
	 * @return
	 */
	@RequestMapping(value = "/product", produces = { "application/json" }, method = RequestMethod.GET)
	public @ResponseBody Response<List<Product>> listAllProducts(
			@RequestParam(value = "all", required = false, defaultValue = "false") boolean all) {

		return new Response<List<Product>>(SUCCESS, null, this.getService().findAll(all));

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody Response<Product> getProductById(@PathVariable Long id,
			@RequestParam(value = "all", required = false, defaultValue = "false") boolean all) {
		return new Response<Product>(SUCCESS, null, this.getService().findById(id, all));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}/childs", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody Response<List<Product>> getProductChildsById(@PathVariable Long id) {
		return new Response<List<Product>>(SUCCESS, null, this.getService().findProductChildsById(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}/images", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody Response<List<Image>> getProductImagesById(@PathVariable Long id) {

		return new Response<List<Image>>(SUCCESS, null, this.getImageService().findByProductId(id));

	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product", produces = { "application/json" }, method = { RequestMethod.PUT })
	public @ResponseBody Response<Long> editProduct(@RequestBody Product product) {

		this.getService().update(product);
		return new Response<Long>(SUCCESS, null, null);

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", produces = { "application/json" }, method = { RequestMethod.DELETE })
	public @ResponseBody Response<Long> removeProductById(@PathVariable Long id) {

		this.getService().remove(id);
		return new Response<Long>(SUCCESS, null, null);

	}

}
