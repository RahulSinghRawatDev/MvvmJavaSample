package com.rahul.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rahul.mainactivity.adapter.RecyclerAdapter;
import com.rahul.mainactivity.models.UserProfile;
import com.rahul.mainactivity.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private FloatingActionButton floatingActionButton;
    private MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewModel();
        initAdapter();
     }

    private void initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getUserProfiles().observe(this, new Observer<List<UserProfile>>() {
            @Override
            public void onChanged(List<UserProfile> userProfiles) {

                Toast.makeText(MainActivity.this,"Total count = "+userProfiles.size(),Toast.LENGTH_SHORT).show();

                recyclerAdapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getUserProfiles().getValue().size()-1);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.addNewUserData(new UserProfile("Chandrayaan 2","https://akm-img-a-in.tosshub.com/indiatoday/images/story/201909/Chandrayaan-2_Isor_YT-770x433.jpeg?S.nQeMyfA_2NhZpCM9JghmA2Zq.sG.Yf"));
            }
        });
    }


    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progress_bar);
        floatingActionButton = findViewById(R.id.fab);
    }

    private void initAdapter() {
        recyclerAdapter = new RecyclerAdapter(this,mainActivityViewModel.getUserProfiles().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

}
