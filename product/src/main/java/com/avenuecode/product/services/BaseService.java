package com.avenuecode.product.services;

import java.util.List;

public interface BaseService<E, I> {
	
	Long insert(E entity);

	E findById(I id);

	List<E> findAll();

	void update(E entity);

	void remove(I id);
	
	long count();


}
