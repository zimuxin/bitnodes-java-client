package engineering.clientside.bitnodes.dslplatform;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.util.List;

import engineering.clientside.bitnodes.BitnodesLeaderboard;
import engineering.clientside.bitnodes.BitnodesPeerIndexData;

@CompiledJson
public final class DslPlatformBitnodesLeaderboard extends DslPlatformBitnodesPage implements
    BitnodesLeaderboard {

  @JsonAttribute(nullable = false)
  public List<DslPlatformBitnodesPeerIndexData> results;

  @Override
  public List<? extends BitnodesPeerIndexData> getResults() {
    return results;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesLeaderboard)) {
      return false;
    }
    final BitnodesLeaderboard that = (BitnodesLeaderboard) obj;
    if (count != that.getCount()) {
      return false;
    }
    if (next != null ? !next.equals(that.getNext()) : that.getNext() != null) {
      return false;
    }
    if (previous != null ? !previous.equals(that.getPrevious()) : that.getPrevious() != null) {
      return false;
    }
    return results.equals(that.getResults());
  }

  @Override
  public int hashCode() {
    int result = count;
    result = 31 * result + (next != null ? next.hashCode() : 0);
    result = 31 * result + (previous != null ? previous.hashCode() : 0);
    result = 31 * result + results.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesLeaderboard{count=" + count
        + ", next='" + next + '\''
        + ", previous='" + previous + '\''
        + ", results=" + results + '}';
  }
}
