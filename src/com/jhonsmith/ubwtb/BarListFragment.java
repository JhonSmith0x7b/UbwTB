package com.jhonsmith.ubwtb;

import com.jhonsmith.ubwtb.geners.BarListGener;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public  class BarListFragment extends Fragment{
	public static final String FRAGMENT_TAG="barlistfragment";
	private Context mContext;
	public BarListFragment(){
		
	}
	public BarListFragment (Context context){
		this.mContext=context;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		this.setRetainInstance(true);
		BarListGener blg=new BarListGener(mContext);
		View view=blg.init(blg).getView();
		return view;
	}
	
}
