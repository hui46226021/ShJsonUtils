package com.sh.zsh.code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sh.shjson.JSONUtil;
import com.sh.shjsonutils.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonStr ="{\n" +
                "    \"people\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Brett\",\n" +
                "            \"lastName\": \"McLaughlin\",\n" +
                "            \"email\": \"aaaa\",\n" +
                "            \"look\": [\n" +
                "                {\n" +
                "                    \"head\": \"1个\",\n" +
                "                    \"tail\": \"1条\",\n" +
                "                    \"leg\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"head\": \"2个\",\n" +
                "                    \"tail\": \"2条\",\n" +
                "                    \"leg\": 5\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jason\",\n" +
                "            \"lastName\": \"Hunter\",\n" +
                "            \"email\": \"bbbb\",\n" +
                "            \"look\": [\n" +
                "                {\n" +
                "                    \"head\": \"1个\",\n" +
                "                    \"tail\": \"1条\",\n" +
                "                    \"leg\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"head\": \"2个\",\n" +
                "                    \"tail\": \"2条\",\n" +
                "                    \"leg\": 5\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Elliotte\",\n" +
                "            \"lastName\": \"Harold\",\n" +
                "            \"email\": \"cccc\",\n" +
                "            \"look\": [\n" +
                "                {\n" +
                "                    \"head\": \"1个\",\n" +
                "                    \"tail\": \"1条\",\n" +
                "                    \"leg\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"head\": \"2个\",\n" +
                "                    \"tail\": \"2条\",\n" +
                "                    \"leg\": 5\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"data\": {\n" +
                "        \"people\": [\n" +
                "            {\n" +
                "                \"firstName\": \"Brett\",\n" +
                "                \"lastName\": \"McLaughlin\",\n" +
                "                \"email\": \"aaaa\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"firstName\": \"Jason\",\n" +
                "                \"lastName\": \"Hunter\",\n" +
                "                \"email\": \"bbbb\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"firstName\": \"Elliotte\",\n" +
                "                \"lastName\": \"Harold\",\n" +
                "                \"email\": \"cccc\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"animal\": \"cat\",\n" +
                "        \"look\": {\n" +
                "            \"head\": \"1个\",\n" +
                "            \"tail\": \"1条\",\n" +
                "            \"leg\": 4\n" +
                "        }\n" +
                "    }\n" +
                "}";
        jsonStr = jsonStr.replace("\n","");
        jsonStr = jsonStr.replace(" ","");
        JSONUtil jsonUtil = new JSONUtil(jsonStr);
        List<People> peoples =jsonUtil.getList(People.class,Look.class,"people");
        Log.e("MainActivity",peoples.toString());
        List<People> peoples2 =jsonUtil.getList(People.class,"data.people");
        Log.e("MainActivity",peoples2.toString());
        String animal = jsonUtil.getObject(String.class,"data.animal");
        Log.e("MainActivity",animal);
        Look look = jsonUtil.getObject(Look.class,"data.look");
        Log.e("MainActivity",look.toString());
    }
}
