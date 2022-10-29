package com.mohit.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertRecord(User users);

    @Query("Select exists(Select * from User where uid = :userId)")
    Boolean isExist(int userId);

    @Query("Select * from User")
    List<User> getAllUsers();

    @Query("Delete from User where uid = :id")
    void deleteById(int id);

}
