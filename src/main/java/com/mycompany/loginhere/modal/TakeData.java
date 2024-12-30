package com.mycompany.loginhere.modal;

import java.sql.*;

public class TakeData {

    public void insert(String f, String l, String u, String p, String e, Connection con) {
        String sql = "INSERT INTO login (username,password,fname,lname,email,token,verify) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, u);
            ps.setString(2, p);
            ps.setString(3, f);
            ps.setString(4, l);
            ps.setString(5, e);
            ps.setString(6, "null");
            ps.setString(7, "no");
            ps.execute();
        } catch (SQLException o) {
            System.out.println(o);
        } 
    }
}
