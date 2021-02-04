package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Problem2 {

  private static void solve(final FastScanner in, final PrintWriter out) {

    int number = in.nextInt();

    List<Integer> list = new ArrayList<>(number);

    for (int i = 0; i < number; i++) {
      list.add(in.nextInt());
    }

    trickySort(list);
    printList(list, out);

  }

  private static void trickySort(List<Integer> list) {

    list.sort((Integer a, Integer b) -> {

      Integer lastValueA = a % 10;
      Integer lastValueB = b % 10;

      if (lastValueA.intValue() != lastValueB.intValue()){
        return lastValueA.compareTo(lastValueB);
      }
      else {
        return a.compareTo(b);
      }

    });
  }

  private static void printList(List<Integer> list, PrintWriter out) {
    for (Integer value: list) {
      out.print(value + " ");
    }
    out.println();
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
        } catch (IOException e) {
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