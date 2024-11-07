package name.rhyn.sandpit.java.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;
import org.junit.jupiter.api.Test;

class StackTest {

  @Test
  void stack() {
    Stack<String> stack = new Stack<>();

    stack.push("One");
    stack.push("Two");
    stack.push("Three");
    stack.add("Four");

    assertEquals(4, stack.size());

    assertEquals("Four", stack.peek());
    assertEquals(4, stack.size());

    // Test pop
    assertEquals("Four", stack.pop());
    stack.pop();
    stack.pop();
    assertFalse(stack.isEmpty());
    stack.pop();
    assertTrue(stack.isEmpty());
  }

}
