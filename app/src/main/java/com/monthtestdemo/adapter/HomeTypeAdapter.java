package com.monthtestdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.service.autofill.ImageTransformation;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.monthtestdemo.R;
import com.monthtestdemo.entity.HomeData;
import com.monthtestdemo.entity.HomeGoods;
import com.monthtestdemo.entity.HomeType;

import java.util.List;

/**
 * date:2019/7/27
 * name:windy
 * function:公共的布局
 */
public class HomeTypeAdapter extends RecyclerView.Adapter<HomeTypeAdapter.ViewHolder> {

    private Context context;
    private HomeType homeType;
    private static final int RXXP = 0;
    private static final int MLSS = 1;
    private static final int PZSH = 2;

    public HomeTypeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_home, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (i) {
            case RXXP:
                HomeData rxxp = homeType.getRxxp();
                List<HomeGoods> rxxpList = rxxp.getCommodityList();
                viewHolder.titleBar.setBackgroundResource(R.drawable.rxxp_bitmap);
                viewHolder.titleTip.setImageResource(R.drawable.common_btn_more_yellow_n);
                viewHolder.homeTitle.setText(rxxp.getName());
                viewHolder.homeTitle.setTextColor(Color.parseColor("#FFFF7F57"));
                viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL, false));
                HomeItemAdapter rxxpListAdapter = new HomeItemAdapter(context);
                viewHolder.recyclerView.setAdapter(rxxpListAdapter);
                rxxpListAdapter.addList(rxxpList);
                rxxpListAdapter.notifyDataSetChanged();
                break;
            case MLSS:
                HomeData mlss = homeType.getMlss();
                List<HomeGoods> mlssList = mlss.getCommodityList();
                viewHolder.titleBar.setBackgroundResource(R.drawable.mlss_bitmap);
                viewHolder.titleTip.setImageResource(R.drawable.home_btn_more_purple_n);
                viewHolder.homeTitle.setText(mlss.getName());
                viewHolder.homeTitle.setTextColor(Color.parseColor("#FF787AF6"));
                viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false));
                HomeItemAdapter mlssListAdapter = new HomeItemAdapter(context);
                viewHolder.recyclerView.setAdapter(mlssListAdapter);
                mlssListAdapter.addList(mlssList);
                mlssListAdapter.notifyDataSetChanged();
                break;
            case PZSH:
                HomeData pzsh = homeType.getPzsh();
                List<HomeGoods> pzshList = pzsh.getCommodityList();
                viewHolder.titleBar.setBackgroundResource(R.drawable.pzsh_bitmap);
                viewHolder.viewLine.setVisibility(View.INVISIBLE);
                viewHolder.titleTip.setImageResource(R.drawable.home_btn_moer_pink_n);
                viewHolder.homeTitle.setText(pzsh.getName());
                viewHolder.homeTitle.setTextColor(Color.parseColor("#FFFF5F71"));
                viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                HomeItemAdapter pzshListAdapter = new HomeItemAdapter(context);
                viewHolder.recyclerView.setAdapter(pzshListAdapter);
                pzshListAdapter.addList(pzshList);
                pzshListAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return homeType == null ? 0 : 3;
    }

    public void addList(HomeType result) {
        homeType = result;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return RXXP;
            case 1:
                return MLSS;
            case 2:
                return PZSH;
        }
        return super.getItemViewType(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout titleBar;
        private TextView homeTitle;
        private ImageView titleTip;
        private RecyclerView recyclerView;
        private View viewLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleBar = (RelativeLayout) itemView.findViewById(R.id.title_bar);
            homeTitle = (TextView) itemView.findViewById(R.id.home_title);
            titleTip = (ImageView) itemView.findViewById(R.id.title_tip);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.home_item);
            viewLine = itemView.findViewById(R.id.view_line);
        }
    }
}
