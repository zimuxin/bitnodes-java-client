package engineering.clientside.bitnodes;

import com.fabahaba.throttle.Throttle;

import feign.Feign;

public final class BitnodesFactory {

  private BitnodesFactory() {}

  public static Bitnodes create() {
    return create(Feign.builder());
  }

  public static Bitnodes create(final Feign.Builder feignBuilder) {
    return create(feignBuilder, Bitnodes.API_URL, 1);
  }

  public static Bitnodes create(final int permitsPerSecond) {
    return create(Feign.builder(), permitsPerSecond);
  }

  public static Bitnodes create(final Feign.Builder feignBuilder, final int permitsPerSecond) {
    return create(feignBuilder, Bitnodes.API_URL, permitsPerSecond);
  }

  public static Bitnodes create(final Feign.Builder feignBuilder, final String apiUrl,
      final int permitsPerSecond) {
    return create(Bitnodes.create(feignBuilder, apiUrl), permitsPerSecond);
  }

  public static Bitnodes create(final Bitnodes delegate) {
    return create(delegate, 1);
  }

  public static Bitnodes create(final Bitnodes delegate, final int permitsPerSecond) {
    return new BitnodesClient(delegate, Throttle.create(permitsPerSecond));
  }
}
