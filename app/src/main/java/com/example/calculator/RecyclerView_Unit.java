package com.example.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.calculator.Database2.MainData;
import com.example.calculator.Database2.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Unit extends RecyclerView.Adapter<RecyclerView_Unit.ViewHolder>
{
    private Activity activity;
    private List<MainData> list = new ArrayList<>();
    private RoomDB roomDB;

    public RecyclerView_Unit(Activity activity, List<MainData> list) {
        this.activity = activity;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        roomDB = RoomDB.getInstance(activity);
        list = roomDB.mainDao().getAll();

        String from_value = list.get(position).getFrom_value();
        String from_abbr = list.get(position).getFrom_abbr();
        double value = list.get(position).getValue();
        String to_abbr = list.get(position).getTo_abbr();

        holder.N1.setText(from_value);
        holder.U1.setText(from_abbr);
        holder.N2.setText(String.valueOf(value));
        holder.U2.setText(to_abbr);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setTitle("Delete");
                dialog.setMessage("Accept Delete ?");
                dialog.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
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
                dialog.setNegativeButton("Calcel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
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
        private TextView N1,N2,U1,U2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            N1 = itemView.findViewById(R.id.N1);
            N2 = itemView.findViewById(R.id.N2);
            U1 = itemView.findViewById(R.id.U1);
            U2 = itemView.findViewById(R.id.U2);
        }
    }
}
