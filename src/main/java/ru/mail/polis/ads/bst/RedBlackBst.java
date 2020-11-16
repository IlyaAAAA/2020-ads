package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
    }

    private Node root;
    private int size;

    RedBlackBst() {
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = root;
        node = findByKey(node, key);

        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
//        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
//        return nodeToReturn == null ? null : nodeToReturn.value;
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

//    private Node put(Node node, Key key, Value value) {
//        if (node == null) {
//            size++;
//            return new Node(key, value);
//        }
//
//        int compare = node.key.compareTo(key);
//
//        if (compare == 0) {
//            node.value = value;
//        } else if (compare > 0) {
//            node.left = put(node.left, key, value);
//        } else {
//            node.right = put(node.right, key, value);
//        }
//
//        fixHeight(node);
//        node = balance(node);
//        return node;
//    }

//    private Node removeByKey(Node node, Key key) {
//        if (node == null) {
//            return null;
//        }
//
//        int compare = node.key.compareTo(key);
//
//        if (compare == 0) {
//            node = innerDelete(node);
//        } else if (compare < 0) {
//            node.right = removeByKey(node.right, key);
//        } else {
//            node.left = removeByKey(node.left, key);
//        }
//
//        return node;
//    }

//    private Node deleteMin(Node node) {
//        if (node.left == null) {
//            return node.right;
//        }
//        node.left = deleteMin(node.left);
//        return node;
//    }

    private Node findByKey(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = node.key.compareTo(key);

        if (compare == 0) {
            return node;
        } else if (compare < 0) {
            return findByKey(node.right, key);
        } else {
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

        int compareNodeAndKey = key.compareTo(node.key);

        if (compareNodeAndKey == 0) {
            return node;
        }

        if (compareNodeAndKey > 0) {
            if (node.right == null) {
                return null;
            }
            node = getCeil(node.right, key);
        } else if (node.left != null) {
            Node leftNode = getCeil(node.left, key);
            if (leftNode != null && leftNode.key.compareTo(key) >= 0) {
                node = leftNode;
            }
        }
        return node;
    }

    private Node getFloor(Node root, Key key) {

        if (root == null) {
            return null;
        }

        Node node = root;

        int compareNodeAndKey = key.compareTo(node.key);

        if (compareNodeAndKey == 0) {
            return node;
        }

        if (compareNodeAndKey > 0) {
            if (node.right != null) {
                if (key.compareTo(node.right.key) < 0) {
                    return node;
                }
            }
            if (root.right == null) {
                return node;
            }
            node = getFloor(node.right, key);
        } else {
            node = getFloor(node.left, key);
        }
        return node;
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