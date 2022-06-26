package com.example.scholaps;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CustomerFragment extends Fragment {

    Button btnEmail, btnLivechat, btnFaq;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        btnEmail = view.findViewById(R.id.btn_email);
        btnLivechat = view.findViewById(R.id.btn_livechat);
        btnFaq = view.findViewById(R.id.btn_faq);
        Fragment selectedFragment = null;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"scholapsdeveloper@gmail.com"});
                intent.putExtra(Intent.EXTRA_CC, new String[] {"scholapsdeveloper2@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Scholaps Contact");
                intent.putExtra(Intent.EXTRA_TEXT, "Halo Admin Scholaps! Saya ingin menyampaikan sebuah pernyataan...(isi sesuai yang kalian inginkan!)");

                try {
                    startActivity(Intent.createChooser(intent, "Ingin Mengirim Email ?"));
                } catch (android.content.ActivityNotFoundException ex) {
                    //do something else
                }
            }
        });


        return view;
    }
}