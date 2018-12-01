package com.faculdade.companycrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.faculdade.companycrud.Database.TableControllerCompany;
import com.faculdade.companycrud.Models.Company;

import java.util.List;

public class ShowCompanysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_companys);

        readRecords();
    }

    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<Company> companys = new TableControllerCompany(this).read();

        if (companys.size() > 0) {

            for (Company obj : companys) {

                int id = obj.getId();
                String companyName = obj.getName();
                String companyCNPJ = obj.getCnpj();
                String companyValue = obj.getValue();
                String companyInscricao = obj.getInscricao();
                String companyRazao = obj.getRazao();
                String companyPhone = obj.getPhone();

                String textViewContents = companyName + " - " + companyCNPJ;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
