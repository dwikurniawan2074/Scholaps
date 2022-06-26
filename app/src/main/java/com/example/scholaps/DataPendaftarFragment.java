package com.example.scholaps;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DataPendaftarFragment extends Fragment {

    ListView listView;
    String[] daftar;
    Database database;
    protected Cursor cursor;
    public static DataPendaftarFragment dpf;

    PendaftaranFragment pf;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_pendaftar, container, false);
        pf.RefreshList();

        return view;
    }
}