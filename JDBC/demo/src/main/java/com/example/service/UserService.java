package com.example.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.repository.UserRepository;
import com.example.utils.DataSource;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void transferMoney(int fromUser, int toUser, double amount) {
        Connection conn = null;
        try {
            conn = DataSource.getConnection();
            
            conn.setAutoCommit(false);

            userRepository.updateBalance(conn, fromUser, -amount);
            userRepository.updateBalance(conn, toUser, amount);

            conn.commit();
            System.out.println("Successfully!");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Failed!");
                } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); 
                    conn.close();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
