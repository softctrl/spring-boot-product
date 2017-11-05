/**
 * 
 */
package com.avenuecode.product.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.avenuecode.product.services.BaseService;
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
public class BaseController<E, I> {

	public static final int SUCCESS = 0;
	public static final int ERROR = 1;

	/**
	 * 
	 * @author carlostimoshenkorodrigueslopes@gmail.com
	 *
	 * @param <R>
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static final class Response<R> {

		/**
		 * 
		 * @author timoshenko
		 *
		 * @param <R>
		 */
		public interface Action<R> {
			R execute();
		}

		private int code = SUCCESS;
		private String msg = null;
		private R res = null;

		/**
		 * 
		 * @param action
		 */
		public Response(Action<R> action) {
			try {
				this.res = action.execute();
				this.code = SUCCESS;
			} catch (Exception ex) {
				this.code = ERROR;
				this.msg = ex.getLocalizedMessage();
			}
		}

		/**
		 * 
		 * @param code
		 * @param msg
		 * @param res
		 */
		public Response(int code, String msg, R res) {
			this.code = code;
			this.msg = msg;
			this.res = res;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		public R getRes() {
			return res;
		}
	}

	private BaseService<E, I> service;

	@Autowired
	public void setService(BaseService<E, I> service) {
		this.service = service;
	}

	/**
	 * 
	 * @return
	 */
	protected BaseService<E, I> getService() {
		return Objects.requireNonNull(this.service, "ERROR: Product Service is null.");
	}

}
