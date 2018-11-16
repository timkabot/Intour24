package com.innopolis.intour24.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innopolis.intour24.App;
import com.innopolis.intour24.R;
import com.innopolis.intour24.model.entity.Sight;
import com.innopolis.intour24.presenter.RecyclerItemClickListener;
import com.innopolis.intour24.view.Impl.MainActivity;
import com.innopolis.intour24.view.Impl.SightDescriptionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Сергей Пинкевич on 24.05.2017.
 */

/**
 * Адаптер для списка достопримечательностей на главном экране
 */
public class SightAdapter extends RecyclerView.Adapter<SightAdapter.ViewHolder> {

    private Context mContext;
    private List<Sight> mSightsList;
    private RecyclerItemClickListener mItemClickListener;
    private MainActivity mainActivity;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.sign_cardview)    CardView mCardView;
        @BindView(R.id.sight_card_image) ImageView mSightImage;
        @BindView(R.id.sight_card_title) TextView mSightTitle;
        @BindView(R.id.sight_card_price) TextView mSightPrice;

        public ViewHolder(CardView itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        /**
         * Обработка нажатия на выбранном элементе
         * Запускает фрагмент описания достопримечательности
         * @param view компонент, на котором был сделан клик
         */
        @Override
        public void onClick(View view) {
            Sight sign = mSightsList.get(getAdapterPosition());

            Bundle arguments = new Bundle();
            arguments.putInt("sightId", sign.getId());
            arguments.putParcelableArrayList("properties",sign.getProperties());

            SightDescriptionFragment fragment = new SightDescriptionFragment();
            fragment.setArguments(arguments);

            mainActivity.showNewFragment(fragment);

            if (mItemClickListener != null)
                mItemClickListener.onItemClickListener(getAdapterPosition());
        }
    }

    public SightAdapter(List<Sight> sights, FragmentActivity activity) {
        mSightsList = sights;
        mainActivity = (MainActivity) activity;
        mContext = activity.getApplicationContext();
    }

    @Override
    public SightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_sight, parent, false);
        return new ViewHolder(cardView);
    }


    @Override
    public void onBindViewHolder(SightAdapter.ViewHolder holder, int position) {
        Sight sight = mSightsList.get(position);
        holder.mSightTitle.setText(sight.getName());
        holder.mSightPrice.setText(sight.getMinPrice() + "-" + sight.getMaxPrice() + "\u20BD");
        App.loadImageFromURL(sight.getCover(), holder.mSightImage,mContext);
    }

    @Override
    public int getItemCount() {
        return mSightsList.size();
    }

    public boolean isHeader(int position){
        return position == 0;
    }

    public void setFilter(ArrayList<Sight> newList) {
        mSightsList = new ArrayList<>();
        mSightsList.addAll(newList);
        notifyDataSetChanged();
    }
    public void setItemClickListener(RecyclerItemClickListener listener) { mItemClickListener = listener; }

}
