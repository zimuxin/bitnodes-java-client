package engineering.clientside.bitnodes;

import java.util.Optional;

import engineering.clientside.feign.completable.CompletableFeign;
import engineering.clientside.throttle.Throttle;

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
    return AsyncBitnodes.createAsync(feignBuilder);
  }
}
