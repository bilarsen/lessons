public class DequeUtils {

    public static boolean isPalindrome(String string) {
        if (string == null) {
            return false;
        }
        Deque<String> deque = new Deque<>();
        String input = string.toLowerCase().replaceAll("\\W", "");
        for (String str : input.split("")) {
            deque.addTail(str);
        }
        for (int i = 0; i < input.length() / 2; i++) {
            if (!deque.removeFront().equals(deque.removeTail())) {
                return false;
            }
        }
        return true;
    }
}