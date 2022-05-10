package com.franpradosdominguez.FitGymFran.interfaces;

import java.util.Collection;
import java.util.List;

public interface interfaceDAO<T,K> {

	boolean insert(T ob);
	T get(K id);
	Collection<T> getAll();
	int update(T ob);
	int delete(T ob);
}
