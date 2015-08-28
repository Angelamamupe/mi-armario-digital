package com.tfg.angela.tfg;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.CharacterPickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        initUi();
    }

    private Spinner spCategory;
    private Spinner spSubCategory;
    private Spinner spStyle;
    private Spinner spWashing;


    private void initUi() {
        // Category spinner
        spCategory = (Spinner) findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO rellenar
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO rellenar
            }
        });

        // SubCategory spinner
        spSubCategory = (Spinner) findViewById(R.id.spSubCategory);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.item_subCategories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSubCategory.setAdapter(adapter2);
        spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO rellenar
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO rellenar
            }
        });

        // Style spinner
        spStyle = (Spinner) findViewById(R.id.spStyle);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.item_cloth_styles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStyle.setAdapter(adapter3);
        spStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO rellenar
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO rellenar
            }
        });

        // Washing spinner
        spWashing = (Spinner) findViewById(R.id.spWashing);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.item_washing, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spWashing.setAdapter(adapter4);
        spWashing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO rellenar
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO rellenar
            }
        });
    }

}
