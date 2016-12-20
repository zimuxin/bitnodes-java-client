package engineering.clientside.bitnodes;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface AsyncBitnodes extends Bitnodes {

  @RequestLine(BASE_GET_PATH + "snapshots/?limit={limit}&page={page}")
  CompletableFuture<BitnodesSnapshots> getSnapshotsFuture(@Param("limit") final int limit,
      @Param("page") final int page);

  @RequestLine(BASE_GET_PATH + "snapshots/?limit={limit}")
  CompletableFuture<BitnodesSnapshots> getSnapshotsFuture(@Param("limit") final int limit);

  @RequestLine(BASE_GET_PATH + "snapshots/")
  CompletableFuture<BitnodesSnapshots> getSnapshotsFuture();

  default CompletableFuture<BitnodesNodes> getLatestNodesSnapshotFuture() {
    return getSnapshotsFuture(1).thenApplyAsync(snapshotResult -> {
      final List<? extends BitnodesSnapshot> snapshots = snapshotResult.getResults();
      return snapshots.isEmpty() ? null : getNodes(snapshots.get(0));
    });
  }

  @RequestLine(BASE_GET_PATH + "snapshots/{snapshotTimestamp}")
  CompletableFuture<BitnodesNodes> getNodesFuture(
      @Param("snapshotTimestamp") final long snapshotTimestamp);

  default CompletableFuture<BitnodesNodes> getNodesFuture(final BitnodesSnapshot snapshot) {
    return getNodesFuture(snapshot.getTimestamp());
  }

  @RequestLine(BASE_GET_PATH + "nodes/{address}-{port}/")
  CompletableFuture<BitnodesNodeStatus> getNodeStatusFuture(@Param("address") final String address,
      @Param("port") final int port);

  default CompletableFuture<BitnodesNodeStatus> getNodeStatusFuture(final String address) {
    return getNodeStatusFuture(address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_GET_PATH + "nodes/{address}-{port}/latency/")
  CompletableFuture<BitnodesNodeLatency> getNodeLatencyFuture(
      @Param("address") final String address, @Param("port") final int port);

  default CompletableFuture<BitnodesNodeLatency> getNodeLatencyFuture(final String address) {
    return getNodeLatencyFuture(address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/?limit={limit}&page={page}")
  CompletableFuture<BitnodesLeaderboard> getLeaderboardFuture(@Param("limit") final int limit,
      @Param("page") final int page);

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/?limit={limit}")
  CompletableFuture<BitnodesLeaderboard> getLeaderboardFuture(@Param("limit") final int limit);

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/")
  CompletableFuture<BitnodesLeaderboard> getLeaderboardFuture();

  @RequestLine(BASE_GET_PATH + "nodes/leaderboard/{address}-{port}/")
  CompletableFuture<BitnodesPeerIndexData> getNodeRankingFuture(
      @Param("address") final String address, @Param("port") final int port);

  default CompletableFuture<BitnodesPeerIndexData> getNodeRankingFuture(final String address) {
    return getNodeRankingFuture(address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_GET_PATH + "inv/{invHash}/")
  CompletableFuture<BitnodesInvPropagation> getInvPropagationFuture(
      @Param("invHash") final String invHash);

  @RequestLine(BASE_POST_PATH + "nodes/{address}-{port}/")
  @Headers({
               "Content-type: application/x-www-form-urlencoded",
               "Accept: application/json",
           })
  @Body("bitcoin_address={bitcoin_address}&url={url}")
  CompletableFuture<BitnodesPostResponse> postNodeBitcoinAddressFuture(
      @Param("bitcoin_address") final String bitcoinAddress, @Param("url") final String url,
      @Param("address") final String address, @Param("port") final int port);

  default CompletableFuture<BitnodesPostResponse> postNodeBitcoinAddressFuture(
      final String bitcoinAddress, final String url, final String address) {
    return postNodeBitcoinAddressFuture(bitcoinAddress, url, address, DEFAULT_NODE_PORT);
  }

  @RequestLine(BASE_POST_PATH + "nodes/{address}-{port}/")
  @Headers({
               "Content-type: application/x-www-form-urlencoded",
               "Accept: application/json",
           })
  @Body("bitcoin_address={bitcoin_address}")
  CompletableFuture<BitnodesPostResponse> postNodeBitcoinAddressFuture(
      @Param("bitcoin_address") final String bitcoinAddress,
      @Param("address") final String address, @Param("port") final int port);

  default CompletableFuture<BitnodesPostResponse> postNodeBitcoinAddressFuture(
      final String bitcoinAddress, final String address) {
    return postNodeBitcoinAddressFuture(bitcoinAddress, address, DEFAULT_NODE_PORT);
  }

  default CompletableFuture<InetAddress[]> getSeededNodeRecordsFuture() {
    return CompletableFuture.supplyAsync(() -> getSeededNodeRecords());
  }

  default CompletableFuture<InetAddress[]> getIPV6SeededNodeRecordsFuture() {
    return CompletableFuture.supplyAsync(() -> getIPV6SeededNodeRecords());
  }

  default CompletableFuture<InetAddress[]> getIPV4SeededNodeRecordsFuture() {
    return CompletableFuture.supplyAsync(() -> getIPV4SeededNodeRecords());
  }

  default CompletableFuture<InetAddress[]> getSeededNodeRecordsFuture(final long servicesFilter) {
    return CompletableFuture.supplyAsync(() -> getSeededNodeRecords(servicesFilter));
  }

  default CompletableFuture<InetAddress[]> getIPV6SeededNodeRecordsFuture(
      final long servicesFilter) {
    return CompletableFuture.supplyAsync(() -> getIPV6SeededNodeRecords(servicesFilter));
  }

  default CompletableFuture<InetAddress[]> getIPV4SeededNodeRecordsFuture(
      final long servicesFilter) {
    return CompletableFuture.supplyAsync(() -> getIPV4SeededNodeRecords(servicesFilter));
  }
}
