package name.rhyn.sandpit.java.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import org.junit.jupiter.api.Test;

class NewFileHandlingTest {

  private static final String FILE_PATH = "src/test/resources/input.txt";

  private static final String OUTPUT_PATH_1 = "target/output-nio-1.txt";
  private static final String OUTPUT_PATH_2 = "target/output-nio-2.txt";

  @Test
  void filesReadAllLines() throws IOException {
    Path path = Paths.get(FILE_PATH);
    assertTrue(Files.exists(path));
    assertEquals("input.txt", path.getFileName().toString());

    List<String> lines = Files.readAllLines(path);

    assertEquals(6, lines.size());
  }

  @Test
  void filesWrite() throws IOException {
    Path path = Paths.get(OUTPUT_PATH_1);
    Files.write(path, "Hello, NIO!".getBytes());

    assertTrue(Files.exists(path));
  }

  @Test
  void fileChannelReadWrite() throws IOException {
    Path path = Paths.get(OUTPUT_PATH_2);

    try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE,
        StandardOpenOption.WRITE)) {
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      buffer.put("Hello, FileChannel!".getBytes());
      buffer.flip();
      int count = fileChannel.write(buffer);
      assertEquals(19, count);
    }

    try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      fileChannel.read(buffer);
      buffer.flip();
      byte[] bytes = new byte[buffer.remaining()];
      buffer.get(bytes);
      assertEquals("Hello, FileChannel!", new String(bytes));
    }
  }

}
