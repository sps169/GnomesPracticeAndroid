package com.example.gnomespractice;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        gnomes = new ArrayList<>();
        gnomes.add(new Gnome("Fede", Color.BLUE, "Caramelo 1"));
        gnomes.add(new Gnome("Sergio", Color.GREEN, "Caramelo 2"));
        gnomes.add(new Gnome("JuanFrancisco", Color.BLUE, "Caramelo 3"));
        gnomes.add(new Gnome("Ulises", Color.RED, "Caramelo Feo"));
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerView;
        recyclerView.setAdapter(new RecycleAdapter(gnomes));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        floatingButton = binding.addGnomeButton;
        floatingButton.setOnClickListener(e -> {
            NewGnomeDialog dialog = new NewGnomeDialog(gnomes);
            dialog.show(getParentFragmentManager(), "Nuevo Gnomo");
        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}