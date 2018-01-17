package com.teknokrat.indekost.codereye.indekost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teknokrat.indekost.codereye.indekost.model.Kosts;

public class DetailActivity extends AppCompatActivity {


    TextView tvNama, tvHarga, tvKecamatan, tvAlamat, tvJenis, tvDeskripsi;
ImageView vwGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = (TextView) findViewById(R.id.tvNamaKost);
        tvHarga = (TextView) findViewById(R.id.tvHargaKost);
        tvKecamatan = (TextView) findViewById(R.id.tvKecamatan);
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        tvJenis = (TextView) findViewById(R.id.tvJenisKost);
        vwGambar = (ImageView) findViewById(R.id.vwGambarKost);

        Kosts kost = (Kosts) getIntent().getSerializableExtra("object");

        tvNama.setText(kost.getNama());
        tvHarga.setText(""+kost.getHarga() +"/bulan");
        tvKecamatan.setText(kost.getKecamatan());
        tvDeskripsi.setText(kost.getDeskripsi());
        tvJenis.setText(kost.getJenis());
        tvAlamat.setText(kost.getAlamat());

        Picasso.with(this).load("https://firebasestorage.googleapis.com/v0/b/indekost-d3487.appspot.com/o/" + kost.getGambar() + ".jpg?alt=media&token=7ed9a494-186f-49bb-b52f-c4f690026fd5").into(vwGambar);
    }

}
