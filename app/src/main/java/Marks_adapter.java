package com.example.najss.javatp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Marks_adapter extends ArrayAdapter<Marks> {

    private Context mcontext;
    private LayoutInflater inflater;
    private ArrayList<Marks> marks;
    int mResource;
    public Marks_adapter( Context context,   ArrayList<Marks> objects) {
        super(context, 0, objects);
        this.marks=objects;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mcontext=context;
        //mResource=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // super.getView(position, convertView, parent);
        int t,c,w;
        String s,q;
        //Marks mark=marks.get(position);
        convertView=inflater.inflate(mResource,null);
        TextView textView1=(TextView)convertView.findViewById(R.id.textView31);
        TextView textView2=(TextView)convertView.findViewById(R.id.textView32);
        TextView textView3=(TextView)convertView.findViewById(R.id.textView33);
        TextView textView4=(TextView)convertView.findViewById(R.id.textView34);
        textView1.setText("5485");
        textView2.setText("5454");
        textView3.setText("56566");
        textView4.setText("6545");
        return convertView;
    }
}
