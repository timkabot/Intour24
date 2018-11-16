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
import com.innopolis.intour24.presenter.Impl.GroupPresenterImpl;
import com.innopolis.intour24.view.Impl.BookingActivity;
import com.innopolis.intour24.view.Impl.MainActivity;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Timkabor on 6/20/2017.
 */

public class BookedGroupAdapter extends RecyclerView.Adapter<BookedGroupAdapter.ViewHolder> {

    private ArrayList<Booking> bookings;
    private Context mContext;
    private MainActivity mainActivity;

    public BookedGroupAdapter(ArrayList<Booking> bookings, FragmentActivity activity) {
        this.bookings = bookings;
        mainActivity = (MainActivity) activity;
        mContext = activity.getApplicationContext();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_booked_group, parent, false);
        return new ViewHolder(cardView);
    }
    //TODO Метод для вычисления 15 минутной сессии, требует доработки
    /*
    public long isInSession(String previousFate)
    {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssZ", Locale.US);
        Date previousDate = null;
        try {
            previousDate = df.parse(previousFate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert previousDate != null;
        long diff = TimeUnit.MILLISECONDS.toMinutes(currentDate.getTime() - previousDate.getTime());
        if(diff>15) return -10;
        return diff;
    }*/
    @Override
    public void onBindViewHolder(BookedGroupAdapter.ViewHolder holder, int position) {

        Booking booking = bookings.get(position);
        int groupId = booking.getGroup().getId();

        holder.bookedExcursionName.setText(booking.getExcursion().getName());
        holder.price.setText(String.valueOf(booking.getTotalPrice())+ Html.fromHtml(" &#x20bd"));
        holder.date.setText(App.makeTextFromDate(booking.getGroup().getTourDate(),booking.getExcursion().getDuration()));
        holder.pickingPlace.setText(booking.getExcursion().getPickingPlace().getName());
        holder.button.setOnClickListener(v -> goToBookingActivity( groupId ));


    }
    public void goToBookingActivity(int groupId)
    {
        Intent i = new Intent(mainActivity,BookingActivity.class);
        i.putExtra(GroupPresenterImpl.GROUP_ID, groupId);
        mainActivity.startActivity(i);
    }
    @Override
    public int getItemCount() {
        return bookings.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bookedExcursionName)        TextView bookedExcursionName;
        @BindView(R.id.bookedExcrusionPrice)       TextView price;
        @BindView(R.id.bookedExcursionDate)        TextView date;
        @BindView(R.id.bookedExcursionGeoposition) TextView pickingPlace;
        @BindView(R.id.bookedExcursionButton)      Button button;
        @BindView(R.id.bookedGroupSession)         TextView session;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}