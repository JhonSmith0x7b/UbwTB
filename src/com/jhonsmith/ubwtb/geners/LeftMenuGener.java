package com.jhonsmith.ubwtb.geners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.jhonsmith.ubwtb.R;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LeftMenuGener {
	private  LeftMenuGener mInstance;
	private Context mContext;
	private LayoutInflater mInflater;
	private View mContentView;
	private RelativeLayout mRl;
	private DisplayMetrics mDm;
	private ListView mLv;
	private LinearLayout mLl;
	private RelativeLayout mRl1;
	//test data
	private static final String LEFTMENU_DATA="{'leftmenu_data':[{'id':'1','position':'1','name':'我的好友','action':'http://www.baidu.com'},{'id':'2','position':'2','name':'我的收藏','action':'http://www.baidu.com'},{'id':'3','position':'3','name':'浏览历史','action':'http://www.baidu.com'},{'id':'4','position':'4','name':'账号管理','action':'http://www.baidu.com'},{'id':'5','position':'5','name':'退出贴吧','action':'http://www.baidu.com'}]}";
	private List<LeftMenuHolder> leftMenuList;
	private LeftMenuGener getInstance(){
		return mInstance;
	}
	public View getView(){
		if(mContentView.getParent()!=null)((ViewGroup)mContentView.getParent()).removeView(mContentView);
		return mContentView;
	}
	public LeftMenuGener(Context context){
		this.mContext=context;
		this.mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mDm=mContext.getResources().getDisplayMetrics();
		this.mContentView=mInflater.inflate(R.layout.leftmenu_portrait, null);
		this.mRl=(RelativeLayout) mContentView.findViewById(R.id.rl);
		this.mLl=(LinearLayout) mRl.findViewById(R.id.ll);
		this.mRl1=(RelativeLayout) mContentView.findViewById(R.id.rl1);
	}
	public LeftMenuGener init(LeftMenuGener instance){
		this.mInstance=instance;
		setListView(LEFTMENU_DATA);
		return mInstance;
	}
	private void setListView(String leftMenuData){
		mLv=(ListView) mRl.findViewById(R.id.lv);
		leftMenuList=new ArrayList<LeftMenuHolder>();
		Gson gson=new Gson();
		try {
			JSONObject jObject=new JSONObject(leftMenuData);
			JSONArray jArray=jObject.getJSONArray("leftmenu_data");
			List<LeftMenuHolder> leftMenuListOriginal=new ArrayList<LeftMenuHolder>();
			for(int i=0;i<jArray.length();i++){
				LeftMenuHolder lmg=new LeftMenuHolder();
				lmg=gson.fromJson(jArray.getString(i), LeftMenuHolder.class);
				leftMenuListOriginal.add(lmg);
			}
			leftMenuList=leftMenuListOriginal;
			for(int i=0;i<leftMenuListOriginal.size()-1;i++){
				for(int j=1;j<leftMenuListOriginal.size();j++){
					String postionI=leftMenuListOriginal.get(i).position;
					String postionJ=leftMenuListOriginal.get(j).position;
					if(Integer.parseInt(postionJ)<Integer.parseInt(postionI)){
						LeftMenuHolder temp=leftMenuListOriginal.get(i);
						leftMenuListOriginal.set(i, leftMenuListOriginal.get(j));
						leftMenuListOriginal.set(j, temp);
					}
				}
			}
			leftMenuList=leftMenuListOriginal;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(leftMenuList.size()==0){
			return;
		}
		mLv.setAdapter(new MyAdapter(leftMenuList));
		mLv.getLayoutParams().height=mDm.heightPixels-mRl1.getLayoutParams().height-mLl.getLayoutParams().height;
	}
	public class LeftMenuHolder{
		String id;
		String position;
		String name;
		String action;
	}
	public class MyAdapter extends BaseAdapter{
		private List<LeftMenuHolder>leftMenuList=new ArrayList<LeftMenuHolder>();
		public class ElementHolder{
			TextView tv;
		}
		public MyAdapter(List<LeftMenuHolder>leftMeunHolder){
			this.leftMenuList=leftMeunHolder;
		}
		@Override
		public int getCount() {
			return leftMenuList.size();
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
			ElementHolder holder;
			if(convertView!=null){
				holder=(ElementHolder) convertView.getTag();
			}else{
				convertView=mInflater.inflate(R.layout.leftmenu_list_portrait, null);
				holder=new ElementHolder();
				holder.tv=(TextView) convertView.findViewById(R.id.tv);
				convertView.setTag(holder);
			}
			holder.tv.setText(leftMenuList.get(position).name);
			return convertView;
		}
		
	}
}
