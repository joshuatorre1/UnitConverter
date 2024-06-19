package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private static final String[] MEASURING_UNITS = {"Length", "Weight", "Volume", "Time"};
    private static final String[] LENGTH_UNITS = {"Kilometer", "Meter", "Centimeter", "Millimeter", "Micrometer", "Nanometer", "Yard", "Foot", "Inch", "Nautical Mile"};
    private static final String[] WEIGHT_UNITS = {"Kilogram", "Gram", "Milligram", "Micrograms", "Pound", "Ounce"};

    private AutoCompleteTextView categoryAutoComplete, fromUnitAutoComplete, toUnitAutoComplete;
    private TextInputEditText inputFieldEdit, outputFieldEdit;
//    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize elements
        categoryAutoComplete = findViewById(R.id.menu_autocomplete);
//        fromUnitAutoComplete = findViewById(R.id.from_unit_autocomplete);
//        toUnitAutoComplete = findViewById(R.id.to_unit_autocomplete);
//        inputFieldEdit = findViewById(R.id.input_field_edit);
//        outputFieldEdit = findViewById(R.id.output_field_edit);

        Button convertButton = findViewById(R.id.convert_button);

        TextInputLayout textInputLayout = findViewById(R.id.menu);
        MaterialAutoCompleteTextView autoCompleteTextView = (MaterialAutoCompleteTextView) textInputLayout.getEditText();
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setSimpleItems(MEASURING_UNITS);
        }

        // Updates the FROM and TO units whenever the CATEGORY menu is changed.
        categoryAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = (String) parent.getItemAtPosition(position);
            System.out.println(selectedCategory);
            updateUnits(selectedCategory);
        });

        // Listener for button click
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });

    }

    private void updateUnits(String unitType) {
        String[] listOfUnits;
        if (unitType.equals("Length")) {
            listOfUnits = LENGTH_UNITS;
        } else if (unitType.equals("Weight")) {
            listOfUnits = WEIGHT_UNITS;
        } else {
            listOfUnits = null;
        }

        if (listOfUnits != null) {
            TextInputLayout fromUnitLayout = findViewById(R.id.from_unit);
            MaterialAutoCompleteTextView fromUnitAutoComplete = (MaterialAutoCompleteTextView) fromUnitLayout.getEditText();
            if (fromUnitAutoComplete != null) {
                fromUnitAutoComplete.setSimpleItems(listOfUnits);
            }

            TextInputLayout toUnitLayout = findViewById(R.id.to_unit);
            MaterialAutoCompleteTextView toUnitAutoComplete = (MaterialAutoCompleteTextView) toUnitLayout.getEditText();
            if (toUnitAutoComplete != null) {
                toUnitAutoComplete.setSimpleItems(listOfUnits);
            }
        }
    }

    private void convertUnits() {
        String fromUnit = fromUnitAutoComplete.getText().toString();
        String toUnit = toUnitAutoComplete.getText().toString();

        System.out.println(fromUnit);
        System.out.println(toUnit);

        double inputValue;

        try {
            inputValue = Double.parseDouble(inputFieldEdit.getText().toString());
        } catch (NumberFormatException e) {
//            resultTextView.setText(R.string.invalid_input);
            return;
        }

        double result = UnitConverter.performConversion(fromUnit, toUnit, inputValue);
//        resultTextView.setText(String.valueOf(result));
    }

}