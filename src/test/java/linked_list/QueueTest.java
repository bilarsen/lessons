package linked_list;

import data_structures.linked_list.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    public void setup() {
        queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
    }

    @Test
    @DisplayName("checking size")
    void testSize() {
        assertEquals(10, queue.size());

        queue = new Queue<>();
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("enqueueing")
    void testEnqueue() {
        queue.enqueue(11);
        assertEquals(11, queue.size());
    }

    @Test
    @DisplayName("dequeueing")
    void testDequeue() {
        assertEquals(10, queue.size());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(9, queue.size());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(8, queue.size());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(7, queue.size());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(6, queue.size());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(5, queue.size());
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(4, queue.size());
        assertEquals(Integer.valueOf(7), queue.dequeue());
        assertEquals(3, queue.size());
        assertEquals(Integer.valueOf(8), queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals(Integer.valueOf(9), queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals(Integer.valueOf(10), queue.dequeue());
        assertEquals(0, queue.size());

        assertNull(queue.dequeue());
        queue = new Queue<>();
        assertNull(queue.dequeue());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
    }

    @Test
    @DisplayName("rotating")
    void testRotate() {
        // empty outgoing stack
        queue.rotate(12);
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(7), queue.dequeue());
        assertEquals(Integer.valueOf(8), queue.dequeue());
        assertEquals(Integer.valueOf(9), queue.dequeue());
        assertEquals(Integer.valueOf(10), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());

        // empty queue
        queue.rotate(100000000);
        assertEquals(0, queue.size());
        assertNull(queue.dequeue());

        // empty incoming stack
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        assertEquals(Integer.valueOf(0), queue.dequeue());
        assertEquals(6, queue.size());

        queue.rotate(5);
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());

        assertEquals(0, queue.size());
        assertNull(queue.dequeue());

        // both incoming and outgoing stacks are not empty

        // shift == outgoing.size()
        rotateSetup();
        queue.rotate(3);
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());

        // shift < outgoing.size()
        rotateSetup();
        queue.rotate(2);
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());

        // shift > outgoing.size()
        rotateSetup();
        queue.rotate(17); // 17 % 6 == 5
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());

        // shift == 0
        rotateSetup();
        queue.rotate(0);
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());

        // shift == queue.size() == 0
        rotateSetup();
        queue.rotate(6);
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());

        // shift < 0 (rotate to the left)
        rotateSetup();
        queue.rotate(-8);
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(6), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
    }

    private void rotateSetup() {
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.dequeue();

        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
    }
}
