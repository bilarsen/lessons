import java.util.*;

public class AlgorithmsDataStructures2 {

    public static int[] GenerateBBSTArray(int[] a) {
        Arrays.sort(a);
        int[] result = new int[a.length];
        generateBBSTArray(a, result, 0, a.length - 1, 0);
        return result;
    }

    private static void generateBBSTArray(int[] incoming, int[] outgoing, int start, int end, int index) {
        if (start > end) return;

        int mid = (start + end + 1) / 2;
        outgoing[index] = incoming[mid];

        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (left < incoming.length) generateBBSTArray(incoming, outgoing, start, mid - 1, left);
        if (right < incoming.length) generateBBSTArray(incoming, outgoing, mid + 1, end, right);
    }
}