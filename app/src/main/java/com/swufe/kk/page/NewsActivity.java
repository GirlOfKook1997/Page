package com.swufe.kk.page;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewsActivity extends ListActivity implements Runnable,AdapterView.OnItemLongClickListener {
    Handler handler;
    private String TAG="News";
    private int msgWhat = 7;
    private String update="2019-04-30";
    private String todayStr;
    ArrayAdapter adapter;
    List<String> list2;
    List<String> retList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_news);
       /* if(!todayStr.equals(update)){
            Log.i(TAG,"需要更新");
        }else{
            Log.i(TAG,"不需要更新");
        }*/
        Date today=Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        todayStr=sdf.format(today);
        Log.i(TAG,"news:todayStr="+todayStr);

        Thread t=new Thread(this);
        t.start();

       handler=new Handler(){
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);
               if(msg.what==7){

                   list2= (List<String>) msg.obj;
                   Log.i(TAG,"run="+list2);
                   adapter=new ArrayAdapter<String>(NewsActivity.this,android.R.layout.simple_list_item_1,retList);//时间延迟时显示的文字内容
                   setListAdapter(adapter); //继承于ListActivity才能调用
                   /*SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString("update_date",todayStr);
                   editor.apply();
                   */
                   //Log.i(TAG,"update_date"+todayStr);
               }
           }
       };
       getListView().setOnItemLongClickListener(this);
    }


    @Override
    public void run() {
        retList=new ArrayList<String>();
        try {

            //Thread.sleep(1000);
            Document doc= Jsoup.connect("https://www.swufe.edu.cn/1456.html").get();
            Log.i(TAG,"run="+doc.title());
            Elements tables=doc.getElementsByTag("table");
            int i;
           /*for(i=3;i<=20;i++){
                Element table=tables.get(i);
                Log.i(TAG,"run:table["+i+"]="+table);
            }*/
          /* for(Element table:tables){
                  Log.i(TAG,"run:table["+i+"]="+table);
                  i++;
           }*/
            Element table4=tables.get(3);
            // Log.i(TAG,"run:=table[4]"+table4);
            Elements tds=table4.getElementsByTag("td");
            Log.i(TAG,"run:tds.size()="+tds.size());
            for(int k=1;k<84;k+=3){
                Element td2=tds.get(k+1);
                Element td1=tds.get(k+2);
                Log.i(TAG,"run:content="+td2+" "+td1);
                String content=td2.text();
                String time=td1.text();
                retList.add(content+"   "+time);

                //写两个循环
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(7);
        msg.obj=retList;
        handler.sendMessage(msg);

    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前新闻").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.remove(parent.getItemAtPosition(position));
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();

        //retList.remove(position);
    return false;

    }

}
