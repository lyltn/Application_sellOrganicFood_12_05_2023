package com.example.calculator.Adaptor;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.calculator.ItemCategoryActivity;
import com.example.calculator.Object.Category;
import com.example.calculator.R;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder>{


    ArrayList<Category> categoryDomains;
    String data;
    private Context mContext;

    public CategoryAdaptor(Context context, ArrayList<Category> categoryDomains) {
        mContext = context;
        this.categoryDomains = categoryDomains;
    }

    public CategoryAdaptor(ArrayList<Category> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getName());
        String picUrl = "";
//        ArrayList<Category> a = ProductDao.getCate(Context.class);
//        int sl = a.size();

//        for(int i=0; i<categoryDomains.size(); i++ ){
//           if( i == position){
//                    Category cate = categoryDomains.get(i);
//                    picUrl = (String) cate.getPic() + "";
//                    String back = "R.drawable." + (String) cate.getBackground() + "";
//                    int a = Integer.parseInt(back);
//
//                    holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), a));
//                    break;
//                }
////
////
//            }
        switch (position){

//            for(int i=position; i<sl; i++ ){
////                case :{
//                    Category cate  = new Category();
//                    cate  =(Category) a.get(i);
//                    picUrl = (String) cate.getPic()+"";
//                    String back = "R.drawable." + (String) cate.getBackground()+"";
//
//                    holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), Integer.parseInt(back)));
//                    break;


//                }
//            }
//
            case 0:{
//                picUrl = "cat_1";
                picUrl = categoryDomains.get(position).getPic()+"";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;

            }
            case 1:{
//                picUrl = "khoaitay";
                picUrl = categoryDomains.get(position).getPic()+"";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
                break;
            }
            case 2:{
//                picUrl = "cachua";
                picUrl = categoryDomains.get(position).getPic()+"";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
                break;
            }
            case 3:{
//                picUrl = "can";
                picUrl = categoryDomains.get(position).getPic()+"";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 4:{
//                picUrl = "su";
                picUrl = categoryDomains.get(position).getPic()+"";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background5));
                break;
            }
            case 5:{
//                picUrl = "carot";
                picUrl = categoryDomains.get(position).getPic()+"";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;

            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
    
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
        Intent intent1 = ((Activity) mContext).getIntent();
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data = intent1.getStringExtra("user");
                Intent intent = new Intent(holder.itemView.getContext(), ItemCategoryActivity.class);
//                intent.putExtra("object", popularfood.get(position));
                intent.putExtra("user", data);
                intent.putExtra("cateId",String.valueOf( categoryDomains.get(position).getId()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.catagoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
