package com.example.uts;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapterTransaksi extends RecyclerView.Adapter<adapterTransaksi.listViewHolder> {

    private Context activity;
    private LayoutInflater inflater;
    private List<list_history> items;

    public adapterTransaksi(Context activity, List<list_history> items) {
        this.activity = activity;
        this.items = items;
    }

    @NonNull
    @Override
    public adapterTransaksi.listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_histori, parent, false);
        return new listViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTransaksi.listViewHolder holder, final int position) {

        final list_history list_history = items.get(position);
        holder.tanggal.setText(list_history.getTanggal());
        holder.waktu.setText(list_history.getWaktu());
        holder.nominal.setText(Integer.toString(list_history.getNominal()));
        if(list_history.getJenis().equals("KREDIT")){
            holder.rp.setText("-Rp ");
        } else {
            holder.rp.setText("+Rp ");
        }

        holder.uraian.setText(list_history.getUraian());
        holder.jenis_transaksi.setText(list_history.getJenis());
        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position, items.size());
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class listViewHolder extends RecyclerView.ViewHolder {

        TextView nominal, uraian, tanggal, waktu, jenis_transaksi, rp;
        Button btn_hapus;


        public listViewHolder(@NonNull View itemView) {

            super(itemView);

            nominal = itemView.findViewById(R.id.saldo_histori);
            uraian = itemView.findViewById(R.id.uraian_histori);
            tanggal = itemView.findViewById(R.id.tanggal_histori);
            waktu = itemView.findViewById(R.id.waktu_histori);
            jenis_transaksi = itemView.findViewById(R.id.jenis_transaksi_histori);
            btn_hapus = itemView.findViewById(R.id.btn_hapus);
            rp = itemView.findViewById(R.id.a);


        }
    }
}