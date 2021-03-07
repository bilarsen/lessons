import java.util.*;

public class SimpleTreeNode<T> {

    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {

    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        // ваш код добавления нового дочернего узла существующему ParentNode
        if (ParentNode == null) return;
        if (ParentNode.Children == null) ParentNode.Children = new ArrayList<>();
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        // ваш код удаления существующего узла NodeToDelete
        if (NodeToDelete == null || NodeToDelete.Parent == null) return;
        NodeToDelete.Parent.Children.remove(NodeToDelete);
        if (NodeToDelete.Children != null) {
            for (SimpleTreeNode<T> child : NodeToDelete.Children) {
                AddChild(NodeToDelete.Parent, child);
            }
            NodeToDelete.Children = null;
        }
        NodeToDelete.Parent = null;
        NodeToDelete = null;
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        // ваш код выдачи всех узлов дерева в определённом порядке
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (Root != null) getAllNodes(Root, nodes);
        return nodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        // ваш код поиска узлов по значению
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (Root != null) findNodesByValue(Root, val, nodes);
        return nodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        if (OriginalNode == null || OriginalNode == Root || NewParent == null || NewParent == OriginalNode) return;
        if (OriginalNode.Parent != null) OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        // количество всех узлов в дереве
        if (Root == null) return 0;
        else if (Root.Children == null) return 1;
        return countNodes(Root);
    }

    public int LeafCount() {
        // количество листьев в дереве
        if (Root == null) return 0;
        return countLeaves(Root);
    }

    private void getAllNodes(SimpleTreeNode<T> root, List<SimpleTreeNode<T>> nodes) {
        if (root.Children == null) {
            nodes.add(root);
            return;
        }
        for (SimpleTreeNode<T> node : root.Children) {
            getAllNodes(node, nodes);
        }
        nodes.add(root);
    }

    private void findNodesByValue(SimpleTreeNode<T> root, T value, List<SimpleTreeNode<T>> nodes) {
        if (root.NodeValue.equals(value)) nodes.add(root);
        if (root.Children == null) return;
        for (SimpleTreeNode<T> child : root.Children) {
            findNodesByValue(child, value, nodes);
        }
    }

    private int countNodes(SimpleTreeNode<T> root) {
        int nodes = 1;
        if (root.Children != null) {
            for (SimpleTreeNode<T> child : root.Children) {
                nodes += countNodes(child);
            }
        }
        return nodes;
    }

    private int countLeaves(SimpleTreeNode<T> root) {
        if (root.Children == null || root.Children.size() == 0) return 1;
        int leaves = 0;
        for (SimpleTreeNode<T> child : root.Children) {
            leaves += countLeaves(child);
        }
        return leaves;
    }
}