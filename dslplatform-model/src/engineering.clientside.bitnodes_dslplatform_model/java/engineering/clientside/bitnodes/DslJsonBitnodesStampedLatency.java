package engineering.clientside.bitnodes;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
public final class DslJsonBitnodesStampedLatency implements BitnodesStampedLatency {

  @JsonAttribute(name = "t", nullable = false)
  public long timestamp;
  @JsonAttribute(name = "v", nullable = false)
  public int latency;

  @Override
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public int getLatency() {
    return latency;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesStampedLatency)) {
      return false;
    }
    final BitnodesStampedLatency that = (BitnodesStampedLatency) obj;
    if (timestamp != that.getTimestamp()) {
      return false;
    }
    return latency == that.getLatency();
  }

  @Override
  public int hashCode() {
    int result = (int) (timestamp ^ (timestamp >>> 32));
    result = 31 * result + latency;
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesStampedLatency{timestamp=" + timestamp + ", latency=" + latency + '}';
  }
}
