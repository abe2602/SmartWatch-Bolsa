package com.example.abe.prototipo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.wear.widget.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter extends WearableRecyclerView.Adapter<Adapter.DespertadorViewHolder>{
    private int numberItens;

    public Adapter(int numberItens){
        this.numberItens = numberItens;
    }

    @NonNull
    @Override
    public DespertadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.item_view;
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
        TextView textViewRV;

        public DespertadorViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRV = itemView.findViewById(R.id.textViewRV);
        }

        public void bind(int position){
            if(position %2 == 0){
                textViewRV.setText("Par");
                textViewRV.setBackgroundColor(0xFF00F0DD);
            }else{
                textViewRV.setText("Impar");
                textViewRV.setBackgroundColor(0xFF00F000);
            }
        }
    }
}
