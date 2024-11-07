package name.rhyn.sandpit.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import name.rhyn.sandpit.java.exceptions.MyBusinessException;
import org.junit.jupiter.api.Test;

class ExceptionsTest {


  /**
   * Reasons for checked exceptions
   * - To force a caller to handle or declare potential problems
   * - To notify the caller of potential types of problems
   * - To give the caller a chance to recover from a problem
   */
  @Test
  void checkedExceptions() {
    var fileNotFoundException = new FileNotFoundException();
    var ioException = new IOException();
    var exception = new Exception();

    assertNotNull(fileNotFoundException);
    assertNotNull(ioException);
    assertNotNull(exception);
  }

  /**
   * Must not be handled with try catch
   */
  @Test
  void uncheckedExceptions() {
    var arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();

    assertNotNull(arrayIndexOutOfBoundsException);
  }

  /**
   * Object <- Throwable <- Error Object <- Throwable <- Exception <- RuntimeException
   */
  @Test
  void exceptionClass() {
    MyBusinessException firstException = new MyBusinessException();
    MyBusinessException secondException = new MyBusinessException("My Business Exception");
    Exception cause = new Exception(firstException);
    MyBusinessException thirdException = new MyBusinessException(cause);

    assertNull(firstException.getMessage());
    assertEquals("My Business Exception", secondException.getMessage());
    assertEquals(
        "java.lang.Exception: name.rhyn.sandpit.java.exceptions.MyBusinessException",
        thirdException.getMessage());
    assertEquals(cause, thirdException.getCause());
  }

  @Test
  void validExceptionNames() {
    Error error = new Error();// A bad name
    _X x = new _X();

    assertNotNull(error);
    assertNotNull(x);
  }

  @Test
  void exceptionInheritance() {
    DetailedException detailedException = new DetailedException();
    MoreDetailedException moreDetailedException = new MoreDetailedException("Not used");

    assertNotNull(detailedException);
    assertNotNull(moreDetailedException);
    assertNull(moreDetailedException.getMessage());
  }

  /**
   * The throws keyword is used in method declarations, whereas the throw keyword is used to send an
   * exception to the surrounding process.
   * <p>
   * An overridden method must not throw any new or broader checked exceptions than the method it
   * inherits.
   * <p>
   * Which of the following types are not recommended to catch in your application?
   * - Throwable
   * - Error
   * <p>
   * Best scenarios in which to use and catch an exception
   * - A network connection goes down.
   * - A caller passes invalid data to a method.
   */
  @Test
  void exceptionHandling() {
    // minimal
    try {

    } finally {

    }

    try {
      if (true) {
        throw new IOException();
      }
      throw new MalformedURLException();

    } catch (MalformedURLException e) { // The order of the exceptions is relevant when they inherit

    } catch (IOException e) {

    } finally {

    }

    surf();
  }

  public void surf() throws RuntimeException {
    try {
      throw new TideException();
            /*
            Multi-catch definition
            - The exception types must be disjoint. One exception cannot be a subclass of an other
             */
    } catch (IllegalStateException | TideException t) {
      // t = null; t is final
      t.getMessage(); // Only common methods are available
      //} catch (Exception t) {
    }
  }

  /**
   * The method signature of method sit throws a different RuntimeException. This is valid. It's not
   * the same as with checked exceptions.
   */
  @Test
  void overrideMethodWithUncheckedException() {
    var chair = new Chair();
    assertThrows(RuntimeException.class, () -> chair.sit());

    Stool stool = new Stool();
    stool.sit();
  }

  static class Chair {

    public void sit() throws IllegalArgumentException {
      throw new RuntimeException();
    }
  }

  static class Stool extends Chair {

    @Override
    public void sit() throws RuntimeException {
      System.out.print("Thanks");
    }
  }

  class Error extends Exception {

  }

  class _X extends Exception {

  }

  public class DetailedException extends Exception {
    // Only one constructor available
  }

  public class MoreDetailedException extends DetailedException {

    public MoreDetailedException(String message) {
      // super(message); Doesn't compile
      super();
    }
  }

  class TideException extends Exception {

  }

}
