package com.example.focusscape;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView itemTime;
    public TextView itemNotes;
    public TextView labelItemNotes;

    public CalendarViewHolder(View itemView) {
        super(itemView);
        itemTime = itemView.findViewById(R.id.itemTime);
        itemNotes = itemView.findViewById(R.id.itemNotes);
        labelItemNotes = itemView.findViewById(R.id.labelItemNotes);
    }
}
