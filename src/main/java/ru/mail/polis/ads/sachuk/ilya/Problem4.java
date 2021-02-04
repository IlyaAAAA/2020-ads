package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem4 {

  private static class Stack {

    private final ArrayList<Integer> arrayList;

    public Stack() {
      arrayList = new ArrayList<>();
    }

    public void push(int value) {
      arrayList.add(value);
    }

    public int pop() throws IllegalArgumentException {
      if (arrayList.isEmpty()) {
        throw new IllegalArgumentException("error");
      }

      int value = arrayList.get(size() - 1);
      arrayList.remove(size() - 1);
      arrayList.trimToSize();

      return value;
    }

    public int back() throws IllegalArgumentException {
      if (arrayList.isEmpty()) {
        throw new IllegalArgumentException("error");
      }

      return arrayList.get(size() - 1);
    }

    public int size() {
      return arrayList.size();
    }

    public void clear() {
      if (arrayList.size() == 0) {
        return;
      }

      arrayList.clear();
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {

    Stack stack = new Stack();

    boolean isEnd = false;
    while (!isEnd) {
      try {
        switch (in.next()) {
          case "push":
            stack.push(in.nextInt());
            out.println("ok");
            break;
          case "pop":
            out.println(stack.pop());
            break;
          case "back":
            out.println(stack.back());
            break;
          case "size":
            out.println(stack.size());
            break;
          case "clear":
            stack.clear();
            out.println("ok");
            break;
          case "exit":
            isEnd = true;
            out.println("bye");
            break;
        }
      }
      catch (IllegalArgumentException e) {
        out.println(e.getMessage());
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
