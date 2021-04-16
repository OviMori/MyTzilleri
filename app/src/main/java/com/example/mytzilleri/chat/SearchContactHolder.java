package com.example.mytzilleri.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytzilleri.R;
import com.example.mytzilleri.databinding.ContactRowItemBinding;

public class SearchContactHolder extends RecyclerView.ViewHolder {
    private TextView contactName;

    public SearchContactHolder(@NonNull View itemView) {
        super(itemView);
        contactName = itemView.findViewById(R.id.contact_user_name_row);

    }

    public void init(String contactNamePar){
        contactName.setText(contactNamePar);
    }
}
