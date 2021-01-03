package com.example.controller.app.vision.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.controller.app.ocr.OcrCaptureActivity;
import com.example.controller.app.SettingsActivity;
import com.example.controller.app.vision.utils.Preference;
import com.example.controller.R;
import com.example.controller.app.vision.utils.ActivityUtils;
import com.example.controller.app.vision.utils.Constants;
import com.example.controller.app.vision.utils.DoubleClick;
import com.example.controller.app.vision.utils.DoubleClickListener;

public class DashBoardFragment extends BaseFragment {
    private TextView tvMessage;

    private TextView tvDiary;
    private TextView btn_ocr;
    private TextView str_settings;

    boolean b = Preference.getInstance().getData(Preference.CALL_IS_FIRST_TIME, true);




    private DoubleClick doubleClick = new DoubleClick(new DoubleClickListener() {
        @Override
        public void onSingleClick(View v) {
            if (v.getId() == tvMessage.getId()) {
                ActivityUtils.addFragment(getActivity(), R.id.container, DashBoardFragment.this, new MessageFragment());
                Log.d("TAG", "OnSingleclick");
            }
            if (v.getId() == tvDiary.getId()) {
                ActivityUtils.addFragment(getActivity(), R.id.container, DashBoardFragment.this, new DiaryFragment());
                Log.d("TAG", "");

            }
            if (v.getId() == btn_ocr.getId()) {
                // Log.d(TAG, LogUtil.prependCallLocation("onSingleclick: OCR button"));
                // launch Ocr capture activity.
                Intent intent = new Intent(v.getContext(), OcrCaptureActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                fragmentAppDemo.startActivity(intent);
            }
            if (v.getId() == str_settings.getId()) {
                // Log.d(TAG, LogUtil.prependCallLocation("onSingleclick: OCR button"));
                // launch Ocr capture activity.
                Intent iio = new Intent(v.getContext(), SettingsActivity.class);
                iio.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                fragmentAppDemo.startActivity(iio);
            }
        }
        @Override
        public void onDoubleClick(View v)
            {
            if (v.getId() == tvMessage.getId()) {
                fragmentAppDemo.speakerbox.play("Message");
                Log.d("TAG", "Message taped");
            }  if (v.getId() == tvDiary.getId())
            {
                fragmentAppDemo.speakerbox.play("Diary");
                Log.d("TAG", "Diary Tapped");

            }
            if (v.getId() == btn_ocr.getId()) {
                fragmentAppDemo.speakerbox.play("OCR");
                Log.d("TAG", "OCR tapped");

            }
            if (v.getId() == str_settings.getId()) {
                fragmentAppDemo.speakerbox.play("Settings");
                Log.d("TAG", "Settings tapped");
            }


        }

        @Override
        public void onTripleClick(View v) {
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            myDialogFragment.setData(Constants.KEY_DASHBOARD, false);
            myDialogFragment.show(getFragmentManager(), MyDialogFragment.class.getSimpleName());

        }

        @Override
        public void onFourthClick(View v) {
            getActivity().onBackPressed();
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tvMessage = view.findViewById(R.id.tvmessage);


        tvDiary = view.findViewById(R.id.tvdairy);
        btn_ocr = view.findViewById(R.id.btn_ocr);
        str_settings = view.findViewById(R.id.str_settings);

        tvMessage.setOnClickListener(doubleClick);

        tvDiary.setOnClickListener(doubleClick);
        btn_ocr.setOnClickListener(doubleClick);
        str_settings.setOnClickListener(doubleClick);


        if (b) {
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            myDialogFragment.setData(Constants.KEY_DASHBOARD, b);
            Preference.getInstance().setData(Preference.DASHBOARD_IS_FIRST_TIME, false);

            myDialogFragment.show(getFragmentManager(), MyDialogFragment.class.getSimpleName());
        }


        return view;
    }

    @Override
    public void onDestroy() {
        fragmentAppDemo.speakerbox.stop();
        super.onDestroy();

    }

    @Override
    public void onPause() {
        fragmentAppDemo.speakerbox.stop();
        super.onPause();
    }
}
