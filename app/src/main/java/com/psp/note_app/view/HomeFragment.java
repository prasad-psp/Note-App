package com.psp.note_app.view;

import static com.psp.note_app.utils.Constants.DESC_KEY;
import static com.psp.note_app.utils.Constants.ID_KEY;
import static com.psp.note_app.utils.Constants.TITLE_KEY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.paging.PagingData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.psp.note_app.R;
import com.psp.note_app.adapter.NoteListAdapter;
import com.psp.note_app.databinding.FragmentHomeBinding;
import com.psp.note_app.model.Note;
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
            Navigation.findNavController(view1).navigate(R.id.action_homeFragment_to_newNoteFragment);
        });
    }

    @Override
    public void onItemClicked(View view, Note note) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, note.getId());
        bundle.putString(TITLE_KEY, note.getTitle());
        bundle.putString(DESC_KEY, note.getDesc());
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newNoteFragment,bundle);
    }
}