package com.example.myfirstapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private TextView result;
    private Button cipher;
    private Button decipher;
    private EditText numberOne;
    private EditText numberTwo;
    private Button copy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.editText_cipher);
        result = findViewById(R.id.textView_result);
        cipher =findViewById(R.id.button);
        decipher=findViewById(R.id.button2);
        numberOne=findViewById(R.id.editTextCipher1);
        numberTwo=findViewById(R.id.editTextCipher2);
        copy=findViewById(R.id.buttonCopy);

        // result.setTextIsSelectable(true);


        cipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOne.length() != 0 && numberTwo.length() != 0) {
                    Cipher(input.getText().toString()
                            , Integer.parseInt(numberOne.getText().toString())
                            , Integer.parseInt(numberTwo.getText().toString()));
                }
                else{
                    Toast.makeText(MainActivity.this, "Please fill Key1 and Key2", Toast.LENGTH_LONG).show();
                }
            }
        });

        decipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOne.length() != 0 && numberTwo.length() != 0) {
                Decipher(input.getText().toString()
                        , Integer.parseInt(numberOne.getText().toString())
                        , Integer.parseInt(numberTwo.getText().toString()));
            }
                else{
                    Toast.makeText(MainActivity.this, "Please fill Key1 and Key2", Toast.LENGTH_LONG).show();
                }
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText",result.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this,"Copied.",Toast.LENGTH_SHORT).show();
            }

        });

    }


    void Cipher(String input, int numberOne, int numberTwo) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i =0; i<input.length(); i++) {
           char[] newArray=input.toCharArray();
           if (i%2==0){
               newArray[i]+=numberOne;
           }
           else{
               newArray[i]+=numberTwo;
           }
           char newSymbol=newArray[i];
           encryptedText.append(newSymbol);
        }
        result.setText(encryptedText.toString());
    }
    void Decipher(String input,int numberOne, int numberTwo) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i =0; i<input.length(); i++) {
            char[] newArray=input.toCharArray();
            if (i%2==0){
                newArray[i]-=numberOne;
            }
            else{
                newArray[i]-=numberTwo;
            }
            char newSymbol=newArray[i];
            encryptedText.append(newSymbol);
        }

        result.setText(encryptedText.toString());

    }
}