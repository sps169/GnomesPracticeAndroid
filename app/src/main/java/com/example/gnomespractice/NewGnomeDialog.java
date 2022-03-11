package com.example.gnomespractice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.room.RoomDatabase;

import com.example.gnomespractice.database.DatabaseController;
import com.example.gnomespractice.model.Color;
import com.example.gnomespractice.model.Gnome;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class NewGnomeDialog extends DialogFragment {
    private List<Gnome> gnomos;
    public NewGnomeDialog(List<Gnome> gnomos) {
        this.gnomos = gnomos;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.add_gnome_dialog, null);

        EditText gnomeName = dialogView.findViewById(R.id.gnomeNameEditText);
        EditText gnomeSweetId = dialogView.findViewById(R.id.sweet_id_edit_text);
        Spinner colorPicker = dialogView.findViewById(R.id.spinner);
        Button button = dialogView.findViewById(R.id.create_gnome_button);
        button.setOnClickListener(l -> {
            if (gnomeName.getText().toString().length() > 0) {
                Color color = null;
                switch(colorPicker.getSelectedItem().toString()) {
                    case "Rojo":
                        color = Color.RED;
                        break;
                    case "Verde":
                        color = Color.GREEN;
                        break;
                    default:
                        color = Color.BLUE;
                }
                if (DatabaseController.getInstance(getContext()).gnomeDao().findByName(gnomeName.getText().toString()) == null) {
                    Gnome gnome = new Gnome(gnomeName.getText().toString(), color, gnomeSweetId.getText().toString());
                    DatabaseController.getInstance(getContext()).gnomeDao().insert(gnome);
                    gnomos.add(gnome);
                    this.dismiss();
                }else {
                    Snackbar.make(dialogView, "Este gnomo ya existe!!!", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }else {
                Snackbar.make(dialogView, "El nombre esta vacio!!!", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        builder.setView(dialogView);
        return builder.create();
    }
}
