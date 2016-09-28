/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;

/**
 *
 * @author Raghavan
 */
public class Answer {
    
    public Answer(){
        
    }
    private String email;
    private String choice;
    private String submissionDate;

    public String getEmail() {
        return email;
    }

    public String getChoice() {
        return choice;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }
}
