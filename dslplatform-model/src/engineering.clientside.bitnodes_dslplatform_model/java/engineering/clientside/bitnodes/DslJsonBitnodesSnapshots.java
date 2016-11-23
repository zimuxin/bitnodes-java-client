package engineering.clientside.bitnodes;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.util.List;

@CompiledJson
public final class DslJsonBitnodesSnapshots extends DslJsonBitnodesPage implements
    BitnodesSnapshots {

  @JsonAttribute(nullable = false)
  public List<DslJsonBitnodesSnapshot> results;

  @Override
  public List<? extends BitnodesSnapshot> getResults() {
    return results;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesSnapshots)) {
      return false;
    }
    final BitnodesSnapshots that = (BitnodesSnapshots) obj;
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
    return "BitnodesSnapshots{count=" + count
        + ", next='" + next + '\''
        + ", previous='" + previous + '\''
        + ", results=" + results + '}';
  }
}
