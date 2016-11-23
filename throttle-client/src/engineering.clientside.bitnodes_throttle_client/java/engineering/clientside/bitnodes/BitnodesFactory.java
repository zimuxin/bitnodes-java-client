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
      final String apiUrl, final int permitsPerSecond) {
    //    final Throttle throttle = Throttle.create(permitsPerSecond);
    //    feignBuilder.futureFactory((dispatch, method, args, executor) -> CompletableFuture.supplyAsync(() -> {
    //      throttle.acquireUnchecked();
    //      try {
    //        return dispatch.get(method).invoke(args);
    //      } catch (final RuntimeException re) {
    //        throw re;
    //      } catch (final Throwable throwable) {
    //        throw new CompletionException(throwable);
    //      }
    //    }, executor));
    return create(AsyncBitnodes.createAsync(feignBuilder, apiUrl), permitsPerSecond);
  }

  public static AsyncBitnodes create(final AsyncBitnodes delegate, final int permitsPerSecond) {
    return new BitnodesClient(delegate, Throttle.create(permitsPerSecond));
  }
}
