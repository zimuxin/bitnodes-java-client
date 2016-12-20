package engineering.clientside.bitnodes;

import java.util.Optional;

import engineering.clientside.feign.CoderProvider;
import engineering.clientside.feign.completable.CompletableFeign;
import engineering.clientside.throttle.Throttle;
import feign.Feign;

public final class BitnodesFactory {

  private static final int DEFAULT_REQUESTS_PER_SECOND = Optional.ofNullable(
      System.getProperty(ThrottleBitnodesConfig.DEFAULT_REQUESTS_PER_SECOND.getPropName()))
      .map(Integer::parseInt).orElse(1);

  private BitnodesFactory() {}

  public static AsyncBitnodes create() {
    return create(CompletableFeign.builder());
  }

  public static AsyncBitnodes create(final int permitsPerSecond) {
    return create(CompletableFeign.builder(), permitsPerSecond);
  }

  public static AsyncBitnodes create(final CompletableFeign.Builder feignBuilder) {
    return create(feignBuilder, DEFAULT_REQUESTS_PER_SECOND);
  }

  public static AsyncBitnodes create(final CompletableFeign.Builder feignBuilder,
      final int permitsPerSecond) {
    final Throttle throttle = Throttle.create(permitsPerSecond);
    feignBuilder.requestInterceptor(template -> throttle.acquireUnchecked());
    return createAsync(feignBuilder);
  }

  static AsyncBitnodes createAsync(final Feign.Builder feignBuilder) {
    return createAsync(feignBuilder, AsyncBitnodes.class);
  }

  static <T extends AsyncBitnodes> T createAsync(final Feign.Builder feignBuilder,
      final Class<T> apiType) {
    return CoderProvider.configureCoder(feignBuilder, BitnodesCoder.class)
        .target(apiType, Bitnodes.API_URL);
  }
}
