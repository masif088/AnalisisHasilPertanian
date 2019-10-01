package com.example.analisishasilpertanian.user;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.analisishasilpertanian.Adapter;
import com.example.analisishasilpertanian.ModelAnalysis;
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
public class AnalysisFragment extends Fragment {
    private DatabaseReference databaseReference;
    private Adapter adapter;
    private ArrayList<ModelAnalysis> analyses = new ArrayList<>();
    public AnalysisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference(ModelAnalysis.MODELANALYSIS);
    }

    @Override
    public void onStart() {
        super.onStart();
        analyses.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ModelAnalysis analysis = snapshot.getValue(ModelAnalysis.class);
                    analyses.add(analysis);
                }
                adapter.setAnalyses(analyses,ModelAnalysis.MODELANALYSIS);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_analysis, container, false);
        RecyclerView recycleView = view.findViewById(R.id.rv);
        adapter = new Adapter();
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
        return view;
    }

}
