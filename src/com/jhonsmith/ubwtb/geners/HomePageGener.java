package com.jhonsmith.ubwtb.geners;

import com.jhonsmith.ubwtb.BarListFragment;
import com.jhonsmith.ubwtb.MessagePageFragment;
import com.jhonsmith.ubwtb.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomePageGener {
	private HomePageGener mInstance;
	private Context mContext;
	private LayoutInflater mInflater;
	private DisplayMetrics mDm;
	private FragmentManager mFm;
	private View mContentView;
	private RelativeLayout mMrl;
	private Fragment mBarListFragment;
	private Fragment mMessagePageFragment;
	private LinearLayout mLl;
	private HomePageGener getInstance(){
		return mInstance;
	}
	public View getView(){
		if(mContentView.getParent()!=null)((ViewGroup) mContentView.getParent()).removeView(mContentView);
		return mContentView;
	}
	public HomePageGener(Context context){
		this.mContext=context;
		this.mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mDm=mContext.getResources().getDisplayMetrics();
		this.mFm=((FragmentActivity)mContext).getSupportFragmentManager();
		this.mContentView=mInflater.inflate(R.layout.homepage_portrait, null);
		this.mMrl=(RelativeLayout) mContentView.findViewById(R.id.mrl);
		this.mLl=(LinearLayout)mMrl.findViewById(R.id.ll);
	}
	public HomePageGener init(HomePageGener instance){
		this.mInstance=instance;
		setFragment();
		return mInstance;
	}
	private void setFragment(){
		FragmentTransaction transaction =mFm.beginTransaction();
		mBarListFragment=new BarListFragment(mContext);
		transaction.replace(R.id.fl, mBarListFragment,BarListFragment.FRAGMENT_TAG);
		transaction.commit();
		//test
		Button btn2=(Button) mLl.findViewById(R.id.llbtn2);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction =mFm.beginTransaction();
				mMessagePageFragment=new MessagePageFragment();
				transaction.replace(R.id.fl, mMessagePageFragment,MessagePageFragment.FRAGMENT_TAG);
				transaction.commit();
			}
		});
	}
}
