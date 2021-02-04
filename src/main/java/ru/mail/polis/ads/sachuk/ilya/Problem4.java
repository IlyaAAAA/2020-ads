package ru.mail.polis.ads.task2.ilya;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Problem4 {
  private static void solve(final FastScanner in, final PrintWriter out) {

    int order = in.nextInt();

    String str = null;

    try {
      str = in.reader.readLine();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    String[] mas = str.split(" ");

    BigInteger[] arr = new BigInteger[mas.length];

    for (int i = 0; i < arr.length; i++) {
      arr[i] = new BigInteger(mas[i]);
    }

    int k = arr.length - order;
    out.println(getOrderStatistic(arr, k, 0, arr.length - 1));
  }

  private static BigInteger getOrderStatistic(BigInteger[] arr, int k, int left, int right) {

    while (true) {
      int mid = partition(arr, left, right);

      if (mid == k) {
        return arr[k];
      }
      else if (mid > k) {
        right = mid;
      }
      else {
        left = mid + 1;
      }

    }
  }

  private static int partition(BigInteger[] arr, int left, int right) {
    BigInteger x = arr[left];
    int i = left;

    for (int j = i + 1; j <= right; j++) {
      if (arr[j].compareTo(x) < 0) {
        i++;
        BigInteger tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
      }
    }
    BigInteger tmp = arr[left];
    arr[left] = arr[i];
    arr[i] = tmp;

    return i;
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