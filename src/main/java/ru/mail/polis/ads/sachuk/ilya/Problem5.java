package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem5 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    int number = in.nextInt();

    int[] arr = new int[number];
    for (int i = 0; i < number; i++) {
      arr[i] = i + 1;
    }

    printMas(arr, out);
    while (true) {
      int tailHead = firstTailIndex(arr);

      if (tailHead == 0) {
        break;
      }

      for (int i = arr.length - 1; i > tailHead - 1; i--) {
        if (arr[i] > arr[tailHead - 1]) {
          int tmp = arr[i];
          arr[i] = arr[tailHead - 1];
          arr[tailHead - 1] = tmp;
          break;
        }
      }
      reverseMas(arr, tailHead);
      printMas(arr, out);
    }
  }

  private static void reverseMas(int[] arr, int tailHead) {
    int j = 1;
    for (int i = tailHead; i < (arr.length + tailHead) / 2; i++) {
      int tmp = arr[i];
      arr[i] = arr[arr.length - j];
      arr[arr.length - j] = tmp;
      j++;
    }
  }

  private static void printMas(int[] arr, final PrintWriter out) {
    for (int i : arr) {
      out.print(i + " ");
    }
    out.println();
  }

  private static int firstTailIndex(int[] arr) {
    int tailIndex = arr.length - 1;
    int index = arr.length - 1;

    while (index > 0 && arr[index] < arr[index - 1]) {
      tailIndex = index - 1;
      index--;
    }
    return tailIndex;
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