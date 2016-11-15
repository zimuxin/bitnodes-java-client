package engineering.clientside.bitnodes;

public interface BitnodesInvArrivalStats {

  int getStdDev();

  int getMin();

  int getMax();

  int getFiftiethPercentile();

  int getNinetiethPercentile();

  int getMean();
}
