package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableSample<Key, Value> implements HashTable<Key, Value> {

  private final double LOAD_FACTOR = 0.75;

  private int elementsNumber;

  private int arrSize = 16;

  private static class Node<Key, Value> {
    private Key key;
    private Value value;
    private Node<Key, Value> next;

    Node(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }

  private Node<Key, Value>[] nodes = new Node[arrSize];

  @Override
  public @Nullable Value get(@NotNull Key key) {
    int index = findIndex(key.hashCode());

    if (nodes[index] != null) {

      Node<Key, Value> node = nodes[index];

      while (node != null && !node.key.equals(key)) {
        node = node.next;
      }

      return node == null ? null : node.value;
    }

    return null;
  }

  @Override
  public void put(@NotNull Key key, @NotNull Value value) {
    int index = findIndex(key.hashCode());

    resize();
    if (nodes[index] == null) {
      nodes[index] = new Node<>(key, value);
    } else {

      Node<Key, Value> node = nodes[index];

      while (node != null) {

        if (node.key.equals(key)) {
          node.key = key;
          node.value = value;
          return;
        }

        if (node.next == null) {
          break;
        }
        node = node.next;
      }

      if (node != null) {
        node.next = new Node<>(key, value);
      }
    }
    elementsNumber++;

  }

  @Override
  public @Nullable Value remove(@NotNull Key key) {
    int index = findIndex(key.hashCode());

    if (nodes[index] != null) {
      Node<Key, Value> node = nodes[index];

      while (nodes[index] != null) {

        if (nodes[index].key.equals(key)) {

          Value valueToReturn = node.value;
          nodes[index] = nodes[index].next;
          elementsNumber--;

          if (!node.key.equals(key)) {
            nodes[index] = node;
          }

          return valueToReturn;
        }

        nodes[index] = nodes[index].next;
      }

    }

    return null;
  }

  @Override
  public int size() {
    return elementsNumber;
  }

  @Override
  public boolean isEmpty() {
    return elementsNumber == 0;
  }

  private void resize() {
    if ((double) (elementsNumber / arrSize) > LOAD_FACTOR) {
      arrSize *= 2;
      rehash();
    }
  }

  private void rehash() {
    int sizeBeforeRehash = elementsNumber;
    Node<Key, Value>[] tmp = nodes;
    nodes = new Node[arrSize];

    for (var node : tmp) {
      while (node != null) {
        put(node.key, node.value);
        node = node.next;
      }
    }
    elementsNumber -= sizeBeforeRehash;
  }

  private int findIndex(int hashCode) {
    return (hashCode & 0x7fffffff) % arrSize;
  }
}
