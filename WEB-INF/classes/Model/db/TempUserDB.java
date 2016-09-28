/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.db;

import Model.bean.User;
import Model.data.ConnectionPool;
import Model.data.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Raghavan
 */
public class TempUserDB {
    
    public static void addTempUser(String userName, String email,String password, String token){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO tempuser (username,email,password,issuedate,token) VALUES(?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2,email);
            ps.setString(3, password);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setString(5, token);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{ 
            
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static User getTempUser(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user =null;
        String query = "SELECT * FROM tempuser WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setEmail(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setToken(rs.getString(5));
            }
            deleteTempUser(email);
            return user;
        } catch (SQLException ex) {
            System.out.println(ex);
            return user;
        } finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
        
    public static void deleteTempUser(String email){
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM tempuser WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{
            
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }    
    }
}
