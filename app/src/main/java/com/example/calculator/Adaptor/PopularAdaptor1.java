package com.example.calculator.Adaptor;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.example.calculator.Object.Product;
import com.example.calculator.R;
import com.example.calculator.ShowDetailActivity;

import java.util.ArrayList;

public class PopularAdaptor1 extends RecyclerView.Adapter<PopularAdaptor1.ViewHolder>{


    ArrayList<Product> popularfood;
    String data;

    private Context mContext;

    public PopularAdaptor1(Context context, ArrayList<Product> categoryDomains) {
        mContext = context;
        popularfood = categoryDomains;
    }

    public PopularAdaptor1(ArrayList<Product> categoryDomains) {
        this.popularfood = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular2,parent,false);

        return new ViewHolder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(popularfood.get(position).getName());
        holder.fee.setText(String.valueOf(popularfood.get(position).getPrice()));
        Intent intent1 = ((Activity) mContext).getIntent();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularfood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = intent1.getStringExtra("user");
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", popularfood.get(position));
                intent.putExtra("user", data);
                intent.putExtra("cateId", String.valueOf(popularfood.get(position).getIdCate()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularfood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
