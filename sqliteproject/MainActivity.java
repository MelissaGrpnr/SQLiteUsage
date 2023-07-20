package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null );
            //Bir database oluşturduk adını modunu ve cursoru yazdık
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(ID INTEGER PRIMARY KEY, name VARCHAR, age INT)");

            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('John', 32)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Alice', 28)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Yushi', 43)");


            //alanın içindeki imleci ifade eder bu imleç alan içinde hareket eder.
            Cursor cursor = database.rawQuery("SELECT *  FROM musicians WHERE AGE>30", null);
            //Yer imleci gibi düşünebiliriz hücreleri tek tek gezerek dataları okur

            int nameIx= cursor.getColumnIndex("name");
            int ageIx= cursor.getColumnIndex("age");
            int ID= cursor.getColumnIndex("ID");


            while(cursor.moveToNext()){
                System.out.println("ID:" + cursor.getInt(ID));
                System.out.println("Name:" + cursor.getString(nameIx));
                System.out.println("Age:" + cursor.getInt(ageIx));
            }

            cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //alınan hataları logcatte gösterir.
        }

    }
}