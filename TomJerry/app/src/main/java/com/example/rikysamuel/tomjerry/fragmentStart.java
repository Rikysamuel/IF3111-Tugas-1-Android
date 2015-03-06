package com.example.rikysamuel.tomjerry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rikysamuel on 3/6/2015.
 */
public class fragmentStart extends Fragment {
    private static final int REQUEST_CODE = 10;


    private double latitude;
    private double longitude;
    private int valid_until;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start_layout, container, false);

        // spikeOnClick
        ImageButton spike = (ImageButton) v.findViewById(R.id.imageButton); // you have to use rootview object..
        spike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "HTTP Request", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(),ActivityHTTP.class);
                startActivityForResult(i, REQUEST_CODE);
                TextView t = (TextView) getActivity().findViewById(R.id.textView);
                t.setText("HTTP Request......");
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            int lon; int lat; int val;
            if (data.hasExtra("long")) {
                longitude = data.getExtras().getDouble("long");
            }
            if (data.hasExtra("lat")){
                latitude = data.getExtras().getDouble("lat");
            }
            if (data.hasExtra("val")){
                valid_until = data.getExtras().getInt("val");
            }

            TextView t = (TextView) getActivity().findViewById(R.id.textView);
            t.setText("long: " + longitude + " lat: " + latitude + " val: " + valid_until);
        }
    }
}