package com.example.controller.app.vision;

import android.app.Application;

import com.example.controller.app.vision.db.DatabaseApp;
import com.example.controller.app.vision.utils.Speakerbox;
import com.facebook.stetho.Stetho;

public class FragmentApp extends Application {

    public Speakerbox speakerbox;
    private static FragmentApp androidApp;
    private static DatabaseApp db;

    public DatabaseApp getDb()
    {
        return db;
    }

    public static FragmentApp getInstance()
    {
        return androidApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        speakerbox = new Speakerbox(FragmentApp.this);
        androidApp = this;

        db = DatabaseApp.getDiaryDatabaseApp(getApplicationContext());
        Stetho.initializeWithDefaults(getApplicationContext());

    }
}



