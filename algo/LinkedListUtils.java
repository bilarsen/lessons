public class LinkedListUtils {

    public static LinkedList sumEqualLinkedLists(LinkedList first, LinkedList second) {
        LinkedList result = new LinkedList();
        if (first.count() == second.count()) {
            Node nodeFirst = first.head;
            Node nodeSecond = second.head;
            while (nodeFirst != null) {
                int sum = nodeFirst.value + nodeSecond.value;
                result.addInTail(new Node(sum));
                nodeFirst = nodeFirst.next;
                nodeSecond = nodeSecond.next;
            }
        }
        return result;
    }
}
