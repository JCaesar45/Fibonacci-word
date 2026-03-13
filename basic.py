import math

def fib_word(n):
    if n < 1:
        return []
    
    result = []
    
    # Initialize first two words
    word1 = "1"  # F_Word1
    word2 = "0"  # F_Word2
    
    def calculate_entropy(word):
        if len(word) == 0:
            return 0
        
        freq = {}
        for char in word:
            freq[char] = freq.get(char, 0) + 1
        
        entropy = 0
        length = len(word)
        
        for count in freq.values():
            p = count / length
            entropy -= p * math.log2(p)
        
        return round(entropy, 8)
    
    # Generate F_Word1 if n >= 1
    if n >= 1:
        result.append({
            "N": 1,
            "Length": len(word1),
            "Entropy": calculate_entropy(word1),
            "Word": word1
        })
    
    # Generate F_Word2 if n >= 2
    if n >= 2:
        result.append({
            "N": 2,
            "Length": len(word2),
            "Entropy": calculate_entropy(word2),
            "Word": word2
        })
    
    # Generate remaining words
    for i in range(3, n + 1):
        new_word = word2 + word1  # F_Wordn = F_Wordn-1 + F_Wordn-2
        result.append({
            "N": i,
            "Length": len(new_word),
            "Entropy": calculate_entropy(new_word),
            "Word": new_word
        })
        
        # Update for next iteration
        word1, word2 = word2, new_word
    
    return result

# Test
print(fib_word(5))
print(fib_word(7))
