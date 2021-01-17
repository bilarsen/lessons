public class StackUtils {

    public static boolean areParenthesesBalanced(String braces) {
        String[] array = braces.split("");
        Stack<String> stack = new Stack<>();
        for (String str : array) {
            if (str.equals("(")) {
                stack.push(str);
            } else if (str.equals(")")) {
                if (stack.size() == 0) {
                    return false;
                }
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.size() == 0;
    }

    public static int calculatePostfix(String expression) {
        Stack<String> stack = new Stack<>();
        Stack<Integer> result = new Stack<>();
        String[] input = expression.split(" ");
        for (int i = input.length - 1; i >= 0; i--) {
            stack.push(input[i]);
        }
        while (stack.size() > 0) {
            String item = stack.pop();
            if (item.matches("\\b\\d+\\b")) {
                result.push(Integer.valueOf(item));
            } else if (item.matches("[+*/-]")) {
                int operand1 = result.pop();
                int operand2 = result.pop();
                switch (item) {
                    case "+":
                        result.push(operand1 + operand2);
                        break;
                    case "-":
                        result.push(operand1 - operand2);
                        break;
                    case "*":
                        result.push(operand1 * operand2);
                        break;
                    case "/":
                        result.push(operand1 / operand2);
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
            } else if (item.equals("=")) {
                return result.peek();
            } else {
                throw new ArithmeticException();
            }
        }
        return Integer.MIN_VALUE;
    }
}