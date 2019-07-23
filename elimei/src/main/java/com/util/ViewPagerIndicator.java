package com.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.elimei.R;

import java.util.ArrayList;
import java.util.List;



public class ViewPagerIndicator
  implements ViewPager.OnPageChangeListener
{
  private Context context;
  private ViewPager viewPager;
  private LinearLayout dotLayout;
  private int size;
  private int img1;
  private int img2;
  private int imgSize;
  private List<ImageView> dotViewLists;
  
  public ViewPagerIndicator(Context context, ViewPager viewPager, LinearLayout dotLayout, int size) {
/* 28 */     this.img1 = R.drawable.black; this.img2 = R.drawable.grayc;
/* 29 */     this.imgSize = 10;
/* 30 */     this.dotViewLists = new ArrayList();

    
/* 33 */     this.context = context;
/* 34 */     this.viewPager = viewPager;
/* 35 */     this.dotLayout = dotLayout;
/* 36 */     this.size = size;
/* 37 */     dotLayout.removeAllViews();
/* 38 */     this.dotViewLists.clear();
/* 39 */     for (int i = 0; i < size; i++) {
/* 40 */       ImageView imageView = new ImageView(context);
/* 41 */       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));

      
/* 44 */       params.leftMargin = 10;
/* 45 */       params.rightMargin = 10;
      
/* 47 */       params.height = this.imgSize;
/* 48 */       params.width = this.imgSize;
/* 49 */       if (i == 0) {
/* 50 */         imageView.setBackgroundResource(this.img1);
      } else {
/* 52 */         imageView.setBackgroundResource(this.img2);
      } 
      
/* 55 */       dotLayout.addView(imageView, params);
/* 56 */       this.dotViewLists.add(imageView);
    } 
  }



  
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}


  
  public void onPageSelected(int position) {
/* 68 */     for (int i = 0; i < this.size; i++) {
      
/* 70 */       if (position % this.size == i) {
/* 71 */         ((View)this.dotViewLists.get(i)).setBackgroundResource(this.img1);
      } else {
/* 73 */         ((View)this.dotViewLists.get(i)).setBackgroundResource(this.img2);
      } 
    } 
  }
  
  public void onPageScrollStateChanged(int state) {}
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\ViewPagerIndicator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */