package com.franpradosdominguez.FitGymFran.model.DAO;

public class RutinaDAO {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//private Connection miConexion;

	/*public RutinaDAO() {
		// TODO Auto-generated constructor stub
		this.miConexion = Connect.getConnect();
	}*/

	/*
	public List<Rutina> getAllRutinesForClient(Cliente c) {
		List<Rutina> listaRutinas = new ArrayList<>();
		String consulta = "SELECT id_cliente, id_rutina FROM cliente_rutina WHERE id_cliente = ?";
		//String consulta = "SELECT id_rutina, rutina_ejercicios FROM rutina WHERE rutina.id_rutina = ?";

		try {
			PreparedStatement ps = miConexion.prepareStatement(consulta);
			ps.setInt(1, c.getId());
			ResultSet rs = ps.executeQuery();
			Rutina r = new Rutina();
			while (rs.next()) {
				r.setId_rut(rs.getInt("id_rutina"));
				listaRutinas.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaRutinas;
	}*/
}
