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

    public static <T> Map<Integer, List<SimpleTreeNode<T>>> assignNodeLevelsRecursively(SimpleTree<T> simpleTree) {
        Map<Integer, List<SimpleTreeNode<T>>> nodeLevelMap = new HashMap<>();
        if (simpleTree.Root != null) assignNodeLevelsRecursively(simpleTree.Root, nodeLevelMap);
        return nodeLevelMap;
    }

    private static <T> void assignNodeLevelsRecursively(SimpleTreeNode<T> root, Map<Integer, List<SimpleTreeNode<T>>> nodeLevelMap) {
        if (root.Children == null) return;
        for (SimpleTreeNode<T> child : root.Children) {
            assignNodeLevelsRecursively(child, nodeLevelMap);
            int level = getNodeLevel(child);
            nodeLevelMap.computeIfAbsent(level, nodes -> new ArrayList<>()).add(child);
        }
    }

    private static <T> int getNodeLevel(SimpleTreeNode<T> root) {
        if (root.Parent == null) return 0;
        return 1 + getNodeLevel(root.Parent);
    }
}