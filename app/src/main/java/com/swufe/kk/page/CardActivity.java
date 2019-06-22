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
        Thread t=new Thread();
        t.start();
    }

    @Override
    public void run() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://10.64.182.31:3306/data1","root","8080");
            System.out.println("连接成功");
            Log.i(TAG,"run:=连接成功");
            //Statement sql=con.createStatement();
            // ResultSet rs=sql.executeQuery("select * from user");
            //Log.i(TAG,"run:1="+rs.getInt(1));
            //Log.i(TAG,"run:2="+rs.getString(2));
            //Log.i(TAG,"run:3="+rs.getString(3));
            //Log.i(TAG,"run:4="+rs.getString(4));
            //PreparedStatement ps=con.prepareStatement("insert into user values(?,?,?,?)");
            //ResultSet rs=ps.executeQuery();
                   /* ps.setInt(1,stuId);
                    ps.setString(2,name);
                    ps.setString(3,email);
                    ps.setString(4,add);
                    ps.executeUpdate();
*/
        } catch (ClassNotFoundException e) {
            Log.i(TAG,"run:=连接不成功");
            e.printStackTrace();
        } catch (SQLException e) {
            Log.i(TAG,"run:failed");
            e.printStackTrace();
        }


    }
}
