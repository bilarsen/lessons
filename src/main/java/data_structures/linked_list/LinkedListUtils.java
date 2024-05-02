package data_structures.linked_list;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkedListUtils {

    public static LinkedList sumEqualLinkedLists(LinkedList first, LinkedList second) {
        LinkedList result = new LinkedList();
        if (first.count() == second.count()) {
            LinkedList.Node nodeFirst = first.getHead();
            LinkedList.Node nodeSecond = second.getHead();
            while (nodeFirst != null) {
                int sum = nodeFirst.getValue() + nodeSecond.getValue();
                result.addInTail(new LinkedList.Node(sum));
                nodeFirst = nodeFirst.getNext();
                nodeSecond = nodeSecond.getNext();
            }
        }
        return result;
    }
}
