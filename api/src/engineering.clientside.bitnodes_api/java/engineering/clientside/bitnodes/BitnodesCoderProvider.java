package engineering.clientside.bitnodes;

import java.util.Iterator;
import java.util.ServiceLoader;

import feign.Feign;

final class BitnodesCoderProvider {

  private BitnodesCoderProvider() {}

  private static final BitnodesCoder coder = loadCoder();

  private static BitnodesCoder loadCoder() {
    final ServiceLoader coderLoader = ServiceLoader.load(BitnodesCoder.class);
    final Iterator<BitnodesCoder> coders = coderLoader.iterator();
    return coders.hasNext() ? coders.next() : null;
  }

  static BitnodesCoder getCoder() {
    return coder;
  }

  static Feign.Builder configureCoder(final Feign.Builder feignBuilder) {
    return coder == null ? feignBuilder : feignBuilder.decoder(coder).encoder(coder);
  }
}
