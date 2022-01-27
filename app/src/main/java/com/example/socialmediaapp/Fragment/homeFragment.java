package com.example.socialmediaapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.Adapter.StoryAdapter;
import com.example.socialmediaapp.Model.StoryModel;
import com.example.socialmediaapp.R;

import java.util.ArrayList;


public class homeFragment extends Fragment {

    RecyclerView storyRv;
    ArrayList<StoryModel> list;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        storyRv = view.findViewById(R.id.storyRV);

        list = new ArrayList<>();

        list.add(new StoryModel(R.drawable.profile,R.drawable.ic_live,R.drawable.profile,"Yuvi"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.ic_live,R.drawable.profile,"Yuvi2"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.ic_live,R.drawable.profile,"Yuvi3"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.ic_live,R.drawable.profile,"Yuvi3"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.ic_live,R.drawable.profile,"Yuvi3"));

        StoryAdapter adapter = new StoryAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        storyRv.setLayoutManager(linearLayoutManager);
        storyRv.setNestedScrollingEnabled(false);
        storyRv.setAdapter(adapter);

        return view;
    }
}