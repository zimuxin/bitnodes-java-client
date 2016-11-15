package engineering.clientside.bitnodes.dslplatform;

import com.dslplatform.json.JsonConverter;
import com.dslplatform.json.JsonReader;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.NumberConverter;
import com.dslplatform.json.StringConverter;
import engineering.clientside.bitnodes.BitnodesNode;

@JsonConverter(target = BitnodesNode.class)
public abstract class BitnodeNodeConverter extends DslPlatformBaseConverter {

  public static final JsonWriter.WriteObject<BitnodesNode> JSON_WRITER = (writer, node) -> {
    writer.writeByte(JsonWriter.ARRAY_START);
    NumberConverter.serialize(node.getProtocolVersion(), writer);
    writer.writeByte(JsonWriter.COMMA);
    StringConverter.serializeShortNullable(node.getUserAgent(), writer);
    writer.writeByte(JsonWriter.COMMA);
    NumberConverter.serialize(node.getConnectedSince(), writer);
    writer.writeByte(JsonWriter.COMMA);
    NumberConverter.serialize(node.getServices(), writer);
    writer.writeByte(JsonWriter.COMMA);
    NumberConverter.serialize(node.getHeight(), writer);
    writer.writeByte(JsonWriter.COMMA);
    StringConverter.serializeShortNullable(node.getHostname(), writer);
    writer.writeByte(JsonWriter.COMMA);
    StringConverter.serializeShortNullable(node.getCity(), writer);
    writer.writeByte(JsonWriter.COMMA);
    StringConverter.serializeShortNullable(node.getCountryCode(), writer);
    writer.writeByte(JsonWriter.COMMA);
    NumberConverter.serialize(node.getLatitude(), writer);
    writer.writeByte(JsonWriter.COMMA);
    NumberConverter.serialize(node.getLongitude(), writer);
    writer.writeByte(JsonWriter.COMMA);
    StringConverter.serializeShortNullable(node.getTimezone(), writer);
    writer.writeByte(JsonWriter.COMMA);
    StringConverter.serializeShortNullable(node.getAsn(), writer);
    writer.writeByte(JsonWriter.COMMA);
    StringConverter.serializeShortNullable(node.getOrganizationName(), writer);
    writer.writeByte(JsonWriter.ARRAY_END);
  };

  public static final JsonReader.ReadObject<BitnodesNode> JSON_READER = reader -> {
    // https://getaddr.bitnodes.io/api/
    // [70002, "/Satoshi:0.9.3/", 1420041581, 1, 197491, "73.168.37.76", null, "US",
    // 38.0, -97.0, null, "AS7922", "Comcast Cable Communications, Inc."]
    reader.getNextToken();
    final long protocolVersion = NumberConverter.deserializeLong(reader);
    advancePosition(reader);
    final String userAgent = reader.wasNull() ? null : reader.readString();
    advancePosition(reader);
    final long connectedSince = NumberConverter.deserializeLong(reader);
    advancePosition(reader);
    final long services = NumberConverter.deserializeLong(reader);
    advancePosition(reader);
    final long height = NumberConverter.deserializeLong(reader);
    advancePosition(reader);
    final String hostname = reader.wasNull() ? null : reader.readString();
    advancePosition(reader);
    final String city = reader.wasNull() ? null : reader.readString();
    advancePosition(reader);
    final String countryCode = reader.wasNull() ? null : reader.readSimpleString();
    advancePosition(reader);
    final double latitude = NumberConverter.deserializeDouble(reader);
    advancePosition(reader);
    final double longitude = NumberConverter.deserializeDouble(reader);
    advancePosition(reader);
    final String timezone = reader.wasNull() ? null : reader.readSimpleString();
    advancePosition(reader);
    final String asn = reader.wasNull() ? null : reader.readSimpleString();
    advancePosition(reader);
    final String organizationName = reader.wasNull() ? null : reader.readString();
    reader.getNextToken();
    reader.checkArrayEnd();
    return new DslPlatformBitnodesNode(protocolVersion, userAgent, connectedSince, services,
        height, hostname, city, countryCode, latitude, longitude, timezone, asn,
        organizationName);
  };
}
