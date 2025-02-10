package com.bush.myapplication.person.listview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;

public class PersonRecyclerView extends RecyclerView.Adapter<PersonRecyclerView.MyViewHolder>
{
    private final OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public PersonRecyclerView(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        var mtpl = MTPL.getInstance();
        holder.nameSurname.setText(
                mtpl.getDriver(position).getName() + " " +
                        mtpl.getDriver(position).getSurname());
        holder.aboutPerson.setText("Территориальный коэффицент: "
                + mtpl.getDriver(position).getTerritorialCoefficient()
                + ", Коэффицент КБС: "
                + mtpl.getDriver(position).getAccidentRate()
                + ", Коэффицент КВС: "
                + mtpl.getDriver(position).getCAECoefficient());
    }

    @Override
    public int getItemCount()
    {
        return MTPL.getInstance().getPersonListSize();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameSurname, aboutPerson;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            nameSurname = itemView.findViewById(R.id.nameSurname);
            aboutPerson = itemView.findViewById(R.id.aboutPerson);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(position);
                }
            });
        }
    }

}
