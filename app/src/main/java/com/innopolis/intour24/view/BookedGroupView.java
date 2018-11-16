package com.innopolis.intour24.view;

import com.innopolis.intour24.model.entity.Booking;

import java.util.ArrayList;

/**
 * Created by Timkabor on 6/21/2017.
 */

public interface BookedGroupView {
    void updateRecyclerView(ArrayList<Booking> bookings);
    void showNetworkError();
}
