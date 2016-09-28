/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.db;

import Model.bean.User;
import Model.data.ConnectionPool;
import Model.data.DBUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uncc.util.PasswordUtil;

/**
 *
 * @author Raghavan
 */
public class UserDB {
    
     public static User selectUser(String email) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs;
        User user =null;
        String query = "SELECT * FROM user WHERE Username = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if(rs.next()){
                user = new User();
                user.setName(rs.getString(8));
                user.setEmail(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setType(rs.getString(4));
                user.setNumCoins(rs.getInt("coins"));
                user.setNumParticipations(rs.getInt("participation"));
                user.setSalt(rs.getString(9));
               
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return user;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
     public static void createUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO user(username,password,email,name,type,salt,studies,participation,coins) VALUES (?,?,?,?,?,?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            String salt = PasswordUtil.getSalt();
            user.setPassword(PasswordUtil.hashAndSaltedPassword(user.getPassword(), salt));
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getName());
            ps.setString(5, "participant");
            ps.setString(6, salt);
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e);
        } catch(NoSuchAlgorithmException  e){
            System.out.println(e);
        }finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
     
     public static ArrayList<User> selectUsers() {  
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM user";
        ArrayList<User> userList = new ArrayList<User>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user;
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return userList;
    }
     
    public static void updateUser(User user){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "UPDATE user SET studies=?,participation=?,coins=?,name=?,password=?,salt=? WHERE"
                + " username=?";
        
        try {
            ps = connection.prepareStatement(query);
            
            ps.setInt(1, user.getStudies());
            ps.setInt(2, user.getNumParticipations());
            ps.setInt(3,user.getNumCoins());
            ps.setString(4,user.getName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getSalt());
            ps.setString(7, user.getEmail());
            ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
}