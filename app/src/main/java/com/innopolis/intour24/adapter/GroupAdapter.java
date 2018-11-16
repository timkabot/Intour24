package com.innopolis.intour24.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innopolis.intour24.App;
import com.innopolis.intour24.R;
import com.innopolis.intour24.model.entity.Group;
import com.innopolis.intour24.model.entity.GroupProperty;
import com.innopolis.intour24.view.Impl.GroupDescriptionActivity;
import com.innopolis.intour24.view.Impl.MainActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Timkabor on 5/29/2017.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private ArrayList<Group> groups;
    private Context mContext;
    private MainActivity mainActivity;

    public GroupAdapter(ArrayList<Group> excursions, FragmentActivity activity) {
        this.groups = excursions;
        mainActivity = (MainActivity) activity;
        mContext = activity.getApplicationContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_group, parent, false);
        return new ViewHolder(cardView);
    }

    public boolean hasFoodIcon(List<GroupProperty> properties) {
        for (GroupProperty property : properties) {
            if (property.getIcon() == null) continue;
            if (property.getIcon().equals("ic_dining.png")) return true;
        }
        return false;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Group group = groups.get(position);
        if (hasFoodIcon(group.getExcursion().getProperties())) {
            holder.excFoodSign.setVisibility(View.VISIBLE);
        }

        holder.excName.setText(group.getExcursion().getName());
        holder.excPrice.setText(String.valueOf(group.getExcursion().getPrice().getAdultPrice()) + Html.fromHtml(" &#x20bd"));
        holder.excStartTime.setText(group.getTourDate().split(" |\\+")[1].substring(0, 5));
        holder.excDuration.setText(String.valueOf(group.getExcursion().getDuration() / 60) + mainActivity.getString(R.string.time));
        holder.tourName.setText(group.getExcursion().getOperator().getName());
        App.loadImageFromURL(group.getExcursion().getOperator().getLogo(), holder.tourLogo,mContext);

        String categoryIconName =group.getExcursion().getCategory().getIcon();
        if(!categoryIconName.equals("error"))
            App.setDrawableByName(categoryIconName,holder.excCategory,mContext);
        holder.setItem(group);
      //  holder.groupId.setText(group.getId());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Group mGroup;

        @BindView(R.id.excursion_name)   TextView  excName;
        @BindView(R.id.excursion_price)  TextView excPrice;
        @BindView(R.id.startTime)        TextView excStartTime;
        @BindView(R.id.duration)         TextView excDuration;
        @BindView(R.id.tourOperatorLogo) ImageView tourLogo;
        @BindView(R.id.tourOperatorName) TextView tourName;
        @BindView(R.id.foodSign)         ImageView excFoodSign;
        @BindView(R.id.busSign)          ImageView excCategory;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setItem(Group group) {
            mGroup = group;
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(mContext.getApplicationContext(), GroupDescriptionActivity.class);
            i.putExtra("groupId",mGroup.getId());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.getApplicationContext().startActivity(i);
        }
    }
}