package engineering.clientside.bitnodes;

import com.dslplatform.json.JsonConverter;
import com.dslplatform.json.JsonReader;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.NumberConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

@JsonConverter(target = BitnodesNodes.class)
public abstract class BitnodeNodesConverter extends DslJsonBaseConverter {

  public static final JsonWriter.WriteObject<BitnodesNodes> JSON_WRITER = (writer, nodes) -> {
    writer.writeByte(JsonWriter.OBJECT_START);
    writer.writeString("timestamp");
    writer.writeByte(JsonWriter.SEMI);
    NumberConverter.serialize(nodes.getTimestamp(), writer);
    writer.writeByte(JsonWriter.COMMA);
    writer.writeString("total_nodes");
    writer.writeByte(JsonWriter.SEMI);
    NumberConverter.serialize(nodes.getTotalNodes(), writer);
    writer.writeByte(JsonWriter.COMMA);
    writer.writeString("latest_height");
    writer.writeByte(JsonWriter.SEMI);
    NumberConverter.serialize(nodes.getLatestHeight(), writer);
    writer.writeByte(JsonWriter.COMMA);
    writer.writeString("nodes");
    writer.writeByte(JsonWriter.SEMI);
    writer.writeByte(JsonWriter.OBJECT_START);
    if (!nodes.getNodes().isEmpty()) {
      final Iterator<Map.Entry<String, BitnodesNode>> iterator = nodes
          .getNodes().entrySet().iterator();
      Map.Entry<String, BitnodesNode> entry = iterator.next();
      writer.writeString(entry.getKey());
      writer.writeByte(JsonWriter.SEMI);
      BitnodeNodeConverter.JSON_WRITER.write(writer, entry.getValue());
      while (iterator.hasNext()) {
        writer.writeByte(JsonWriter.COMMA);
        entry = iterator.next();
        writer.writeString(entry.getKey());
        writer.writeByte(JsonWriter.SEMI);
        BitnodeNodeConverter.JSON_WRITER.write(writer, entry.getValue());
      }
    }
    writer.writeByte(JsonWriter.OBJECT_END);
    writer.writeByte(JsonWriter.OBJECT_END);
  };

  public static final JsonReader.ReadObject<BitnodesNodes> JSON_READER = reader -> {
    long timestamp = -1;
    int totalNodes = -1;
    long latestHeight = -1;
    Map<String, BitnodesNode> nodes = null;
    reader.getNextToken();
    for (;;) {
      switch (reader.fillName()) {
        case -1299983069: // timestamp
          reader.getNextToken();
          timestamp = NumberConverter.deserializeLong(reader);
          break;
        case -1004415757: // total_nodes
          reader.getNextToken();
          totalNodes = NumberConverter.deserializeInt(reader);
          if (nodes == null) {
            nodes = new HashMap<>(totalNodes);
          }
          break;
        case 1956590228: // latest_height
          reader.getNextToken();
          latestHeight = NumberConverter.deserializeLong(reader);
          break;
        case 1364103258: // nodes
          reader.getNextToken();
          reader.getNextToken();
          if (nodes == null) {
            nodes = new HashMap<>(8_192);
          }
          for (;;) {
            final String ip = reader.readSimpleString();
            reader.read();
            reader.getNextToken();
            nodes.put(ip, BitnodeNodeConverter.JSON_READER.read(reader));
            final byte nextToken = reader.getNextToken();
            if (nextToken == ',') {
              reader.getNextToken();
              continue;
            }
            if (nextToken == '}') {
              break;
            }
          }
          break;
        default:
          LOG.log(Level.WARNING, String.format("Skipping unexpected field '%s' in %s.",
              reader.getLastName(), reader));
          reader.getNextToken();
          reader.skip();
      }
      final byte nextToken = reader.getNextToken();
      if (nextToken == ',') {
        reader.getNextToken();
        continue;
      }
      if (nextToken == '}') {
        if (timestamp == -1 || totalNodes == -1 || latestHeight == -1 || nodes == null) {
          throw new IOException("Missing fields in BitnodesNodes response: " + reader);
        }
        return new DslJsonBitnodesNodes(timestamp, totalNodes, latestHeight, nodes);
      }
    }
  };
}
