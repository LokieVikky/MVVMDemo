package com.thesoftwarecompany.mvvmdemo.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thesoftwarecompany.mvvmdemo.Models.User;
import com.thesoftwarecompany.mvvmdemo.Repo.UserRepo;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    UserRepo userRepo;
    private LiveData<List<User>> mutableLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userRepo = new UserRepo(application);
    }

    public LiveData<List<User>> getUsers() {
        if (mutableLiveData == null) {
            mutableLiveData = userRepo.getUsers();
        }
        return mutableLiveData;
    }

    public void insertUser(User user) {
        userRepo.insertUser(user);
    }


}
