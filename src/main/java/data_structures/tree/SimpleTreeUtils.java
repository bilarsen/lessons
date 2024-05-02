package data_structures.tree;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleTreeUtils {

    public static <T> Map<Integer, List<SimpleTree.SimpleTreeNode<T>>> assignNodeLevels(SimpleTree<T> simpleTree) {
        Map<Integer, List<SimpleTree.SimpleTreeNode<T>>> nodeLevelMap = new HashMap<>();
        SimpleTree.SimpleTreeNode<T> root = simpleTree.root();
        int level = 1;
        nodeLevelMap.computeIfAbsent(level, nodes -> new ArrayList<>()).addAll(root.getChildren());
        int count = 1;
        while (count < simpleTree.count()) {
            for (SimpleTree.SimpleTreeNode<T> child : nodeLevelMap.get(level)) {
                if (child.getChildren() != null) {
                    nodeLevelMap.computeIfAbsent(level + 1, nodes -> new ArrayList<>()).addAll(child.getChildren());
                }
                count++;
            }
            level++;
        }
        return nodeLevelMap;
    }

    public static <T> Map<Integer, List<SimpleTree.SimpleTreeNode<T>>> assignNodeLevelsRecursively(SimpleTree<T> simpleTree) {
        Map<Integer, List<SimpleTree.SimpleTreeNode<T>>> nodeLevelMap = new HashMap<>();
        if (simpleTree.root() != null) assignNodeLevelsRecursively(simpleTree.root(), nodeLevelMap);
        return nodeLevelMap;
    }

    private static <T> void assignNodeLevelsRecursively(
            SimpleTree.SimpleTreeNode<T> root, Map<Integer, List<SimpleTree.SimpleTreeNode<T>>> nodeLevelMap) {
        if (root.getChildren() == null) return;
        for (SimpleTree.SimpleTreeNode<T> child : root.getChildren()) {
            assignNodeLevelsRecursively(child, nodeLevelMap);
            nodeLevelMap.computeIfAbsent(getNodeLevel(child), nodes -> new ArrayList<>()).add(child);
        }
    }

    private static <T> int getNodeLevel(SimpleTree.SimpleTreeNode<T> root) {
        if (root.getParent() == null) return 0;
        return 1 + getNodeLevel(root.getParent());
    }
}