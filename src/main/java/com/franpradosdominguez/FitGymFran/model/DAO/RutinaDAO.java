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

public class RutinaDAO extends Rutina implements interfaceDAO<Rutina, Integer> {
	
	private Connection miConexion;

	public RutinaDAO() {
		// TODO Auto-generated constructor stub
		this.miConexion = Connect.getConnect();
	}

	@Override
	public boolean insert(Rutina r) {
		// TODO Auto-generated method stub
		boolean insert = false;
		String consulta = "INSERT INTO rutina (nombreRutina, descripcion) VALUES (?, ?)";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setString(1, r.getNombreRutina());
			ps.setString(2, r.getDescripcion());
			ps.executeUpdate();
			insert = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			insert = false;
			e.printStackTrace();
		}
		return insert;
	}
	
	public boolean addRoutineForClient(Cliente c, Rutina r) {
		boolean insert = false;
		String consulta = "INSERT INTO cliente_rutina (id_cliente, id_rutina) VALUES (?, ?)";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, c.getIdCliente());
			ps.setInt(2, r.getIdRutina());
			ps.executeUpdate();
			insert = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return insert;
	}

	/**
	 * Este método obtiene a la rutina por su id
	 * @param id el valor del campo por el que obtiene
	 * @return la rutina obtenido o null si no existe.
	 */
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
			r.setIdRutina(rs.getInt("id_rutina"));
			r.setNombreRutina(rs.getString("nombreRutina"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * Este método nos sive para obtener una collección de todas las rutinas por sus campos.
	 * @return la rutina de la lista obtenida por sus campos.
	 */
	@Override
	public Collection<Rutina> getAll() {
		// TODO Auto-generated method stub
		Collection<Rutina> r = new ArrayList<>();
		String consulta = "SELECT id_rutina, nombreRutina, descripcion FROM rutina";
		
		Statement st;
		try {
			st = miConexion.createStatement();
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				Rutina aux = new Rutina();
				aux.setIdRutina(rs.getInt("id_rutina"));
				aux.setNombreRutina(rs.getString("nombreRutina"));
				aux.setDescripcion(rs.getString("descripcion"));
				r.add(aux);
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public int update(Rutina r) {
		// TODO Auto-generated method stub
		int up = -1;
		String consulta = "UPDATE rutina SET nombreRutina = ?, descripcion = ? WHERE rutina.id_rutina = ?";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setString(1, r.getNombreRutina());
			ps.setString(2, r.getDescripcion());
			ps.setInt(3, r.getIdRutina());
			ps.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}

	@Override
	public int delete(Rutina r) {
		// TODO Auto-generated method stub
		int del = -1;
		String consulta = "DELETE FROM rutina WHERE rutina.id_rutina = ?";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, r.getIdRutina());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return del;
	}
}
