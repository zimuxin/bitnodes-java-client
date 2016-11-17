package engineering.clientside.bitnodes;

import java.util.Iterator;
import java.util.ServiceLoader;

import feign.Feign;

final class BitnodesCoderProvider {

  private BitnodesCoderProvider() {}

  private static volatile BitnodesCoder coder = null;

  static BitnodesCoder getCoder() {
    if (coder == null) {
      synchronized (BitnodesCoderProvider.class) {
        if (coder == null) {
          final ServiceLoader coderLoader = ServiceLoader.load(BitnodesCoder.class);
          final Iterator<BitnodesCoder> coders = coderLoader.iterator();
          if (coders.hasNext()) {
            coder = coders.next();
          }
        }
      }
    }
    return coder;
  }

  static Feign.Builder configureCoder(final Feign.Builder feignBuilder) {
    final BitnodesCoder coder = getCoder();
    if (coder != null) {
      feignBuilder.decoder(coder).encoder(coder);
    }
    return feignBuilder;
  }
}
