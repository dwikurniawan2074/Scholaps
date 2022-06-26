package com.example.scholaps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PendaftaranFragment extends Fragment {

    String[] daftar;
    protected Cursor cursor;
    public static PendaftaranFragment pf;
    Database database;
    Button btn_daftar, btn_lihat;
    EditText nama_beasiswa, nama, alamat, email, asal_sekolah;
    ListView listView;

    String[] items = {"Djarum Foundation","Lazada Forward","AMINEF","Sariraya Japan 2022","Beasiswa JIS", "Beasiswa Brunei Darussalam", "Mitsui Bussan"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pendaftaran, container, false);
        database = new Database(getActivity());
        nama_beasiswa = view.findViewById(R.id.nama_beasiswa);
        nama = view.findViewById(R.id.nama);
        alamat = view.findViewById(R.id.alamat);
        email = view.findViewById(R.id.email);
        asal_sekolah = view.findViewById(R.id.asal_sekolah);
        btn_daftar = view.findViewById(R.id.btn_daftar);
        pf = this;

        Fragment selectedFragment = null;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        autoCompleteTextView = view.findViewById(R.id.nama_beasiswa);

        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.list_item, items);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,  View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });


        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into pendaftar(nama_beasiswa, nama, alamat, email, asal_sekolah) values('" +
                        nama_beasiswa.getText().toString() + "','" +
                        nama.getText().toString() + "','" +
                        alamat.getText().toString() + "','" +
                        email.getText().toString() + "','" +
                        asal_sekolah.getText().toString() + "')");
                Toast.makeText(getActivity(), "Data Tersimpan!", Toast.LENGTH_SHORT).show();
                PendaftaranFragment.pf.RefreshList();
            }
        });



        RefreshList();





        return view;
    }

    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pendaftar", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0 ).toString();
        }
    }
}