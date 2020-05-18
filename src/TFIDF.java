import java.util.*;

public class TFIDF {
    // Divide amount of words by total words in document to get the term frequency
    protected double termFrequency(int wordsInDocument, Integer wordCount) {
       return (double) wordCount / wordsInDocument;
    }
    // Log(total number of documents by number of document containing that word) to get the IDF
    // Add 1 to not get 0 if a word exists in all documents
    protected double invertedDocumentFrequency(double numberOfDocuments, double numberOfDocumentWordExistsIn) {
        return Math.log(1 + (numberOfDocuments / numberOfDocumentWordExistsIn));
    }
    // Multiply the return value of TF by the return value of IDF to get the TF-IDF
    protected Map<Integer, Double> tfIdf(Map<String, Map<Integer, Integer>> mappedWords, String searchedWord, ArrayList<String> allDocuments) {
        InvertedIndex invertedIndex = new InvertedIndex();
        Map<Integer, Double> documentsWithRank = new HashMap<>();
        mappedWords.get(searchedWord).forEach((key, value) -> documentsWithRank.put(key, (termFrequency(invertedIndex.getWordsFromDocument(allDocuments.get(key - 1)).length, value)) * (invertedDocumentFrequency(allDocuments.size(), mappedWords.get(searchedWord).size()))));
        return documentsWithRank;
    }
}
