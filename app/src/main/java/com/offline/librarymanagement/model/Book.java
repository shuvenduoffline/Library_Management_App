package com.offline.librarymanagement.model;

import com.google.firebase.firestore.DocumentId;

public class Book {

    @DocumentId
    String id;
    String bookName;
    String author;
    String details;



}
