package com.example.analisishasilpertanian.admin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analisishasilpertanian.Adapter;
import com.example.analisishasilpertanian.ModelInformation;
import com.example.analisishasilpertanian.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {
    private DatabaseReference databaseReference;
    private Adapter adapter;
    private ArrayList<ModelInformation> informations= new ArrayList<>();
    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference(ModelInformation.MODELINFORMATION);
    }

    @Override
    public void onStart() {
        super.onStart();
        informations.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelInformation information = snapshot.getValue(ModelInformation.class);
                    informations.add(information);
                }
                adapter.setInformations(informations,ModelInformation.MODELINFORMATION);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_information, container, false);
        RecyclerView recycleView = view.findViewById(R.id.rv);
        adapter = new Adapter();
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
        return view;
    }


}
