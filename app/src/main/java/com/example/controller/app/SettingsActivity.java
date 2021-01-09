package com.example.controller.app;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.example.controller.R;

//TODO : may add FPS or Preview size too
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    Switch swc_autofocus , swc_flash ;

    int ocrLangPos;
    PreferenceManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new PreferenceManager(getApplicationContext());
        setContentView(R.layout.activity_settings);
        swc_autofocus = findViewById(R.id.swc_autofocus);
        swc_flash = findViewById(R.id.swc_flash);

        /*// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arr_ocr_lang, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spr_ocr_lang.setAdapter(adapter);*/
        getSettings();
        swc_autofocus.setOnClickListener(this);
        swc_flash.setOnClickListener(this);





    }

    private void getSettings() {
        swc_autofocus.setChecked(manager.getAutofocus());
        swc_flash.setChecked(manager.getFlash());

        ocrLangPos = manager.getOcrLangPos();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.swc_autofocus:
                manager.setAutoFocus(swc_autofocus.isChecked());
                break;
            case R.id.swc_flash:
                manager.setFlash(swc_flash.isChecked());
                break;

        }
    }
}
