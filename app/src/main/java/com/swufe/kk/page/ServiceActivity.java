package com.swufe.kk.page;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
      ImageButton btn4=findViewById(R.id.btn_party4);
      btn4.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              Intent intent=new Intent(ServiceActivity.this,Party4Activity.class);
              startActivity(intent);
          }
        });

    }
}
