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
public class ImageController extends BaseController<Image, Long> {

	/**
	 * 
	 * @param image
	 * @return
	 */
	@RequestMapping(value = "/image", produces = { "application/json" }, method = { RequestMethod.POST })
	public @ResponseBody Response<Long> insertProduct(@RequestBody Image image) {

		return new Response<Long>(() -> this.getService().insert(image));

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/image", produces = { "application/json" }, method = RequestMethod.GET)
	public @ResponseBody Response<List<Image>> listAllImages() {

		return new Response<List<Image>>(() -> this.getService().findAll());

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/image/{id}", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody Response<Image> getImageById(@PathVariable Long id) {
		return new Response<Image>(() -> this.getService().findById(id));
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
