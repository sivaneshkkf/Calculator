package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.Myutils.StatusBar;
import com.example.calculator.databinding.ActivityMainBinding;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity=this;

        Window w=getWindow();
        StatusBar.setColorStatusBar(w,R.color.white,activity);

        assignid(binding.c);
        assignid(binding.rBracket);
        assignid(binding.lBracket);
        assignid(binding.slash);
        assignid(binding.b7);
        assignid(binding.b8);
        assignid(binding.b9);
        assignid(binding.star);
        assignid(binding.b4);
        assignid(binding.b5);
        assignid(binding.b6);
        assignid(binding.plus);
        assignid(binding.b1);
        assignid(binding.b2);
        assignid(binding.b3);
        assignid(binding.minus);
        assignid(binding.AC);
        assignid(binding.b0);
        assignid(binding.dot);
        assignid(binding.equal);

    }

    public void assignid(View v){
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView tv=(TextView) v;
        String buttonText=tv.getText().toString();
        String calculateText=binding.calculate.getText().toString();
        Log.i("" ,"");
        if (buttonText.equals("AC")){
            binding.calculate.setText("");
            binding.result.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            binding.calculate.setText(binding.result.getText().toString());
            return;
        }
        if (buttonText.equals("C")){
            if(calculateText.length()>=2){
                calculateText=calculateText.substring(0,calculateText.length()-1);
            }else{
               calculateText="0";
            }

        }else{
            calculateText=calculateText+buttonText;
        }

        binding.calculate.setText(calculateText);

        String finalresult=getresult(calculateText);
        if(!finalresult.equals("ERR")){
            binding.result.setText(finalresult);
        }

    }
    String getresult(String data){

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            String finalresult="0";
            Object result = engine.eval(data);
            finalresult=result.toString();

            if(finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;
        } catch (ScriptException e) {
            return "ERR";
        }
    }
}