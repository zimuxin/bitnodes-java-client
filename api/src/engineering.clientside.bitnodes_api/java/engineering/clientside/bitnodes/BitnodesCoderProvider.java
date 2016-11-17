package engineering.clientside.bitnodes;

import java.util.Iterator;
import java.util.ServiceLoader;

final class BitnodesCoderProvider {

  private BitnodesCoderProvider() {}

  private static volatile BitnodesCoder coder = null;

  static BitnodesCoder getCoder() {
    if (coder == null) {
      synchronized (Bitnodes.API_URL) {
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
}
