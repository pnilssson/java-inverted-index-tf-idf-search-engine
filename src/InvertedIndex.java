import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InvertedIndex {
      protected Map<String, Map<Integer, Integer>> mapWordsToDocuments(ArrayList<String> documents) {
          // Creating a HashMap to store each word with a reference of amount of time it exists in each document
          Map<String, Map<Integer, Integer>> allWords = new HashMap<>();
          HashMap<String, String> m = new HashMap<>();
          // Looping through each document
        for(String document : documents) {
            int documentReference = (documents.indexOf(document) + 1);
            // Looping through each word in the current document
            for(String word : getWordsFromDocument(document)) {
                if(!allWords.containsKey(word)) {
                    // Creating a HashMap to be able to store a reference for the current word together with its document
                    Map<Integer, Integer> wordCountWithDocRef = new HashMap<>();
                    // If the word does not exists in our allWords HashMap then add it with a reference to the current document
                    wordCountWithDocRef.put(documentReference, 1);
                    allWords.put(word, wordCountWithDocRef);

                    // If the word exists but has no reference to current document create a new reference to that word and its document
                } else if(allWords.containsKey(word) && !allWords.get(word).containsKey(documentReference)){
                    allWords.get(word).put(documentReference, 1);

                    // If the word exists AND has a reference to current document, increase the count of that word
                } else if(allWords.containsKey(word) && allWords.get(word).containsKey(documentReference)){
                    allWords.get(word).put(documentReference, allWords.get(word).get(documentReference) + 1);
                }
            }
        }
        return allWords;
    }

    // Tokenize each word in the documents to a single string and return all of them as a String Array
    protected String[] getWordsFromDocument(String document) {
        return document.toLowerCase().split(" ");
    }
}
