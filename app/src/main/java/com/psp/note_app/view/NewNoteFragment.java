package com.psp.note_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psp.note_app.databinding.FragmentNewNoteBinding;
import com.psp.note_app.viewmodel.AppViewModelFactory;
import com.psp.note_app.viewmodel.NewNoteFragViewModel;


public class NewNoteFragment extends Fragment {

    private FragmentNewNoteBinding binding;

    private NewNoteFragViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewNoteBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this, new AppViewModelFactory(this.requireContext())).get(NewNoteFragViewModel.class);

        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getTitleObserver().observe(this.getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.edtTitle.setText(s);
            }
        });

        viewModel.getDescObserver().observe(this.getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.edtDesc.setText(s);
            }
        });

        viewModel.setValues(getArguments());
    }

    @Override
    public void onDestroy() {
        addNote();
        super.onDestroy();
    }

    private void addNote() {
        String title = binding.edtTitle.getText().toString().trim();
        String desc = binding.edtDesc.getText().toString().trim();

        if(title.isEmpty() && desc.isEmpty()) {
            return;
        }

        if(title.isEmpty()) {
            title = "";
        }

        if(desc.isEmpty()) {
            desc = "";
        }

        if(viewModel.isExistingNote()) {
            viewModel.addNote(viewModel.getExistingId(), title, desc);
        } else {
            viewModel.addNote(title, desc);
        }
    }

}