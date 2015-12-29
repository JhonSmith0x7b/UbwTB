package com.jhonsmith.ubwtb.myviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImage extends ImageView{

	public RoundImage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("NewApi")
	public RoundImage(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	public RoundImage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public RoundImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable=this.getDrawable();
		if(drawable==null)return;
		if(this.getWidth()==0||this.getHeight()==0)return;
		Bitmap tempBitmap=((BitmapDrawable)drawable).getBitmap();
		Bitmap originalBitmap=tempBitmap.copy(Bitmap.Config.ARGB_8888, true);
		int width=this.getWidth();
		int height=this.getHeight();
		Bitmap roundBitmap=getCroppedBitmap(originalBitmap, width);
		canvas.drawBitmap(roundBitmap, 0, 0,null);
	}
	
	public static Bitmap getCroppedBitmap(Bitmap bitmap,int radius){
		bitmap=Bitmap.createScaledBitmap(bitmap, radius, radius, false);
		Bitmap output=Bitmap.createBitmap(radius,radius,Config.ARGB_8888);
		Canvas  canvas=new Canvas(output);
		int color=0xffa19774;
		Paint paint= new Paint();
		Rect rect=new Rect(0,0,radius,radius);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawCircle(radius/2+0.7f, radius/2+0.7f, radius/2+0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap,rect,rect, paint);
		return output;
	}
}
