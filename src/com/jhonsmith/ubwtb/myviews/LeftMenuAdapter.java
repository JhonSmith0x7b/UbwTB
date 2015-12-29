package com.jhonsmith.ubwtb.myviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LeftMenuAdapter extends BaseAdapter{
	private Context mContext;
	public LeftMenuAdapter(Context context){
		this.mContext=context;
	}
	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv =new TextView(mContext);
		tv.setText("123");
		tv.setWidth(48);
		tv.setHeight(48);
		return tv;
	}

}
