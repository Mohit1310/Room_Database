package com.mohit.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    List<User> users;

    public myAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.recId.setText(String.valueOf(users.get(position).getUid()));
        holder.recFName.setText(users.get(position).getFirstName());
        holder.recLName.setText(users.get(position).getLastName());

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(holder.recId.getContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();

                // This is to delete record from room database
                userDao.deleteById(users.get(position).getUid());

                // this is to delete data from arraylist which is the source of recycler view data
                users.remove(position);

                // update the fresh list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView recId, recFName, recLName;
        ImageButton delBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            recId = itemView.findViewById(R.id.recId);
            recFName = itemView.findViewById(R.id.recFName);
            recLName = itemView.findViewById(R.id.recLName);
            delBtn = itemView.findViewById(R.id.delBtn);
        }
    }
}
