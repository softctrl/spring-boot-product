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
