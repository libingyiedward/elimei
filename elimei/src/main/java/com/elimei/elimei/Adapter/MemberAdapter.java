package com.elimei.elimei.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.elimei.R;
import com.elimei.databinding.ItemMemberBinding;
import com.elimei.elimei.Model.MemBerModel;

import java.util.List;


public class MemberAdapter
        extends RecyclerView.Adapter {
    List<MemBerModel.ResultBean.MemberBean> list;
    public AdapterView.OnItemClickListener onItemClickLIstener;

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*  32 */
        ItemMemberBinding inflate = (ItemMemberBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_member, parent, false);
        /*  33 */
        return new ViewHolder(inflate);
    }


    /*  40 */
    public void setOnItemClickLIstener(AdapterView.OnItemClickListener onItemClickLIstener) {
        this.onItemClickLIstener = onItemClickLIstener;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MemBerModel.ResultBean.MemberBean bean;
        /*  45 */
        ViewHolder viewHolder = (ViewHolder) holder;
        /*  46 */
        switch (getItemViewType(position)) {
            case 1:
                /*  48 */
                bean = (MemBerModel.ResultBean.MemberBean) this.list.get(position - 1);
                /*  49 */
                viewHolder.bind.name.setText(bean.getRelname());
                /*  50 */
                viewHolder.bind.tele.setText(bean.getMobile());
                /*  51 */
                viewHolder.bind.no.setText(bean.getWxid());
                /*  52 */
                viewHolder.bind.getRoot().setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        /*  55 */
                        MemberAdapter.this.onItemClickLIstener.onItemClick(null, v, position, 0L);
                    }
                });
                break;
            case 0:
                /*  60 */
                viewHolder.bind.tele.setGravity(17);
                /*  61 */
                viewHolder.bind.no.setGravity(17);
                /*  62 */
                viewHolder.bind.name.setText("姓名");
                /*  63 */
                viewHolder.bind.tele.setText("电话");
                /*  64 */
                viewHolder.bind.no.setText("会员号");
                break;
        }
    }


    public int getItemViewType(int position) {
        /*  71 */
        if (position == 0) {
            /*  72 */
            return 0;
        }
        /*  74 */
        return 1;
    }


    public int getItemCount() {
        /*  80 */
        if (this.list == null) {
            /*  81 */
            return 1;
        }
        /*  83 */
        return this.list.size() + 1;
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        public final ItemMemberBinding bind;

        public ViewHolder(ItemMemberBinding itemView) {
            /*  92 */
            super(itemView.getRoot());
            /*  93 */
            this.bind = itemView;
        }
    }

    public void setdata(List<MemBerModel.ResultBean.MemberBean> list) {
        /*  98 */
        this.list = list;
        /*  99 */
        notifyDataSetChanged();
    }


    /* 103 */
    public List<MemBerModel.ResultBean.MemberBean> getData() {
        return this.list;
    }
}


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\Adapter\MemberAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */