import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    @Test
    public void testOffByN() {
        OffByN byN = new OffByN(5);
        assertTrue(byN.equalChars('a','f'));
        assertTrue(byN.equalChars('f','a'));
        assertFalse(byN.equalChars('f','h'));
    }
}
