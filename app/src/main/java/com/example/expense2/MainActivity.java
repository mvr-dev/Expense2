package com.example.expense2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.expense2.R;

import java.util.ArrayList;
import java.util.List;

import Data.DatabaseHandler;
import Model.Contribution;
import Utils.Util;

public class MainActivity extends AppCompatActivity {
    int income = -1;
    EditText editText;
    EditText editTextNum;
    Button button;
    TextView textView;
    public static DatabaseHandler databaseHandler;
    Button delButton;
    Switch aSwitch;
    Button showTable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);
        button=(Button) findViewById(R.id.button2);
        delButton=(Button)findViewById(R.id.button);
        editText=(EditText) findViewById(R.id.editTextText);
        editTextNum=(EditText) findViewById(R.id.editTextNumber);
        textView= findViewById(R.id.textView2);
        aSwitch=(Switch)findViewById(R.id.switch1);
        showTable=(Button)findViewById(R.id.button3);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        };
        showTable.setOnClickListener(onClickListener);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked){
                    income =1;
                }
                else{
                    income =-1;
                }
            }
        });

//        databaseHandler.addCont(new Contribution(-1,"Buy",1000));
//        databaseHandler.addCont(new Contribution(1,"Sell",1000));
        List<Contribution> contributions = databaseHandler.getAllCont();
        for (Contribution c: contributions){
            Log.d("Cont:","ID "+c.getId()+" type "+c.getType()+" goal "+c.getGoal()+" value "+c.getValue());
        }
    }

    public void addContInTable(View view) {
        if(editTextNum.getText().length()!=0 && editText.getText().length()!=0){
            Contribution contribution = new Contribution(income,editText.getText().toString(),
                    Integer.parseInt(editTextNum.getText().toString()));
            databaseHandler.addCont(contribution);
            textView.setText("Успешно");
        } else if (editTextNum.getText().length()!=0) {
            Contribution contribution = new Contribution(income,Integer.parseInt(editTextNum.getText().toString()));
            databaseHandler.addCont(contribution);
            textView.setText("Успешно");
        }
        else{
            textView.setText("Ошибка");
        }
    }
    public void deleteTable(View view){
        databaseHandler.onUpgrade(this.databaseHandler.getWritableDatabase(), Util.DATABASE_VERSION,Util.DATABASE_VERSION+1);
        textView.setText("Таблица отчищена");
    }

}