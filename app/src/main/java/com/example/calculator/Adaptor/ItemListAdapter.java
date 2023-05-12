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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.calculator.Hepler.ManagementCart;
import com.example.calculator.Interface.ChangeNumberItemsListener;
import com.example.calculator.Object.Product;
import com.example.calculator.R;
import com.example.calculator.ShowDetailActivity;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    private ArrayList<Product> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;
    String data;

    private Context mContext;

    public ItemListAdapter(Context context, ArrayList<Product> foodDomains) {
        mContext = context;
        this.foodDomains = foodDomains;
    }

//    public ItemListAdapter(ArrayList<Product> foodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
//        this.foodDomains = foodDomains;
//        this.managementCart = new ManagementCart(context);
//        this.changeNumberItemsListener = changeNumberItemsListener;
//    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_itemcategory, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodDomains.get(position).getName());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getPrice()));

        holder.num.setText(foodDomains.get(position).getDescrip().substring(0,72)+"...");
        Intent intent1 = ((Activity) mContext).getIntent();
        int drawableResouceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic()
        , "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResouceId)
                .into(holder.pic);

//        holder.plusItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                managementCart.plusNumberFoood(foodDomains, position, new ChangeNumberItemsListener() {
//                    @Override
//                    public void changed() {
//                        notifyDataSetChanged();
//                        changeNumberItemsListener.changed();
//                    }
//                });
//            }
//        });
//
        holder.itemCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = intent1.getStringExtra("user");
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object",  foodDomains.get(position));
                intent.putExtra("user", data);
                intent.putExtra("cateId", String.valueOf(foodDomains.get(position).getIdCate()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic;
        TextView  num;
        ConstraintLayout itemCon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCartTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            itemCon = itemView.findViewById(R.id.itemConstrainBill);
            num = itemView.findViewById(R.id.spTxt);

        }
    }


}
