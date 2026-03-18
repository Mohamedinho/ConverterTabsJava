package com.example.convertertabsjava;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TempFragment extends Fragment {

    private RadioGroup rgTemp;
    private RadioButton rbCtoF;
    private EditText etTempInput;
    private Button btnConvertTemp;
    private TextView tvTempResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp, container, false);

        rgTemp = view.findViewById(R.id.rgTemp);
        rbCtoF = view.findViewById(R.id.rbCtoF);
        etTempInput = view.findViewById(R.id.etTempInput);
        btnConvertTemp = view.findViewById(R.id.btnConvertTemp);
        tvTempResult = view.findViewById(R.id.tvTempResult);

        btnConvertTemp.setOnClickListener(v -> {
            String input = etTempInput.getText().toString();
            if (TextUtils.isEmpty(input)) {
                Toast.makeText(getContext(), "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double val = Double.parseDouble(input);
                double result;

                if (rbCtoF.isChecked()) {
                    // Celsius to Fahrenheit: (C * 9/5) + 32
                    result = (val * 1.8) + 32;
                } else {
                    // Fahrenheit to Celsius: (F - 32) * 5/9
                    result = (val - 32) / 1.8;
                }

                tvTempResult.setText(String.format(Locale.getDefault(), "Résultat : %.2f", result));
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Valeur invalide", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
