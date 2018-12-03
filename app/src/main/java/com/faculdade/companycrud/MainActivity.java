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
import android.widget.TextView;
import android.widget.Toast;

import com.faculdade.companycrud.Database.TableControllerCompany;
import com.faculdade.companycrud.Models.Company;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countRecords();

        Button BTNShowCompanys = findViewById(R.id.Btnshowcompanys);

        BTNShowCompanys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowCompanysActivity.class));
            }
        });

        Button BTNShowGraph = findViewById(R.id.Btnshowgraph);

        BTNShowGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GraphViewActivity.class));
            }
        });

        Button buttonCreateCompany = findViewById(R.id.Btnaddcompany);
        buttonCreateCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getRootView().getContext();
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
                                        String companyName = ETCompanyName.getText().toString();
                                        String companyCNPJ = ETCompanyCNPJ.getText().toString();
                                        String companyInscricao = ETCompanyInscricao.getText().toString();
                                        String companyPhone = ETCompanyPhone.getText().toString();
                                        String companyRazao = ETCompanyRazao.getText().toString();
                                        String companyValue = ETCompanyValue.getText().toString();

                                        Company company = new Company();
                                        company.setName(companyName);
                                        company.setCnpj(companyCNPJ);
                                        company.setInscricao(companyInscricao);
                                        company.setPhone(companyPhone);
                                        company.setRazao(companyRazao);
                                        company.setValue(companyValue);

                                        boolean createSuccessful = new TableControllerCompany(context).create(company);
                                        if(createSuccessful){
                                            Toast.makeText(context, "Company information was saved.", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(context, "Unable to save Company information.", Toast.LENGTH_SHORT).show();
                                        }

                                        dialog.cancel();
                                    }

                                }).show();
            }
        });
    }
    public void countRecords() {
        int recordCount = new TableControllerCompany(this).count();
        TextView textViewRecordCount = findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }
}
