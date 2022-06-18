package backend;

import java.util.Date;
public class Comment {
    String customerName;
    String ownerName;
    String content;
    double rating;
    Date commentTime;

    public Comment() {
    }

    public Comment(String customerName, String ownerName, String content, double rating, Date commentTime) {
        this.customerName = customerName;
        this.ownerName = ownerName;
        this.content = content;
        this.rating = rating;
        this.commentTime = commentTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
