import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleTreeUtils {

    public static <T> Map<Integer, List<SimpleTreeNode<T>>> assignNodeLevels(SimpleTree<T> simpleTree) {
        Map<Integer, List<SimpleTreeNode<T>>> nodeLevelMap = new HashMap<>();
        SimpleTreeNode<T> root = simpleTree.Root;
        int level = 1;
        nodeLevelMap.computeIfAbsent(level, nodes -> new ArrayList<>()).addAll(root.Children);
        int count = 1;
        while (count < simpleTree.Count()) {
            for (SimpleTreeNode<T> child : nodeLevelMap.get(level)) {
                if (child.Children != null) {
                    nodeLevelMap.computeIfAbsent(level + 1, nodes -> new ArrayList<>()).addAll(child.Children);
                }
                count++;
            }
            level++;
        }
        return nodeLevelMap;
    }
}