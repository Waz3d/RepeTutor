package it.dsmt.repetutor.dto;

import it.dsmt.repetutor.accessingmysql.entities.Comment;

import java.util.List;

public class TutorDTO extends StudentDTO{
    private String subjects;
    private Integer price;
    private String currency;
    private String location;
    private String description;
    private Double averageEvaluation;
    private List<Comment> receivedComments;

    public List<Comment> getReceivedComments() { return receivedComments; }

    public void setReceivedComments(List<Comment> receivedComments) {
        this.receivedComments = receivedComments;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAverageEvaluation() {
        return averageEvaluation;
    }

    public void setAverageEvaluation(Double averageEvaluation) {
        this.averageEvaluation = averageEvaluation;
    }
}
