package engineering.clientside.bitnodes;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.util.List;

@CompiledJson
public final class DslJsonBitnodesInvPropagation implements BitnodesInvPropagation {

  @JsonAttribute(name = "inv_hash", nullable = false)
  public String invHash;
  @JsonAttribute(nullable = false)
  public NestedStats stats;

  @Override
  public String getInvHash() {
    return invHash;
  }

  @Override
  public List<BitnodesInvArrivalStamp> getHeadInvArrival() {
    return stats.headInvArrival;
  }

  @Override
  public DslJsonBitnodesInvArrivalStats getInvArrivalStats() {
    return stats.invArrivalStats;
  }

  @CompiledJson
  public static final class NestedStats {

    @JsonAttribute(name = "head", nullable = false)
    public List<BitnodesInvArrivalStamp> headInvArrival;
    @JsonAttribute(name = "stats", nullable = false)
    public DslJsonBitnodesInvArrivalStats invArrivalStats;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesInvPropagation)) {
      return false;
    }
    final BitnodesInvPropagation that = (BitnodesInvPropagation) obj;
    if (!invHash.equals(that.getInvHash())) {
      return false;
    }
    return stats.headInvArrival.equals(that.getHeadInvArrival())
        && stats.invArrivalStats.equals(that.getInvArrivalStats());
  }

  @Override
  public int hashCode() {
    int result = invHash.hashCode();
    result = 31 * result + stats.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesInvPropagation{invHash='" + invHash + '\''
        + ", headInvArrival=" + stats.headInvArrival
        + ", invArrivalStats=" + stats.invArrivalStats + '}';
  }
}
