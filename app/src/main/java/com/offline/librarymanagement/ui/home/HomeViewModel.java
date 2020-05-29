package com.offline.librarymanagement.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.offline.librarymanagement.FirebaseReprository.FireBaseRepro;
import com.offline.librarymanagement.Interfaces.onBookFetch;
import com.offline.librarymanagement.model.Book;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Book>> bookList;
    FireBaseRepro repro;

    public HomeViewModel() {
        bookList = new MutableLiveData<>();
        repro = new FireBaseRepro(new onBookFetch() {
            @Override
            public void onComplete(List<Book> booklist) {
               bookList.setValue(booklist);
            }
        });
    }

    public LiveData<List<Book>> getBooks(String search) {
        repro.getBookslist(search);
        return bookList;
    }
}