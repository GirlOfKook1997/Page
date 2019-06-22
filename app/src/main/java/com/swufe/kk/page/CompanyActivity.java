package com.swufe.kk.page;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyActivity extends ListActivity implements AdapterView.OnItemLongClickListener,Runnable {
    Handler handler;
    private List<HashMap<String,Object>> listItems; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter; // 适配器
    private String TAG="Com";
    private int msgWhat=7;
    Resources com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_company

       initListView();
       this.setListAdapter(listItemAdapter);

        Thread t=new Thread(this);
        t.start();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==7){
                   listItems=(List<HashMap<String,Object>>)msg.obj;
                    Log.i(TAG,"run="+listItems);
                    listItemAdapter = new SimpleAdapter(CompanyActivity.this, listItems, // listItems数据源
                            R.layout.activity_list_item, // ListItem的XML布局实现
                            new String[] { "tab","Title","pic","com","detail","Time" },
                            new int[] { R.id.tab,R.id.Title,R.id.pic,R.id.com,R.id.detail,R.id.time}
                    );//时间延迟时显示的文字内容
                    setListAdapter(listItemAdapter); //继承于ListActivity才能调用

                }
            }
        };
        getListView().setOnItemLongClickListener(this);
    }


    private void initListView() {

        int[] pic={R.mipmap.com_pic1,R.mipmap.com_pic2,R.mipmap.com_pic3};
        String[] tit={this.getString(R.string.com1),this.getString(R.string.com2),this.getString(R.string.com3)};
        String[] com={this.getString(R.string.com1_intro),this.getString(R.string.com2_intro),this.getString(R.string.com3_intro)};
        String[] det={this.getString(R.string.com1_intro_1),this.getString(R.string.com2_intro_1),this.getString(R.string.com3_intro_1)};
        String[] time={this.getString(R.string.time1),this.getString(R.string.time2),this.getString(R.string.time3)};

        listItems =new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <3; i++) {
            HashMap<String,Object> map=new HashMap<>();
            map.put("tab",R.id.tab);
            map.put("Title",tit[i]);
            map.put("pic",pic[i]);
            map.put("com",com[i]);
            map.put("detail",det[i]);
            map.put("Time",time[i]);
            listItems.add(map);
        }

        // 生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems, // listItems数据源
                R.layout.activity_list_item, // ListItem的XML布局实现
                new String[] { "tab","Title","pic","com","detail","Time" },
                new int[] { R.id.tab,R.id.Title,R.id.pic,R.id.com,R.id.detail,R.id.time}
        );

    }




    @Override
    public void run() {
        int[] pic={R.mipmap.com_pic1,R.mipmap.com_pic2,R.mipmap.com_pic3};
        String[] tit={this.getString(R.string.com1),this.getString(R.string.com2),this.getString(R.string.com3)};
        String[] com={this.getString(R.string.com1_intro),this.getString(R.string.com2_intro),this.getString(R.string.com3_intro)};
        String[] det={this.getString(R.string.com1_intro_1),this.getString(R.string.com2_intro_1),this.getString(R.string.com3_intro_1)};
        String[] time={this.getString(R.string.time1),this.getString(R.string.time2),this.getString(R.string.time3)};

        listItems =new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <3; i++) {
            HashMap<String,Object> map=new HashMap<>();
            map.put("tab",R.id.tab);
            map.put("Title",tit[i]);
            map.put("pic",pic[i]);
            map.put("com",com[i]);
            map.put("detail",det[i]);
            map.put("Time",time[i]);
            listItems.add(map);
        }


        /*/ 生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems, // listItems数据源
                R.layout.activity_list_item, // ListItem的XML布局实现
                new String[] { "tab","Title","pic","com","detail","Time" },
                new int[] { R.id.tab,R.id.Title,R.id.pic,R.id.com,R.id.detail,R.id.time}
        ); */
        Message msg=handler.obtainMessage(7);
        msg.obj=listItems;
        handler.sendMessage(msg);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前招聘信息").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listItems.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();


        return false;
    }
}
