package com.swufe.kk.page;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageButton btn_src=findViewById(R.id.btnsrc);
        btn_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent=new Intent(Main2Activity.this,CompanyActivity.class);
                  startActivity(intent);
            }
        });
        ImageButton btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,MoreActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_ser=findViewById(R.id.btn3);
        btn_ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,ServiceActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_icon=findViewById(R.id.btn4);
        btn_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Main2Activity.this,IconActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_card=findViewById(R.id.btn5);
        btn_card.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Main2Activity.this,CardActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_news=findViewById(R.id.btn7);
        btn_news.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Main2Activity.this,NewsActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_more=findViewById(R.id.btn9);
        btn_more.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Main2Activity.this,MoreActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btn6=findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Main2Activity.this,MoreActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn7=findViewById(R.id.btn8);
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Main2Activity.this,MoreActivity.class);
                startActivity(intent);
            }
        });

        Thread t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {

    }
}
