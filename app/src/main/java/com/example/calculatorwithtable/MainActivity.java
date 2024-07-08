package com.example.calculatorwithtable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {

    private final char add = '+';
    private final char sub = '-';
    private final char mul = '*';
    private final char div = '/';
    private final char percent = '%';
    private char curr;
    private double first = Double.NaN;
    private double second;
    private TextView input, output;
    private DecimalFormat decimalFormat;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnMul, btnAdd,
            btnDiv, btnPercent, btnSub, btnOff, btnClear, btnEqual, history;


    ArrayList<String> calculus = new ArrayList<>();
    int i = 0;
    int c = 0;
    int z = 0;
    int k=0;
    String out;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        history = findViewById(R.id.historyWatch);

        btnAdd = findViewById(R.id.plus);
        btnSub = findViewById(R.id.minus);
        btnMul = findViewById(R.id.multiply);
        btnDiv = findViewById(R.id.division);
        btnPercent = findViewById(R.id.percent);
        btnDot = findViewById(R.id.point);
        btnOff = findViewById(R.id.off);
        btnClear = findViewById(R.id.clear);
        btnEqual = findViewById(R.id.equal);

        btn0.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + "9");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations();
                curr = add;
                if (i != 0) {
                    calculus.add(" " + curr + " ");
                }

                output.setText(decimalFormat.format(first) + "+");
                input.setText(null);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations();
                curr = sub;
                if (i != 0) {
                    calculus.add(" " + curr + " ");
                }
                output.setText(decimalFormat.format(first) + "-");
                input.setText(null);
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations();
                curr = mul;
                if (i != 0) {
                    calculus.add(" " + curr + " ");
                }
                output.setText(decimalFormat.format(first) + "*");
                input.setText(null);
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations();
                curr = div;
                if (i != 0) {
                    calculus.add(" " + curr + " ");
                }
                output.setText(decimalFormat.format(first) + "/");
                input.setText(null);
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations();
                curr = percent;
                output.setText(decimalFormat.format(first) + "%");
                input.setText(null);
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText() + ".");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().length() > 0) {
                    CharSequence currentText = input.getText();
                    input.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    first = Double.NaN;
                    second = Double.NaN;
                    input.setText("");
                    output.setText("");
                }
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ArrayList<String>[] tot = new ArrayList[]{new ArrayList<>()};

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = 1;
                calculations();
                out = "";
                for (String s : calculus) {
                    out += s;
                }
                output.setText(decimalFormat.format(first));

                String temp = "";
                if (z == 0) {
                    saveStringToFile(out);
                } else {
                    tot[0] = readFileAsString(getFilesDir().getAbsolutePath()
                            + "/.FolderName/FileName.txt");
                    for (String s : tot[0]) {
                        temp += s;
                        temp += "\n";
                    }

                    String temp3 = temp  + out;
                    saveStringToFile(temp3);
                }
                calculus.clear();
                i = 0;
                c = 0;
                k=0;
                first = Double.NaN;
                curr = '0';
                z += 1;
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History_list.class);
                startActivity(intent);
            }
        });


    }

    void saveStringToFile(String str) {
        File root = getFilesDir();
        File dir = new File(root.getAbsolutePath() + "/.FolderName");
        dir.mkdirs(); // build directory
        File file = new File(dir, "FileName.txt"); // build file

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFileAsString(String Path) {
        ArrayList<String> total = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Path));
            String line = reader.readLine();

            while (line != null) {
                total.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }




    private void calculations() {
        if (!Double.isNaN(first)) {
            second = Double.parseDouble(input.getText().toString());
            input.setText(null);

            if (i == 0) {
                    calculus.add(decimalFormat.format(first) + " " + curr + " ");
                    calculus.add(decimalFormat.format(second) + " ");
                i++;
            } else if (c == 0) {
                calculus.add(decimalFormat.format(second) + " ");
            }

            k++;

            if (curr == add)
                first = first + second;
            else if (curr == sub)
                first = first - second;
            else if (curr == div)
                first = first / second;
            else if (curr == mul)
                first = first * second;
            else if (curr == percent)
                first = first % second;
        } else {
            try {
                first = Double.parseDouble(input.getText().toString());
            } catch (Exception e) {

            }
        }

        if (k == 1 && c==1) {
            calculus.add(" = " + decimalFormat.format(first));
        }
        if(k!=1 && c==1){
            calculus.add(decimalFormat.format(second) + " = " + decimalFormat.format(first));
        }
    }
}