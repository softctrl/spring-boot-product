/**
 * 
 */
package com.avenuecode.product.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.avenuecode.product.services.BaseService;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author timoshenko
 *
 */
public class BaseController<E, I> {

	public static final int SUCCESS = 0;
	public static final int ERROR = 1;

	/**
	 * 
	 * @author timoshenko
	 *
	 * @param <R>
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static final class Response<R> {

		private int code = SUCCESS;
		private String msg = null;
		private R res = null;

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
