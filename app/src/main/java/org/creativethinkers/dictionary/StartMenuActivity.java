package org.creativethinkers.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import stanford.androidlib.SimpleActivity;

public class StartMenuActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void playTheGameClick(View view) {
        //go to the DICTIONARY ACTIVITY
        Intent intent = new Intent(this, DictionaryActivity.class);
        startActivityForResult(intent, 1234);
    }

    public void addANewWordClick(View view) {
        //go to the ADD NEW WORD ACTIVITY
        Intent intent = new Intent(this, AddWordActivity.class);
        intent.putExtra("intialtext", "foobar");
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1234) {
            String newWord = intent.getStringExtra("newword");
            toast("you added " + newWord);
        }
        //intent
        // -> "new word"
    }
}
