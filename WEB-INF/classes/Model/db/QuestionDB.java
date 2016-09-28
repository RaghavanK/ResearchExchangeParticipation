/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.db;

import Model.bean.Study;
import Model.bean.User;
import Model.data.ConnectionPool;
import Model.data.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Raghavan
 */
public class QuestionDB {
    
     public static Study selectQuestion(String studyCode) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        Study study=null;
        String query = "SELECT * FROM question WHERE studyCode = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(studyCode));
            rs = ps.executeQuery();
            
            if(rs.next()){
                study = new Study();
                ArrayList<String> listOfAnswers = new ArrayList();
               
                listOfAnswers.add(0, rs.getString(5));
                listOfAnswers.add(1, rs.getString(6));
                listOfAnswers.add(2, rs.getString(7));
                if(rs.getString(8) != null)listOfAnswers.add(3, rs.getString(8));
                if(rs.getString(9) != null)listOfAnswers.add(4, rs.getString(9));
                study.setQuestionId(rs.getString(1));
                study.setQuestion(rs.getString("question"));
                study.setListOfAnswers(listOfAnswers);
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return study;
        } catch (Exception e) {
            System.out.println(e);
            return study;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
     
    public static Study selectQuestion(String questionID,String dummy) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        Study study=null;
        String query = "SELECT * FROM question WHERE questionID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(questionID));
            rs = ps.executeQuery();
            
            if(rs.next()){
                study = new Study();
                ArrayList<String> listOfAnswers = new ArrayList();
               
                listOfAnswers.add(0, rs.getString(5));
                listOfAnswers.add(1, rs.getString(6));
                listOfAnswers.add(2, rs.getString(7));
                if(rs.getString(8) != null)listOfAnswers.add(3, rs.getString(8));
                if(rs.getString(9) != null)listOfAnswers.add(4, rs.getString(9));
                study.setQuestionId(rs.getString(1));
                study.setQuestion(rs.getString("question"));
                study.setStudyCode(rs.getString("studycode"));
                study.setListOfAnswers(listOfAnswers);
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return study;
        } catch (Exception e) {
            System.out.println(e);
            return study;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
     public static void createQuestion(Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query1 = "SELECT last_insert_id() as last_id from study";
        String query = "INSERT INTO question(studycode,question,answertype,option1,"
                + "option2,option3,option4,option5) VALUES (?,?,?,?,?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(query1);
            rs = ps.executeQuery();
            rs.next();
            String last_id = rs.getString("last_id");
            
            ps = connection.prepareStatement(query);
            ps.setString(1, last_id);
            ps.setString(2, study.getQuestion());
            ps.setString(3, study.getAnswerType());
            ps.setString(4, study.getListOfAnswers().get(0));
            ps.setString(5, study.getListOfAnswers().get(1));
            ps.setString(6, study.getListOfAnswers().get(2));
            ps.setString(7, study.getListOfAnswers().get(3));
            ps.setString(8, study.getListOfAnswers().get(4));
          
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
     
     public static ArrayList<User> selectQuestions() {  
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM question";
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
    public static void updateQuestion(Study study){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "UPDATE question SET question=?,answertype=?,option1=?,"
                 + "option2=?,option3=?,option4=?,option5=? "
                 + "WHERE studycode=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,study.getQuestion());
            ps.setString(2,study.getAnswerType());
            ps.setString(3,study.getListOfAnswers().get(0));
            ps.setString(4,study.getListOfAnswers().get(1));
            ps.setString(5,study.getListOfAnswers().get(2));
            ps.setString(6,study.getListOfAnswers().get(3));
            ps.setString(7,study.getListOfAnswers().get(4));
            ps.setInt(8,Integer.parseInt(study.getStudyCode()));
            ps.execute();
            
          } catch (SQLException e) {
            System.out.println(e); 
          } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
    }
}