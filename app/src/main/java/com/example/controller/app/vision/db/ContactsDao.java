package com.example.controller.app.vision.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.controller.app.vision.models.ContactsDBModel;

import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM contacts")
    List<ContactsDBModel> getAll();

    @Query("SELECT * FROM contacts where mobile LIKE :mobile OR work LIKE :mobile OR main LIKE :mobile")
    ContactsDBModel findByName(String mobile);




    @Query("SELECT COUNT(*) from contacts")
    int countContacts();

    @Insert
    void insertAll(ContactsDBModel... contacts);

    @Delete
    void delete(ContactsDBModel contact);
}

