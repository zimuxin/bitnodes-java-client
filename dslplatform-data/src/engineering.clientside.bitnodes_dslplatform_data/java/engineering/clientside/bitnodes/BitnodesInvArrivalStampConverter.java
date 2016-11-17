package engineering.clientside.bitnodes;

import com.dslplatform.json.JsonConverter;
import com.dslplatform.json.JsonReader;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.NumberConverter;

@JsonConverter(target = BitnodesInvArrivalStamp.class)
public abstract class BitnodesInvArrivalStampConverter extends DslJsonBaseConverter {

  public static final JsonWriter.WriteObject<BitnodesInvArrivalStamp> JSON_WRITER =
      (writer, invStamp) -> {
        writer.writeByte(JsonWriter.ARRAY_START);
        writer.writeString(invStamp.getAddress());
        writer.writeByte(JsonWriter.COMMA);
        NumberConverter.serialize(invStamp.getTimestamp(), writer);
        writer.writeByte(JsonWriter.ARRAY_END);
      };

  public static final JsonReader.ReadObject<BitnodesInvArrivalStamp> JSON_READER = reader -> {
    reader.getNextToken();
    final String nodeIpPort = reader.readSimpleString();
    advancePosition(reader);
    final long timestamp = NumberConverter.deserializeLong(reader);
    reader.getNextToken();
    reader.checkArrayEnd();
    return new DslJsonBitnodesInvArrivalStamp(nodeIpPort, timestamp);
  };
}
