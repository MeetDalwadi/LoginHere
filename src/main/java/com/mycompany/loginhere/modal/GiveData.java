package com.mycompany.loginhere.modal;

import java.sql.*;

public class GiveData {

    public boolean check(String username, String password, Connection con) throws SQLException, ClassNotFoundException {
        String sql = "select * from login where username = ? and password = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
