package engineering.clientside.bitnodes;

import java.util.List;

public interface BitnodesNodeLatency {

  List<? extends BitnodesStampedLatency> getDailyLatency();

  List<? extends BitnodesStampedLatency> getWeeklyLatency();
}
