package com.example.gnomespractice;

import static androidx.core.os.BundleKt.bundleOf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gnomespractice.model.Gnome;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.GnomeHolder> {

    private List<Gnome> gnomeList;
    private Fragment fragment;

    public RecycleAdapter(List<Gnome> list, Fragment fragment) {
        gnomeList = list;
        this.fragment = fragment;
    }

    public RecycleAdapter () {
        gnomeList = new ArrayList<>();
    }
    @Override
    public GnomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gnome_item_view, parent, false);

        return new GnomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GnomeHolder holder, int position) {
        holder.getGnomeName().setText(gnomeList.get(position).getName());
        holder.getGnomeSweetId().setText(gnomeList.get(position).getCaramelo_id());
        holder.itemView.setBackgroundColor(Color.parseColor(gnomeList.get(position).getColor().name()));
        holder.detailsButton.setOnClickListener(e -> {
            holder.itemView.animate().setDuration(100).rotationBy(180).start();
        });
        holder.itemView.setOnClickListener(l -> {
            Bundle currentGnome = new Bundle();
            currentGnome.putString("gnomeName", gnomeList.get(position).getName());
            currentGnome.putString("gnomeSweetId", gnomeList.get(position).getCaramelo_id());
            currentGnome.putString("gnomeColor", gnomeList.get(position).getColor().toString());
            NavHostFragment.findNavController(fragment).navigate(R.id.action_FirstFragment_to_SecondFragment, currentGnome);
        });
    }

    @Override
    public int getItemCount() {
        return gnomeList.size();
    }

    public List<Gnome> getGnomeList() {
        return gnomeList;
    }

    public void setGnomeList(List<Gnome> gnomeList) {
        this.gnomeList = gnomeList;
    }

    public class GnomeHolder extends RecyclerView.ViewHolder {

        private TextView gnomeName;
        private TextView gnomeSweetId;
        private Button detailsButton;

        public GnomeHolder(@NonNull View itemView) {
            super(itemView);
            this.gnomeName = itemView.findViewById(R.id.gnomeName);
            this.gnomeSweetId = itemView.findViewById(R.id.gnomeSweetId);
            this.detailsButton = itemView.findViewById(R.id.do_the_barrer_roll);
        }

        public TextView getGnomeName() {
            return gnomeName;
        }

        public void setGnomeName(TextView gnomeName) {
            this.gnomeName = gnomeName;
        }

        public TextView getGnomeSweetId() {
            return gnomeSweetId;
        }

        public void setGnomeSweetId(TextView gnomeSweetId) {
            this.gnomeSweetId = gnomeSweetId;
        }

        public Button getDetailsButton() {
            return detailsButton;
        }

        public void setDetailsButton(Button detailsButton) {
            this.detailsButton = detailsButton;
        }
    }
}
