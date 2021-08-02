package com.thesoftwarecompany.mvvmdemo.UserDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.thesoftwarecompany.mvvmdemo.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
