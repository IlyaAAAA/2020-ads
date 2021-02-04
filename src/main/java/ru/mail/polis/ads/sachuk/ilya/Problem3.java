package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem3 {

  abstract static class Heap {
    private final int[] mas;
    private int indexLastValue;

    public Heap() {
      this.mas = new int[1000000];
      this.mas[0] = -1;
      this.indexLastValue = 0;
    }

    public void insert(int n) {
      indexLastValue++;

      mas[indexLastValue] = n;
      swim(indexLastValue);
    }

    public int pop() {
      int value = mas[1];

      mas[1] = mas[indexLastValue];
      mas[indexLastValue] = 0;
      indexLastValue--;

      sink(1);
      return value;
    }

    public int peek() {
      return mas[1];
    }

    public int size() {
      return indexLastValue;
    }

    public int[] getMas() {
      return mas;
    }

    public int getIndexLastValue() {
      return indexLastValue;
    }

    abstract protected void swim(int index);

    abstract protected void sink(int indexToSink);
  }

  private static class LeftHeap extends Heap {

    @Override
    protected void swim(int index) {
      int[] mas = getMas();

      while (mas[index] > mas[index / 2]) {
        if (index / 2 > 0) {
          int tmp = mas[index];
          mas[index] = mas[index / 2];
          mas[index / 2] = tmp;

          index /= 2;
        }
        else {
          break;
        }
      }
    }

    @Override
    protected void sink(int indexToSink) {
      int k = indexToSink * 2;
      int[] mas = getMas();

      while (k <= getIndexLastValue()) {
        if (k < getIndexLastValue() && mas[k] < mas[k + 1]) {
          k++;
        }

        if (mas[indexToSink] < mas[k]) {
          int tmp = mas[k];
          mas[k] = mas[indexToSink];
          mas[indexToSink] = tmp;
          indexToSink = k;
        }
        else {
          break;
        }

        k *= 2;
      }
    }
  }

  private static class RightHeap extends Heap {

    @Override
    protected void swim(int index) {
      int[] mas = getMas();
      while (mas[index] < mas[index / 2]) {
        if (index / 2 > 0) {
          int tmp = mas[index];
          mas[index] = mas[index / 2];
          mas[index / 2] = tmp;

          index /= 2;
        }
        else {
          break;
        }
      }
    }

    @Override
    protected void sink(int indexToSink) {
      int[] mas = getMas();

      int k = indexToSink * 2;
      while (k <= getIndexLastValue()) {
        if (k < getIndexLastValue() && mas[k] > mas[k + 1]) {
          k++;
        }

        if (mas[indexToSink] > mas[k]) {
          int tmp = mas[k];
          mas[k] = mas[indexToSink];
          mas[indexToSink] = tmp;
          indexToSink = k;
        }
        else {
          break;
        }

        k *= 2;
      }
    }
  }

  private static class Head {
    private int head;
    private final LeftHeap leftHeap;
    private final RightHeap rightHeap;

    public Head() {
      leftHeap = new LeftHeap();
      rightHeap = new RightHeap();
      head = -1;
    }

    public void addToHeap(int value) {

      if (head == -1) {
        head = value;

        if (leftHeap.size() == 0 || rightHeap.size() == 0) {
          return;
        }

        if (head < leftHeap.peek()) {
          int tmp = head;
          head = leftHeap.pop();
          leftHeap.insert(tmp);
        }
        else if (head > rightHeap.peek()) {
          int tmp = head;
          head = rightHeap.pop();
          rightHeap.insert(tmp);
        }
      }
      else {
        if (head > value) {
          leftHeap.insert(value);
          rightHeap.insert(head);
        }
        else {
          rightHeap.insert(value);
          leftHeap.insert(head);
        }
        head = -1;
      }
    }

    public int getMedian() {
      if (head == -1) {
        return (leftHeap.peek() + rightHeap.peek()) / 2;
      }
      else {
        return head;
      }
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    Head head = new Head();

    while (true) {
      head.addToHeap(in.nextInt());
      out.println(head.getMedian());
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
    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
      solve(in, out);
    }
    catch (Exception e) {
    }
  }
}