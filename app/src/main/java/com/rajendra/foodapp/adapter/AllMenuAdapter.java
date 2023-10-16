package com.rajendra.foodapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.foodapp.FoodDetails;
import com.rajendra.foodapp.R;
import com.rajendra.foodapp.model.Allmenu;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

    Context context;
    List<Allmenu> allmenuList;

    public AllMenuAdapter(Context context, List<Allmenu> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items, parent, false);

        return new AllMenuViewHolder(view);
    }
    private double convertToDollar(String priceInRupiah, double exchangeRate) {
        // Hapus "Rp " dan koma jika ada, lalu ubah ke tipe data double
        double priceInRupiahDouble = Double.parseDouble(priceInRupiah.replace("Rp ", "").replace(",", ""));

        // Konversi ke dolar dengan nilai tukar mata uang yang ditentukan
        double priceInDollar = priceInRupiahDouble / exchangeRate;

        // Pembulatan harga ke 2 desimal
        return Math.round(priceInDollar * 100.0) / 100.0;
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.allMenuName.setText(allmenuList.get(position).getName());

        holder.allMenuPrice.setText("$"+convertToDollar(allmenuList.get(position).getPrice(), 15000));
        holder.allMenuTime.setText(allmenuList.get(position).getDeliveryTime());
        holder.allMenuRating.setText(allmenuList.get(position).getRating());
        holder.allMenuCharges.setText(allmenuList.get(position).getDeliveryCharges());
        holder.allMenuNote.setText(allmenuList.get(position).getNote());

        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.allMenuImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name", allmenuList.get(position).getName());
                i.putExtra("price", "$"+convertToDollar(allmenuList.get(position).getPrice(), 15000));
                i.putExtra("rating", allmenuList.get(position).getRating());
                i.putExtra("image", allmenuList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView allMenuName, allMenuNote, allMenuRating, allMenuTime, allMenuCharges, allMenuPrice;
        ImageView allMenuImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            allMenuName = itemView.findViewById(R.id.all_menu_name);
            allMenuNote = itemView.findViewById(R.id.all_menu_note);
            allMenuCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            allMenuRating = itemView.findViewById(R.id.all_menu_rating);
            allMenuTime = itemView.findViewById(R.id.all_menu_deliverytime);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);
        }
    }

}
