package name.rhyn.sandpit.java.exceptions;

public class MyBusinessException extends Exception {

  public MyBusinessException() {
    super();
  }

  public MyBusinessException(String message) {
    super(message);
  }

  public MyBusinessException(Exception cause) {
    super(cause);
  }
}