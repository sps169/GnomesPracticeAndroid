package com.example.gnomespractice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.gnomespractice.model.Color;
import com.example.gnomespractice.model.Gnome;

import java.util.List;

public class NewGnomeDialog extends DialogFragment {

    private List<Gnome> list;

    public NewGnomeDialog(List<Gnome> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.add_gnome_dialog, null);

        EditText gnomeName = dialogView.findViewById(R.id.gnomeNameEditText);
        Spinner colorPicker = dialogView.findViewById(R.id.spinner);
        Button button = dialogView.findViewById(R.id.create_gnome_button);
        button.setOnClickListener(l -> {
            list.add(new Gnome(gnomeName.getText().toString(), Color.BLUE, "no bitches"));
            this.dismiss();
        });
        builder.setView(dialogView);
        return builder.create();
    }


}
