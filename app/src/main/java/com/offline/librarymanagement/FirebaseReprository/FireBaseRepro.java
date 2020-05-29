package com.offline.librarymanagement.FirebaseReprository;

import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.LogDescriptor;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.offline.librarymanagement.Interfaces.onBookFetch;
import com.offline.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class FireBaseRepro {
    final String TAG = "FirebaseRepro";
    FirebaseFirestore fb = FirebaseFirestore.getInstance();
    CollectionReference booksRef = fb.collection("BooksCollection");
    onBookFetch book_listner;

    public FireBaseRepro(onBookFetch listner) {
        this.book_listner = listner;
    }

    public void getBookslist(final String search){


        // logd
        Log.d(TAG, "getBookslist: "+search);
        if (search == null || search.equals("")) {
            booksRef.orderBy("createdon").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<Book> booklist = queryDocumentSnapshots.toObjects(Book.class);
                    book_listner.onComplete(booklist);
                    Log.d(TAG, "onSuccess: ");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());
                }
            });
        } else {
            booksRef.whereArrayContains("keywords", search).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<Book> booklist = queryDocumentSnapshots.toObjects(Book.class);
                    book_listner.onComplete(booklist);
                    Log.d(TAG, "onSuccess: "+search);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());
                }
            });
        }
    }
}
