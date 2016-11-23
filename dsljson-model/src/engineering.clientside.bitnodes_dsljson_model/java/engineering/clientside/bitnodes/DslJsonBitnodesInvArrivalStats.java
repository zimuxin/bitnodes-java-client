package engineering.clientside.bitnodes;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
public final class DslJsonBitnodesInvArrivalStats implements BitnodesInvArrivalStats {

  @JsonAttribute(name = "std", nullable = false)
  public int stdDev;
  @JsonAttribute(nullable = false)
  public int min;
  @JsonAttribute(nullable = false)
  public int max;
  @JsonAttribute(name = "50%", nullable = false)
  public int fiftiethPercentile;
  @JsonAttribute(name = "90%", nullable = false)
  public int ninetiethPercentile;
  @JsonAttribute(nullable = false)
  public int mean;

  @Override
  public int getStdDev() {
    return stdDev;
  }

  @Override
  public int getMin() {
    return min;
  }

  @Override
  public int getMax() {
    return max;
  }

  @Override
  public int getFiftiethPercentile() {
    return fiftiethPercentile;
  }

  @Override
  public int getNinetiethPercentile() {
    return ninetiethPercentile;
  }

  @Override
  public int getMean() {
    return mean;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesInvArrivalStats)) {
      return false;
    }
    final BitnodesInvArrivalStats that = (BitnodesInvArrivalStats) obj;
    if (stdDev != that.getStdDev()) {
      return false;
    }
    if (min != that.getMin()) {
      return false;
    }
    if (max != that.getMax()) {
      return false;
    }
    if (fiftiethPercentile != that.getFiftiethPercentile()) {
      return false;
    }
    if (ninetiethPercentile != that.getNinetiethPercentile()) {
      return false;
    }
    return mean == that.getMean();
  }

  @Override
  public int hashCode() {
    int result = stdDev;
    result = 31 * result + min;
    result = 31 * result + max;
    result = 31 * result + fiftiethPercentile;
    result = 31 * result + ninetiethPercentile;
    result = 31 * result + mean;
    return result;
  }
}
