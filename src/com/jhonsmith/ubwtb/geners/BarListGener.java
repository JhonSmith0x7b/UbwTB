package com.jhonsmith.ubwtb.geners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.jhonsmith.ubwtb.R;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BarListGener {
	private BarListGener mInstance;
	private Context mContext;
	private DisplayMetrics mDm;
	private View mContentView;
	private RelativeLayout mMrl;
	private LayoutInflater mInflater;
	//test data
	public static final String BAR_DATA="{'bar_data':[{'name':'clannad','level':'8','action':'http://www.baidu.com'},{'name':'clannad','level':'8','action':'http://www.baidu.com'},{'name':'clannad1','level':'0','action':'http://www.baidu.com'},{'name':'clannad2','level':'1','action':'http://www.baidu.com'},{'name':'clannad3','level':'2','action':'http://www.baidu.com'},{'name':'clannad4','level':'3','action':'http://www.baidu.com'},{'name':'clannad5','level':'4','action':'http://www.baidu.com'},{'name':'clannad6','level':'5','action':'http://www.baidu.com'},{'name':'clannad7','level':'6','action':'http://www.baidu.com'},{'name':'clannad8','level':'7','action':'http://www.baidu.com'},{'name':'clannad9','level':'8','action':'http://www.baidu.com'},{'name':'clannad0','level':'9','action':'http://www.baidu.com'},{'name':'clannad01','level':'10','action':'http://www.baidu.com'},{'name':'clannad02','level':'11','action':'http://www.baidu.com'},{'name':'clannad03','level':'12','action':'http://www.baidu.com'},{'name':'clannad04','level':'13','action':'http://www.baidu.com'},{'name':'clannad05','level':'14','action':'http://www.baidu.com'}]}";
	private GridView mGv;
	//bar icon switch 0 off 1 on 
	private int mBarIconTragger=1;
	private BarListGener getInstance(Context context){
		return mInstance;
	}
	public View getView(){
		if(mContentView.getParent()!=null)((ViewGroup)mContentView.getParent()).removeView(mContentView);
		return mContentView;
	}
	public BarListGener(Context context){
		this.mContext=context;
		this.mDm=((Activity)mContext).getResources().getDisplayMetrics();
		this.mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContentView=mInflater.inflate(R.layout.barlist_portrait, null);
		this.mMrl=(RelativeLayout) mContentView.findViewById(R.id.mrl);
	}
	public BarListGener init(BarListGener blg){
		this.mInstance=blg;
		setGrid();
		return this.mInstance;
	}
	private void setGrid(){
		mGv=(GridView) mMrl.findViewById(R.id.gv);
		List<BarDataHolder>barDataList =new ArrayList<BarDataHolder>();
		try {
			Gson gson=new Gson();
			JSONObject jObject=new JSONObject(BarListGener.BAR_DATA);
			JSONArray jArray=jObject.getJSONArray("bar_data");
			for(int i=0;i<jArray.length();i++){
				BarDataHolder barData=gson.fromJson(jArray.getString(i), BarDataHolder.class);
				barDataList.add(barData);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(barDataList.size()==0){
			Toast.makeText(mContext, "BarData ERROR", Toast.LENGTH_SHORT).show();
			return;
		}
		mGv.setAdapter(new MyAdapter(barDataList));
		if(mBarIconTragger==0){
			mGv.setNumColumns(2);
		}else{
			mGv.setNumColumns(1);
		}
	}
	/**
	 * barData POJO 
	 * @author Administrator
	 *
	 */
	public class BarDataHolder{
		String name;
		String level;
		String action;
	}
	public class MyAdapter extends BaseAdapter{
		List<BarDataHolder> barDataList;
		public MyAdapter(List<BarDataHolder> barDataList){
			this.barDataList=barDataList;;
		}
		@Override
		public int getCount() {
			if(barDataList==null)return 0;
			return barDataList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		public class ElementHolder{
			Button btn;
			TextView tv;
			TextView tv1;
			ImageView img;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(barDataList.size()==0)return null;
			ElementHolder holder;
			if(convertView!=null){
				holder=(ElementHolder) convertView.getTag();
			}else{
				convertView =mInflater.inflate(R.layout.barlist_grid_portrait, null);
				holder=new ElementHolder();
				holder.btn=(Button) convertView.findViewById(R.id.btn);
				holder.tv=(TextView)convertView.findViewById(R.id.tv);
				holder.tv1=(TextView)convertView.findViewById(R.id.tv1);
				holder.img=(ImageView)convertView.findViewById(R.id.img);
				convertView.setTag(holder);
			}
			holder.tv.setText(barDataList.get(position).name);
			int barLevel=0;
			try{
				barLevel=Integer.parseInt(barDataList.get(position).level);
			}catch(Exception e){
				barLevel=0;
			}
			if(barLevel>0){
				if(barLevel<8){
					holder.tv1.setBackgroundColor(0xFF5cc7f1);
				}else if(barLevel<13){
					holder.tv1.setBackgroundColor(0xFFFF8000);
				}else{
					holder.tv1.setBackgroundColor(0xFFFF4500);
				}
			}else{
				holder.tv1.setBackgroundColor(Color.TRANSPARENT);
//				holder.tv1.setVisibility(View.INVISIBLE);
			}
			holder.tv1.setText("LV."+barLevel);
			holder.btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(mBarIconTragger==0){
						mGv.setNumColumns(1);
						mBarIconTragger=1;
						synchronized(mContext){
						mContext.notifyAll();
						}
					}else{
						mGv.setNumColumns(2);
						mBarIconTragger=0;
						synchronized(mContext){
						mContext.notifyAll();
						}
					}
				}
			});
			if(mBarIconTragger==0){
				holder.img.setVisibility(View.GONE);
			}else{
				holder.img.setVisibility(View.VISIBLE);
			}
			//test icon
			if(barDataList.get(position).name.equals("clannad3")){
				holder.img.setBackgroundResource(R.drawable.example_headportrait);
			}else{
				holder.img.setBackgroundResource(R.drawable.baricon_default);
			}
			return convertView;
		}
		
	}
}
