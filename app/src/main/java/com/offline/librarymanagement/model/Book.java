package com.offline.librarymanagement.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {

    @DocumentId
    String id;
    String author, accnumber, callnumber, details, name,publisher,imgurl;
    Integer issued, quantity;
    @ServerTimestamp
    Date createdon;

    List<String> keywords= new ArrayList<>();

    public Book() {
    }

    public Book(String author, String accnumber, String callnumber, String details, String name, String publisher, Integer quantity,String imgurl) {
        this.author = author;
        this.accnumber = accnumber;
        this.callnumber = callnumber;
        this.details = details;
        this.name = name;
        this.publisher = publisher;
        this.quantity = quantity;
        this.imgurl = imgurl;



        //generate the keywords from name and author
        for (int i = 0; i < name.length(); i++) {
            for (int j = i+1; j <= name.length(); j++) {
                this.keywords.add(name.substring(i,j));
            }
        }

        for (int i = 0; i < author.length(); i++) {
            for (int j = i+1; j <= author.length(); j++) {
                this.keywords.add(author.substring(i,j));
            }
        }
    }


    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getAccnumber() {
        return accnumber;
    }

    public String getCallnumber() {
        return callnumber;
    }

    public String getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getIssued() {
        return issued;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public String getImgurl() {
        return imgurl;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
