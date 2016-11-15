package engineering.clientside.bitnodes;

import java.util.Map;

public interface BitnodesNodes {

  long getTimestamp();

  int getTotalNodes();

  long getLatestHeight();

  Map<String, BitnodesNode> getNodes();
}
