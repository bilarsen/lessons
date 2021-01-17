import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

    private Stack<Integer> stack;

    @Before
    public void setup() {
        stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(5, stack.size());

        stack = new Stack<>();
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void testPeek() {
        Assert.assertEquals(Integer.valueOf(5), stack.peek());

        stack.pop();
        Assert.assertEquals(Integer.valueOf(4), stack.peek());
    }

    @Test
    public void testPop() {
        Integer expected = 5;
        while (stack.size() > 0) {
            Integer deleted = stack.pop();
            Assert.assertEquals(expected, deleted);
            expected--;
        }
        Assert.assertNull(stack.peek());

        stack = new Stack<>();
        Assert.assertNull(stack.pop());
    }

    @Test
    public void testPush() {
        Stack<String> list = new Stack<>();
        Assert.assertEquals(0, list.size());
        list.push("hello");
        list.push("world");
        list.push("people");
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("people", list.peek());

        String[] expected = new String[]{"people", "world", "hello"};
        String[] actual = new String[list.size()];
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String temp = list.pop();
            actual[i] = temp;
        }
        Assert.assertArrayEquals(expected, actual);
    }
}