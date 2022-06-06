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
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.model.DataObject.Rutina;
import com.franpradosdominguez.FitGymFran.utils.Connect;

public class ClienteDAO extends Cliente implements interfaceDAO<Cliente, Integer> {

	private Connection miConexion;
	
	public ClienteDAO() {
		// TODO Auto-generated constructor stub
		this.miConexion = Connect.getConnect();
	}
	
	public ClienteDAO(int id, String name, String email, String dni, String phone, List<Rutina> misRutinas) {
		super(id, name, email, dni, phone, misRutinas);
		this.miConexion = miConexion;
	}
	
	public ClienteDAO(String name, String email, String dni, String phone) {
		super(name, email, dni, phone);
		this.miConexion = miConexion;
	}

	public ClienteDAO(String name) {
		super(name);
		this.miConexion = miConexion;
	}

	public ClienteDAO(Cliente c) {
		this.name = c.getName();
		this.email = c.getEmail();
		this.dni = c.getDni();
		this.phone = c.getPhone();
	}
	
	/**
	 * Este método nos sirve para insertar un cliente con todos los atributos
	 * de la propia clase.
	 * @param c: el cliente a insertar.
	 * @return true si el cliente se ha insertado y false si no se ha insertado correctamente.
	 */
	@Override
	public boolean insert(Cliente c) {
		// TODO Auto-generated method stub
		boolean addC = false;
		String consulta = "INSERT INTO cliente (nombre, email, dni, telefono) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getDni());
			ps.setString(4, c.getPhone());
			ps.executeUpdate();
			addC = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			addC = false;
		}
		return addC;
	}

	/**
	 * Este método obtiene al cliente por su id
	 * @param id el valor del campo por el que obtiene
	 * @return el cliente obtenido o null si no existe.
	 */
	@Override
	public Cliente get(Integer id) {
		// TODO Auto-generated method stub
		Cliente c = null;
		String consulta = "SELECT id_cliente, nombre, email, dni, telefono FROM cliente WHERE id_cliente = ?";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			c = new Cliente();
			rs.next();
			c.setIdCliente(rs.getInt("id_cliente"));
			c.setName(rs.getString("nombre"));
			c.setEmail(rs.getString("email"));
			c.setDni(rs.getString("dni"));
			c.setPhone(rs.getString("telefono"));
			
			//RutinaDAO rdao = new RutinaDAO();
			//c.setMiRutina(rdao.getAllRutinesForClient(c));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	/**
	 * Este método nos sirve para obtener una lista de las rutinas de cada cliente
	 * @param c cliente que se obtiene para saber su rutina
	 * @return una lista de rutinas que tiene asignado a ese cliente.
	 */
	public List<Rutina> getAllRutineForClient(Cliente c) {
		List<Rutina> listaRutina = new ArrayList<>();
		String consulta = "SELECT r.id_rutina, r.nombreRutina, r.descripcion FROM cliente c, cliente_rutina cr, rutina r WHERE c.id_cliente = cr.id_cliente AND cr.id_rutina = r.id_rutina AND c.id_cliente = ?";
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, c.getIdCliente());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Rutina r = new Rutina();
				r.setIdRutina(rs.getInt("id_rutina"));
				r.setNombreRutina(rs.getString("nombreRutina"));
				r.setDescripcion(rs.getString("descripcion"));
				listaRutina.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaRutina;
	}
	
	/**
	 * Este método nos sive para obtener una collección de todos los clientes por sus campos.
	 * @return el cliente de la lista obtenida por sus campos.
	 */
	@Override
	public Collection<Cliente> getAll() {
		// TODO Auto-generated method stub
		Collection<Cliente> c = new ArrayList<>();
		String consulta = "SELECT id_cliente, nombre, email, dni, telefono FROM cliente";
		
		Statement st;
		try {
			st = miConexion.createStatement();
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				Cliente aux = new Cliente();
				aux.setIdCliente(rs.getInt("id_cliente"));
				aux.setName(rs.getString("nombre"));
				aux.setEmail(rs.getString("email"));
				aux.setDni(rs.getString("dni"));
				aux.setPhone(rs.getString("telefono"));
				c.add(aux);
				//Cliente cl = new Cliente(id, name, email, dni, phone);
				//c.add(cl);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

	/**
	 * Este método nos sirve para actualizar un cliente por algunos campos de su tabla
	 * @param c es el cliente que se recibe para actualizarlo.
	 * @return la posición del cliente.
	 */
	@Override
	public int update(Cliente c) {
		// TODO Auto-generated method stub
		int up = -1;
		String consulta = "UPDATE cliente SET nombre = ?, email = ?, dni = ?, telefono = ? WHERE cliente.id_cliente = ?";

		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getDni());
			ps.setString(4, c.getPhone());
			ps.setInt(5, c.getIdCliente());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return up;
	}

	/**
	 * Este método nos sirve para eliminar un cliente de la tabla
	 * @param c es el cliente que seleccionamos para el borrado.
	 * @return -1 si no existe.
	 */
	@Override
	public int delete(Cliente c) {
		// TODO Auto-generated method stub
		int d = -1;
		String consulta = "DELETE FROM cliente WHERE cliente.id_cliente = ?";
		
		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, c.getIdCliente());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

}
