import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StackTest {
    @Test
    public void addTreeElementsTest() {
        Stack stack = new Stack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        assertEquals(stack.size(), 3);
    }

    @Test
    public void removeTooManyElements() {
        Stack stack = new Stack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        for (int i = 0; i < 10; i++) {
            stack.pop();
        }
        assertEquals(stack.size(), 0);
        assertTrue(stack.isEmpty());
    }
}
