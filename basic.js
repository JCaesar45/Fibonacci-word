function fibWord(n) {
  if (n < 1) return [];
  
  const result = [];
  
  // Initialize first two words
  let word1 = "1";  // F_Word1
  let word2 = "0";  // F_Word2
  
  // Helper function to calculate entropy
  function calculateEntropy(word) {
    if (word.length === 0) return 0;
    
    const freq = {};
    for (const char of word) {
      freq[char] = (freq[char] || 0) + 1;
    }
    
    let entropy = 0;
    const len = word.length;
    
    for (const char in freq) {
      const p = freq[char] / len;
      entropy -= p * Math.log2(p);
    }
    
    return Math.round(entropy * 100000000) / 100000000;
  }
  
  // Generate F_Word1 if n >= 1
  if (n >= 1) {
    result.push({
      N: 1,
      Length: word1.length,
      Entropy: calculateEntropy(word1),
      Word: word1
    });
  }
  
  // Generate F_Word2 if n >= 2
  if (n >= 2) {
    result.push({
      N: 2,
      Length: word2.length,
      Entropy: calculateEntropy(word2),
      Word: word2
    });
  }
  
  // Generate remaining words
  for (let i = 3; i <= n; i++) {
    const newWord = word2 + word1;  // F_Wordn = F_Wordn-1 + F_Wordn-2
    result.push({
      N: i,
      Length: newWord.length,
      Entropy: calculateEntropy(newWord),
      Word: newWord
    });
    
    // Update for next iteration
    word1 = word2;
    word2 = newWord;
  }
  
  return result;
}

// Test
console.log(fibWord(5));
console.log(fibWord(7));
