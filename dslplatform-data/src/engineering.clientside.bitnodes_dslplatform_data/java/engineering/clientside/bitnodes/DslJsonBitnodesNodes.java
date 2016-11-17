package engineering.clientside.bitnodes;

import java.util.Map;

final class DslJsonBitnodesNodes implements BitnodesNodes {

  private final long timestamp;
  private final int totalNodes;
  private final long latestHeight;
  private final Map<String, BitnodesNode> nodes;

  DslJsonBitnodesNodes(final long timestamp, final int totalNodes, final long latestHeight,
      final Map<String, BitnodesNode> nodes) {
    this.timestamp = timestamp;
    this.totalNodes = totalNodes;
    this.latestHeight = latestHeight;
    this.nodes = nodes;
  }

  @Override
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public int getTotalNodes() {
    return totalNodes;
  }

  @Override
  public long getLatestHeight() {
    return latestHeight;
  }

  @Override
  public Map<String, BitnodesNode> getNodes() {
    return nodes;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesNodes)) {
      return false;
    }
    final BitnodesNodes that = (BitnodesNodes) obj;
    if (timestamp != that.getTimestamp()) {
      return false;
    }
    if (totalNodes != that.getTotalNodes()) {
      return false;
    }
    if (latestHeight != that.getLatestHeight()) {
      return false;
    }
    return nodes.equals(that.getNodes());
  }

  @Override
  public int hashCode() {
    int result = (int) (timestamp ^ (timestamp >>> 32));
    result = 31 * result + totalNodes;
    result = 31 * result + (int) (latestHeight ^ (latestHeight >>> 32));
    result = 31 * result + nodes.hashCode();
    return result;
  }


  @Override
  public String toString() {
    return "BitnodesNodes{timestamp=" + timestamp
        + ", totalNodes=" + totalNodes
        + ", latestHeight=" + latestHeight
        + ", nodes=" + nodes + '}';
  }
}
