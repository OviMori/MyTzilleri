package com.example.mytzilleri.chat;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytzilleri.R;
import com.example.mytzilleri.databinding.FragmentChatChoseContactBinding;


public class ChatChoseContact extends AppCompatActivity {

    FragmentChatChoseContactBinding binding;
    RecyclerView recyclerView;
    SearchContactAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_chat_chose_contact);

        binding = DataBindingUtil.setContentView(this, R.layout.fragment_chat_chose_contact);
        recyclerView = binding.contactRecyclerView;
        initRecyclerView();

    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SearchContactAdapter(DataRepositoryContact.INSTANCE.getContactList());
        recyclerView.setAdapter(adapter);
    }
}
