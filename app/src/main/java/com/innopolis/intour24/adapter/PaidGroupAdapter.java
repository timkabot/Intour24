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
import android.widget.Button;
import android.widget.TextView;

import com.innopolis.intour24.App;
import com.innopolis.intour24.R;
import com.innopolis.intour24.model.entity.Booking;
import com.innopolis.intour24.view.Impl.GroupDescriptionActivity;
import com.innopolis.intour24.view.Impl.MainActivity;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Timkabor on 6/20/2017.
 */

public class PaidGroupAdapter extends RecyclerView.Adapter<PaidGroupAdapter.ViewHolder> {

    private ArrayList<Booking> bookings;
    private Context mContext;
    private MainActivity mainActivity;
    public PaidGroupAdapter(ArrayList<Booking> bookings, FragmentActivity activity) {
        this.bookings = bookings;
        mainActivity = (MainActivity) activity;
        mContext = activity.getApplicationContext();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_paid_group, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(PaidGroupAdapter.ViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.paidExcursionName.setText(booking.getExcursion().getName());
        holder.price.setText(String.valueOf(booking.getTotalPrice())+ Html.fromHtml(" &#x20bd"));

        holder.date.setText(App.makeTextFromDate(booking.getGroup().getTourDate(),booking.getExcursion().getDuration()));

        holder.pickingPlace.setText(booking.getExcursion().getPickingPlace().getName());

        holder.button.setOnClickListener(v -> startGroupInfoActivity(booking.getGroup().getId()));
    }
    public void startGroupInfoActivity(int groupId)
    {
        Intent i = new Intent(mainActivity, GroupDescriptionActivity.class);
        i.putExtra("button","noButton");
        i.putExtra("groupId",groupId);
        mainActivity.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.paidExcursionName)            TextView paidExcursionName;
        @BindView(R.id.paidExcursionPrice)           TextView price;
        @BindView(R.id.paidExcursionDate)            TextView date;
        @BindView(R.id.paidExcursionGeopositionText) TextView pickingPlace;
        @BindView(R.id.paidExcursionButton)          Button  button;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}