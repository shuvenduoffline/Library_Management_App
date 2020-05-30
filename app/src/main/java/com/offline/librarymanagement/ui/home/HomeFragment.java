package com.offline.librarymanagement.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.offline.librarymanagement.MyAdapters.BooksAdapter;
import com.offline.librarymanagement.R;
import com.offline.librarymanagement.model.Book;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private EditText searchInput;
    private RecyclerView booksRv;
    private BooksAdapter adapter;
    private ImageView loadingImg;

    public static final String TAG = "HomeFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        searchInput = root.findViewById(R.id.search_input);
        booksRv = root.findViewById(R.id.books_rv);
        loadingImg = root.findViewById(R.id.loading_image);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //view model
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        queryBook("");

        //Hide the soft key on page open
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //start the animation
        showLoadingGif();

        //on ime button press sone
        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                    showLoadingGif();
                    queryBook(searchInput.getText().toString().trim());
                }
                return false;
            }
        });

    }

    public void queryBook(String search){
        homeViewModel.getBooks(search).observe(getViewLifecycleOwner(), new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                updateBookRv(books);
            }
        });
    }

    void showLoadingGif(){
        Log.d(TAG, "showLoadingGif: ");
            booksRv.setVisibility(View.GONE);
            loadingImg.setVisibility(View.VISIBLE);
        Glide
                .with( getActivity().getApplicationContext() )
                .load(R.drawable.book_loading)
                .into( loadingImg );
    }



    private void updateBookRv(List<Book> books) {
        if (books == null ) return;
        if( books.isEmpty()) {
            showNoResultFound();
            return;
        }
        booksRv.setVisibility(View.VISIBLE);
        loadingImg.setVisibility(View.GONE);
        adapter = new BooksAdapter(books, getActivity().getApplicationContext());
        booksRv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        booksRv.setLayoutManager(mLayoutManager);
        booksRv.setItemAnimator(new DefaultItemAnimator());
        booksRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showNoResultFound() {

        Glide
                .with( getActivity().getApplicationContext() )
                .load(R.drawable.ic_undraw_empty_xct9)
                .centerInside()
                .into( loadingImg );
        booksRv.setVisibility(View.GONE);
        loadingImg.setVisibility(View.VISIBLE);
    }
}
