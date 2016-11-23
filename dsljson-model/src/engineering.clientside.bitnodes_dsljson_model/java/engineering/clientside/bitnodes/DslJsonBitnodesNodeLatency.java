package engineering.clientside.bitnodes;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

import java.util.List;

@CompiledJson
public final class DslJsonBitnodesNodeLatency implements BitnodesNodeLatency {

  @JsonAttribute(name = "daily_latency", nullable = false)
  public List<DslJsonBitnodesStampedLatency> dailyLatency;

  @JsonAttribute(name = "weekly_latency", nullable = false)
  public List<DslJsonBitnodesStampedLatency> weeklyLatency;

  @Override
  public List<DslJsonBitnodesStampedLatency> getDailyLatency() {
    return dailyLatency;
  }

  @Override
  public List<DslJsonBitnodesStampedLatency> getWeeklyLatency() {
    return weeklyLatency;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesNodeLatency)) {
      return false;
    }
    final BitnodesNodeLatency that = (BitnodesNodeLatency) obj;
    if (!dailyLatency.equals(that.getDailyLatency())) {
      return false;
    }
    return weeklyLatency.equals(that.getWeeklyLatency());
  }

  @Override
  public int hashCode() {
    int result = dailyLatency.hashCode();
    result = 31 * result + weeklyLatency.hashCode();
    return result;
  }
}
