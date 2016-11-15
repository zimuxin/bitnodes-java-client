package engineering.clientside.bitnodes.dslplatform;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import engineering.clientside.bitnodes.BitnodesNodeLatency;

import java.util.List;

@CompiledJson(baseReaders = {BitnodesNodeLatency.class})
public final class DslPlatformBitnodesNodeLatency implements BitnodesNodeLatency {

  @JsonAttribute(name = "daily_latency", nullable = false)
  public List<DslPlatformBitnodesStampedLatency> dailyLatency;

  @JsonAttribute(name = "weekly_latency", nullable = false)
  public List<DslPlatformBitnodesStampedLatency> weeklyLatency;

  @Override
  public List<DslPlatformBitnodesStampedLatency> getDailyLatency() {
    return dailyLatency;
  }

  @Override
  public List<DslPlatformBitnodesStampedLatency> getWeeklyLatency() {
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

  @Override
  public String toString() {
    return "BitnodesNodeLatency{dailyLatency=" + dailyLatency
        + ", weeklyLatency=" + weeklyLatency + '}';
  }
}
