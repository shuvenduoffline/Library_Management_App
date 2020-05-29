package com.offline.librarymanagement.ui.bookadd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.offline.librarymanagement.R;


public class BookAddFragment extends Fragment {



    public BookAddFragment() {
        // Required empty public constructor
    }

    public static BookAddFragment newInstance(String param1, String param2) {
        BookAddFragment fragment = new BookAddFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_add, container, false);
    }
}
