package org.creativethinkers.dictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class DictionaryActivity extends SimpleActivity {
    // A list of words and definitions to show in our app.
    // In a later lecture, we will read the words from a file instead.

    // a dictionary of {word -> definition} pairs for lookup
    private Map<String, String> dictionary;
    private List<String> words;

    //pick the word
    //pick 5 random defns
    //show all of that on the screen
    private void chooseWords() {
        Random randy = new Random();
        int randomIndex = randy.nextInt(words.size());
        String theWord = words.get(randomIndex);
        String theDefn = dictionary.get(theWord);

        List<String> defns = new ArrayList<>(dictionary.values());
        defns.remove(theDefn);
        Collections.shuffle(defns);
        defns = defns.subList(0, 4);
        defns.add(theDefn);
        Collections.shuffle(defns);

        $TV(R.id.the_word).setText(theWord);
        SimpleList.with(this)
                .setItems(R.id.word_list, defns);
    }

    /*
     * This method runs when the app is first loading up.
     * It sets up the dictionary of words and definitions.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        Scanner scan = new Scanner(getResources().openRawResource(R.raw.grewords2));

        dictionary = new HashMap<>();
        words = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if (parts.length < 2) continue;
            dictionary.put(parts[0], parts[1]);
            words.add(parts[0]);
        }

        chooseWords();

        // put the dictionary words into an Adapter so they can be seen in ListView
//        ListView list = (ListView) findViewById(R.id.word_list);
        ListView list = $(R.id.word_list);

        // this is the code that should run when the user taps items in the list
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // look up definition of word and display as a Toast
                String defnClicked = parent.getItemAtPosition(position).toString();
                String theWord = $TV(R.id.the_word).getText().toString();
                String correctDefn = dictionary.get(theWord);

                if (defnClicked.equals(correctDefn)) {
                    toast("Awesome");
                } else {
                    toast("Incorrect");
                }
                chooseWords();
            }
        });
    }
}


