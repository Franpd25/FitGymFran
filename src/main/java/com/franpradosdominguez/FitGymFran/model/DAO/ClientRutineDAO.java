package com.franpradosdominguez.FitGymFran.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.franpradosdominguez.FitGymFran.interfaces.interfaceDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.ClientRutine;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.model.DataObject.Rutina;
import com.franpradosdominguez.FitGymFran.utils.Connect;

public class ClientRutineDAO implements interfaceDAO<ClientRutine, Integer> {
	
	private ClienteDAO cdao = new ClienteDAO();
	private RutinaDAO rdao = new RutinaDAO();

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
		ClientRutine cr = null;
		String consulta = "SELECT id_cliente, id_rutina FROM cliente_rutina WHERE id_cliente = ?";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			cr = new ClientRutine();
			rs.next();
			cr.setCliente(cdao.get(rs.getInt("id_cliente")));
			Rutina r = new Rutina();
			cr.setRutina(rdao.get(rs.getInt("id_rutina")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cr;
	}
	
	public List<Rutina> getAllRutineForClient(Cliente c) {
		
		List<Rutina> listaRutina = new ArrayList<>();
		String consulta = "SELECT r.id_rutina, r.nombreRutina FROM cliente c, cliente_rutina cr, rutina r WHERE c.id_cliente = cr.id_cliente AND cr.id_rutina = r.id_rutina AND c.id_cliente = ?";
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, c.getId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Rutina r = new Rutina();
				r.setId_rut(rs.getInt("id_rutina"));
				r.setNombreRutina(rs.getString("nombreRutina"));
				listaRutina.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaRutina;
	}

	@Override
	public Collection<ClientRutine> getAll() {
		// TODO Auto-generated method stub
		return null;
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
