package com.example.socialmediaapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmediaapp.Adapter.FriendAdapter;
import com.example.socialmediaapp.Model.FriendModel;
import com.example.socialmediaapp.Model.UserModel;
import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.ActivitySignUpBinding;
import com.example.socialmediaapp.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<FriendModel> list;
    ImageView coverPhoto,profileImage,verifiedAccount,profi;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    TextView userNaam, profession;
    FragmentProfileBinding binding;



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


        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
//        binding = FragmentProfileBinding.inflate(inflater,container,false);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        recyclerView = view.findViewById(R.id.friendRv);
        userNaam = view.findViewById(R.id.userNaam);
        profession = view.findViewById(R.id.profession);
        profi = view.findViewById(R.id.userProfile);

        list = new ArrayList<>();



        database.getReference().child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    UserModel user = snapshot.getValue(UserModel.class);
                    Picasso.get().load(user.getCoverPhoto()).placeholder(R.drawable.placeholder).into(profileImage);
                    Picasso.get().load(user.getProfile()).placeholder(R.drawable.placeholder).into(profi);

                    userNaam.setText(user.getName());
                    profession.setText(user.getProfession());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

        verifiedAccount = view.findViewById(R.id.verifiedAccount);

        verifiedAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,22);
            }
        });



//        return binding.getRoot();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11){
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

        else{
            if(data.getData()!=null){
                Uri uri = data.getData();
                profi.setImageURI(uri);
//                    profi.setVisibility(View.INVISIBLE);
                final StorageReference reference = storage.getReference().child("profile_image").child(FirebaseAuth.getInstance().getUid());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override

                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Profile Photo Saved", Toast.LENGTH_SHORT).show();
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("Users").child(auth.getUid()).child("profile").setValue(uri.toString());
                            }
                        });
                    }
                });

            }
        }

    }
}