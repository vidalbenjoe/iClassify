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


public class FirstFragment extends Fragment{
	public Button skipbt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_frag, container, false);

       
        TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
        tv.setText(getArguments().getString("msg"));
        
        
   skipbt = (Button) v.findViewById(R.id.viewpagerskip);

   skipbt.setOnClickListener(new View.OnClickListener()
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

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
