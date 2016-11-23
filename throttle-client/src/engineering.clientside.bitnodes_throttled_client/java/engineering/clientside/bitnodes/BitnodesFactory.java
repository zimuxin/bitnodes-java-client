package engineering.clientside.bitnodes;

import engineering.clientside.feign.completable.CompletableFeign;
import engineering.clientside.throttle.Throttle;

public final class BitnodesFactory {

  private BitnodesFactory() {}

  public static AsyncBitnodes create() {
    return create(CompletableFeign.builder());
  }

  public static AsyncBitnodes create(final CompletableFeign.Builder feignBuilder) {
    return create(feignBuilder, Bitnodes.API_URL, 1);
  }

  public static AsyncBitnodes create(final int permitsPerSecond) {
    return create(CompletableFeign.builder(), permitsPerSecond);
  }

  public static AsyncBitnodes create(final CompletableFeign.Builder feignBuilder,
      final int permitsPerSecond) {
    return create(feignBuilder, Bitnodes.API_URL, permitsPerSecond);
  }

  public static AsyncBitnodes create(final CompletableFeign.Builder feignBuilder,
      final String apiUrl,
      final int permitsPerSecond) {
    return create(AsyncBitnodes.createAsync(feignBuilder, apiUrl), permitsPerSecond);
  }

  public static AsyncBitnodes create(final AsyncBitnodes delegate) {
    return create(delegate, 1);
  }

  public static AsyncBitnodes create(final AsyncBitnodes delegate, final int permitsPerSecond) {
    return new BitnodesClient(delegate, Throttle.create(permitsPerSecond));
  }
}
