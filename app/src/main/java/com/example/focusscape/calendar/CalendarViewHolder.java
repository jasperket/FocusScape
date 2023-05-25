package com.example.focusscape.calendar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.focusscape.R;

import org.w3c.dom.Text;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView itemTime;
    public TextView itemNotes;
    public TextView labelItemNotes;

    public Button btnDeleteReminder;

    private DeleteReminderListener deleteReminderListener;

    public CalendarViewHolder(View itemView) {
        super(itemView);
        itemTime = itemView.findViewById(R.id.itemTime);
        itemNotes = itemView.findViewById(R.id.itemNotes);
        labelItemNotes = itemView.findViewById(R.id.labelItemNotes);
        btnDeleteReminder = itemView.findViewById(R.id.btnDeleteReminder);

        deleteReminderListener = (DeleteReminderListener) itemView.getContext();

        btnDeleteReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = itemTime.getText().toString();
                String notes = itemNotes.getText().toString();
                deleteReminderListener.reminderDelete(time,notes);
            }
        });
    }

    public interface DeleteReminderListener {
        void reminderDelete(String time, String notes);
    }
}
