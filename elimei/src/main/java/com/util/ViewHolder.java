package com.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public class ViewHolder
        extends RecyclerView.ViewHolder {
    private BaseViewHolder viewHolder;

    public ViewHolder(View itemView) {
        /* 16 */
        super(itemView);
        /* 17 */
        this.viewHolder = BaseViewHolder.getViewHolder(itemView);
    }


    /* 22 */
    public BaseViewHolder getViewHolder() {
        return this.viewHolder;
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\co\\util\ViewHolder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */