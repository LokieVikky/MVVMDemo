package com.thesoftwarecompany.mvvmdemo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.thesoftwarecompany.mvvmdemo.Adapters.UsersAdapter;
import com.thesoftwarecompany.mvvmdemo.Database.AppDatabase;
import com.thesoftwarecompany.mvvmdemo.Models.User;
import com.thesoftwarecompany.mvvmdemo.R;
import com.thesoftwarecompany.mvvmdemo.Repo.UserRepo;
import com.thesoftwarecompany.mvvmdemo.ViewModels.UserViewModel;
import com.thesoftwarecompany.mvvmdemo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    UsersAdapter adapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        mContext = MainActivity.this;
        mBinding.rvUserlist.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new UsersAdapter();
        mBinding.rvUserlist.setAdapter(adapter);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, usersList -> adapter.setData(usersList));
        mBinding.btnSave.setOnClickListener(v -> {
            if(mBinding.edtUsername.getText().toString().trim().equals("")){
                mBinding.tilUsername.setError("Enter username");
                return;
            }
            User user = new User();
            user.setUserName(mBinding.edtUsername.getText().toString());
            userViewModel.insertUser(user);
        });
    }
}