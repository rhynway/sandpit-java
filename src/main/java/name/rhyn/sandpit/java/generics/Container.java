package name.rhyn.sandpit.java.generics;

import java.util.HashMap;
import java.util.Map;

public class Container {

  private final Map<Key<?>, Object> map = new HashMap<>();

  // Generic Method
  public <T> void put(Key<T> key, T value) {
    map.put(key, value);
  }

  // Generic Method
  public <T> T get(Key<T> key) {
    return key.getType().cast(map.get(key));
  }

}
