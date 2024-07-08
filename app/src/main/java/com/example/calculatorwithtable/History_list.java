package com.example.calculatorwithtable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class History_list extends AppCompatActivity {
    TextView textView;
    final ArrayList<String>[] tot = new ArrayList[]{new ArrayList<>()};
    RecyclerView recyclerView;
    MyAdapter adapter;
    Button delete;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        delete = findViewById(R.id.deleteBtn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStringToFile("");
                Toast.makeText(History_list.this, "history delete successfully", Toast.LENGTH_SHORT).show();
            }
        });

        tot[0] = readFileAsString(getFilesDir().getAbsolutePath()
                + "/.FolderName/FileName.txt");

        adapter = new MyAdapter(tot[0]);
        recyclerView.setAdapter(adapter);


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
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
}