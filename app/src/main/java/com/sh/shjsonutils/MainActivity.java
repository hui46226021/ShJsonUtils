package com.sh.shjsonutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sh.shjson.JSONUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonStr ="{\"people\":[{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"},{\"firstName\":\"Jason\",\"lastName\":\"Hunter\",\"email\":\"bbbb\"},{\"firstName\":\"Elliotte\",\"lastName\":\"Harold\",\"email\":\"cccc\"}],\"data\":{\"people\":[{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"},{\"firstName\":\"Jason\",\"lastName\":\"Hunter\",\"email\":\"bbbb\"},{\"firstName\":\"Elliotte\",\"lastName\":\"Harold\",\"email\":\"cccc\"}],\"animal\":\"cat\",\"look\":{\"head\":\"1个\",\"tail\":\"1条\",\"leg\":4}}}";
        jsonStr = jsonStr.replace(" ","");
        JSONUtil jsonUtil = new JSONUtil(jsonStr);
        List<People> peoples =jsonUtil.getList(People.class,"people");
        Log.e("MainActivity",peoples.toString());
        List<People> peoples2 =jsonUtil.getList(People.class,"data.people");
        Log.e("MainActivity",peoples2.toString());
        String animal = jsonUtil.getObject(String.class,"data.animal");
        Log.e("MainActivity",animal);
        Look look = jsonUtil.getObject(Look.class,"data.look");
        Log.e("MainActivity",look.toString());
    }
}
