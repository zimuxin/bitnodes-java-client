package com.fabahaba.bitnodes;

public interface BitnodesPeerIndexData {

  String getNode();

  double getProtocolVersionIndex();

  double getServicesIndex();

  double getHeightIndex();

  double getASNIndex();

  double getPortIndex();

  double getAverageDailyLatencyIndex();

  double getDailyUptimeIndex();

  double getAverageWeeklyLatencyIndex();

  double getWeeklyUptimeIndex();

  double getNodesIndex();

  double getBlockIndex();

  double getPeerIndex();

  int getRank();
}
