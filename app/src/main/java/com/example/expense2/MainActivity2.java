package com.example.expense2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import Model.Contribution;
import Utils.Util;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    ListView listView2;
    Button b1,b2,b3;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    List<Contribution> allCont;
    int s=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        allCont = MainActivity.databaseHandler.getAllCont();
        listView  = (ListView) findViewById(R.id.main_list);
        b1=(Button) findViewById(R.id.button4);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        };
        b1.setOnClickListener(onClickListener);
        b2=(Button) findViewById(R.id.button5);
        b3=(Button) findViewById(R.id.button6);
        listView2=(ListView)findViewById(R.id.header);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        adapter2.add("   ID   |     ТИП     |     ЦЕЛЬ     |  СУММА  ");
        listView2.setAdapter(adapter2);
        showFull(null);
        listView.setAdapter(adapter);
    }
    public void showIncome(View view){
        adapter2.remove(adapter2.getItem(1));
        adapter.clear();
        for (Contribution c: allCont){
            String type ="";
            if (c.getType()==1){
                s+=c.getValue();
                type = "Доход";
                adapter.add(c.getId()+"     |    "+type+"    |    "+c.getGoal()+"    |    "+c.getValue());
            }
        }
        adapter2.add("Доход: "+s);
        s=0;
        }
        public void showExpense(View view){
            adapter2.remove(adapter2.getItem(1));

            String type ="";
            adapter.clear();
            for (Contribution c: allCont){
            if (c.getType()==-1){
                s+=c.getValue();
                type = "Расход";
                adapter.add(c.getId()+"     |    "+type+"    |    "+c.getGoal()+"    |    "+c.getValue());
            }
            //{type="Доход";}
            }
            adapter2.add("Расход: "+s);
            s=0;
}
    public void showFull(View view){
        if(adapter2.getCount()>=2){
        adapter2.remove(adapter2.getItem(1));}
        adapter.clear();
        for (Contribution c: allCont) {
            String type ="";
            s+=c.getValue()*c.getType();
            if (c.getType()==-1){
                type = "Расход";
            }
            else{type="Доход";}
            adapter.add(c.getId()+"     |    "+type+"    |    "+c.getGoal()+"    |    "+c.getValue());

        }
        adapter2.add("Доход/Расход: "+s);
        s=0;
    }









}


