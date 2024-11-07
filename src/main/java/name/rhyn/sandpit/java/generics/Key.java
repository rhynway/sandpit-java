package name.rhyn.sandpit.java.generics;

public class Key<T> {

  private final String key;

  private final Class<T> type;

  public Key(String key, Class<T> type) {
    this.key = key;
    this.type = type;
  }

  public String getKey() {
    return key;
  }

  public Class<T> getType() {
    return type;
  }

}
