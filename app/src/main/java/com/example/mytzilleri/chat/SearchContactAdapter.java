package com.example.mytzilleri.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytzilleri.R;

import java.util.List;

public class SearchContactAdapter extends RecyclerView.Adapter<SearchContactHolder> {

    private List<Contact> contactsList;

    public SearchContactAdapter(List<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public SearchContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_row_item, viewGroup, false);
        return new SearchContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchContactHolder holder, int position) {
        Contact contact = DataRepositoryContact.INSTANCE.getContact(contactsList.get(position).getEmail());

        holder.init(contact.getNome());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}