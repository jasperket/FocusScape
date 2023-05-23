package com.example.focusscape;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private List<CalendarItem> calendarItems;

    public CalendarAdapter(List<CalendarItem> calendarItems) {
        this.calendarItems = calendarItems;
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar, parent, false);
        return new CalendarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        CalendarItem calendarItem = calendarItems.get(position);

        holder.itemTime.setText(calendarItem.getTime());
        holder.itemNotes.setText(calendarItem.getNotes());
    }

    @Override
    public int getItemCount() {
        return calendarItems.size();
    }

    public void clearData() {
        calendarItems.clear();
    }


    public void setData(List<CalendarItem> calendarItems) {
        this.calendarItems = calendarItems;
    }
}
