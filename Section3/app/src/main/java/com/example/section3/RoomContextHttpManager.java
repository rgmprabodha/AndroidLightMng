package com.example.section3;

import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class RoomContextHttpManager {

    static RoomContextHttpManager instance;

    public static synchronized RoomContextHttpManager getInstance() {
        if (instance == null)
            instance = new RoomContextHttpManager();
        return instance;
    }

    public static void switchLight(RoomContextState state, String room) {
        final String roomId = room;
        String url = "http://thawing-journey-78988.herokuapp.com/api/rooms/" + roomId + "/switch-light-and-list";
        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jo = jsonArray.getJSONObject(0);
                            String id = jo.getString("id").toString();
                            int lightLevel = Integer.parseInt(jo.getJSONObject("light").get("level").toString());

                            String lightStatus = jo.getJSONObject("light").get("status").toString();
                            float noise = Float.parseFloat(jo.getJSONObject("noise").get("level").toString());
                            ContextManagementActivity.state = new RoomContextState(id, lightStatus, lightLevel, noise);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("room", roomId);
                return params;
            }
        };
        ContextManagementActivity.queue.add(putRequest);
    }

    public static void retrieveRoomContextState(String r) {
        String url = "https://thawing-journey-78988.herokuapp.com/api/rooms/" + r + "/";
        Log.d("Room is ", url);

//        JsonObjectRequest contextRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//
//                    @Override
//                    public void onResponse(JSONArray responseArray) {
//                        try {
//                            ArrayList<RoomContextState> rmArray = new ArrayList<RoomContextState>();
//                            for (int i = 0; i < responseArray.length() ; i++) {
//                                JSONObject response = responseArray.getJSONObject(i);
//                                String id = response.getString("id").toString();
//                                int lightLevel = Integer.parseInt(response.getJSONObject("light").get("level").toString());
//                                String lightStatus = response.getJSONObject("light").get("status").toString();
//                                float noise = Float.parseFloat(response.getJSONObject("noise").get("level").toString());
//                                RoomContextState rm = new RoomContextState(id, lightStatus, lightLevel, noise);
//                                ContextManagementActivity.state.add(rm);
//                            }
//
//                            //ContextManagementActivity.state = rmArray;
//                            Message msg = new Message();
//                            if (ContextManagementActivity.state != null) {
//                                msg.what = 1;
//                                ContextManagementActivity.uiHandler.sendMessage(msg);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                });
 //       ContextManagementActivity.queue.add(contextRequest);
    }

    public static void loadRooms() {
//        String url = "https://faircorp-emse.cleverapps.io/api/rooms";
//        Log.d("Room is ", url);
//
//        JsonObjectRequest contextRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            ArrayList<RoomObject> roomArray = new ArrayList<RoomObject>();
//                            for (int i = 0; i < response.length(); i++) {
//                                RoomObject obj = new RoomObject();
//                                JSONObject responseObj = response.getJSONObject(i);
//                                obj.setRoomId(responseObj.getInt("id"));
//                                obj.setRoomName(responseObj.getString("name"));
//                                obj.setFloorId(responseObj.getString("floor"));
//                                roomArray.add(obj);
//                                ContextManagementActivity.roomArray  = "" ;
//
//                            }
//                            Message msg = new Message();
//                            if (ContextManagementActivity.roomArray != null) {
//                                msg.what = 1;
//                                ContextManagementActivity.uiHandler.sendMessage(msg);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                });
//        ContextManagementActivity.queue.add(contextRequest);

    }
}
