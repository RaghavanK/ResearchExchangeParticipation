/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;

import java.io.Serializable;

/**
 *
 * @author Raghavan
 */
public class User implements Serializable{

    public User(){
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }

    public void setNumPostedStudies(int numPostedStudies) {
        this.numPostedStudies = numPostedStudies;
    }

    public void setNumParticipations(int numParticipations) {
        this.numParticipations = numParticipations;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public int getNumPostedStudies() {
        return numPostedStudies;
    }

    public int getNumParticipations() {
        return numParticipations;
    }
    
    private String name;
    private String email;
    private String password;
    private String type;
    private int numCoins;
    private int studies;
    private String salt;
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private int numPostedStudies;
    private int numParticipations;

    /**
     * @return the studies
     */
    public int getStudies() {
        return studies;
    }

    /**
     * @param studies the studies to set
     */
    public void setStudies(int studies) {
        this.studies = studies;
    }

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
