package com.offline.librarymanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.offline.librarymanagement.Interfaces.ShowBottomSheetListener;
import com.offline.librarymanagement.model.Book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainScreenActivity  extends AppCompatActivity implements ShowBottomSheetListener {

    private CardView mButtomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView bookName, bookAuthor , bookDescription, bookCallAccn ;
    private Button checkOut ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
      //  NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // To hide Appp Bar
        NavigationUI.setupWithNavController(navView, navController);

        mButtomSheet = findViewById(R.id.mybuttom_sheet);
        bookName = findViewById(R.id.buttom_book_title);
        bookAuthor = findViewById(R.id.butttom_book_author_name);
        bookCallAccn = findViewById(R.id.butttom_book_call_accn);
        bookDescription = findViewById(R.id.buttom_book_description);
        checkOut = findViewById(R.id.butttom_btn_issue_book);

        bottomSheetBehavior = BottomSheetBehavior.from(mButtomSheet);


    }

    @Override
    public void showBottomSheet(Book book) {
        bookName.setText(book.getName());
        bookAuthor.setText(book.getAuthor());
        bookCallAccn.setText(book.getCallnumber() + " • " + book.getAccnumber());
        bookDescription.setText(book.getDetails());
        checkOut.setText("Check Out ("+(book.getQuantity()-book.getIssued())+" Left)");
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreenActivity.this, "Checkout", Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior != null && (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED || bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_DRAGGING)){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else{
            super.onBackPressed();
        }
    }
}
