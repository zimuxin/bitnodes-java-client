package com.fabahaba.bitnodes;


public interface BitnodesNodeStatus {

  String getHostname();

  String getAddress();

  BitnodesNode.Status getStatus();

  String getBitcoinAddress();

  String getUrl();

  boolean isVerified();

  BitnodesNode getData();
}
