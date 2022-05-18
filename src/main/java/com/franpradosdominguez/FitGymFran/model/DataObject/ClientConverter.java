package com.franpradosdominguez.FitGymFran.model.DataObject;

import javafx.util.StringConverter;

/**
 * Esta clase sirve para poder obtener de cada clase del toString()
 * el campo adecuado acortado. En este caso, del toString() de la clase
 * Cliente sólo cojo el campo del nombre y así no me saldría todo el toString()
 * de dicha clase.
 * @author franp
 *
 */
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
