package com.franpradosdominguez.FitGymFran.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.franpradosdominguez.FitGymFran.model.DataObject.User;
import com.franpradosdominguez.FitGymFran.utils.Connect;

public class UserDAO {

	private Connection miConexion;

	public UserDAO() {
		// TODO Auto-generated constructor stub
		this.miConexion = Connect.getConnect();
	}
	
	public int loginUser (String nickname, String password) {
		
		int state = -1;
		
		if (miConexion != null) {
			String sql = "SELECT nickname, password FROM user WHERE nickname = ? AND password = ?";
			
			try {
				PreparedStatement ps = miConexion.prepareStatement(sql);
				ps.setString(1, nickname);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					state = 1;
				} else {
					state = 0;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return state;
	}
}
