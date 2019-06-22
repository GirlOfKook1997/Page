package com.swufe.kk.page;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyActivity extends ListActivity {
    Handler handler;
    private ArrayList<HashMap<String,Object>> listItems; // 存放文字、图片信息
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



}
