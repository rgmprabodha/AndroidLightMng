package com.example.section3;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class ContextManagementActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    public static Handler uiHandler;
    public static RequestQueue queue;
    private String room;
    public static RoomContextState state;
    public static ArrayList<RoomObject> roomArray;
    public Context aContext;
    private Spinner spin;
    private ListView listLights;
    private ArrayList<RoomContextState> lightArray;
    private ListAdapter adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aContext = getApplicationContext();
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
        roomArray = new ArrayList<RoomObject>();
        lightArray = new ArrayList<RoomContextState>();
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        listLights = (ListView) findViewById(R.id.listLights);
        adapterList = new ListAdapter(lightArray, ContextManagementActivity.this);
        listLights.setAdapter(adapterList);
        loadRooms();
        ((Button) findViewById(R.id.btnAddRoom)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(ContextManagementActivity.this, AddRoomActivity.class);
                startActivity(in);
            }
        });

        ((Button) findViewById(R.id.btnAddLight)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(ContextManagementActivity.this, AddLightActivity.class);
                startActivity(in);
            }
        });
    }

    public void clickLightOnOffSwitch(RoomContextState lightPosition){
               //call bulb on off method and reload table by loading room light again
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
        RoomObject roomObj = roomArray.get(position);

//        RoomContextHttpManager mySingleton = RoomContextHttpManager.getInstance();
//        mySingleton.retrieveRoomContextState(roomObj.getRoomId()+"");
//
//        uiHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 1:
//                        onUpdate(state);
//
//                        break;
//                }
//            }
//        };

        //temporary create a array because cant return arry from reponse TO DO to malshani
        lightArray.clear();
        RoomContextState obj1 = new RoomContextState(-4+"","OFF",0,0);
        RoomContextState obj2 = new RoomContextState(-2+"","ON",0,0);
        lightArray.add(obj1);
        lightArray.add(obj2);
        adapterList = new ListAdapter(lightArray, ContextManagementActivity.this);
        listLights.setAdapter(adapterList);

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private void loadRooms() {
//        RoomContextHttpManager mySingleton = RoomContextHttpManager.getInstance();
//        mySingleton.loadRooms();
        RoomObject obj1 = new RoomObject(-4,"room1","1");
        RoomObject obj2 = new RoomObject(-2,"room2","1");
        roomArray.add(obj1);
        roomArray.add(obj2);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,roomArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initView() {
    }

    public void updateContextView() {
        ImageView img = (ImageView) findViewById(R.id.imageView1);
        if (this.state != null) {
//            ((TextView) findViewById(R.id.textViewLightValue)).setText(Integer
//                    .toString(state.getLight()));
//            ((TextView) findViewById(R.id.textViewNoiseValue)).setText(Float
//                    .toString(state.getNoise()));
//            if (state.getStatus().equals("ON")) {
//                img.setImageResource(R.drawable.ic_bulb_on);
//            } else {
//                img.setImageResource(R.drawable.ic_bulb_off);
//            }
        } else {
            initView();
        }
    }

    public void onUpdate(RoomContextState state) {
        updateContextView();
    }

    public void switchLight(View view) {
        RoomContextHttpManager.switchLight(state, room);
        RoomContextHttpManager.retrieveRoomContextState(room);
    }

    public void switchRinger(View view) {
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int mode = audioManager.getRingerMode();
        if (mode == AudioManager.RINGER_MODE_SILENT)
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        else {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
    }
}