/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.security.NoSuchAlgorithmException;

import com.uncc.util.PasswordUtil;

import Model.bean.User;
import Model.db.UserDB;

/**
 *
 * @author Raghavan
 */
public class Validations {
   
    public static boolean validateCredentials(String email,String password){
        User fetchedUser = UserDB.selectUser(email);
        if(null != fetchedUser){
        String savedPassword = fetchedUser.getPassword();
        try {
		password = PasswordUtil.hashAndSaltedPassword(password, fetchedUser.getSalt());
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        return (savedPassword.equals(password));
        }
        return false;
    }
    
        
    public static String validateCredentials(String name,String email,String password,String confirm_password){
        
        String validationMsg;
        if(!password.equals(confirm_password)){
            validationMsg = "Password and confirm password does not match";
        }else if(null != UserDB.selectUser(email) ){
            validationMsg = "User already exists with the given email";
        }else{
            validationMsg = "validated";
        }
        return validationMsg ;
    }
}
