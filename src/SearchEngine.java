import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchEngine {
    private InvertedIndex invertedIndex = new InvertedIndex();

    protected void initiateIndexing() {
        Map<String, Map<Integer, Integer>> mappedWords;
        String doc1 = "The blue ball fell from the very blue sky";
        String doc2 = "The red ball fell from the blue sky";
        String doc3 = "The red red balloon fell from the clear blue sky";
        ArrayList<String> allDocuments = new ArrayList<>();
        allDocuments.add(doc1);
        allDocuments.add(doc2);
        allDocuments.add(doc3);
        mappedWords = invertedIndex.mapWordsToDocuments(allDocuments);
        initiateSearch(mappedWords, allDocuments);
    }

    protected void initiateSearch(Map<String, Map<Integer, Integer>> mappedWords, ArrayList<String> allDocuments) {
        SearchInput searchInput = new SearchInput();
        TFIDF tfidf = new TFIDF();
        String searchedWord;
        do {
            searchedWord = searchInput.getSearchInput();
            if(mappedWords.containsKey(searchedWord)) {
                sortDocuments(tfidf.tfIdf(mappedWords, searchedWord, allDocuments));
            } else {
                printNoMatch();
            }
        } while(!searchedWord.equals("exit"));
    }

    // Sort documents by their TF-IDF results given by previous methods
    protected void sortDocuments(Map<Integer, Double> documentsWithRank) {
        List<Map.Entry<Integer, Double>> listOfDocumentsWithRank = new ArrayList<>(documentsWithRank.entrySet());
        listOfDocumentsWithRank.sort((o1, o2) -> (o2.getValue().compareTo(o1.getValue())));
        displayMostRelevantDocuments(listOfDocumentsWithRank);
    }

    protected void displayMostRelevantDocuments(List<Map.Entry<Integer, Double>> listOfDocumentsWithRank) {
        if (listOfDocumentsWithRank.size() == 1) {
            System.out.print("Document in most relevant order: ");
        } else {
            System.out.print("Documents in most relevant order: ");
        }
        for(Map.Entry<Integer, Double> map : listOfDocumentsWithRank) {
            System.out.print("doc" + map.getKey() + " ");
        }
        System.out.println();
    }

    private void printNoMatch() {
        System.out.println("Word not found in documents");
    }
}
