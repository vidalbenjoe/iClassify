package com.capstoneii.iclassify.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.capstoneii.iclassify.ActivityAnimator;
import com.capstoneii.iclassify.R;

import drawer.MainDrawerActivity;


public class FifthFragment extends Fragment {
public Button startbuttoncircle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fifth_fragment, container, false);

        TextView tv = (TextView) v.findViewById(R.id.tvFragFifth);
        tv.setText(getArguments().getString("msg"));

        startbuttoncircle = (Button) v.findViewById(R.id.startbuttoncircles);
        
        startbuttoncircle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
            	// Start main activity
            	 Intent intent = new Intent(getActivity(), MainDrawerActivity.class);
                 getActivity().startActivity(intent);
                 getActivity().finish();
            
                try
              	{
              	ActivityAnimator anim = new ActivityAnimator();
              	anim.getClass().getMethod("Animation", Activity.class).invoke(anim, this);
              	}
              	catch (Exception e) {
              		
              	}
              	 
            }
        });
        
        return v;
    }

    public static FifthFragment newInstance(String text) {

    	FifthFragment f = new FifthFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}