/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.bean.Study;
import Model.bean.User;
import Model.db.StudyDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.bean.GMailSender;
import Model.db.AnswerDB;
import Model.db.QuestionDB;
import Model.db.ReportDB;
import Model.db.UserDB;

/**
 *
 * @author Raghavan
 */
@WebServlet("/StudyController")
public class StudyController extends HttpServlet{
   
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        
        String url;
        url = "/main.jsp";
        
        if(null == action) {
        } else if ("".equals(action)){
                    if(null != session.getAttribute("theUser")){
                        url = "/main.jsp";
                    }else if(null != session.getAttribute("theAdmin")){
                        url = "/admin.jsp";
                    }else{
                        url= "/main.jsp";
                    }
                }
        else if (action.equals("participate")){
                    if(null != session.getAttribute("theUser")){
                        String studyCode = request.getParameter("studyCode");
                        if(null != studyCode){
                            Study study = StudyDB.selectUser(studyCode);
                            Study studyFromQuestionDB = QuestionDB.selectQuestion(studyCode);
                            study.setListOfAnswers(studyFromQuestionDB.getListOfAnswers());
                            request.setAttribute("study", study);
                            url = "/question.jsp";
                        }else {
                            List<Study> studyList = StudyDB.getStudiesBasedOnStatus("start");                            
                            request.setAttribute("studyList", studyList);
                            url="/participate.jsp";
                        }
                    }else{
                        url = "/login.jsp";
                    }}
        else if(action.equals("edit")){
                    if(null != session.getAttribute("theUser")){
                        String studyCode = (String)request.getParameter("studyCode");
                        Study study = StudyDB.selectUser(studyCode);
                        Study studyFromQuestionDB = QuestionDB.selectQuestion(studyCode);
                        study.setListOfAnswers(studyFromQuestionDB.getListOfAnswers());
                        request.setAttribute("listOfAnswers",studyFromQuestionDB.getListOfAnswers());
                        url = "/editStudy.jsp";
                        request.setAttribute("study", study);
                        
                    }else{
                        url = "/login.jsp";
                    }}
        else if (action.equals("report")){
                    User user = (User) session.getAttribute("theUser");
                    if(null != user){
                        String studyCode = request.getParameter("studyCode");
                        if(null != studyCode) {
                            if(null != user.getEmail()){
                            Study study = StudyDB.selectStudy(studyCode);
                            Study studyFromQuestionDB = QuestionDB.selectQuestion(studyCode);
                            study.setEmail(user.getEmail());
                            study.setQuestionId(studyFromQuestionDB.getQuestionId());
                            ReportDB.insertReport(study);
                            url = "/confirmrep.jsp";
                            }
                        }else {
                            ArrayList<Study> studyList = ReportDB.selectReports(user.getEmail());
                            request.setAttribute("studyList", studyList);
                            url = "/reporth.jsp";
                        }
                    }else{
                        url = "/login.jsp";
                    }}
        else if(action.equals("approve")){
                    if(null != session.getAttribute("theUser")){
                        String studyCode = (String)request.getParameter("studyCode");
                        Study study = StudyDB.selectStudy(studyCode);
                        Study studyFromReportDB = ReportDB.selectQuestion(studyCode);
                        study.setListOfAnswers(studyFromReportDB.getListOfAnswers());
                        study.setQuestion(studyFromReportDB.getQuestion());
                        study.setStatus("approve");
                        //StudyDB.updateStudy(studyCode, study);
                        ReportDB.updateReport(study);
                        ArrayList<Study> studyList = ReportDB.selectQuestionBasedOnStatus("Pending");//StudyDB.getStudies();
                        request.setAttribute("studyList", studyList);
                        url = "/requestc.jsp";
                    }else{
                        url = "/login.jsp";
                    }}
        else if(action.equals("disapprove")){
                    if(null != session.getAttribute("theUser")){
                        String studyCode = (String)request.getParameter("studyCode");
                        Study study = StudyDB.selectStudy(studyCode);
                        Study studyFromReportDB = ReportDB.selectQuestion(studyCode);
                        study.setListOfAnswers(studyFromReportDB.getListOfAnswers());
                        study.setQuestion(studyFromReportDB.getQuestion());
                        study.setStatus("disapprove");
                        //StudyDB.updateStudy(studyCode, study);
                        ReportDB.updateReport(study);
                        List<Study> studyList = ReportDB.selectQuestionBasedOnStatus("Pending");
                        request.setAttribute("studyList", studyList);
                        url = "/requestc.jsp";
                    }else{
                        url = "/login.jsp";
                    }   }
        else if(action.equals("update")){
                    User user = (User)session.getAttribute("theUser");
                    if(null != user){
                        String studyCode = request.getParameter("studyCode");
                        
                        Study study = StudyDB.selectStudy(studyCode);
                        study.setStudyName(request.getParameter("study_name"));
                        study.setEmail(user.getEmail());
                        study.setDescription(request.getParameter("description"));
                        study.setImageURL(request.getParameter("file"));
                        study.setRequestedParticipants(Integer.parseInt(request.getParameter("participants")));
                        study.setQuestion(request.getParameter("question_text"));
                        ArrayList<String> listOfAnswers = new ArrayList();
                            listOfAnswers.add(0, request.getParameter("DynamicTextBox1"));
                            listOfAnswers.add(1, request.getParameter("DynamicTextBox2"));
                            listOfAnswers.add(2, request.getParameter("DynamicTextBox3"));
                            listOfAnswers.add(3, request.getParameter("DynamicTextBox4"));
                            listOfAnswers.add(4, request.getParameter("DynamicTextBox5"));
                        study.setListOfAnswers(listOfAnswers);
 
                        StudyDB.updateStudy(studyCode, study);
                        QuestionDB.updateQuestion(study);
                        List<Study> studyList = StudyDB.getStudies(); 
                        request.setAttribute("studyList", studyList);
                        url = "/studies.jsp";
                    }else{
                        url = "/login.jsp";
                    }   }
        else if(action.equals("add")){
                    User user = (User)session.getAttribute("theUser"); 
                    if(null != user){
                        Study study = new Study();
                        study.setStudyName(request.getParameter("study_name"));
                        study.setQuestion(request.getParameter("question_text"));
                        study.setEmail(user.getEmail());
                        study.setImageURL(request.getParameter("file"));
                        study.setDescription(request.getParameter("description"));
                        StudyDB.addStudy(study);
                     
                        ArrayList<String> listOfAnswers = new ArrayList();
                            listOfAnswers.add(0, request.getParameter("DynamicTextBox1"));
                            listOfAnswers.add(1, request.getParameter("DynamicTextBox2"));
                            listOfAnswers.add(2, request.getParameter("DynamicTextBox3"));
                            listOfAnswers.add(3, request.getParameter("DynamicTextBox4"));
                            listOfAnswers.add(4, request.getParameter("DynamicTextBox5"));
                        study.setListOfAnswers(listOfAnswers);
                        
                        QuestionDB.createQuestion(study);
                        
                        List<Study> studyList = StudyDB.getStudies(user.getEmail());
                        request.setAttribute("studyList", studyList);
                        url = "/studies.jsp";
                    }   }
        else if(action.equals("start")){
                    User user = (User)session.getAttribute("theUser");
                    if(null != user){
                        String studyCode = (String)request.getParameter("studyCode");
                        Study study = StudyDB.selectUser(studyCode);
                        study.setStatus("stop");
                        StudyDB.updateStudy(studyCode, study);
                        List<Study> studyList = StudyDB.getStudies(user.getEmail());
                        request.setAttribute("studyList", studyList);
                        url = "/studies.jsp";
                    }else{
                        url = "/login.jsp";
                    }   }
        else if(action.equals("stop")){
                    User user = (User)session.getAttribute("theUser");
                    if(null != user){
                        String studyCode = (String)request.getParameter("studyCode");
                        Study study = StudyDB.selectUser(studyCode);
                        study.setStatus("start");
                        StudyDB.updateStudy(studyCode, study);
                        List<Study> studyList = StudyDB.getStudies(user.getEmail());
                        request.setAttribute("studyList", studyList);
                        url = "/studies.jsp";
                    }else{
                        url = "/login.jsp";
                    }   }
        else if(action.equals("answer")){
                     User user = (User)session.getAttribute("theUser");
                    if(null != user){
                        String studyCode = (String)request.getParameter("studyCode");
                        String choice = (String)request.getParameter("number");
                        
                        Study study = StudyDB.selectUser(studyCode);
                        
                        Study studyFromQuestionDB = QuestionDB.selectQuestion(studyCode);
                        study.setQuestionId(studyFromQuestionDB.getQuestionId());
                        AnswerDB.insertAnswer(study, choice,user.getEmail());
                        study.setListOfAnswers(studyFromQuestionDB.getListOfAnswers());
                        user.setNumCoins(user.getNumCoins()+1);
                        user.setNumParticipations(user.getNumParticipations()+1);
                        user.setStudies(user.getStudies()+1);
                        UserDB.updateUser(user);
                        study.setNumOfCoins(user.getNumCoins());
                        study.setNumOfParticipants(study.getNumOfParticipants()+1);
                        StudyDB.updateStudy(studyCode, study);
                        List<Study> studyList = StudyDB.getStudies();
                        request.setAttribute("studyList", studyList);
                        url = "/participate.jsp";
                    }else{
                        url = "/login.jsp";
                    }       
                }
    else if(action.equals("studies")){
                      User user = (User)session.getAttribute("theUser");
                    if(null != user){
                        String userEmail = user.getEmail();
                        List<Study> studyList = StudyDB.getStudies(userEmail); 
                        request.setAttribute("studyList", studyList);
                        url = "/studies.jsp";
                    }else{
                        url = "/login.jsp";
                    }
                }
    else if(action.equals("reportques")){
                    
                    User user = (User)session.getAttribute("theUser");
                    if(null != user){
                        ArrayList<Study> studyList = ReportDB.selectQuestionBasedOnStatus("Pending");
                        request.setAttribute("studyList", studyList);
                        url = "/reportques.jsp";
                    }else{
                        url = "/login.jsp";
                    }
                }
                
    else if(action.equals("recommend")){
                    String from =request.getParameter("email"); // email of sender
                    String subject ="Recommend Message";// subject of the mail
                    String emailId = "ragz4042@gmail.com"; // email of the sender
                    String password = "ragz40424"; // password of the sender's email
                    String to=request.getParameter("friend_email");
                    String name=request.getParameter("study_name");
                    String fwdURL = "http://"+request.getServerName()+":"+request.getServerPort()+"/Raghavan_Assignment4/signup.jsp?&recommenderEmail="+email;
                    String message=request.getParameter("message");
                    message = message + "\n\n Please click on the below link to signup.\n\n"+fwdURL;
                    String body ="Hello "+to+"\n\n"+
                          "This recommendation is sent by"+" "+name +"\n"
                        +message+"\n\n"+
                        "Thank you " +"\n\n"+from+" ";
                String myMailbody="Hello "+name+"\n\n"+
                          "Below recommendation is sent to your friend"+" "+to+"\n"
                        +message+"\n\n"+
                        ""+"\n"+"Thank you ";//Body of the mail
		GMailSender sender = new GMailSender(emailId, password);
		
		try {
			sender.sendMail(subject, body, from, to);
                        sender.sendMail(subject,myMailbody,to,from);
                        url="/confirmc.jsp";
		} catch (Exception e) {
			System.out.println(e);
		}
                }
                
    else if(action.equals("contact")){
                    String to ="ragz4042@gmail.com";
                    String subject ="Contact Message";
                    String emailId = "ragz4042@gmail.com"; 
                    String password = "ragz40424"; 
                    String from=request.getParameter("email");
                    String name=request.getParameter("study_name");
                    String message=request.getParameter("message");
                    String myMailBody ="Hello "+to+"\n\n"+
                          "This is from "+" "+name +"\n"
                        +message+"\n\n"+
                        "Thank you " +"\n\n"+from+" ";
              
		GMailSender sender = new GMailSender(emailId, password);
		
		try {
                        sender.sendMail(subject,myMailBody,from,to);
                        url="/confirmc.jsp";
		} catch (Exception e) {
			System.out.println(e);
		}
                }else if(action.equals("sharedStudy")){
                	String studyCode = request.getParameter("studyCode");
                	Study fetchedStudy = StudyDB.selectStudy(studyCode);
                	Study studyFromQuestionDB = QuestionDB.selectQuestion(studyCode);
                	fetchedStudy.setListOfAnswers(studyFromQuestionDB.getListOfAnswers());
                	request.setAttribute("sharedStudy", fetchedStudy);
                	url = "/studyShare.jsp";
                }
                
                
                
    else{
                    if(null != session.getAttribute("theUser")){
                        url = "/main.jsp";
                    }else if(null != session.getAttribute("theAdmin")){
                        url = "/admin.jsp";
                    }else{
                        url= "/main.jsp";
                    }   }
            
        
        
         getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    } 
}
