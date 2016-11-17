package engineering.clientside.bitnodes;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import feign.Feign;
import feign.RequestLine;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import rx.Observable;
import rx.Single;

public interface HystrixBitnodes extends Bitnodes {

  static HystrixBitnodes create() {
    return create(HystrixFeign.builder());
  }

  static HystrixBitnodes create(final HystrixCommandProperties.Setter commandProperties) {
    return create(HystrixFeign.builder(), commandProperties);
  }

  static HystrixBitnodes create(final HystrixFeign.Builder feignBuilder) {
    return create(feignBuilder, Bitnodes.API_URL);
  }

  static HystrixBitnodes create(final HystrixFeign.Builder feignBuilder,
      final HystrixCommandProperties.Setter commandProperties) {
    return create(feignBuilder, Bitnodes.API_URL, commandProperties);
  }

  static HystrixBitnodes create(final HystrixFeign.Builder feignBuilder,
      final String apiUrl) {
    final HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
        .withExecutionTimeoutInMilliseconds(7000);
    return create(feignBuilder, Bitnodes.API_URL, commandProperties);
  }

  static HystrixBitnodes create(final HystrixFeign.Builder feignBuilder,
      final String apiUrl, final HystrixCommandProperties.Setter commandProperties) {
    final HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey(apiUrl);
    final SetterFactory commandKeyIsRequestLine = (target, method) -> HystrixCommand.Setter
        .withGroupKey(groupKey)
        .andCommandKey(HystrixCommandKey.Factory.asKey(Feign.configKey(target.type(), method)))
        .andCommandPropertiesDefaults(commandProperties);
    return Bitnodes.create(feignBuilder
        .setterFactory(commandKeyIsRequestLine), HystrixBitnodes.class, apiUrl);
  }

  @RequestLine(BASE_GET_PATH + "snapshots/")
  HystrixCommand<BitnodesSnapshots> getHystrixSnapshots();

  @RequestLine(BASE_GET_PATH + "snapshots/")
  Observable<BitnodesSnapshots> getObservableSnapshots();

  @RequestLine(BASE_GET_PATH + "snapshots/")
  Single<BitnodesSnapshots> getSingleSnapshots();
}
