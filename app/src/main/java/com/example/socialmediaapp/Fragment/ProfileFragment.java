package com.example.socialmediaapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialmediaapp.Adapter.FriendAdapter;
import com.example.socialmediaapp.Model.FriendModel;
import com.example.socialmediaapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<FriendModel> list;
    ImageView coverPhoto,profileImage;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;


    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        recyclerView = view.findViewById(R.id.friendRv);

        list = new ArrayList<>();

        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile));

        FriendAdapter adapter = new FriendAdapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        coverPhoto = view.findViewById(R.id.changeCoverPhoto);
        profileImage = view.findViewById(R.id.profileImage3);

        coverPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,11);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            Uri uri = data.getData();
            profileImage.setImageURI(uri);

            final StorageReference reference = storage.getReference().child("cover_photo").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Cover Photo Saved", Toast.LENGTH_SHORT).show();
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(auth.getUid()).child("coverPhoto").setValue(uri.toString());
                        }
                    });
                }
            });

        }
    }
}