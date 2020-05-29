package com.offline.librarymanagement.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.offline.librarymanagement.MyAdapters.BooksAdapter;
import com.offline.librarymanagement.R;
import com.offline.librarymanagement.model.Book;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private EditText searchInput;
    private RecyclerView booksRv;
    private BooksAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        searchInput = root.findViewById(R.id.search_input);
        booksRv = root.findViewById(R.id.books_rv);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //view model
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getBooks("").observe(getViewLifecycleOwner(), new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                updateBookRv(books);
            }
        });

    }

    private void updateBookRv(List<Book> books) {
        if (books == null) return;
        adapter = new BooksAdapter(books, getActivity().getApplicationContext());
        booksRv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        booksRv.setLayoutManager(mLayoutManager);
        booksRv.setItemAnimator(new DefaultItemAnimator());
        booksRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
