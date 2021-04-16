package com.example.mytzilleri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<HolderChat> {

    private List<String> chatsNameList;

    public ChatAdapter(List<String> chatsNameList){
        this.chatsNameList = chatsNameList;
    }

    @NonNull
    @Override
    public HolderChat onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_row_item, viewGroup, false);
        return new HolderChat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderChat holder, int position) {
        Chat userChat = DataRepositoryChat.INSTANCE.getChat(chatsNameList.get(position));

        if(userChat != null){
            holder.init(userChat.getNomeUtente(), userChat.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return chatsNameList.size();
    }
}
