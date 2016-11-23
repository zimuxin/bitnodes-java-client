package engineering.clientside.bitnodes;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.util.List;

@CompiledJson
public final class DslJsonBitnodesLeaderboard extends DslJsonBitnodesPage implements
    BitnodesLeaderboard {

  @JsonAttribute(nullable = false)
  public List<DslJsonBitnodesPeerIndexData> results;

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
}
