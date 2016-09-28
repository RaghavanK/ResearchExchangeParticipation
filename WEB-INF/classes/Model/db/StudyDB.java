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
public class StudyDB {
    
    public static Study selectUser(String studyCode) {
        ArrayList<Study> studyList = getStudies();
        for(Study study : studyList){
            if(studyCode.endsWith(study.getStudyCode())){
                return study;
            }
        }
        return null;
    }
    
    public static ArrayList<Study> getStudies() {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<Study> studyList = new ArrayList<Study>();
        Study study = null;
        String query = "SELECT * FROM study";
        try{
             ps = connection.prepareStatement(query);
             rs = ps.executeQuery();
             while(rs.next()){
                 study = new Study();
                 study.setStudyCode(rs.getString(1));
                 study.setStudyName(rs.getString(2));
                 study.setDescription(rs.getString(3));
                 study.setEmail(rs.getString(4));
                 study.setDateCreated(rs.getString(5));
                 study.setImageURL(rs.getString(6));
                 study.setNumOfParticipants(rs.getInt(8));
                 study.setRequestedParticipants(rs.getInt(7));
                 study.setStatus(rs.getString(9));
                 study.setQuestion(rs.getString(10));
                 studyList.add(study);
             }
        } catch (SQLException e) {
            System.out.println(e);
            return studyList;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        /*ArrayList<String> answer = new ArrayList<String>();
        answer.add("3");
        answer.add("4");
        answer.add("5");
        ArrayList<Study> studyList = new ArrayList<>();
        Study study1 = new Study();
        study1.setStudyName("Tree");
        study1.setStudyCode("code1001");
        study1.setEmail("john@gmail.com");
        study1.setNumOfParticipants(0);
        study1.setStatus("start");
        study1.setRequestedParticipants(0);
        study1.setQuestion("I enjoy outdoor activities");
        study1.setDescription("I enjoy outdoor activities(5 Strongly agree - 3 Strongly disagree)");
        study1.setImageURL("images/small_tree.jpg");
        study1.setListOfAnswers(answer);
        studyList.add(study1);
        
        Study study2 = new Study();
        study2.setStudyName("Computer");
        study2.setStudyCode("code1002");
        study2.setEmail("john@gmail.com");
        study2.setNumOfParticipants(0);
        study2.setStatus("start");
        study2.setQuestion("I use computers on a daily basis");
        study1.setDescription("I use computers on a daily basis(5 Strongly agree - 3 Strongly disagree)");
        study2.setImageURL("images/computer.jpg");
        study2.setListOfAnswers(answer);
        studyList.add(study2);
        */
        return studyList;
    }
    
    public static Study selectStudy(String studyCode){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Study study = null;
        String query = "SELECT * FROM study WHERE studycode = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            rs = ps.executeQuery();
            
            if(rs.next()){
                study = new Study();
                study.setStudyName(rs.getString("studyName"));
                study.setStudyCode(rs.getString("studyCode"));
                study.setDescription(rs.getString("description"));
                study.setEmail(rs.getString("username"));
                study.setImageURL(rs.getString("imageURL"));
                study.setRequestedParticipants(rs.getInt("reqParticipants"));
                study.setNumOfParticipants(rs.getInt("actParticipants"));
                study.setStatus(rs.getString("sstatus"));
                study.setQuestion(rs.getString("question"));
            }
            return study;
        }  catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return study;
    }
     public static ArrayList<Study> getStudies(String email) {
       ArrayList<Study> createdStudies = new ArrayList();
       ArrayList<Study> studies = getStudies();
       if(studies.size()>0 && studies!=null){
        for(Study study : studies){
            if(study.getEmail().equals(email))
                createdStudies.add(study);
        }
       }
       return createdStudies;
    }
    public static ArrayList<Study> getStudiesBasedOnStatus(String status) {
          ArrayList<Study> studyList = getStudies();
          ArrayList<Study> finalStudyList = new ArrayList<Study>(); 
        for(Study study : studyList){
            if(status.endsWith(study.getStatus())){
                finalStudyList.add(study);
            }
        }
        return finalStudyList;
    }
    
    public static void addStudy(Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO study(studyName,description,username,imageURL,reqParticipants,actParticipants,sStatus,question) VALUES (?,?,?,?,?,?,?,?)";
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getStudyName());
            ps.setString(2, study.getDescription());
            ps.setString(3, study.getEmail());
            ps.setString(4, study.getImageURL());
            ps.setInt(5, study.getRequestedParticipants());
            ps.setInt(6, 0);
            ps.setString(7, "start");
            ps.setString(8, study.getQuestion());
            ps.execute();
            
         } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void updateStudy(String sCode, Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE study SET studyName=?,"
                + "description=?,"
                + "username=?,imageURL=?,reqParticipants=?,actParticipants=?,sStatus=?,question=? "
                + "WHERE studycode=?";
        try{
             ps = connection.prepareStatement(query); 
            ps.setString(1, study.getStudyName());
            ps.setString(2, study.getDescription());
            ps.setString(3, study.getEmail());
            ps.setString(4, study.getImageURL());
            ps.setInt(5, study.getRequestedParticipants());
            ps.setInt(6, study.getNumOfParticipants());
            ps.setString(7, study.getStatus());
            ps.setString(8, study.getQuestion());
            ps.setString(9, sCode);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
   /* public static ArrayList<Study> retrieveStudyObject(String propName, String propVal){
        
        ArrayList<Study> studyList = new ArrayList<>();
        Study study1 = new Study();
        study1.setStudyName("Tree");
        study1.setStudyCode("code1001");
        study1.setEmail("john@gmail.com");
        study1.setNumOfParticipants(0);
        study1.setRequestedParticipants(0);
        study1.setQuestion("I enjoy outdoor activities");
        study1.setImageURL("images/small_tree.jpg");
        studyList.add(study1);
        
        Study study2 = new Study();
        study2.setStudyName("Computer");
        study2.setStudyCode("code1002");
        study2.setEmail("john@gmail.com");
        study2.setNumOfParticipants(0);
        study2.setQuestion("I use computers on a daily basis");
        study2.setImageURL("images/computer.jpg");
        studyList.add(study2);
        
        return studyList;
        
       
    }*/
}
