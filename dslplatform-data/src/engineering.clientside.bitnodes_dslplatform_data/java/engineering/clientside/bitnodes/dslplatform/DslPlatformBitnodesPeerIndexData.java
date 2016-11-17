package engineering.clientside.bitnodes.dslplatform;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import engineering.clientside.bitnodes.BitnodesPeerIndexData;

@CompiledJson
public final class DslPlatformBitnodesPeerIndexData implements BitnodesPeerIndexData {

  @JsonAttribute(nullable = false)
  public String node;
  @JsonAttribute(name = "vi", nullable = false)
  public double protocolVersionIndex;
  @JsonAttribute(name = "si", nullable = false)
  public double servicesIndex;
  @JsonAttribute(name = "hi", nullable = false)
  public double heightIndex;
  @JsonAttribute(name = "ai", nullable = false)
  public double asnIndex;
  @JsonAttribute(name = "pi", nullable = false)
  public double portIndex;
  @JsonAttribute(name = "dli", nullable = false)
  public double avgDailyLatencyIndex;
  @JsonAttribute(name = "dui", nullable = false)
  public double dailyUptimeIndex;
  @JsonAttribute(name = "wli", nullable = false)
  public double avgWeeklyLatencyIndex;
  @JsonAttribute(name = "wui", nullable = false)
  public double weeklyUptimeIndex;
  @JsonAttribute(name = "ni", nullable = false)
  public double nodesIndex;
  @JsonAttribute(name = "bi", nullable = false)
  public double blockIndex;
  @JsonAttribute(name = "peer_index", nullable = false)
  public double peerIndex;
  public int rank;

  @Override
  public String getNode() {
    return node;
  }

  @Override
  public double getProtocolVersionIndex() {
    return protocolVersionIndex;
  }

  @Override
  public double getServicesIndex() {
    return servicesIndex;
  }

  @Override
  public double getHeightIndex() {
    return heightIndex;
  }

  @Override
  public double getASNIndex() {
    return asnIndex;
  }

  @Override
  public double getPortIndex() {
    return portIndex;
  }

  @Override
  public double getDailyUptimeIndex() {
    return dailyUptimeIndex;
  }

  @Override
  public double getWeeklyUptimeIndex() {
    return weeklyUptimeIndex;
  }

  @Override
  public double getAverageDailyLatencyIndex() {
    return avgDailyLatencyIndex;
  }

  @Override
  public double getAverageWeeklyLatencyIndex() {
    return avgWeeklyLatencyIndex;
  }

  @Override
  public double getNodesIndex() {
    return nodesIndex;
  }

  @Override
  public double getBlockIndex() {
    return blockIndex;
  }

  @Override
  public double getPeerIndex() {
    return peerIndex;
  }

  @Override
  public int getRank() {
    return rank;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof  BitnodesPeerIndexData)) {
      return false;
    }
    final BitnodesPeerIndexData that = (BitnodesPeerIndexData) obj;
    if (Double.compare(that.getProtocolVersionIndex(), protocolVersionIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getServicesIndex(), servicesIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getHeightIndex(), heightIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getASNIndex(), asnIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getPortIndex(), portIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getAverageDailyLatencyIndex(), avgDailyLatencyIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getDailyUptimeIndex(), dailyUptimeIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getAverageWeeklyLatencyIndex(), avgWeeklyLatencyIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getWeeklyUptimeIndex(), weeklyUptimeIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getNodesIndex(), nodesIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getBlockIndex(), blockIndex) != 0) {
      return false;
    }
    if (Double.compare(that.getPeerIndex(), peerIndex) != 0) {
      return false;
    }
    if (rank != that.getRank()) {
      return false;
    }
    return node.equals(that.getNode());
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = node.hashCode();
    temp = Double.doubleToLongBits(protocolVersionIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(servicesIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(heightIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(asnIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(portIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(avgDailyLatencyIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(dailyUptimeIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(avgWeeklyLatencyIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(weeklyUptimeIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(nodesIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(blockIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(peerIndex);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + rank;
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesPeerIndexData{node='" + node + '\''
        + ", protocolVersionIndex=" + protocolVersionIndex
        + ", servicesIndex=" + servicesIndex
        + ", heightIndex=" + heightIndex
        + ", asnIndex=" + asnIndex
        + ", portIndex=" + portIndex
        + ", avgDailyLatencyIndex=" + avgDailyLatencyIndex
        + ", dailyUptimeIndex=" + dailyUptimeIndex
        + ", avgWeeklyLatencyIndex=" + avgWeeklyLatencyIndex
        + ", weeklyUptimeIndex=" + weeklyUptimeIndex
        + ", nodesIndex=" + nodesIndex
        + ", blockIndex=" + blockIndex
        + ", peerIndex=" + peerIndex
        + ", rank=" + rank + '}';
  }
}
