package engineering.clientside.bitnodes;

import java.net.InetAddress;
import java.util.concurrent.CompletableFuture;

import engineering.clientside.throttle.Throttle;

final class BitnodesClient implements AsyncBitnodes {

  private final AsyncBitnodes delegate;
  private final Throttle throttle;

  BitnodesClient(final AsyncBitnodes delegate, final Throttle throttle) {
    this.delegate = delegate;
    this.throttle = throttle;
  }

  private AsyncBitnodes getDelegate() {
    return getDelegate(1);
  }

  private AsyncBitnodes getDelegate(final int permits) {
    throttle.acquireUnchecked(permits);
    return delegate;
  }

  @Override
  public BitnodesSnapshots getSnapshots(final int limit, final int page) {
    return getDelegate().getSnapshots(limit, page);
  }

  @Override
  public BitnodesSnapshots getSnapshots(final int limit) {
    return getDelegate().getSnapshots(limit);
  }

  @Override
  public BitnodesSnapshots getSnapshots() {
    return getDelegate().getSnapshots();
  }

  @Override
  public BitnodesNodes getNodes(final long snapshotTimestamp) {
    return getDelegate().getNodes(snapshotTimestamp);
  }

  @Override
  public BitnodesNodeStatus getNodeStatus(final String address, final int port) {
    return getDelegate().getNodeStatus(address, port);
  }

  @Override
  public BitnodesNodeLatency getNodeLatency(final String address, final int port) {
    return getDelegate().getNodeLatency(address, port);
  }

  @Override
  public BitnodesLeaderboard getLeaderboard(final int page, final int limit) {
    return getDelegate().getLeaderboard(page, limit);
  }

  @Override
  public BitnodesLeaderboard getLeaderboard(final int limit) {
    return getDelegate().getLeaderboard(limit);
  }

  @Override
  public BitnodesLeaderboard getLeaderboard() {
    return getDelegate().getLeaderboard();
  }

  @Override
  public BitnodesPeerIndexData getNodeRanking(final String address, final int port) {
    return getDelegate().getNodeRanking(address, port);
  }

  @Override
  public BitnodesInvPropagation getInvPropagation(final String invHash) {
    return getDelegate().getInvPropagation(invHash);
  }

  @Override
  public BitnodesPostResponse postNodeBitcoinAddress(final String bitcoinAddress, final String url,
      final String address, final int port) {
    return getDelegate().postNodeBitcoinAddress(bitcoinAddress, url, address, port);
  }

  @Override
  public BitnodesPostResponse postNodeBitcoinAddress(final String bitcoinAddress,
      final String address, final int port) {
    return getDelegate().postNodeBitcoinAddress(bitcoinAddress, address, port);
  }

  @Override
  public InetAddress[] getSeededNodeRecords() {
    return getDelegate().getSeededNodeRecords();
  }

  @Override
  public InetAddress[] getIPV6SeededNodeRecords() {
    return getDelegate().getIPV6SeededNodeRecords();
  }

  @Override
  public InetAddress[] getIPV4SeededNodeRecords() {
    return getDelegate().getIPV4SeededNodeRecords();
  }

  @Override
  public InetAddress[] getSeededNodeRecords(final long servicesFilter) {
    return getDelegate().getSeededNodeRecords(servicesFilter);
  }

  @Override
  public InetAddress[] getIPV6SeededNodeRecords(final long servicesFilter) {
    return getDelegate().getIPV6SeededNodeRecords(servicesFilter);
  }

  @Override
  public InetAddress[] getIPV4SeededNodeRecords(final long servicesFilter) {
    return getDelegate().getIPV4SeededNodeRecords(servicesFilter);
  }

  @Override
  public CompletableFuture<BitnodesSnapshots> getSnapshotsFuture(final int limit, final int page) {
    return getDelegate().getSnapshotsFuture(limit, page);
  }

  @Override
  public CompletableFuture<BitnodesSnapshots> getSnapshotsFuture(final int limit) {
    return getDelegate().getSnapshotsFuture(limit);
  }

  @Override
  public CompletableFuture<BitnodesSnapshots> getSnapshotsFuture() {
    return getDelegate().getSnapshotsFuture();
  }

  @Override
  public CompletableFuture<BitnodesNodes> getNodesFuture(final long snapshotTimestamp) {
    return getDelegate().getNodesFuture(snapshotTimestamp);
  }

  @Override
  public CompletableFuture<BitnodesNodeStatus> getNodeStatusFuture(final String address,
      final int port) {
    return getDelegate().getNodeStatusFuture(address, port);
  }

  @Override
  public CompletableFuture<BitnodesNodeLatency> getNodeLatencyFuture(final String address,
      final int port) {
    return getDelegate().getNodeLatencyFuture(address, port);
  }

  @Override
  public CompletableFuture<BitnodesLeaderboard> getLeaderboardFuture(final int limit,
      final int page) {
    return getDelegate().getLeaderboardFuture(limit, page);
  }

  @Override
  public CompletableFuture<BitnodesLeaderboard> getLeaderboardFuture(final int limit) {
    return getDelegate().getLeaderboardFuture(limit);
  }

  @Override
  public CompletableFuture<BitnodesLeaderboard> getLeaderboardFuture() {
    return getDelegate().getLeaderboardFuture();
  }

  @Override
  public CompletableFuture<BitnodesPeerIndexData> getNodeRankingFuture(final String address,
      final int port) {
    return getDelegate().getNodeRankingFuture(address, port);
  }

  @Override
  public CompletableFuture<BitnodesInvPropagation> getInvPropagationFuture(final String invHash) {
    return getDelegate().getInvPropagationFuture(invHash);
  }

  @Override
  public CompletableFuture<BitnodesPostResponse> postNodeBitcoinAddressFuture(
      final String bitcoinAddress, final String url, final String address, final int port) {
    return getDelegate().postNodeBitcoinAddressFuture(bitcoinAddress, url, address, port);
  }

  @Override
  public CompletableFuture<BitnodesPostResponse> postNodeBitcoinAddressFuture(
      final String bitcoinAddress, final String address, final int port) {
    return getDelegate().postNodeBitcoinAddressFuture(bitcoinAddress, address, port);
  }
}
