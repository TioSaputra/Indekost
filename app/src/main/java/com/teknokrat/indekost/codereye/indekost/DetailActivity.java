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

public class DetailActivity extends AppCompatActivity {
    Intent i;

    String[] nama_kosan = {"Kostan Widya", "Kost", "Kost", "Kost", "Kost","Kost",
            "Kost", "Kost", "Kost", "Kost"};

    int image []={R.drawable.rumah_contoh,R.drawable.rumah_contoh,R.drawable.rumah_contoh,R.drawable.rumah_contoh,R.drawable.rumah_contoh,
            R.drawable.rumah_contoh,R.drawable.rumah_contoh,R.drawable.rumah_contoh,R.drawable.rumah_contoh,R.drawable.rumah_contoh};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ListView listview=(ListView)findViewById(R.id.list_kosan);
        Customadapter customadapter=new Customadapter();
        listview.setAdapter(customadapter);
    }



    class Customadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view =getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.imgv);
            TextView names=(TextView)view.findViewById(R.id.tv_nama);
            TextView detailss=(TextView)view.findViewById(R.id.tv_detail);

            imageView.setImageResource(image[i]);
            names.setText(nama_kosan[i]);
//            detailss.setText(detail[i]);

            return view;
        }
    }

}
