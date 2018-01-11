package com.teknokrat.indekost.codereye.indekost;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.teknokrat.indekost.codereye.indekost.model.Kosts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by tio on 1/3/18.
 */

public class ListKost extends Fragment implements AdapterView.OnItemClickListener{

    private static final String TAG=MainActivity.class.getSimpleName();
    private FirebaseDatabase database;
    private DatabaseReference myRef=null;
    private List<Kosts> kostsList;
    private List<String> path;

    View RootView;
    ListView listView;

//    Firebase Reference
    private StorageReference mStorageRef;
    File localFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("On Create ListKost Called");
        super.onCreate(savedInstanceState);
        getListData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("On Create View ListKost Called");
        RootView = inflater.inflate(R.layout.activity_list_kost, container, false);
        return RootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        startActivity(i);
    }

    private void fillFoodList(){
        listView = (ListView) getView().findViewById(R.id.beranda_list);
        Customadapter customadapter = new Customadapter();
        listView.setAdapter(customadapter);
        listView.setOnItemClickListener(this);
    }

    private void getListData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("kost");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.getChildrenCount();
                Log.d(TAG, "no of children : " + value);

                try {
                    kostsList = new ArrayList<Kosts>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        kostsList.add(child.getValue(Kosts.class));
                        getImageData();
                    }

                    for (int i = 0; i < kostsList.size(); i++) {
                        System.out.println("Nama Kost an : " + kostsList.get(i).getNama());
                    }
                }catch (Exception e){
                    System.out.println("Catch : " + e);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void getImageData(){
        String fileType = ".jpg";
        String fileName = "";
        String fullName = "";

        mStorageRef = FirebaseStorage.getInstance().getReference();
        path = new ArrayList<String>();

        try {
            for(int i = 0; i<kostsList.size(); i++) {
                fileName = kostsList.get(i).getGambar().toString();
                fullName = fileName + fileType;
                StorageReference riversRef = mStorageRef.child(fullName);
                localFile = File.createTempFile(fileName, fileType);
                path.add(localFile.getAbsolutePath());


                riversRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                // Successfully downloaded data to local file
                                // ...
                                System.out.println("Successfully downloaded data to local file");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
                        // ...
                    }
                });
            }
            fillFoodList();
        }catch (Exception e){

        }

    }

    class Customadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return kostsList.size();
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
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate(R.layout.custom_list_item, null);
            ImageView vwGambar = (ImageView) view.findViewById(R.id.list_item_gambar);
            TextView txtNama = (TextView) view.findViewById(R.id.list_item_nama);
            TextView txtAlamat = (TextView) view.findViewById(R.id.list_item_alamat);

            Bitmap bitmap = BitmapFactory.decodeFile(path.get(i));
            vwGambar.setImageBitmap(bitmap);

            txtNama.setText(kostsList.get(i).getNama());
            txtAlamat.setText(kostsList.get(i).getNama());

            return view;
        }
    }
}
