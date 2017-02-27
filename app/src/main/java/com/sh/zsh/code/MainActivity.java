package com.sh.zsh.code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.sh.shjson.JSONUtil;
import com.sh.shjsonutils.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonStr =getJson();
        JSONUtil jsonUtil = new JSONUtil(jsonStr);
        List<People> peoples3 =jsonUtil.getList(People.class,"people",Look.class,En.class);
        Log.e("MainActivity",peoples3.toString());
        List<People> peoples =jsonUtil.getList(People.class,"people",Look.class);
        Log.e("MainActivity",peoples.toString());
        List<People> peoples2 =jsonUtil.getList(People.class,"data.people");
        Log.e("MainActivity",peoples2.toString());
        String animal = jsonUtil.getObject(String.class,"data.animal");
        Log.e("MainActivity",animal);
        Look look = jsonUtil.getObject(Look.class,"data.look");
        Log.e("MainActivity",look.toString());
    }


    public String getJson(){
        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("newdata.json"),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;

            while((line = br.readLine()) != null){
                builder.append(line);
            }
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
