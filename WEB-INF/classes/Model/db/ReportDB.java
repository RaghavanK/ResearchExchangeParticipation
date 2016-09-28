/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.db;

import Model.bean.Study;
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
public class ReportDB {
    
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
    
    public static ArrayList<Study> selectQuestionBasedOnStatus(String status) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        ArrayList<Study> studyList=new ArrayList();
        Study study=null;
        String query = "SELECT DISTINCT questionId FROM reported WHERE status = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            rs = ps.executeQuery();
            
            while(rs.next()){
                study = QuestionDB.selectQuestion(rs.getString("questionId"),"");
                study.setQuestionId(rs.getString("questionId"));
                study.setStatus(status);
                studyList.add(study);
            }
            return studyList;
        } catch (SQLException e) {
            System.out.println(e);
            return studyList;
        } catch (Exception e) {
            System.out.println(e);
            return studyList;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }     
    
    public static void insertReport(Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        String query = "INSERT INTO reported(questionId,studyCode,username,numparticipants,status) VALUES (?,?,?,?,?)"
                + "ON DUPLICATE KEY UPDATE numparticipants=?,status=?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getQuestionId());
            ps.setString(2, study.getStudyCode());
            ps.setString(3, study.getEmail());
            ps.setInt(4, study.getNumOfParticipants());
            ps.setString(5, "Pending");
            ps.setInt(6, study.getNumOfParticipants()+1);
            ps.setString(7, "Pending");
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
     
     public static ArrayList<Study> selectReports() {  
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM reported";
        ArrayList<Study> studyList = new ArrayList<Study>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Study study;
            while (rs.next()) {
                study = new Study();
                study.setStudyCode(rs.getString("studyCode"));
                study.setQuestion(QuestionDB.selectQuestion(rs.getString("studyCode")).getQuestion());
                study.setQuestionId(rs.getString("questionId"));
                study.setDateCreated(rs.getString("date"));
                study.setNumOfParticipants(rs.getInt("numparticipants"));
                study.setStatus(rs.getString("status"));
                studyList.add(study);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return studyList;
    }
     
    public static ArrayList<Study> selectReports(String userName) {  
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM reported WHERE username=?";
        ArrayList<Study> studyList = new ArrayList<Study>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            Study study;
            while (rs.next()) {
                study = new Study();
                study.setStudyCode(rs.getString("studyCode"));
                study.setQuestion(QuestionDB.selectQuestion(rs.getString("studyCode")).getQuestion());
                study.setQuestionId(rs.getString("questionId"));
                study.setDateCreated(rs.getString("date"));
                study.setNumOfParticipants(rs.getInt("numParticipants"));
                study.setStatus(rs.getString("status"));
                studyList.add(study);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return studyList;
    }

    public static void updateReport(Study study){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "UPDATE reported SET numparticipants=?,status=?"
                 + "WHERE studyCode=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,study.getNumOfParticipants());
            ps.setString(2,study.getStatus());
            ps.setInt(3,Integer.parseInt(study.getStudyCode()));
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