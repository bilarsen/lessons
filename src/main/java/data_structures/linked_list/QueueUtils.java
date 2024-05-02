package data_structures.linked_list;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueueUtils {

    public static <T> void rotate(Queue<T> queue, int positions) {
        if (queue.size() == 0) {
            return;
        }
        int shift = positions % queue.size();
        if (shift == 0) {
            return;
        }
        shift = shift < 0 ? shift + queue.size() : shift;
        for (int i = 0; i < shift; i++) {
            queue.enqueue(queue.dequeue());
        }
    }
}
