package com.jhonsmith.ubwtb;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.MenuDrawer.OnDrawerStateChangeListener;
import net.simonvt.menudrawer.Position;

import com.jhonsmith.ubwtb.geners.BarListGener;
import com.jhonsmith.ubwtb.geners.LeftMenuGener;
import com.jhonsmith.ubwtb.myviews.LeftMenuAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

//bar  list 
public class BarListActivity extends Activity{
	protected MenuDrawer mMenuDrawer;
	private View mContentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMenuDrawer=MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.START, MenuDrawer.MENU_DRAG_CONTENT);
		LeftMenuGener lmg=new LeftMenuGener(this);
		mMenuDrawer.setMenuView(lmg.init(lmg).getView());
		BarListGener blg=new BarListGener(this);
		this.mContentView =blg.init(blg).getView();
		mMenuDrawer.setContentView(mContentView);
		mMenuDrawer.setOnDrawerStateChangeListener(new OnDrawerStateChangeListener() {
			
			@Override
			public void onDrawerStateChange(int oldState, int newState) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onDrawerSlide(float openRatio, int offsetPixels) {
				changeContentViewHeight(mContentView,openRatio);
			}
		});
		mMenuDrawer.setBackgroundResource(R.drawable.leftmenu_background);
		mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	private void changeContentViewHeight(View contentView,float openRatio){
		contentView.setScaleX(1f-openRatio/8);
		contentView.setScaleY(1f-openRatio/8);
	}
	
}
