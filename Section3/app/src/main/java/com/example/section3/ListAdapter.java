package com.example.section3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;


import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<RoomContextState> implements View.OnClickListener{

    private ArrayList<RoomContextState> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView textViewLightValue;
        ImageView imageView1;
        RelativeLayout layout;
        Switch switchLightOnOff;

    }

    public ListAdapter(ArrayList<RoomContextState> data, Context context) {
        super(context, R.layout.list_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        RoomContextState dataModel=(RoomContextState)object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final RoomContextState state = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.layout);
            viewHolder.textViewLightValue = (TextView) convertView.findViewById(R.id.textViewLightValue);
            viewHolder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
            viewHolder.switchLightOnOff = (Switch) convertView.findViewById(R.id.switchLightOnOff);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.textViewLightValue.setText(state.getLight()+"");
        if (state.getStatus().equals("ON")) {
            viewHolder.imageView1.setImageResource(R.drawable.ic_bulb_on);
            viewHolder.switchLightOnOff.setChecked(true);
        } else {
            viewHolder.imageView1.setImageResource(R.drawable.ic_bulb_off);
            viewHolder.switchLightOnOff.setChecked(false);

        }
//        viewHolder.txtName.setText(dataModel.getName());
//        Node startNode = dataModel.getStartNode();
//        viewHolder.startNode.setText("start position : ("+startNode.getX()+","+startNode.getY()+")");
//        Node endNode = dataModel.getEndNode();
//        viewHolder.endNode.setText("end position : ("+endNode.getX()+","+endNode.getY()+")");
//        viewHolder.layout.setBackgroundColor(dataModel.getColor());
//        viewHolder.btnDelete.setOnClickListener (new View.OnClickListener() {
//            @Override
//            public void onClick ( View v ) {
//
//                ((GridRobotAddActivity)mContext).updateGridAndRobotList(position);
//            }
//        });
        // Return the completed view to render on screen

        viewHolder.switchLightOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((ContextManagementActivity)mContext).clickLightOnOffSwitch(state);
            }

        });
        return convertView;
    }
}