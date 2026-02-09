package com.example;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.utils.DataSource;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        UserRepository userRepository = new UserRepository();

        System.out.println();

        // insert
        try (Connection conn = DataSource.getConnection()) {
            userRepository.insert(conn, "C", 150.0, "c@gmail.com");
            System.out.println("Successfully!");
        } catch (SQLException ex) {
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        // select and mapping
        try (Connection conn = DataSource.getConnection()) {
            userRepository.findById(conn, 1);
            System.out.println("Successfully!");
        } catch (SQLException ex) {
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        //transaction 
        System.out.println("Err");
        userService.transferMoney(1, 99, 200.0);
        System.out.println("Success");
        userService.transferMoney(1, 2, 300.0);
        try (Connection conn = DataSource.getConnection()) {
            userRepository.findById(conn, 1);
            userRepository.findById(conn, 2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}