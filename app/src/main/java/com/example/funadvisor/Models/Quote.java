package com.example.funadvisor.Models;
import com.google.gson.annotations.SerializedName;

public class Quote  {

    @SerializedName("quoteText")
    private String body;

    @SerializedName("quoteAuthor")
    private String author;

    @SerializedName("senderName")
    private String senderName;

    @SerializedName("senderLink")
    private String senderLink;

    @SerializedName("quoteLink")
    private String quoteLink;

    public Quote() {

    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
