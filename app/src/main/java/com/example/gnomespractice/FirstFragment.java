package com.example.gnomespractice;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gnomespractice.database.DatabaseController;
import com.example.gnomespractice.databinding.FragmentFirstBinding;
import com.example.gnomespractice.model.Color;
import com.example.gnomespractice.model.Gnome;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RecyclerView recyclerView;
    private List<Gnome> gnomes;
    private FloatingActionButton floatingButton;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerView;
        floatingButton = binding.addGnomeButton;

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseController database = DatabaseController.getInstance(getContext());
        Gnome gnome1 = new Gnome("Fede", Color.BLUE, "Caramelo 1");
        Gnome gnome2 = new Gnome("Sergio", Color.GREEN, "Caramelo 2");
        Gnome gnome3 = new Gnome("JuanFrancisco", Color.BLUE, "Caramelo 3");
        Gnome gnome4 = new Gnome("Ulises", Color.RED, "Caramelo Feo");
        List<Gnome> toBeInserted = new ArrayList<>();
        toBeInserted.add(gnome1);
        toBeInserted.add(gnome2);
        toBeInserted.add(gnome3);
        toBeInserted.add(gnome4);

        List<Gnome> gnomeList = database.gnomeDao().getAll();

        for (int i = 0; i < gnomeList.size(); i++) {
            for (int j = 0; j < toBeInserted.size(); j++) {
                if (toBeInserted.get(j).getName().equals(gnomeList.get(i).getName())) {
                    toBeInserted.remove(toBeInserted.get(j));
                }
            }
        }

        for (Gnome insertableGnome: toBeInserted) {
            database.gnomeDao().insert(insertableGnome);
        }

        gnomes = database.gnomeDao().getAll();
        recyclerView.setAdapter(new RecycleAdapter(gnomes, this));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        floatingButton.setOnClickListener(e -> {
            NewGnomeDialog dialog = new NewGnomeDialog(gnomes);
            dialog.show(getParentFragmentManager(), "Nuevo Gnomo");
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(new RecycleAdapter(DatabaseController.getInstance(getContext()).gnomeDao().getAll(), this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}