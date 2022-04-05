package com.psp.note_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.psp.note_app.R;
import com.psp.note_app.adapter.NoteListAdapter;
import com.psp.note_app.databinding.FragmentHomeBinding;
import com.psp.note_app.model.Note;
import com.psp.note_app.repo.NewNoteFragRepo;
import com.psp.note_app.utils.DateUtils;
import com.psp.note_app.viewmodel.HomeFragViewModel;


public class HomeFragment extends Fragment implements NoteListAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;

    private HomeFragViewModel viewModel;

    private NoteListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this).get(HomeFragViewModel.class);
        viewModel.init(this.requireContext(), this);
        adapter = new NoteListAdapter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init recyclerview
        binding.recyclerView.setAdapter(adapter);

        viewModel.getNotesObserver().observe(this.getViewLifecycleOwner(), new Observer<PagingData<Note>>() {
            @Override
            public void onChanged(PagingData<Note> notePagingData) {
                if(notePagingData != null) {
                    adapter.submitData(HomeFragment.this.getLifecycle(), notePagingData);
                }
            }
        });

        binding.btnFloating.setOnClickListener(view1 -> {
            Toast.makeText(view1.getContext(), "Floating button clicked", Toast.LENGTH_SHORT).show();
            logmsg("Floating button clicked");
        });
    }

    @Override
    public void onItemClicked(Note note) {
        Toast.makeText(this.requireContext(), "item clicked "+note.getTime(), Toast.LENGTH_SHORT).show();
    }

    private void logmsg(String msg) {
        Log.d("HomeFragment",msg);
    }
}