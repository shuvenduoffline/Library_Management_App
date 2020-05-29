package com.offline.librarymanagement.Interfaces;

import com.offline.librarymanagement.model.Book;

import java.util.List;

public interface onBookFetch {
    void onComplete(List<Book> booklist);
}
