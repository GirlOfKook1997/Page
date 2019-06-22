package com.swufe.kk.page;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.PseudoTextElement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardActivity extends AppCompatActivity implements  Runnable{
   EditText inp1;
   EditText inp2;
   EditText inp3;
   EditText inp4;
   TextView label1;
   TextView label2;
   TextView label3;
   TextView label4;
   Button submit;
   private  String TAG="Card";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        inp1=findViewById(R.id.inp1);
        inp2=findViewById(R.id.inp2);
        inp3=findViewById(R.id.inp3);
        inp4=findViewById(R.id.inp4);
        submit=findViewById(R.id.submit);
        Log.i(TAG,"run:Card");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t=new Thread(CardActivity.this);
                t.start();
                Toast.makeText(CardActivity.this,"保存成功",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://119.3.167.204:3306/data","root","mySQL@2019");
            Log.i(TAG,"run:=连接成功");

            String id=inp2.getText().toString();
            int stuId=Integer.parseInt(id);
            Log.i(TAG,"run:stuId="+stuId);
            String name=inp1.getText().toString();
            Log.i(TAG,"run:name="+name);
            String email=inp3.getText().toString();
            Log.i(TAG,"run:email="+email);
            String add=inp4.getText().toString();
            Log.i(TAG,"run:add="+add);
            PreparedStatement ps=con.prepareStatement("select * from user");
            ResultSet rs=ps.executeQuery();
            rs.last();
                ps=con.prepareStatement("insert into user values(?,?,?,?)");
                ps.setInt(1, stuId);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, add);
                ps.executeUpdate();


        } catch (ClassNotFoundException e) {
            Log.i(TAG,"run:failed1");
            e.printStackTrace();
        } catch (SQLException e) {
            Log.i(TAG,"run:failed2");
            e.printStackTrace();
        }


    }
}
