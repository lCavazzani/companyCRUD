package com.faculdade.companycrud.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.faculdade.companycrud.Database.TableControllerCompany;
import com.faculdade.companycrud.MainActivity;
import com.faculdade.companycrud.Models.Company;
import com.faculdade.companycrud.R;
import com.faculdade.companycrud.ShowCompanysActivity;

public class OnLongClickListenerCompanyRecord implements View.OnLongClickListener {
    Context context;
    String id;
    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("Company Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerCompany(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Company record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete Company record.", Toast.LENGTH_SHORT).show();
                            }

//                            ((MainActivity) context).countRecords();
                            ((ShowCompanysActivity) context).readRecords();

                        }

                        dialog.dismiss();

                    }
                }).show();

        return false;

    }

    public void editRecord(final int companyId) {
        final TableControllerCompany tableControllerCompany = new TableControllerCompany(context);
        Company company = tableControllerCompany.readSingleRecord(companyId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.modal_add_company, null, false);

        final EditText ETCompanyName = formElementsView.findViewById(R.id.Etcompanyname);
        final EditText ETCompanyCNPJ = formElementsView.findViewById(R.id.Etcompanycnpj);
        final EditText ETCompanyInscricao = formElementsView.findViewById(R.id.Etcompanyinscricao);
        final EditText ETCompanyPhone = formElementsView.findViewById(R.id.Etcompanyphone);
        final EditText ETCompanyRazao = formElementsView.findViewById(R.id.Etcompanyrazao);
        final EditText ETCompanyValue = formElementsView.findViewById(R.id.Etcompanyvalue);

        ETCompanyName.setText(company.getName());
        ETCompanyCNPJ.setText(company.getCnpj());
        ETCompanyInscricao.setText(company.getInscricao());
        ETCompanyPhone.setText(company.getPhone());
        ETCompanyRazao.setText(company.getRazao());
        ETCompanyValue.setText(company.getValue());

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Company company = new Company();
                                company.setId(companyId);
                                company.setName(ETCompanyName.getText().toString());
                                company.setCnpj(ETCompanyCNPJ.getText().toString());
                                company.setInscricao(ETCompanyInscricao.getText().toString());
                                company.setPhone(ETCompanyPhone.getText().toString());
                                company.setRazao(ETCompanyRazao.getText().toString());
                                company.setValue(ETCompanyValue.getText().toString());

                                boolean updateSuccessful = tableControllerCompany.update(company);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Company record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update Company record.", Toast.LENGTH_SHORT).show();
                                }
//                                ((MainActivity) context).countRecords();
                                ((ShowCompanysActivity) context).readRecords();
                                dialog.cancel();
                            }

                        }).show();
    }

}
