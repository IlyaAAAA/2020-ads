package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Problem2 {

  private static void solve(final FastScanner in, final PrintWriter out) {

    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      String resLine = reworkLine(in.next());
      out.println(resLine);
    }
  }

  private static String reworkLine(String str) {
    Deque<Node> deque = new ArrayDeque<>();

    for (int i = 0; i < str.length(); i++) {

      char ch = str.charAt(i);

      if (Character.isUpperCase(ch)) {
        Node right = deque.poll();
        Node left = deque.poll();

        deque.push(new Node(ch, left, right));
      }
      else {
        deque.push(new Node(ch, null, null));
      }

    }

    return getCorrectLine(deque);
  }

  private static String getCorrectLine(Deque<Node> deque) {

    StringBuilder resultLine = new StringBuilder();

    while (!deque.isEmpty()) {
      Node node = deque.pollFirst();

      if (node.left != null) {
        deque.addLast(node.left);
      }

      if (node.right != null) {
        deque.addLast(node.right);
      }
      resultLine.append(node.operator);
    }

    return resultLine.reverse().toString();
  }

  private static class Node {
    private final Node left;
    private final Node right;
    private final char operator;

    Node(char operator, Node left, Node right) {
      this.operator = operator;
      this.left = left;
      this.right = right;
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