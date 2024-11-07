package name.rhyn.sandpit.java.fp;

interface AddNumbers {

  static int subtract(int x, int y) {
    return x - y;
  }

  int add(int x, int y);

  default int multiply(int x, int y) {
    return x * y;
  }

}

public class Calculator {

  protected int calculate(AddNumbers n, int a, int b) {
    return n.add(a, b);
  }

}
