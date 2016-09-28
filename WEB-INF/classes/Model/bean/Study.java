/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Raghavan
 */
public class Study implements Serializable{
    
    public Study(){
        
    }

    public String getStudyName() {
        return studyName;
    }

    public String getStudyCode() {
        return studyCode;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getRequestedParticipants() {
        return requestedParticipants;
    }

    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getAnswerType() {
        return answerType;
    }

    public List<String> getListOfAnswers() {
        return listOfAnswers;
    }

    public int getAverage() {
        return average;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public double getSd() {
        return sd;
    }
    private String studyName;
    private String studyCode;
    private String dateCreated;
    private String email;
    private String question;
    private String questionId;
    private String imageURL;
    private int requestedParticipants;
    private int numOfParticipants;
    private String description;
    private String status;
    private String answerType;
    private List<String> listOfAnswers;
    private int average;
    private int minimum;
    private int maximum;
    private double sd;
    private int numOfCoins;

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public void setStudyCode(String studyCode) {
        this.studyCode = studyCode;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setRequestedParticipants(int requestedParticipants) {
        this.requestedParticipants = requestedParticipants;
    }

    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public void setListOfAnswers(List<String> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    /**
     * @return the questionId
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the numOfCoins
     */
    public int getNumOfCoins() {
        return numOfCoins;
    }

    /**
     * @param numOfCoins the numOfCoins to set
     */
    public void setNumOfCoins(int numOfCoins) {
        this.numOfCoins = numOfCoins;
    }
}
