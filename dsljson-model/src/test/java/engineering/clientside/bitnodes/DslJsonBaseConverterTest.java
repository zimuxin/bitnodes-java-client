package engineering.clientside.bitnodes;

import com.dslplatform.json.JsonReader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class DslJsonBaseConverterTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testHash() throws IOException {
    final String testField = "testhash";
    final JsonReader reader = new JsonReader(String
        .format("\"%s\": null", testField).getBytes(UTF_8), null);
    reader.getNextToken();
    assertEquals(reader.fillName(), DslJsonBaseConverter.hashFieldName(testField));
  }

  @Test
  public void testInvalidAdvance() throws IOException {
    final String testField = "testhash";
    thrown.expect(IOException.class);
    thrown.expectMessage(String.format("Unexpected token 't' at position 2 in \"%s\"", testField));
    final JsonReader reader = new JsonReader(String
        .format("\"%s\": null", testField).getBytes(UTF_8), null);
    reader.getNextToken();
    DslJsonBaseConverter.advancePosition(reader);
  }
}
