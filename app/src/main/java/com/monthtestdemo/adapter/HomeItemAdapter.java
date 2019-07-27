package com.monthtestdemo.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.monthtestdemo.R;
import com.monthtestdemo.entity.HomeGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:杨高峰
 * Description：
 */
public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {

    private Context context;
    private List<HomeGoods> list;

    public HomeItemAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (list.size() == 2) {
            return new ViewHolder(inflater.
                    inflate(R.layout.item_mlss, viewGroup, false));
        } else if (list.size() == 3) {
            return new ViewHolder(inflater.
                    inflate(R.layout.item_rxxp, viewGroup, false));
        } else {
            return new ViewHolder(inflater.
                    inflate(R.layout.item_pzsh, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final HomeGoods goods = list.get(i);

        viewHolder.tvName.setText(goods.getCommodityName());
        viewHolder.tvPrice.setText("￥" + goods.getPrice());
        Glide.with(context).load(goods.getMasterPic())
                .into(viewHolder.ivIcon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<HomeGoods> result) {
        if (result != null)
            list.addAll(result);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        SimpleDraweeView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
