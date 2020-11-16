package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    AvlBst() {
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = root;
        node = findByKey(node, key);

        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node nodeToReturn = findByKey(root, key);
        root = removeByKey(root, key);
        return nodeToReturn == null ? null : nodeToReturn.value;
    }

    @Override
    public Key min() {
        Node nodeMinKey = findNodeMinKey(root);
        return nodeMinKey == null ? null : nodeMinKey.key;
    }

    @Override
    public Value minValue() {
        Node nodeMinKey = findNodeMinKey(root);
        return nodeMinKey == null ? null : nodeMinKey.value;
    }

    @Override
    public Key max() {
        Node nodeMaxKey = findNodeMaxKey(root);
        return nodeMaxKey == null ? null : nodeMaxKey.key;
    }

    @Override
    public Value maxValue() {
        Node nodeMaxKey = findNodeMaxKey(root);
        return nodeMaxKey == null ? null : nodeMaxKey.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node node = getFloor(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = getCeil(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int compare = node.key.compareTo(key);

        if (compare == 0) {
            node.value = value;
        }
        else if (compare > 0) {
            node.left = put(node.left, key, value);
        }
        else {
            node.right = put(node.right, key, value);
        }

        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node removeByKey(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = node.key.compareTo(key);

        if (compare == 0) {
            node = innerDelete(node);
        }
        else if (compare < 0) {
            node.right = removeByKey(node.right, key);
        }
        else {
            node.left = removeByKey(node.left, key);
        }

        return node;
    }

    private Node innerDelete(Node node) {
        size--;
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }

        Node tmp = node;

        node = findNodeMinKey(tmp.right);
        node.right = deleteMin(tmp.right);
        node.left = tmp.left;

        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node findByKey(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = node.key.compareTo(key);

        if (compare == 0) {
            return node;
        }
        else if (compare < 0) {
             return findByKey(node.right, key);
        }
        else {
            return findByKey(node.left, key);
        }
    }

    private Node findNodeMinKey(Node root) {
        Node node = root;

        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node findNodeMaxKey(Node root) {
        Node node = root;

        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private Node getCeil(Node root, Key key) {
        if (root == null) {
            return null;
        }

        Node node = root;

        int compare = key.compareTo(node.key);

        if (compare == 0) {
            return node;
        }

        if (compare > 0) {
            return getCeil(node.right, key);
        }

        Node left = getCeil(node.left, key);
        return left == null ? node : left;
    }

    private Node getFloor(Node root, Key key) {

        if (root == null) {
          return null;
        }

        Node node = root;

        int compare = key.compareTo(node.key);

        if (compare == 0) {
            return node;
        }

        if (compare < 0) {
            return getFloor(node.left, key);
        }

        Node right = getFloor(node.right, key);

        return right == null ? node : right;
    }

    private Node balance(Node node) {
        if (getHeightDiff(node) == 2) {
            if (getHeightDiff(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
           return rotateRight(node);
        }
        if (getHeightDiff(node) == -2) {
            if (getHeightDiff(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private int getHeightDiff(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node parent) {
        Node children = parent.left;
        parent.left = children.right;
        children.right = parent;

        fixHeight(parent);
        fixHeight(children);

        return children;
    }

    private Node rotateLeft(Node parent) {
        Node children = parent.right;
        parent.right = children.left;
        children.left = parent;

        fixHeight(parent);
        fixHeight(children);
        return children;
    }
}

