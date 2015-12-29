package com.jhonsmith.ubwtb;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import net.simonvt.menudrawer.MenuDrawer.OnDrawerStateChangeListener;

import com.jhonsmith.ubwtb.geners.HomePageGener;
import com.jhonsmith.ubwtb.geners.LeftMenuGener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class HomePageActivity extends FragmentActivity{
	protected MenuDrawer mMenuDrawer;
	private View mContentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initMenuDrawer();
	}
	private void initMenuDrawer(){
		mMenuDrawer=MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.START, MenuDrawer.MENU_DRAG_CONTENT);
		LeftMenuGener lmg=new LeftMenuGener(this);
		mMenuDrawer.setMenuView(lmg.init(lmg).getView());
		HomePageGener hpg=new HomePageGener(this);
		mContentView=hpg.init(hpg).getView();
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
	private void changeContentViewHeight(View contentView,float openRatio){
		contentView.setScaleX(1f-openRatio/8);
		contentView.setScaleY(1f-openRatio/8);
	}
	
}
