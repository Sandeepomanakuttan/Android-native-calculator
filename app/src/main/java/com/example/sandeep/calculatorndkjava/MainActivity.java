package com.example.sandeep.calculatorndkjava;

import static com.example.sandeep.calculatorndkjava.R.id.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("calculatorndkjava");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * A native method that is implemented by the 'calculatorndkjava' native library,
     * which is packaged with this application.
     */
   // public native String stringFromJNI();
    public native void ToTextView(String expression,Object target);

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
    final int[] digitButtonIDs = {
            btn0,
            btn1,
            btn2,
            btn3,
            btn4,
            btn5,
            btn6,
            btn7,
            btn8,
            btn9,
            btn2Zero,
            btn,
    };

    TextView input=findViewById(R.id.input);
    int btnID=v.getId();

    switch (btnID){
        case btnequal:{
            TextView tv = findViewById(result);
            String s=input.getText().toString();
            if (!s.equals("")) {
                ToTextView(input.getText().toString(), tv);
            }else{
                Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        case openpra:{
            input.append("(");
            break;
        }
        case closspra:{
            input.append(")");
            break;
        }
        case buttonPow: {
            input.append("^");
            break;
        }
        case btnClear:{
            input.setText("");
            TextView tv=findViewById(result);
            tv.setText("");
            break;
        }

        case btnback:{
            String pre = input.getText().toString();
            if (pre.length()!=0){
                pre = pre.substring(0,pre.length()-1);
            }
            input.setText(pre);
            break;
        }
        case btnplus:{
            input.append("+");
            break;
        }

        case btnmul:{
            input.append("*");
            break;
        }

        case btnsub:{
            input.append("-");
            break;
        }

        case btndiv:{
            input.append("/");
            break;
        }

        case btnper:{
            input.append("%");
            break;
        }

        default:{
            for (int i=0;i<digitButtonIDs.length;++i){

                if(i<digitButtonIDs.length-2) {
                    if (btnID == digitButtonIDs[i]) {
                        input.append(String.valueOf(i));
                        break;
                    }
                }
                else if (i<digitButtonIDs.length-1){
                    if (btnID == digitButtonIDs[i]) {
                        input.append("00");
                        break;
                    }
                }
                else{
                    if (btnID == digitButtonIDs[i]) {
                        input.append(".");
                        break;
                    }
                }
            }
            break;
        }

    }
    }
}

