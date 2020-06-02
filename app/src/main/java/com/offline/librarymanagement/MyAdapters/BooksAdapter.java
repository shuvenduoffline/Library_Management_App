package com.offline.librarymanagement.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.offline.librarymanagement.Interfaces.BookListOnClickEvent;
import com.offline.librarymanagement.R;
import com.offline.librarymanagement.model.Book;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private List<Book> bookList;
    private Context mcontext;
    private BookListOnClickEvent listner;

    public BooksAdapter(List<Book> books, Context context, BookListOnClickEvent listner) {
        this.bookList = books;
        this.mcontext = context;
        this.listner = listner;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_book_item, null);

        // create ViewHolder

        BooksAdapter.ViewHolder viewHolder = new BooksAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final BooksAdapter.ViewHolder viewHolder, final int position) {
        Book book = bookList.get(position);
        viewHolder.title.setText(book.getName());
        viewHolder.author.setText(book.getAuthor());
        String description = book.getDetails().length() < 240 ? book.getDetails() : book.getDetails().substring(0,240) + " ...";
        viewHolder.description.setText(description);


        Glide.with(mcontext)
                .load(book.getImgurl())
                .centerCrop()
                .into(viewHolder.img);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.bookListOnClickListener(bookList.get(position));
            }
        });

    }



    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,author, description;
        public ImageView img;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            title = (TextView) itemLayoutView.findViewById(R.id.book_title);
            author = (TextView) itemLayoutView.findViewById(R.id.book_author_name);
            description = (TextView) itemLayoutView.findViewById(R.id.book_description);
            img = itemLayoutView.findViewById(R.id.book_image);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return bookList.size();
    }
}