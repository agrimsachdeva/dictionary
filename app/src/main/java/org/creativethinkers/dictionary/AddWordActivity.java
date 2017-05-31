package org.creativethinkers.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.PrintStreamPrinter;
import android.view.View;

import java.io.PrintStream;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;


public class AddWordActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Intent intent = getIntent();
        String text = intent.getStringExtra("initialtext"); //foobar
        $ET(R.id.new_word).setText(text);
    }

    public void addThisWordClick(View view) {
    //add given word to dictionary
        String newWord = $ET(R.id.new_word).getText().toString();
        String newDefn = $ET(R.id.new_defn).getText().toString();

        PrintStream output = new PrintStream(openFileOutput("added_words.txt", MODE_APPEND | MODE_PRIVATE));
        output.println(newWord + "\t" + newDefn);
        output.close();

        //go back to start menu activity
        Intent goBack = new Intent();
        goBack.putExtra("newword", newWord);
        setResult(RESULT_OK, goBack);

        finish();
    }
}
