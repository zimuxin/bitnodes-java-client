package engineering.clientside.bitnodes;

import com.dslplatform.json.JsonReader;

import java.io.IOException;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

abstract class DslJsonBaseConverter {

  static final Logger LOG = Logger.getLogger(DslJsonBaseConverter.class.getName());

  static void advancePosition(final JsonReader reader) throws IOException {
    final byte nextToken = reader.getNextToken();
    if (nextToken != ',') {
      throw new IOException(String.format("Unexpected token '%c' at position %d in %s.",
          nextToken, reader.getCurrentIndex(), reader.toString()));
    }
    reader.getNextToken();
  }

  static int hashFieldName(final String fieldName) {
    long hash = 0x811c9dc5;
    for (final byte b : fieldName.getBytes(UTF_8)) {
      hash ^= b;
      hash *= 0x1000193;
    }
    return (int) hash;
  }
}
