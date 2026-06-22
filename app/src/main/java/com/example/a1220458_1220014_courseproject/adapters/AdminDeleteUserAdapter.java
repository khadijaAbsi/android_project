package com.example.a1220458_1220014_courseproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.models.User;

import java.util.ArrayList;

public class AdminDeleteUserAdapter
        extends RecyclerView.Adapter<AdminDeleteUserAdapter.ViewHolder>{

    ArrayList<User> users;

    OnDeleteClick listener;

    public interface OnDeleteClick{

        void click(User user);

    }

    public AdminDeleteUserAdapter(
            ArrayList<User> users,
            OnDeleteClick listener){

        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType){

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_event_delete,
                                parent,
                                false
                        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position){

        User user = users.get(position);

        holder.title.setText(
                user.getName()
        );

        holder.deleteButton.setOnClickListener(v -> {

            listener.click(user);

        });
    }

    @Override
    public int getItemCount(){

        return users.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView){

            super(itemView);

            title =
                    itemView.findViewById(
                            R.id.eventTitle
                    );

            deleteButton =
                    itemView.findViewById(
                            R.id.deleteButton
                    );
        }
    }
}