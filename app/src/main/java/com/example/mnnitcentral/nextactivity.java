package com.example.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class nextactivity extends AppCompatActivity {

//    private long backpress;
//    private Toast backtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nextactivity);

    }

//    @Override
//    public void onBackPressed() {
//
//        if (backpress +2000>System.currentTimeMillis()){
//            backtoast.cancel();
//            super.onBackPressed();
//            return;
//        }
//        else {
//            backtoast= Toast.makeText(this, "press back again to exit", Toast.LENGTH_SHORT);
//            backtoast.show();
//        }
//        backpress = System.currentTimeMillis();
//    }
}
