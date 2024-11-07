package name.rhyn.sandpit.java.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import org.h2.jdbc.JdbcResultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseTest {

  private static final String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

  private static final String CREATE_TABLE = "CREATE TABLE test(id INT PRIMARY KEY, name VARCHAR(255))";

  // Specify the driver on the command line -Djdbc.drivers=

  // URL jdbc:
  // Example jdbc:mysql://localhost:3306/db?user=root&password=123456&useSSL=false

  @BeforeEach
  void cleanup() {
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()) {
      // Drop the table if it exists
      statement.execute("DROP TABLE IF EXISTS test");
    } catch (SQLException e) {
      // Ignore if the table does not exist
    }
  }

  /**
   * Find the driver:
   * - as a service (java module system)
   * - use system property -Djdbc.drivers
   * - by calling Class.forName("fully qualified name of class")
   * - by instantiating the class
   */
  @Test
  void connectionFromDriverManager() {
    //String url = "jdbc:mysql://localhost:3306";
    try (Connection connection = DriverManager.getConnection(url)) {
      // Do work here

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    try (Connection connection = DriverManager.getConnection(url, "", "")) {
      connection.setAutoCommit(false);
      //
      connection.setAutoCommit(true); // Note: Causes data to be immediately committed

    } catch (SQLException e) {
      fail(e);
    }
  }

  @Test
  void executeStatement() {
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()) {

      // execute can do all types of statments
      boolean resultSetFromCreate = statement.execute(CREATE_TABLE);
      boolean resultSetFromInsert = statement.execute(
          "INSERT INTO test(id, name) VALUES(1, 'Learn Java')");
      assertFalse(resultSetFromCreate);
      assertFalse(resultSetFromInsert);

    } catch (SQLException e) {
      fail(e);
    }
  }

  @Test
  void executeUpdateStatement() {
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()) {
      statement.execute(CREATE_TABLE);

      // INSERT, UPDATE, DELETE
      int numberOfUpdates = statement.executeUpdate(
          "INSERT INTO test(id, name) VALUES(2, 'Learn more')");

      assertEquals(1, numberOfUpdates);

      statement.addBatch("INSERT INTO  test(id, name) VALUES(3, 'Hello Test')");
      statement.addBatch("INSERT INTO test(id, name) VALUES(4, 'More hello')");
      int[] entriesModified = statement.executeBatch();
      assertEquals(2, entriesModified.length);

    } catch (SQLException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void executeQueryStatement() {
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()) {
      statement.execute(CREATE_TABLE);
      statement.execute("INSERT INTO test(id, name) VALUES(5, 'Learn')");
      statement.execute("INSERT INTO test(id, name) VALUES(6, 'more')");
      statement.execute("INSERT INTO test(id, name) VALUES(7, 'Java')");
      statement.execute("INSERT INTO test(id, name) VALUES(8, '!')");

      // SELECT
      try (ResultSet resultSet = statement.executeQuery("SELECT * FROM test")) {
        ResultSetMetaData metaData = resultSet.getMetaData();
        assertEquals(2, metaData.getColumnCount());
        assertEquals(4, ((JdbcResultSet) resultSet).getResult().getRowCount());
        while (resultSet.next()) {
          System.out.println(resultSet.getInt("id") + " : " + resultSet.getString("name"));
          // Result set is one based. Starts with 1 not 0
          System.out.println(resultSet.getInt(1) + " : " + resultSet.getString(2));

          if (false) {
            //              Argument can be the column index or column name
            resultSet.getInt("");
            resultSet.getString("");
            resultSet.getBigDecimal("");
            resultSet.getBoolean("");
            resultSet.getDate("");
          }

        }
      }
    } catch (SQLException e) {
      fail(e);
    }
  }

  @Test
  void preparedStatements() {
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()) {
      boolean resultSetFromCreate = statement.execute(CREATE_TABLE);
      boolean resultSetFromInsert = statement.execute(
          "INSERT INTO test(id, name) VALUES(1, 'Some text')");
      assertFalse(resultSetFromCreate);
      assertFalse(resultSetFromInsert);

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          "UPDATE test SET name = ? WHERE id = ?")) {
        preparedStatement.setString(1, "Changed text");
        preparedStatement.setInt(2, 1);
        //preparedStatement.setInt(2, Types.BIGINT);
        //preparedStatement.setNull(2, 1);
        int numberOfEntriesUpdated = preparedStatement.executeUpdate();
        assertEquals(1, numberOfEntriesUpdated);
      }

      try (ResultSet resultSet = statement.executeQuery("SELECT * FROM test")) {
        while (resultSet.next()) {
          System.out.println(resultSet.getInt("id") + " : " + resultSet.getString("name"));
          assertEquals(1, resultSet.getInt("id"));
          assertEquals("Changed text", resultSet.getString("name"));
        }
      }

    } catch (SQLException e) {
      fail(e.getMessage());
    }
  }

  /**
   * When not setting connection.setAutoCommit(false) every statement is immediately executed
   * <p>
   * rollback() is ignored while autocommit mode is set to true. When not in autocommit mode,
   * calling setAutoCommit(true) causes the data to be immediately committed.
   */
  @Test
  void autoCommitAndSavepoint() {
    try (var conn = DriverManager.getConnection(url)) {
      conn.setAutoCommit(false);
      Statement statement = conn.createStatement();
      statement.execute(CREATE_TABLE);

      String insert = "INSERT INTO test VALUES (?,?)";
      Savepoint sp = null;
      try (var stmt = conn.prepareStatement(insert)) {
        stmt.setInt(1, 1);
        stmt.setString(2, "Kitty");
        stmt.executeUpdate();
      }
      sp = conn.setSavepoint(); // Savepoint is created

      try (var stmt = conn.prepareStatement(insert)) {
        stmt.setInt(1, 2);
        stmt.setString(2, "Beverly");
        stmt.executeUpdate();

        conn.rollback(sp);// By this call only one entry will be inserted
        // This will result in a different behavior
        conn.rollback();
      }
      conn.commit();

    } catch (SQLException e) {
      fail(e);
    }
  }


}