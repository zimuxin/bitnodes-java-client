package engineering.clientside.bitnodes.dslplatform;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import engineering.clientside.bitnodes.BitnodesSnapshot;

@CompiledJson(baseReaders = {BitnodesSnapshot.class})
public final class DslPlatformBitnodesSnapshot implements BitnodesSnapshot {

  @JsonAttribute(nullable = false)
  public String url;
  public long timestamp;
  @JsonAttribute(name = "total_nodes", nullable = false)
  public int totalNodes;
  @JsonAttribute(name = "latest_height", nullable = false)
  public long latestHeight;

  public DslPlatformBitnodesSnapshot() {}

  @Override
  public String getUrl() {
    return url;
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
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesSnapshot)) {
      return false;
    }
    final BitnodesSnapshot that = (BitnodesSnapshot) obj;
    if (timestamp != that.getTimestamp()) {
      return false;
    }
    if (totalNodes != that.getTotalNodes()) {
      return false;
    }
    if (latestHeight != that.getLatestHeight()) {
      return false;
    }
    return url.equals(that.getUrl());
  }

  @Override
  public int hashCode() {
    int result = url.hashCode();
    result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
    result = 31 * result + totalNodes;
    result = 31 * result + (int) (latestHeight ^ (latestHeight >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesSnapshot{url='" + url + '\''
        + ", timestamp=" + timestamp
        + ", totalNodes=" + totalNodes
        + ", latestHeight=" + latestHeight + '}';
  }
}
