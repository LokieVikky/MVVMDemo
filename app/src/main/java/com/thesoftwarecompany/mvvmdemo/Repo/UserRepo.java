package com.thesoftwarecompany.mvvmdemo.Repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thesoftwarecompany.mvvmdemo.Database.AppDatabase;
import com.thesoftwarecompany.mvvmdemo.Models.User;
import com.thesoftwarecompany.mvvmdemo.UserDao.UserDao;

import java.util.List;

public class UserRepo {
    private static final String TAG = UserRepo.class.getSimpleName();

    Context mContext;
    AppDatabase db;

    public UserRepo(Context mContext) {
        this.mContext = mContext;
    }

    public LiveData<List<User>> getUsers() {
        db = AppDatabase.getDatabase(mContext);
        UserDao userDao = db.userDao();
        return userDao.getAll();
    }

    public void insertUser(User user) {
        db.userDao().insertAll(user);
    }

}

