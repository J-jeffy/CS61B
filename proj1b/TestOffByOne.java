import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertFalse(offByOne.equalChars('x', 'x'));
        assertTrue(new Palindrome().isPalindrome("a", offByOne));
        assertTrue(new Palindrome().isPalindrome("ab", offByOne));
        assertFalse(new Palindrome().isPalindrome("aa", offByOne));
        assertTrue(offByOne.equalChars('x', 'y'));
        assertTrue(offByOne.equalChars('z', 'y'));
        assertTrue(offByOne.equalChars('2', '1'));
        assertFalse(offByOne.equalChars('x', 'z'));

    }
    // Your tests go here.
}
