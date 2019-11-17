package com.example.uts.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.uts.R;

import static com.example.uts.ui.home.HomeFragment.itemList;
import static com.example.uts.ui.home.HomeFragment.sisaSaldo;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    TextView total_pem, total_peng, total_sal;

    int kredit = 0;
    int debet = 0;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final TextView textView = root.findViewById(R.id.text_notifications);

        total_pem = root.findViewById(R.id.total_pem);
        total_peng = root.findViewById(R.id.total_peng);
        total_sal = root.findViewById(R.id.total_sal);

        for (int i = 0; i < itemList.size(); i++) {

            if (itemList.get(i).getJenis() == "KREDIT") {
                kredit = kredit + itemList.get(i).getNominal();
            } else {
                debet = debet + itemList.get(i).getNominal();
            }
        }

        total_pem.setText(Integer.toString(debet));
        total_peng.setText(Integer.toString(kredit));
        total_sal.setText(Integer.toString(sisaSaldo));


        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}