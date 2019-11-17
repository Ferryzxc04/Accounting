package com.example.uts.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.uts.R;
import com.example.uts.list_history;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    Button btnKredit, tambah;
    TextView jenisTransaksi, saldo, tanggal, waktu;
    EditText uraian, nominal;

    public static int sisaSaldo = 0;
    int kredit = 0;
    int debet = 0;

    public static List<list_history> itemList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        btnKredit = root.findViewById(R.id.btn_tambah_saldo);
        tambah = root.findViewById(R.id.tambah);
        tanggal = root.findViewById(R.id.tanggal);
        waktu = root.findViewById(R.id.waktu);
        uraian = root.findViewById(R.id.uraian);
        nominal = root.findViewById(R.id.nominal);
        saldo = root.findViewById(R.id.saldo);

        jenisTransaksi = root.findViewById(R.id.jenis_transaksi);

        jenisTransaksi.setText("KREDIT");

        String datatanggal = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());

        tanggal.setText(datatanggal);

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                waktu.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        cekDebetOrKredit();

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uraian.getText().toString().length() == 0) {
                    uraian.setError("Wajib Isi Data");
                    Toast.makeText(getContext(), "Maaf Data Masih Kosong", Toast.LENGTH_SHORT).show();
                } else if (nominal.getText().toString().length() == 0) {
                    nominal.setError("Wajib Isi Data");
                    Toast.makeText(getContext(), "Maaf Data Masih Kosong", Toast.LENGTH_SHORT).show();
                } else {

                    if (jenisTransaksi.getText().toString().equals("KREDIT")) {

                        if (sisaSaldo <= Integer.parseInt(nominal.getText().toString())) {

                            if (sisaSaldo == Integer.parseInt(nominal.getText().toString())) {

                                Toast.makeText(getContext(), "Saldo Anda Habis!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getContext(), "Maaf saldo Anda kurang", Toast.LENGTH_SHORT).show();

                            }
                        }

                        if (sisaSaldo >= Integer.parseInt(nominal.getText().toString())) {

                            list_history data = new list_history();

                            data.setNominal(Integer.parseInt(nominal.getText().toString()));
                            data.setUraian(uraian.getText().toString());
                            data.setJenis(jenisTransaksi.getText().toString());
                            data.setTanggal(tanggal.getText().toString());
                            data.setWaktu(waktu.getText().toString());

                            itemList.add(data);

                            cekDebetOrKredit();

                            Toast.makeText(getContext(), "Data Transaksi Berhasil", Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        list_history data = new list_history();

                        data.setNominal(Integer.parseInt(nominal.getText().toString()));
                        data.setUraian(uraian.getText().toString());
                        data.setJenis(jenisTransaksi.getText().toString());
                        data.setTanggal(tanggal.getText().toString());
                        data.setWaktu(waktu.getText().toString());

                        itemList.add(data);

                        cekDebetOrKredit();

                        Toast.makeText(getContext(), "Data Transaksi Berhasil", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        btnKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnKredit.getText().toString().equals("DEBET")) {
                    jenisTransaksi.setText("DEBET");
                    btnKredit.setText("KREDIT");
                } else {
                    jenisTransaksi.setText("KREDIT");
                    btnKredit.setText("DEBET");
                }


            }
        });

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void cekDebetOrKredit() {

        debet = 0;
        kredit = 0;

        for (int i = 0; i < itemList.size(); i++) {

            if (itemList.get(i).getJenis() == "KREDIT") {
                kredit = kredit + itemList.get(i).getNominal();
            } else {
                debet = debet + itemList.get(i).getNominal();
            }

        }

        debet = debet - kredit;

        String str = NumberFormat.getNumberInstance(Locale.US).format(debet);

        saldo.setText(str);

        sisaSaldo = debet;
    }

}