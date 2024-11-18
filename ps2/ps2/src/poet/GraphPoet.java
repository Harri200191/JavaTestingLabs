package poet;

import graph.Graph;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * A class that generates a poetic output by constructing a word affinity graph
 * from a given corpus.
 */
public class GraphPoet {
    
    private final Graph<String> wordGraph;
    
    /**
     * Creates a new GraphPoet with a graph constructed from the given corpus file.
     * 
     * @param corpusFile the file containing the corpus text
     * @throws IOException if the file cannot be read
     */
    public GraphPoet(File corpusFile) throws IOException {
        wordGraph = Graph.empty();
        buildGraph(corpusFile);
    }
    
    /**
     * Builds the word affinity graph using the given corpus file.
     * 
     * @param corpusFile the file containing the corpus text
     * @throws IOException if the file cannot be read
     */
    private void buildGraph(File corpusFile) throws IOException {
        List<String> lines = Files.readAllLines(corpusFile.toPath());
        String corpus = String.join(" ", lines).toLowerCase();
        
        // Split the corpus into words, treating non-alphabetic characters as delimiters.
        String[] words = corpus.split("\\W+");
        
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            if (!word1.isEmpty() && !word2.isEmpty()) {
                wordGraph.set(word1, word2, wordGraph.targets(word1).getOrDefault(word2, 0) + 1);
            }
        }
    }
    
    /**
     * Generates a poetic transformation of the input text.
     * 
     * @param input the input text
     * @return a transformed version of the input text
     */
    public String poem(String input) {
        String[] inputWords = input.split("\\s+"); // Simplified splitting by spaces.
        StringBuilder poemBuilder = new StringBuilder();
        
        for (int i = 0; i < inputWords.length - 1; i++) {
            String word1 = inputWords[i].toLowerCase();
            String word2 = inputWords[i + 1].toLowerCase();
            
            // Append the original word to the output
            poemBuilder.append(inputWords[i]).append(" ");
            
            // Check if word1 and word2 are actual words (not spaces/punctuation)
            if (word1.matches("\\w+") && word2.matches("\\w+")) {
                String bridgeWord = findBridgeWord(word1, word2);
                if (bridgeWord != null) {
                    poemBuilder.append(bridgeWord).append(" ");
                }
            }
        }
        
        // Append the last word from the input.
        poemBuilder.append(inputWords[inputWords.length - 1]);
        
        return poemBuilder.toString().trim();
    }

    /**
     * Finds the bridge word with the highest weight between two given words.
     * 
     * @param word1 the first word
     * @param word2 the second word
     * @return the bridge word with the highest weight, or null if none exists
     */
    private String findBridgeWord(String word1, String word2) {
        Map<String, Integer> word1Targets = wordGraph.targets(word1);
        int maxWeight = 0;
        String bridgeWord = null;
        
        for (String intermediate : word1Targets.keySet()) {
            if (wordGraph.sources(intermediate).containsKey(word2)) {
                int weight = word1Targets.get(intermediate) + wordGraph.sources(intermediate).get(word2);
                if (weight > maxWeight) {
                    maxWeight = weight;
                    bridgeWord = intermediate;
                }
            }
        }
        
        return bridgeWord;
    }
}
