package com.franpradosdominguez.FitGymFran.interfaces;

import java.util.Collection;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;

public interface InterfaceDAO<T,K> {

	abstract boolean insert(T ob);
	T get(K id);
	abstract Collection<T> getAll();
	int update(T ob);
	int delete(T ob);
}
