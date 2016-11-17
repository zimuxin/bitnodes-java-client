package engineering.clientside.bitnodes;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;

import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesInvArrivalStats;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesInvPropagation;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesLeaderboard;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesNodeLatency;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesNodeStatus;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesPeerIndexData;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesPostResponse;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesSnapshot;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesSnapshots;
import engineering.clientside.bitnodes.dslplatform.DslPlatformBitnodesStampedLatency;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class BitnodesCoder implements Decoder, Encoder {

  private BitnodesCoder() {}

  private static final BitnodesCoder singleton = new BitnodesCoder();

  public static BitnodesCoder get() {
    return singleton;
  }

  private static final DslJson<Object> dslJson = new DslJson<>();

  private static void registerInterface(final Class<?> clas) {
    for (final Class<?> iface : clas.getInterfaces()) {
      if (iface.getPackageName().equals("engineering.clientside.bitnodes")) {
        dslJson.registerReader(iface, dslJson.tryFindReader(clas));
      }
    }
  }

  static {
    registerInterface(DslPlatformBitnodesInvArrivalStats.class);
    registerInterface(DslPlatformBitnodesInvPropagation.class);
    registerInterface(DslPlatformBitnodesLeaderboard.class);
    registerInterface(DslPlatformBitnodesNodeLatency.class);
    registerInterface(DslPlatformBitnodesNodeStatus.class);
    registerInterface(DslPlatformBitnodesPeerIndexData.class);
    registerInterface(DslPlatformBitnodesPostResponse.class);
    registerInterface(DslPlatformBitnodesSnapshot.class);
    registerInterface(DslPlatformBitnodesSnapshots.class);
    registerInterface(DslPlatformBitnodesStampedLatency.class);
  }

  private static final ThreadLocal<byte[]> buffer = ThreadLocal.withInitial(() -> new byte[4_096]);
  private static final ThreadLocal<JsonWriter> writer = ThreadLocal
      .withInitial(() -> new JsonWriter(buffer.get()));

  @Override
  public Object decode(final Response response, final Type type) throws IOException {
    final Response.Body body = response.body();
    if (body == null) {
      throw new IOException("Empty response:" + response);
    }
    try (final InputStream is = body.asInputStream()) {
      return dslJson.deserialize(type, is, buffer.get());
    }
  }

  @Override
  public void encode(final Object object, final Type bodyType,
      final RequestTemplate template) throws EncodeException {
    try (final JsonWriter localWriter = writer.get()) {
      dslJson.serialize(localWriter, object);
      final byte[] body = localWriter.toByteArray();
      template.body(body, UTF_8);
    } catch (final IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
