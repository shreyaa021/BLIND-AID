package com.example.controller.app.vision.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.controller.app.LogUtil;
import com.example.controller.app.LogUtil;
import com.example.controller.app.ocr.OcrCaptureActivity;
import com.example.controller.R;
import com.example.controller.app.vision.fragments.DashBoardFragment;
import com.example.controller.app.vision.utils.ActivityUtils;
import com.example.controller.app.vision.utils.DoubleClick;
import com.example.controller.app.vision.utils.DoubleClickListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final boolean DEV_MODE = true;
    private static final int CHECK_TTS_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityUtils.replaceFragment(MainActivity.this, R.id.container, new DashBoardFragment());

        fragmentAppDemo.speakerbox.play("Welcome to Blind Aid. Press on different sides to open and double tap to know the details");

    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (fragment != null) {
            if (fragment instanceof DashBoardFragment) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                super.onBackPressed();
            }
        }
    }

    private static final String TAG = "MainActivity";
    public void onClick(View view) {

        Log.e(TAG, LogUtil.prependCallLocation("onClick: " + view.getId() + " " + view.getContentDescription()));

        if (view.getId() == R.id.btn_ocr) {
            Log.d(TAG, LogUtil.prependCallLocation("onClick: OCR button"));
            // launch Ocr capture activity.
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            startActivity(intent);
        }


    }
}
