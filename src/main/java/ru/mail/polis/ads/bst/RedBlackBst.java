package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

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

    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
      this.color = RED;
    }
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
    root = put(root, key, value);
    root.color = BLACK;
  }

  @Override
  public Value remove(@NotNull Key key) {
    Value valueToRemove = get(key);
    if (valueToRemove != null) {
      root = removeByKey(root, key);
      size--;
    }
    return valueToRemove;
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

  private Node put(Node node, Key key, Value value) {
    if (node == null) {
      size++;
      return new Node(key, value);
    }

    int compare = node.key.compareTo(key);

    if (compare == 0) {
      node.value = value;
    } else if (compare > 0) {
      node.left = put(node.left, key, value);
    } else {
      node.right = put(node.right, key, value);
    }

    return fixUp(node);
  }

  private Node removeByKey(Node node, Key key) {
    if (node == null) {
      return null;
    }

    int compare = key.compareTo(node.key);

    if (compare < 0) {
      if (node.left != null) {
        if (!isRed(node.left) && !isRed(node.left.left)) {
          node = moveRedLeft(node);
        }
        node.left = removeByKey(node.left, key);
      }
    } else {
      if (isRed(node.left)) {
        node = rotateRight(node);
        node.right = removeByKey(node.right, key);
      } else if (compare == 0 && node.right == null) {
        return null;
      } else {
        if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
          node = moveRedRight(node);
        }

        if (key.equals(node.key)) {
          Node min = findNodeMinKey(node.right);
          node.key = min.key;
          node.value = min.value;
          node.right = deleteMin(node.right);
        } else {
          node.right = removeByKey(node.right, key);
        }
      }
    }

    return fixUp(node);
  }

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

  private Node rotateRight(Node parent) {
    Node children = parent.left;
    parent.left = children.right;
    children.right = parent;

    children.color = parent.color;
    parent.color = RED;

    return children;
  }

  private Node rotateLeft(Node parent) {
    Node children = parent.right;
    parent.right = children.left;
    children.left = parent;

    children.color = parent.color;
    parent.color = RED;

    return children;
  }

  private boolean isRed(Node node) {
    return node != null && node.color == RED;
  }

  private void flipColor(Node node) {
    node.color = !node.color;
    node.left.color = !node.left.color;
    node.right.color = !node.right.color;
  }

  private Node fixUp(Node node) {
    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    if (isRed(node.left) && isRed(node.right)) {
      flipColor(node);
    }
    return node;
  }

  private Node moveRedLeft(Node node) {
    flipColor(node);

    if (isRed(node.right.left)) {
      node.right = rotateRight(node.right);
      node = rotateLeft(node);
      flipColor(node);
    }
    return node;
  }

  private Node moveRedRight(Node node) {
    flipColor(node);

    if (isRed(node.left.left)) {
      node = rotateRight(node);
      flipColor(node);
    }
    return node;
  }

  private void deleteMin() {
    root = deleteMin(root);
    root.color = BLACK;
  }

  private Node deleteMin(Node node) {
    if (node.left == null) {
      return null;
    }
    if (!isRed(node.left) && !isRed(node.left.left)) {
      node = moveRedLeft(node);
    }
    node.left = deleteMin(node.left);

    return fixUp(node);
  }

  private void deleteMax() {
    root = deleteMax(root);
    root.color = BLACK;
  }

  private Node deleteMax(Node node) {
    if (isRed(node.left)) {
      node = rotateRight(node);
    }
    if (node.right == null) {
      return null;
    }
    if (!isRed(node.right) && !isRed(node.right.left)) {
      node = moveRedRight(node);
    }
    node.right = deleteMax(node.right);
    return fixUp(node);
  }
}