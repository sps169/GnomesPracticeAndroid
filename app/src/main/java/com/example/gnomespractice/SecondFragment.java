package com.example.gnomespractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gnomespractice.database.DatabaseController;
import com.example.gnomespractice.databinding.FragmentSecondBinding;
import com.example.gnomespractice.model.Color;
import com.example.gnomespractice.model.Gnome;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private EditText name;
    private EditText sweetId;
    private Spinner colorPicker;
    private Button modify;
    private Button save;
    private Button returnToList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        name = binding.editTextTextPersonName;
        sweetId = binding.gnomeSweet;
        colorPicker = binding.colorPicker;
        modify = binding.modifyButton;
        save = binding.saveButton;
        returnToList = binding.goBackList;
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Gnome gnome = new Gnome();
        gnome.setName(getArguments().getString("gnomeName"));
        gnome.setCaramelo_id(getArguments().getString("gnomeSweetId"));
        gnome.setColor(Color.valueOf(getArguments().getString("gnomeColor")));
        name.setEnabled(false);
        sweetId.setEnabled(false);
        colorPicker.setEnabled(false);
        name.setText(gnome.getName());
        sweetId.setText(gnome.getCaramelo_id());
        switch (gnome.getColor()) {
            case RED:
                colorPicker.setSelection(1);
                break;
            case BLUE:
                colorPicker.setSelection(0);
                break;
            case GREEN:
                colorPicker.setSelection(2);
                break;
        }

        modify.setOnClickListener(l -> {
            name.setEnabled(true);
            sweetId.setEnabled(true);
            colorPicker.setEnabled(true);
        });

        save.setOnClickListener(l -> {
            DatabaseController.getInstance(getContext()).gnomeDao().delete(gnome);
            Gnome newGnome = new Gnome();
            newGnome.setName(name.getText().toString());
            newGnome.setCaramelo_id(sweetId.getText().toString());
            switch(colorPicker.getSelectedItem().toString()) {
                case "Azul":
                    newGnome.setColor(Color.BLUE);
                    break;
                case "Rojo":
                    newGnome.setColor(Color.RED);
                    break;
                default:
                    newGnome.setColor(Color.GREEN);
                    break;

            }
            DatabaseController.getInstance(getContext()).gnomeDao().insert(newGnome);
        });

        returnToList.setOnClickListener(l -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_SecondFragment_to_FirstFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}