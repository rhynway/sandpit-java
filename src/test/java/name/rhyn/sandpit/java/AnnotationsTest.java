package name.rhyn.sandpit.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import name.rhyn.sandpit.java.annotations.MyAnnotation;
import org.junit.jupiter.api.Test;

class AnnotationsTest {

  @Test
  void override() {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        System.out.println("run");
      }
    };

    assertNotNull(r);
  }

  @Test
  void deprecated() {
    int number = oldMethod();

    assertEquals(4, number);
  }

  @Deprecated
  private int oldMethod() {
    return 4;
  }

  @Test
  void suppressWarnings() {
    List<?> letters = List.of("A", "B", "C");

    @SuppressWarnings("unchecked")
    List<String> strings = (List<String>) letters;

    assertEquals(3, strings.size());
  }

  @Test
  void myAnnotation() {
    Method[] methods = this.getClass().getDeclaredMethods();
    Stream<Method> methodsWithMyAnnotation = Arrays.stream(methods)
        .filter(m -> m.isAnnotationPresent(MyAnnotation.class));
    methodsWithMyAnnotation.forEach(m -> {
      MyAnnotation myAnnotation = m.getAnnotation(MyAnnotation.class);
      assertEquals("My Annotation", myAnnotation.value());
    });
  }

  @MyAnnotation
  private void aMethodWithMyAnnotation() {

  }

}
