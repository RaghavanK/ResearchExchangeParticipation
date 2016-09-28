/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.bean.GMailSender;
import Model.bean.User;
import Model.db.TempUserDB;
import Model.db.UserDB;
import com.uncc.util.PasswordUtil;
import com.uncc.util.TokenUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Raghavan
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet{
   
    private User user;
    private Validations validations;

    public Validations getValidations() {
        return validations;
    }

    public void setValidations(Validations validations) {
        this.validations = validations;
    }
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/home.jsp";
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if(null != action){
            
            
            if(action.equals("")){
                url = "/home.jsp";
            }else if(action.equals("create")){
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String recommenderEmail = request.getParameter("recommenderEmail");
                String confirm_password = request.getParameter("confirm_password");
                String msg = Validations.validateCredentials(name, email, password, confirm_password);
                if("validated".equals(msg)){
                    String token = TokenUtil.generateToken();
                    
                    TempUserDB.addTempUser(name, email, password, token);
                    String URL = TokenUtil.getURL(request.getServerName(), request.getServerPort(), "activate", token, email);
                    if(!recommenderEmail.equals("")){
                        URL = URL+"&recommenderEmail="+recommenderEmail; 
                    }
                    String subject = "Activate account";
                    String id = "ragz4042@gmail.com";
                    String mPassword = "ragz40424";
                    String body = "Hi "+name+","+"\n\n"+"Please click on the link to activate your account \n\n"
                            +"  "+URL+"\n\n"+"Thank You.";
                    GMailSender sender = new GMailSender(id,mPassword);
                    try {
                        sender.sendMail(subject, body, id, email);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    request.setAttribute("infoMsg", "Activation link has been sent to mail.Please check.");
                    url = "/signup.jsp";
                }else{
                    request.setAttribute("msg", msg);
                    url = "/signup.jsp";
                }
            
            }else if(action.equals("activate")){
                String email = request.getParameter("email");
                String recommenderEmail =request.getParameter("recommenderEmail");
                 user = TempUserDB.getTempUser(email);
                if(user != null){
                    UserDB.createUser(user);
                    if(recommenderEmail != null){
                        user = UserDB.selectUser(recommenderEmail);
                        user.setNumCoins(user.getNumCoins()+2);
                        UserDB.updateUser(user);
                    }
                    user = UserDB.selectUser(email);
                    session.setAttribute("theUser", user);
                    url = "/main.jsp";
                }else{
                    String msg = "Account already activated";
                    request.setAttribute("msg", msg);
                    url = "/signup.jsp";
                }
                
            }else if(action.equals("login")){
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                
                if(Validations.validateCredentials(email,password)){
                	
                    user = UserDB.selectUser(email);
                    String userType = user.getType();
                    if("participant".equals(userType)){
                        session.setAttribute("theUser", user);
                        url = "/main.jsp";
                    }else if("Admin".equals(userType)){
                        session.setAttribute("theUser", user);
                        url = "/admin.jsp";
                    }
                }else{
                    request.setAttribute("msg", "Please enter a valid email/password");
                    url="/login.jsp";
                }
                }
            else if(action.equals("signup")){
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String confirm_password = request.getParameter("confirm_password");
                String password = request.getParameter("password");
                String validationMsg = Validations.validateCredentials(name,email,password,confirm_password);
                if(validationMsg.equals("validated")){
                    user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    UserDB.createUser(user);
                    session.setAttribute("theUser", user);
                    request.setAttribute("msg", "Activation link has been sent to the above mail. Please check your mail.");
                    url="/signup.jsp";
                }else{
                    request.setAttribute("msg", validationMsg);
                    url="/signup.jsp";
                }       
                }
            else if(action.equals("forgot")){
                String email = request.getParameter("email");
                String token = request.getParameter("token");
                if(email != null){
                    user = UserDB.selectUser(email);
                    if(user != null){
                        if(token == null){
                            String newToken = TokenUtil.generateToken();
                            TempUserDB.addTempUser(user.getName(), email, user.getPassword(), newToken);
                            String forgotPasswordURL = TokenUtil.getForgetURL(request.getServerName(), request.getServerPort(), "forgot", newToken, email);
                            String subject = "Change Password";
                            String id = "ragz4042@gmail.com";
                            String mPassword = "ragz40424";
                            String body = "Hi "+user.getName()+","+"\n\n"+"Please click on the link to reset your password. \n\n"
                            +"  "+forgotPasswordURL+"\n\n"+"Thank You.";
                            GMailSender sender = new GMailSender(id,mPassword);
                            try {
                                sender.sendMail(subject, body, id, email);
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                            request.setAttribute("msg", "Mail sent to reset password");
                                url = "/forgotPassword.jsp";
                        } else {
                            user = TempUserDB.getTempUser(email);
                            String dbToken = user.getToken();
                            if(token.equals(dbToken)){
                                request.setAttribute("forgotToken", dbToken);
                                request.setAttribute("email", email);
                                url = "/reset.jsp";
                            }else{
                                request.setAttribute("msg", "Invalid Link...");
                                url = "/login.jsp";
                            }
                        }
                    }
                }
            }
            else if(action.equals("reset")){
                String token = request.getParameter("token");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String confirm_password = request.getParameter("confirm_password");
                if(password.equals(confirm_password)){
                    User tempUser = TempUserDB.getTempUser(email);
                    String tokenFromDB = tempUser.getToken();
                    if(token.equals(tokenFromDB)){
                        String salt = PasswordUtil.getSalt();
                        user.setSalt(salt);
                        String encryptedPassword="";
                        try {
                            encryptedPassword = PasswordUtil.hashAndSaltedPassword(password, salt);
                            user.setPassword(encryptedPassword);
                        
                            UserDB.updateUser(user);
                            request.setAttribute("msg", "Please login with your new credentials.");
                            url = "/login.jsp";
                        } catch (NoSuchAlgorithmException ex) {
                            System.out.println(ex);
                            request.setAttribute("msg", "Internal Error.");
                        }
                        
                    }else {
                        request.setAttribute("msg", "Invalid Link.");
                        url = "/login.jsp";
                    }
                }else{
                    request.setAttribute("msg", "Password and Confirm Password doesn't match.");
                    url = "/reset.jsp";
                }
            }
            else if(action.equals("how")){
                if(null != session.getAttribute("theUser")){
                    url = "/main.jsp";
                }else{
                    url="/how.jsp";
                }   }
            else if(action.equals("about")){
                if(null != session.getAttribute("theUser")){
                    url = "/about.jsp";
                }else{
                    url="/aboutl.jsp";
                }   }
            else if (action.equals("home")){
                if(null != session.getAttribute("theUser")){
                    url = "/main.jsp";
                }else{
                    url="/home.jsp";
                }   }
            else if(action.equals("main")){
                if(null != session.getAttribute("theUser")){
                    url = "/main.jsp";
                }else{
                    url="/login.jsp";
                }   }
            else if(action.equals("logout")){
                if(null != session.getAttribute("theUser") || null != session.getAttribute("theAdmin")){
                    session.invalidate();
                }   url = "/home.jsp";
            }
            else{
            }
        }    
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    } 
    
}
