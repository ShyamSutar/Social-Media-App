package com.example.socialmediaapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.Adapter.NotificationAdapter;
import com.example.socialmediaapp.Adapter.ViewPagerAdapter;
import com.example.socialmediaapp.Model.NotificationModel;
import com.example.socialmediaapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class Notification2Fragment extends Fragment {


RecyclerView recyclerView;
ArrayList<NotificationModel> list;


    public Notification2Fragment() {
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
        View view =  inflater.inflate(R.layout.fragment_notification2, container, false);

        recyclerView = view.findViewById(R.id.notificationRV);

        list = new ArrayList<>();
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));
        list.add(new NotificationModel(R.drawable.profile,"<b>fadsf</b> mentioned you in a comment","just now"));

        NotificationAdapter adapter = new NotificationAdapter(list,getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}