package com.swufe.kk.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardActivity extends AppCompatActivity {
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=inp1.getText().toString();
                int stuId=Integer.parseInt(inp2.getText().toString());
                String email=inp3.getText().toString();
                String add=inp4.getText().toString();
                Log.i(TAG, String.valueOf(stuId));
                Log.i(TAG,name);
                Log.i(TAG,email);
                Log.i(TAG,add);
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://10.63.152.182:3306/data1","root","8080");
                    PreparedStatement ps=con.prepareStatement("insert into user values(?,?,?,?)");
                    ResultSet rs=ps.executeQuery();
                    ps.setInt(1,stuId);
                    ps.setString(2,name);
                    ps.setString(3,email);
                    ps.setString(4,add);
                    ps.executeUpdate();

                    con.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
