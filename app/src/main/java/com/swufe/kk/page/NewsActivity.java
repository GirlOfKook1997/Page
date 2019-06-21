package com.swufe.kk.page;

import android.app.ListActivity;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.HashMap;

public class NewsActivity extends ListActivity implements Runnable{
    Handler handler;
    private String TAG="News";
    private ArrayList<HashMap<String, String>> listItems; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter; // 适配器
    private int msgWhat = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_news);
        String data[]={"one","two","three"};
        android.widget.ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);//时间延迟时显示的文字内容
        setListAdapter(adapter); //继承于ListActivity才能调用
        Thread t=new Thread(this);
        t.start();
       handler=new Handler(){
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);
               if(msg.what==7){
                   String str=(String) msg.obj;
                   Log.i(TAG,"run="+str);
               }
           }
       };
    }


    @Override
    public void run() {
      for(int i=0;i<=3;i++){
          Log.i(TAG,"run="+i);
          try {
              Thread.sleep(2000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }

      Message msg=handler.obtainMessage(7);
     msg.obj="Hello from run";
     handler.sendMessage(msg);
        try {
            Document doc= Jsoup.connect("https://www.swufe.edu.cn/1456.html").get();
            Log.i(TAG,"run="+doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
