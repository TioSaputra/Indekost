package com.teknokrat.indekost.codereye.indekost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        addItemsOnSpinner1();
        addItemsOnSpinner2();
        addItemsOnSpinner3();

    }

        // add items into spinner dynamically

    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.pilihan);
        List<String> list = new ArrayList<String>();
        list.add("--- Pilih Semua ---");
        list.add("Terbaru");
        list.add("Termurah");
        list.add("Termahal");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.pilihan_jambertamu);
        List<String> list = new ArrayList<String>();
        list.add("--- Pilih ---");
        list.add("Bebas");
        list.add("Dibatasi");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner3() {

        spinner3 = (Spinner) findViewById(R.id.pilihan_PelBinatang);
        List<String> list = new ArrayList<String>();
        list.add("--- Pilih ---");
        list.add("Boleh");
        list.add("Tidak");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter);
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.pilihan);
        spinner2 = (Spinner) findViewById(R.id.pilihan_jambertamu);
        spinner3 = (Spinner) findViewById(R.id.pilihan_PelBinatang);
    }

}


