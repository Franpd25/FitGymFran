package com.franpradosdominguez.FitGymFran.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.franpradosdominguez.FitGymFran.interfaces.interfaceDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.ClientRutine;
import com.franpradosdominguez.FitGymFran.utils.Connect;

public class ClientRutineDAO implements interfaceDAO<ClientRutine, Integer>{

	private Connection miConexion;
	
	public ClientRutineDAO() {
		// TODO Auto-generated constructor stub
		this.miConexion = Connect.getConnect();
	}
	
	
	@Override
	public boolean insert(ClientRutine ob) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClientRutine get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ClientRutine> getAll() {
		// TODO Auto-generated method stub
		Collection<ClientRutine> cr = new ArrayList<>();
		String consulta = "SELECT id_cliente, id_rutina FROM cliente_rutina";
		
		Statement st;
		try {
			st = miConexion.createStatement();
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				ClientRutine aux = new ClientRutine();
				aux.setCliente(null);
				aux.setRutina(null);
				cr.add(aux);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cr;
	}

	@Override
	public int update(ClientRutine ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ClientRutine ob) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
