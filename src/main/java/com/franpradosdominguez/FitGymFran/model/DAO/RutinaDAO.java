package com.franpradosdominguez.FitGymFran.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.franpradosdominguez.FitGymFran.interfaces.interfaceDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.model.DataObject.Rutina;
import com.franpradosdominguez.FitGymFran.utils.Connect;

public class RutinaDAO implements interfaceDAO<Rutina, Integer> {
	
	private Connection miConexion;

	public RutinaDAO() {
		// TODO Auto-generated constructor stub
		this.miConexion = Connect.getConnect();
	}

	@Override
	public boolean insert(Rutina ob) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rutina get(Integer id) {
		// TODO Auto-generated method stub
		Rutina r = null;
		String consulta = "SELECT id_rutina, nombreRutina FROM rutina WHERE id_rutina = ?";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			r = new Rutina();
			rs.next();
			r.setId_rut(rs.getInt("id_rutina"));
			r.setNombreRutina(rs.getString("nombreRutina"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Collection<Rutina> getAll() {
		// TODO Auto-generated method stub
		Collection<Rutina> r = new ArrayList<>();
		String consulta = "SELECT id_rutina, nombreRutina FROM rutina";
		
		Statement st;
		try {
			st = miConexion.createStatement();
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				Rutina aux = new Rutina();
				aux.setId_rut(rs.getInt("id_rutina"));
				aux.setNombreRutina(rs.getString("nombreRutina"));
				r.add(aux);
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public int update(Rutina ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Rutina ob) {
		// TODO Auto-generated method stub
		return 0;
	}
}
