package com.example.todosapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.example.todosapp.R;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;

    private String username;
    private String email;
    private String password;

    public ExampleDialog(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_profile_setting, null);

        bindView(view);
        setText();

        builder.setView(view)
                .setTitle("Setting")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String username = editTextUsername.getText().toString();
                        // String password = editTextPassword.getText().toString();
                        // listener.applyTexts(username, password);
                    }
                });
        return builder.create();
    }

    public void bindView(View view) {
        edtUsername = view.findViewById(R.id.edit_username);
        edtEmail = view.findViewById(R.id.edit_email);
        edtPassword = view.findViewById(R.id.edit_password);
        edtConfirmPassword = view.findViewById(R.id.edit_password_confirm);
    }

    public void setText() {
        edtUsername.setText(username);
        edtEmail.setText(email);
        edtPassword.setText(password);
        edtConfirmPassword.setText(password);
    }
}
