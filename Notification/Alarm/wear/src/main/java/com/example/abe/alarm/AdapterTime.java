package com.example.abe.alarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.wear.widget.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdapterTime extends WearableRecyclerView.Adapter<AdapterTime.DespertadorViewHolder>{
    private int numberItens;

    public AdapterTime(int numberItens){
        this.numberItens = numberItens;
    }

    @NonNull
    @Override
    public DespertadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.time_itens;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachImmediately = false;

        View view = inflater.inflate(layoutId, parent, attachImmediately);
        DespertadorViewHolder viewHolder = new DespertadorViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DespertadorViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return this.numberItens;
    }

    class DespertadorViewHolder extends WearableRecyclerView.ViewHolder{
        private TextView numbers;

        public DespertadorViewHolder(@NonNull View itemView) {
            super(itemView);
            numbers = itemView.findViewById(R.id.numbers);
        }

        public void bind(int position){
            numbers.setText(Integer.toString(position));
        }
    }
}