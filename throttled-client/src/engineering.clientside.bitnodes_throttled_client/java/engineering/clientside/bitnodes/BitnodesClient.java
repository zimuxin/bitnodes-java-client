package engineering.clientside.bitnodes;

import com.fabahaba.throttle.Throttle;

final class BitnodesClient implements Bitnodes {

  private final Bitnodes delegate;
  private final Throttle throttle;

  BitnodesClient(final Bitnodes delegate, final Throttle throttle) {
    this.delegate = delegate;
    this.throttle = throttle;
  }

  private Bitnodes getDelegate() {
    return getClient(1);
  }

  private Bitnodes getClient(final int permits) {
    throttle.acquire(permits);
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
    return null;
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
}
