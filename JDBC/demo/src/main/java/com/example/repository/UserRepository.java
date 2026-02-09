package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.dto.UserDTO;

public class UserRepository {
    public void insert(Connection conn, String username, double balance, String email) throws SQLException{
        String sql="INSERT INTO users (username, balance, email) values (?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setDouble(2, balance);
        ps.setString(3, email);
        ps.executeUpdate();
    }
    public void updateBalance(Connection conn, int userId, double amount) throws SQLException {
        String sql = "UPDATE users SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    public void delete(Connection conn, int userId) {
        String sql = "delete from users where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(UserRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public void findById(Connection conn, int userId){
        String sql="Select * from users where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                UserDTO user=new UserDTO();
                user.setId((rs.getInt("id")));
                user.setName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setBalance(rs.getDouble("balance"));
                System.out.println(user.getName() + " - " + user.getBalance());
            }
            else{
                System.out.println("Not find "+userId);
            }
        } catch (SQLException ex) {
            System.getLogger(UserRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }


    }
}
