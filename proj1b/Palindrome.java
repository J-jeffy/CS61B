public class Palindrome {
    /*Given a String, wordToDeque should return a Deque
    where the characters appear in the same order as in the String.
     For example, if the word is “persiflage”, then the returned Deque should have ‘p’ at the front,
     followed by ‘e’, and so forth. Don’t implement wordToDeque yet!*/
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /*判断回文数*/
    public boolean isPalindrome(String word) {
        Deque<Character> characterDeque = wordToDeque(word);
        int n = characterDeque.size() / 2;
        for (int i = 0; i < n; i++) {
            if (characterDeque.removeFirst() != characterDeque.removeLast()) {
                return false;
            }
        }
        return true;
        /*for (int i = 0; i < word.length()/2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;*/
    }

    /*判断回文数*/
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> cd = wordToDeque(word);
        int num = cd.size() / 2;
        for (int i = 0; i < num; i++) {
            if (!cc.equalChars(cd.removeFirst(), cd.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
