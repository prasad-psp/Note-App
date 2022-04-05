package com.psp.note_app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.psp.note_app.databinding.ItemNoteRecyclerviewBinding;
import com.psp.note_app.model.Note;

public class NoteListAdapter extends PagingDataAdapter<Note, NoteListAdapter.MyViewHolder> {
    private final OnItemClickListener itemClickListener;

    public NoteListAdapter(OnItemClickListener itemClickListener) {
        super(callback);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteRecyclerviewBinding binding = ItemNoteRecyclerviewBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = getItem(position);

        if(note != null) {
            holder.binding.txtTitle.setText(note.getTitle());
            String dateTime = note.getDate()+" "+note.getTime();
            holder.binding.txtDateTime.setText(dateTime);
        }

        holder.binding.rootView.setOnClickListener(view -> {
            if(note != null) {
                itemClickListener.onItemClicked(view, note);
            }
        });
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemNoteRecyclerviewBinding binding;
        public MyViewHolder(ItemNoteRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static DiffUtil.ItemCallback<Note> callback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }
    };

    public interface OnItemClickListener {
        void onItemClicked(View view, Note note);
    }
}
