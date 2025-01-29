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
import com.bush.myapplication.person.Person;

import java.util.List;

public class PersonRecyclerView extends RecyclerView.Adapter<PersonRecyclerView.MyViewHolder>
{
    private List<Person> personList = MTPL.GetInstance().getPersonList();
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
        holder.nameSurname.setText(
                MTPL.GetInstance().getPersonList().get(position).getName() + " " +
                        MTPL.GetInstance().getPersonList().get(position).getSurname());
        holder.aboutPerson.setText("Территориальный коэффицент: "
                + MTPL.GetInstance().getPersonList().get(position).getTerritorialCoefficient()
                + ", Коэффицент КБС: "
                + MTPL.GetInstance().getPersonList().get(position).getAccidentRate()
                + ", Коэффицент КВС: "
                + MTPL.GetInstance().getPersonList().get(position).getCAECoefficient());
    }

    @Override
    public int getItemCount()
    {
        return personList.size();
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
