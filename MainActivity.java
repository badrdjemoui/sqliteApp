package com.example.programmingknowledge.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    EditText editMob;
    EditText editDet;
    EditText editId;


    Button btnAddData;
    Button btnviewAll;
    Button btnSrchName;
    Button btnSrchMob;
    Button btnupdate;
    Button btndelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_Name);
        editMob = (EditText) findViewById(R.id.editText_Mob);
        editDet = (EditText) findViewById(R.id.editText_Det);
        editId = (EditText) findViewById(R.id.editText_ID);


        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_show);
        btnupdate = (Button) findViewById(R.id.button_update);
        btndelete = (Button) findViewById(R.id.button_delete);
        btnSrchName = (Button) findViewById(R.id.button_srch_name);
        btnSrchMob = (Button) findViewById(R.id.button_srch_mob);


        AddData();
        viewAll();
        UpdateData();
        deleteData();
        search_name();
        search_mob();

    }


    public void deleteData() {
        btndelete.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             Integer deletRows = myDb.deleteData(editId.getText().toString());
                                             if (deletRows > 0)
                                                 Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(MainActivity.this, "data not deleted", Toast.LENGTH_LONG).show();
                                         }
                                     }
        );

    }

    public void UpdateData() {
        btnupdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {


                                             boolean isUpdate = myDb.updateData(editId.getText().toString(), editName.getText().toString(), editMob.getText().toString(), editDet.getText().toString());
                                             if (isUpdate = true)
                                                 Toast.makeText(MainActivity.this, "data update", Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(MainActivity.this, "data not updated", Toast.LENGTH_LONG).show();


                                         }
                                     }
        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinserted = myDb.insertData(editName.getText().toString(), editMob.getText().toString(), editDet.getText().toString());
                if (isinserted = true)
                    Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("error", "no data");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        showMessage(res.getCount() +" raw" , buffer.toString());
                        while (res.moveToNext()) {
                            buffer.append("ID = " + res.getString(0) + "\n");
                            buffer.append("name = " + res.getString(1) + "\n");
                            buffer.append("Mob = " + res.getString(2) + "\n");
                            buffer.append("Det = " + res.getString(3) + "\n\n");
                        }
                        showMessage("data", buffer.toString());
                    }
                }
        );

    }
    public void search_name() {
        btnSrchName.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.get_name(editName.getText().toString());
                        if (res.getCount() == 0) {
                            showMessage("error", "no data");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        showMessage(res.getCount() +" raw" , buffer.toString());
                        while (res.moveToNext()) {
                            buffer.append("ID = " + res.getString(0) + "\n");
                            buffer.append("name = " + res.getString(1) + "\n");
                            buffer.append("Mob = " + res.getString(2) + "\n");
                            buffer.append("det = " + res.getString(3) + "\n\n");
                        }
                        showMessage("data", buffer.toString());
                    }
                }
        );

    }
    public void search_mob() {
        btnSrchMob.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.get_mob(editMob.getText().toString());
                        if (res.getCount() == 0) {
                            showMessage("error", "no data");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        showMessage(res.getCount() +" raw" , buffer.toString());
                        while (res.moveToNext()) {
                            buffer.append("ID = " + res.getString(0) + "\n");
                            buffer.append("name = " + res.getString(1) + "\n");
                            buffer.append("Mob = " + res.getString(2) + "\n");
                            buffer.append("det = " + res.getString(3) + "\n\n");
                        }
                        showMessage("data", buffer.toString());
                    }
                }
        );

    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
