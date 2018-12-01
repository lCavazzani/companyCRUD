package com.faculdade.companycrud;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateCompany = findViewById(R.id.Btnaddcompany);
        buttonCreateCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getRootView().getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.modal_add_company, null, false);
                final EditText ETCompanyName = formElementsView.findViewById(R.id.Etcompanyname);
                final EditText ETCompanyCNPJ = formElementsView.findViewById(R.id.Etcompanycnpj);
                final EditText ETCompanyInscricao = formElementsView.findViewById(R.id.Etcompanyinscricao);
                final EditText ETCompanyPhone = formElementsView.findViewById(R.id.Etcompanyphone);
                final EditText ETCompanyRazao = formElementsView.findViewById(R.id.Etcompanyrazao);
                final EditText ETCompanyValue = formElementsView.findViewById(R.id.Etcompanyvalue);
                new AlertDialog.Builder(context)
                        .setView(formElementsView)
                        .setTitle("Create Company")
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }

                                }).show();
            }
        });
    }
}
