package com.example.calculator.Adaptor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Hepler.CheckOutDao;
import com.example.calculator.Object.Order;
import com.example.calculator.Object.OrderDetail;
import com.example.calculator.ProfileActivity;
import com.example.calculator.R;

import java.util.ArrayList;

public class OrderAdaptor extends RecyclerView.Adapter<OrderAdaptor.ViewHolder> {
    private ArrayList<Order> foodDomains;
    String data;

    private Context mContext;

    public OrderAdaptor(Context context, ArrayList<Order> foodDomains) {
        mContext = context;
        this.foodDomains = foodDomains;
    }

//    public ItemListAdapter(ArrayList<Product> foodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
//        this.foodDomains = foodDomains;
//        this.managementCart = new ManagementCart(context);
//        this.changeNumberItemsListener = changeNumberItemsListener;
//    }

    @Override
    public OrderAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order, parent, false);
        return new OrderAdaptor.ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.date.setText(foodDomains.get(position).getDate());
        holder.sp.setText(String.valueOf(CheckOutDao.getOrderDetail(holder.itemView.getContext(), String.valueOf(foodDomains.get(position).getId())).size())+" Product");
        String numString = Double.toString(foodDomains.get(position).getTotal());
        String decimalPart = numString.substring(0, numString.indexOf(".") + 2);
        holder.billtotal.setText(decimalPart+"kđ");
        Intent intent1 = ((Activity) mContext).getIntent();
//        int drawableResouceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic()
//                , "drawable", holder.itemView.getContext().getPackageName());

//        Glide.with(holder.itemView.getContext())
//                .load(drawableResouceId)
//                .into(holder.pic);

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
        holder.itemConBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                data = intent1.getStringExtra("user");
//                Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
//
//                intent.putExtra("user", data);
//
//                holder.itemView.getContext().startActivity(intent);
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
//                LayoutInflater inflater = getLayoutInflater();
//                View view = inflater.inflate(R.layout.dialog_description, null);
//                builder.setView(view);
//        Dialog dialog = builder.create();
                Dialog dialog = new Dialog(holder.itemView.getContext());
                dialog.setContentView(R.layout.dialog_orderdetail);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                TextView nameb,note,  phoneb, dateb, addressb, totallb, huyBtn;
                nameb = dialog.findViewById(R.id.namere);
                phoneb = dialog.findViewById(R.id.phonebill);
                dateb = dialog.findViewById(R.id.datebill);
                addressb = dialog.findViewById(R.id.address);
                totallb = dialog.findViewById(R.id.totalbill);
                huyBtn = dialog.findViewById(R.id.huyBtn);
                note = dialog.findViewById(R.id.note);
                nameb.setText(foodDomains.get(position).getNameRe());
                phoneb.setText(foodDomains.get(position).getPhone());
                dateb.setText(foodDomains.get(position).getDate());
                addressb.setText(foodDomains.get(position).getAddress());
                totallb.setText(foodDomains.get(position).getTotal()+"");
                note.setText(foodDomains.get(position).getNote());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false);
                RecyclerView recyclerViewGoiy = (RecyclerView) dialog.findViewById(R.id.detailRecy);
                recyclerViewGoiy.setLayoutManager(linearLayoutManager);


                ArrayList<OrderDetail> odetil = new ArrayList<>();
                RecyclerView.Adapter  adapter2;
                ArrayList<OrderDetail> orde = new ArrayList<>();
                ArrayList<OrderDetail> orde1 = new ArrayList<>();
                OrderDetail t = new OrderDetail(2,2,2,2,2);
                orde1.add(t);
                 orde = CheckOutDao.getOrderDetail(holder.itemView.getContext(), foodDomains.get(position).getId()+"");
                   if(orde.size() != 0){
                       adapter2 = new ItemDetailAdapter(holder.itemView.getContext(), CheckOutDao.getOrderDetail(holder.itemView.getContext(), foodDomains.get(position).getId()+""));
                   }else{
                       adapter2 = new ItemDetailAdapter(holder.itemView.getContext(), orde1);
                   }
                recyclerViewGoiy.setAdapter(adapter2);
                huyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(CheckOutDao.deleteOrder(holder.itemView.getContext(), foodDomains.get(position).getId())){
                            data = intent1.getStringExtra("user");
                            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
                            intent.putExtra("user", data);
                            holder.itemView.getContext().startActivity(intent);
                        }
                    }
                });

                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date, sp, billtotal;
        ConstraintLayout itemConBill;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateTxt);
            sp = itemView.findViewById(R.id.spTxt);
            billtotal = itemView.findViewById(R.id.billtotalTxi);
            itemConBill = itemView.findViewById(R.id.itemConstrainBill);


        }
    }


}
