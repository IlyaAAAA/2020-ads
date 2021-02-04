package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem5 {

  private static class Queue {

    private Node head;
    private Node tail;
    private int size;

    public Queue() {
      this.size = 0;
      this.head = null;
      this.tail = null;
    }

    private static class Node {
      private final int value;
      private Node next;

      public Node(int value) {
        this.value = value;
        this.next = null;
      }
    }

    public void push(int value) {

      Node node = new Node(value);

      if (size == 0) {
        head = node;
        tail = node;
      }
      else {
        tail.next = node;
        tail = tail.next;
      }
      size++;
    }

    public int pop() {

      Node nextNode = head.next;

      int value = head.value;

      head.next = null;
      head = nextNode;
      size--;

      return value;
    }

    public int front() {
      return head.value;
    }

    public int size() {
      return size;
    }

    public void clear() {
      if (size == 0) {
        return;
      }

      while (head != tail) {

        Node nextNode = head.next;

        head.next = null;
        head = nextNode;
        size--;
      }
      head = null;
      tail = null;
      size--;
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {

    Queue queue = new Queue();

    boolean isEnd = false;

    while (!isEnd) {
      switch (in.next()) {
        case "push":
          queue.push(in.nextInt());
          out.println("ok");
          break;
        case "pop":
          out.println(queue.pop());
          break;
        case "front":
          out.println(queue.front());
          break;
        case "size":
          out.println(queue.size());
          break;
        case "clear":
          queue.clear();
          out.println("ok");
          break;
        case "exit":
          isEnd = true;
          out.println("bye");
          break;
      }
    }
  }

  private static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      return tokenizer.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}