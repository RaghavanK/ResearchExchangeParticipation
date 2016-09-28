/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uncc.util;

import java.util.UUID;

/**
 *
 * @author Raghavan
 */
public class TokenUtil {
    
    public static String generateToken(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","");
        return uuid;
    }
    
    public static String getURL(String server, int port, String action, String token, String email){
        return ("http://"+server+":"+port+"/Raghavan_Assignment4/UserController?action="+action+"&token="+token+"&email="+email);
    }
    public static String getForgetURL(String server, int port, String action, String token, String email){
        return ("http://"+server+":"+port+"/Raghavan_Assignment4/reset.jsp?action="+action+"&token="+token+"&email="+email);
    }
}
