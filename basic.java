import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FibonacciWord {
    
    public static List<Result> fibWord(int n) {
        List<Result> result = new ArrayList<>();
        
        if (n < 1) {
            return result;
        }
        
        // Initialize first two words
        String word1 = "1";  // F_Word1
        String word2 = "0";  // F_Word2
        
        // Generate F_Word1 if n >= 1
        if (n >= 1) {
            result.add(new Result(1, word1.length(), calculateEntropy(word1), word1));
        }
        
        // Generate F_Word2 if n >= 2
        if (n >= 2) {
            result.add(new Result(2, word2.length(), calculateEntropy(word2), word2));
        }
        
        // Generate remaining words
        for (int i = 3; i <= n; i++) {
            String newWord = word2 + word1;  // F_Wordn = F_Wordn-1 + F_Wordn-2
            result.add(new Result(i, newWord.length(), calculateEntropy(newWord), newWord));
            
            // Update for next iteration
            word1 = word2;
            word2 = newWord;
        }
        
        return result;
    }
    
    private static double calculateEntropy(String word) {
        if (word.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : word.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        double entropy = 0;
        int length = word.length();
        
        for (int count : freq.values()) {
            double p = (double) count / length;
            entropy -= p * (Math.log(p) / Math.log(2));  // log2(p) = ln(p) / ln(2)
        }
        
        return Math.round(entropy * 100000000.0) / 100000000.0;
    }
    
    public static class Result {
        public int N;
        public int Length;
        public double Entropy;
        public String Word;
        
        public Result(int n, int length, double entropy, String word) {
            this.N = n;
            this.Length = length;
            this.Entropy = entropy;
            this.Word = word;
        }
        
        @Override
        public String toString() {
            return String.format("{N=%d, Length=%d, Entropy=%.8f, Word=%s}", 
                N, Length, Entropy, Word);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(fibWord(5));
        System.out.println(fibWord(7));
    }
}
