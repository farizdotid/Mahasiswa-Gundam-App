package com.farizdotid.mahasiswagundam.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.farizdotid.mahasiswagundam.R;
import com.farizdotid.mahasiswagundam.adapter.MahasiswaAdapter;
import com.farizdotid.mahasiswagundam.helper.DBHandler;
import com.farizdotid.mahasiswagundam.model.Mahasiswa;

import java.util.List;

public class LihatMahasiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MahasiswaAdapter adapter;
    private DBHandler dbHandler;
    private TextView txt_resultadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        initComponents();
        initRecyclerView();
        cekDataRecyclerView();
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_mahasiswa);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DBHandler(LihatMahasiswaActivity.this);
        List<Mahasiswa> mahasiswaList = dbHandler.getSemuaMahasiswa();
        adapter = new MahasiswaAdapter(mahasiswaList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents(){
        txt_resultadapter = (TextView) findViewById(R.id.txt_resultadapter);
    }

    private void cekDataRecyclerView(){
        if (adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
