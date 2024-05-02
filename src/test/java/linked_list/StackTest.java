package linked_list;

import data_structures.linked_list.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    public void setup() {
        stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
    }

    @Test
    @DisplayName("checking size")
    void testSize() {
        assertEquals(5, stack.size());

        stack = new Stack<>();
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("peeking")
    void testPeek() {
        assertEquals(Integer.valueOf(5), stack.peek());

        stack.pop();
        assertEquals(Integer.valueOf(4), stack.peek());
    }

    @Test
    @DisplayName("popping")
    void testPop() {
        Integer expected = 5;
        while (stack.size() > 0) {
            Integer deleted = stack.pop();
            assertEquals(expected, deleted);
            expected--;
        }
        assertNull(stack.peek());

        stack = new Stack<>();
        assertNull(stack.pop());
    }

    @Test
    @DisplayName("pushing")
    void testPush() {
        Stack<String> list = new Stack<>();
        assertEquals(0, list.size());
        list.push("hello");
        list.push("world");
        list.push("people");
        assertEquals(3, list.size());
        assertEquals("people", list.peek());

        String[] expected = new String[]{"people", "world", "hello"};
        String[] actual = new String[list.size()];
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String temp = list.pop();
            actual[i] = temp;
        }
        assertArrayEquals(expected, actual);
    }
}