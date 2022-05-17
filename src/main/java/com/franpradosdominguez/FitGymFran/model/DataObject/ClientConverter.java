package com.franpradosdominguez.FitGymFran.model.DataObject;

import javafx.util.StringConverter;

public class ClientConverter extends StringConverter<Cliente> {

	@Override
	public String toString(Cliente c) {
		// TODO Auto-generated method stub
		return c == null ? null : c.getName();
	}

	@Override
	public Cliente fromString(String cstring) {
		// TODO Auto-generated method stub
		return null;
	}

}
