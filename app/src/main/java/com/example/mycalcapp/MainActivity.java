package com.example.mycalcapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{
    private final String DEBUG_TAG = "MyCalC";
    private EditText editNum1;
    private EditText editNum2;
    private Button addBtn;
    private Button subtractBtn;
    private Button multiplyBtn;
    private Button divideBtn;
    private Button resetBtn;
    private TextView myText;
    boolean flag= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            editNum1 = (EditText) findViewById(R.id.num1);
            editNum2 = (EditText) findViewById(R.id.num2);
            addBtn = (Button) findViewById(R.id.add);
            subtractBtn = (Button) findViewById(R.id.subtract);
            multiplyBtn = (Button) findViewById(R.id.multiply);
            divideBtn = (Button) findViewById(R.id.divide);
            resetBtn = (Button) findViewById(R.id.reset);

            addBtn.setOnClickListener(this);
            subtractBtn.setOnClickListener(this);
            multiplyBtn.setOnClickListener(this);
            divideBtn.setOnClickListener(this);
            resetBtn.setOnClickListener(this);

            editNum1.addTextChangedListener(watch);
            editNum2.addTextChangedListener(watch);
            Log.d(DEBUG_TAG, "onCreate: ");
        } catch (Exception E){

        }

        //Toast t1 =
    }


    private TextWatcher watch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String num1 = editNum1.getText().toString();
            String num2 = editNum2.getText().toString();
            flag = !num1.isEmpty() && !num2.isEmpty();
//            if(num1.length()>=15){
//                Toast.makeText(getApplicationContext(), "Maximum length is 15", Toast.LENGTH_SHORT).show();
//            }
                //Toast.makeText(this, "Max length is 15", Toast.LENGTH_LONG).show();
            addBtn.setEnabled(flag);
            subtractBtn.setEnabled(flag);
            multiplyBtn.setEnabled(flag);
            divideBtn.setEnabled(flag);
            if(editNum1.getText().toString().length()==15){
                //Toast.makeText(this, "Max Length is 15", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @Override
    public void onClick(View view){
        String num1 = editNum1.getText().toString();
        String num2 = editNum2.getText().toString();
        flag = !num1.isEmpty() && !num2.isEmpty();
        BigDecimal resultNum = BigDecimal.valueOf(0.0);
        myText = (TextView) findViewById(R.id.result);
        switch (view.getId()){
            case R.id.reset:
                //Toast.makeText(this, "Max Length is 15", Toast.LENGTH_LONG).show();
                editNum1.setText("");
                editNum2.setText("");
                myText.setText("");
                flag=false;
                editNum1.requestFocus();
                break;
            case R.id.add:
                resultNum = BigDecimal.valueOf(Double.parseDouble(editNum1.getText().toString())+Double.parseDouble(editNum2.getText().toString()));
                break;
            case R.id.subtract:
                resultNum = BigDecimal.valueOf(Double.parseDouble(editNum1.getText().toString())- Double.parseDouble(editNum2.getText().toString()));
                break;
            case R.id.multiply:
                resultNum = BigDecimal.valueOf(Double.parseDouble(editNum1.getText().toString())* Double.parseDouble(editNum2.getText().toString()));
                break;
            case R.id.divide:
                if(Double.parseDouble(editNum2.getText().toString())==0){
                    flag=false;
                    myText.setText("NA");
                }
                else
                    resultNum = BigDecimal.valueOf(Double.parseDouble(editNum1.getText().toString())/ Double.parseDouble(editNum2.getText().toString()));
                break;
        }
        if(flag){

            String temp = resultNum.toString();

            myText.setText(temp);
        }

    }


}
