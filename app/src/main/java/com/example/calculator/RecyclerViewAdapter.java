package com.example.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Database.MainData;
import com.example.calculator.Database.RoomDB;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Activity context;
    private List<MainData> list = new ArrayList<>();
    private RoomDB roomDB;

    public RecyclerViewAdapter(Activity context, List<MainData> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        roomDB = RoomDB.getInstance(context);
        list = roomDB.mainDao().getAll();
        String cur1 = list.get(position).getCurrency1();
        String cur2 = list.get(position).getCurrency2();
        String num1 = list.get(position).getResult1();
        String num2 = list.get(position).getResult2();

        double d = Double.parseDouble(num2);
        BigDecimal b = new BigDecimal(d);
        double d1 = b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();

        holder.c1.setText(cur1);
        holder.num1.setText(num1);
        holder.c2.setText(cur2);
        holder.num2.setText(String.valueOf(d1));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Delete");
                dialog.setMessage("Accept Delete ?");
                dialog.setPositiveButton("sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainData mainData = list.get(holder.getAdapterPosition());
                        roomDB.mainDao().delete(mainData);
                        int s = holder.getAdapterPosition();
                        list.remove(s);
                        notifyItemRemoved(s);
                        notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView num1,num2,c1,c2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num1 = itemView.findViewById(R.id.num1);
            num2 = itemView.findViewById(R.id.num2);
            c1 = itemView.findViewById(R.id.c1);
            c2 = itemView.findViewById(R.id.c2);
        }
    }
}
