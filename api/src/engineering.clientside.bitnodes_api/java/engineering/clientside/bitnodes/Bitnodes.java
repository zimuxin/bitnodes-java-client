package engineering.clientside.bitnodes;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Accept: application/json")
public interface Bitnodes {

  String API_URL = System.getProperty(BitnodesConfig.API_URL.getPropName(),
      "https://bitnodes.21.co");
  String BASE_API_PATH = "/api/v1/";
  String BASE_GET_PATH = "GET " + BASE_API_PATH;
  String BASE_POST_PATH = "POST " + BASE_API_PATH;
  String DNS_SEED_URL = System.getProperty(BitnodesConfig.DNS_SEED_URL.getPropName(),
      "seed.bitnodes.io");
  int DEFAULT_NODE_PORT = Optional
      .ofNullable(System.getProperty(BitnodesConfig.DEFAULT_NODE_PORT.getPropName()))
      .map(Integer::parseInt).orElse(8333);

  @RequestLine(BASE_GET_PATH + "snapshots/?limit={limit}&page={page}")
  BitnodesSnapshots getSnapshots(@Param("limit") final int limit, @Param("page") final int page);

  @RequestLine(BASE_GET_PATH + "snapshots/?limit={limit}")
  BitnodesSnapshots getSnapshots(@Param("limit") final int limit);

  @RequestLine(BASE_GET_PATH + "snapshots/")
  BitnodesSnapshots getSnapshots();

  default BitnodesNodes getLatestNodesSnapshot() {
    final List<? extends BitnodesSnapshot> snapshots = getSnapshots(1).getResults();
    return snapshots.isEmpty() ? null : getNodes(snapshots.get(0));
  }

  @RequestLine(BASE_GET_PATH + "snapshots/{snapshotTimestamp}")
  BitnodesNodes getNodes(@Param("snapshotTimestamp") final long snapshotTimestamp);

  default BitnodesNodes getNodes(final BitnodesSnapshot snapshot) {
    return getNodes(snapshot.getTimestamp());
  }

  @RequestLine(BASE_GET_PATH + "nodes/{address}-{port}/")
  BitnodesNodeStatus getNodeStatus(@Param("address") final String address,
      @Param("port") final int port);

  default BitnodesNodeStatus getNodeStatus(final String address) {
    return getNodeStatus(address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_GET_PATH + "nodes/{address}-{port}/latency/")
  BitnodesNodeLatency getNodeLatency(@Param("address") final String address,
      @Param("port") final int port);

  default BitnodesNodeLatency getNodeLatency(final String address) {
    return getNodeLatency(address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/?limit={limit}&page={page}")
  BitnodesLeaderboard getLeaderboard(@Param("limit") final int limit,
      @Param("page") final int page);

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/?limit={limit}")
  BitnodesLeaderboard getLeaderboard(@Param("limit") final int limit);

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/")
  BitnodesLeaderboard getLeaderboard();

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/{address}-{port}/")
  BitnodesPeerIndexData getNodeRanking(@Param("address") final String address,
      @Param("port") final int port);

  default BitnodesPeerIndexData getNodeRanking(final String address) {
    return getNodeRanking(address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_GET_PATH + "inv/{invHash}/")
  BitnodesInvPropagation getInvPropagation(@Param("invHash") final String invHash);

  @RequestLine(BASE_POST_PATH + "nodes/{address}-{port}/")
  @Headers({
               "Content-type: application/x-www-form-urlencoded",
               "Accept: application/json",
           })
  @Body("bitcoin_address={bitcoin_address}&url={url}")
  BitnodesPostResponse postNodeBitcoinAddress(@Param("bitcoin_address") final String bitcoinAddress,
      @Param("url") final String url, @Param("address") final String address,
      @Param("port") final int port);

  default BitnodesPostResponse postNodeBitcoinAddress(final String bitcoinAddress, final String url,
      final String address) {
    return postNodeBitcoinAddress(bitcoinAddress, url, address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_POST_PATH + "nodes/{address}-{port}/")
  @Headers({
               "Content-type: application/x-www-form-urlencoded",
               "Accept: application/json",
           })
  @Body("bitcoin_address={bitcoin_address}")
  BitnodesPostResponse postNodeBitcoinAddress(@Param("bitcoin_address") final String bitcoinAddress,
      @Param("address") final String address, @Param("port") final int port);

  default BitnodesPostResponse postNodeBitcoinAddress(final String bitcoinAddress,
      final String address) {
    return postNodeBitcoinAddress(bitcoinAddress, address, DEFAULT_NODE_PORT);
  }

  default InetAddress[] getSeededNodeRecords() {
    try {
      return InetAddress.getAllByName(DNS_SEED_URL);
    } catch (final UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

  default InetAddress[] getIPV6SeededNodeRecords() {
    try {
      return Inet6Address.getAllByName(DNS_SEED_URL);
    } catch (final UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

  default InetAddress[] getIPV4SeededNodeRecords() {
    try {
      return Inet4Address.getAllByName(DNS_SEED_URL);
    } catch (final UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

  static String createFilteredDnsSeedUrl(final long servicesFilter) {
    return "x" + servicesFilter + "." + DNS_SEED_URL;
  }

  default InetAddress[] getSeededNodeRecords(final long servicesFilter) {
    try {
      return InetAddress.getAllByName(createFilteredDnsSeedUrl(servicesFilter));
    } catch (final UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

  default InetAddress[] getIPV6SeededNodeRecords(final long servicesFilter) {
    try {
      return Inet6Address.getAllByName(createFilteredDnsSeedUrl(servicesFilter));
    } catch (final UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

  default InetAddress[] getIPV4SeededNodeRecords(final long servicesFilter) {
    try {
      return Inet4Address.getAllByName(createFilteredDnsSeedUrl(servicesFilter));
    } catch (final UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }
}
