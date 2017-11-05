/**
 * 
 */
package com.avenuecode.product.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avenuecode.product.domain.Image;

/**
 * @author timoshenko
 *
 */
@Controller
public class ImageController extends BaseController<Image, Long> {

	/**
	 * 
	 * @param image
	 * @return
	 */
	@RequestMapping(value = "/image", produces = { "application/json" }, method = { RequestMethod.POST })
	public @ResponseBody Response<Long> insertProduct(@RequestBody Image image) {

		return new Response<Long>(SUCCESS, null, this.getService().insert(image));

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/image", produces = { "application/json" }, method = RequestMethod.GET)
	public @ResponseBody Response<List<Image>> listAllImages() {

		return new Response<List<Image>>(SUCCESS, null, this.getService().findAll());

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/image/{id}", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody Response<Image> getImageById(@PathVariable Long id) {
		return new Response<Image>(SUCCESS, null, this.getService().findById(id));
	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	@RequestMapping(value = "/image", produces = { "application/json" }, method = { RequestMethod.PUT })
	public @ResponseBody Response<Long> editProduct(@RequestBody Image image) {

		this.getService().update(image);
		return new Response<Long>(SUCCESS, null, null);

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/image/{id}", produces = { "application/json" }, method = { RequestMethod.DELETE })
	public @ResponseBody Response<Long> removeImageById(@PathVariable Long id) {

		this.getService().remove(id);
		return new Response<Long>(SUCCESS, null, null);

	}

}
