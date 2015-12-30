package com.jhonsmith.ubwtb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessagePageFragment extends Fragment{
	public static final String FRAGMENT_TAG="messagepagefragment";
	public MessagePageFragment(){
		
	}
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.setRetainInstance(true);
		View view=inflater.inflate(R.layout.messagepage_portrait, null);
		return view;
	}
	
}
