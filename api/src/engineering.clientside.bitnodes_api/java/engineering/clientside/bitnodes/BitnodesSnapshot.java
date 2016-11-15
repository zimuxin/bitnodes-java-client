package engineering.clientside.bitnodes;

public interface BitnodesSnapshot {

  String getUrl();

  long getTimestamp();

  int getTotalNodes();

  long getLatestHeight();
}
