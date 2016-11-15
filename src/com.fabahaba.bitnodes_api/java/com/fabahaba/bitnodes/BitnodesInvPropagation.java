package com.fabahaba.bitnodes;

import java.util.List;

public interface BitnodesInvPropagation {

  String getInvHash();

  List<BitnodesInvArrivalStamp> getHeadInvArrival();

  BitnodesInvArrivalStats getInvArrivalStats();
}
