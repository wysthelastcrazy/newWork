package com.dchz.newwork.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dchz.newwork.R;


/***
 * 转菊花 对话框
 * @date 2015/07/09
 * @author billywen
 */
public class MLoadingDialog extends Dialog {
	private LayoutInflater li = null;
	private TextView txtView;
	
	public MLoadingDialog(Context context) {
		super(context);
		init();
	}
	
	public MLoadingDialog(Context context, int theme) {
		super(context, theme);
		init();
	}

	private void init(){
		li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.setCancelable(true);
		this.setCanceledOnTouchOutside(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View mView = li.inflate(R.layout.dialog_loading, null);
		ImageView imgView = (ImageView) mView.findViewById(R.id.loading_img);
		txtView = (TextView) mView.findViewById(R.id.load_tips);
		Animation ani = AnimationUtils.loadAnimation(getContext(),R.anim.loading_anim_rotate);
		ani.setRepeatCount(Animation.INFINITE);
		imgView.startAnimation(ani);
		LayoutParams llp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		this.setContentView(mView,llp);
	}
	
	public void setMyTips(String str){
		if(txtView != null){
			txtView.setVisibility(View.VISIBLE);
			txtView.setText(str);
		}
	}
}
