package name.rhyn.sandpit.java.io;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FileHandlingTest {

  private static final String FILE_PATH = "src/test/resources/input.txt";

  @Test
  void byteStream() {
    try (FileInputStream fis = new FileInputStream(FILE_PATH);
        FileOutputStream fos = new FileOutputStream("target/output-fos.txt")) {
      int data;
      while ((data = fis.read()) != -1) {
        fos.write(data);
      }
    } catch (IOException e) {
      fail(e);
    }
  }

  @Test
  void characterStream() {
    try (FileReader fr = new FileReader(FILE_PATH);
        FileWriter fw = new FileWriter("target/output-fw.txt")) {
      int data;
      while ((data = fr.read()) != -1) {
        fw.write(data);
      }
    } catch (IOException e) {
      fail(e);
    }
  }

}
