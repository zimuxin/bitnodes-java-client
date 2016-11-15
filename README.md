#[Bitnodes](https://bitnodes.21.co/api/) Java client [![Build Status](https://travis-ci.org/client-side/bitnodes-java-client.svg?branch=master)](https://travis-ci.org/client-side/bitnodes-java-client) [![JCenter](https://api.bintray.com/packages/client-side/libs/bitnodes-java-client/images/download.svg) ](https://bintray.com/client-side/libs/bitnodes-java-client/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](http://www.apache.org/licenses/LICENSE-2.0)

### [List Snapshots](https://bitnodes.21.co/api/#list-snapshots) & [Nodes](https://bitnodes.21.co/api/#list-nodes)
```java
int permistPerSecond = 2;
Bitnodes client = BitnodesFactory.create(permistPerSecond);

BitnodesSnapshots snapshotsPage = client.getSnapshots();
BitnodesSnapshot snapshot = snapshotsPage.getResults().get(0);
BitnodesNodes nodes = client.getNodes(snapshot);
nodes.getNodes().forEach((nodeAddress, node) -> {
  System.out.format("%s: %s%n", nodeAddress, node);
});
```

### [Get Node Status](https://bitnodes.21.co/api/#node-status) & [Latency](https://bitnodes.21.co/api/#node-latency)
```java
Bitnodes client = BitnodesFactory.create();

BitnodesNodeStatus nodeStatus = client.getNodeStatus(nodeAddress);
BitnodesNode node = nodeStatus.getData();

BitnodesNodeLatency nodeLatency = client.getNodeLatency(nodeAddress);
List<? extends BitnodesStampedLatency> dailyLatency = nodeLatency.getDailyLatency();
List<? extends BitnodesStampedLatency> weeklyLatency = nodeLatency.getWeeklyLatency();
```

### [Get Leaderboard](https://bitnodes.21.co/api/#leaderboard)
```java
Bitnodes client = BitnodesFactory.create();
BitnodesLeaderboard leaderboard = client.getLeaderboard();
List<? extends BitnodesPeerIndexData> results = leaderboard.getResults();
```

### [Get Data Propagation](https://bitnodes.21.co/api/#data-propagation)
```java
Bitnodes client = BitnodesFactory.create();
BitnodesInvPropagation invPropagation = client.getInvPropagation(invHash);
List<BitnodesInvArrivalStamp> arrivalStamps = invPropagation.getHeadInvArrival();
BitnodesInvArrivalStats arrivalStats = invPropagation.getInvArrivalStats();
```

### [Post Node Bitcoin Address & URL](https://bitnodes.21.co/api/#node-bitcoin-address)
```java
Bitnodes client = BitnodesFactory.create();
String bitcoinAddress = "17vWico7jLKQoXtfWHpvEhHzzremZqQnNM";
String nodeAddress = "2a01:4f8:202:81b1::2";
String url = "http://[" + nodeAddress + ']';
client.postNodeBitcoinAddress(bitcoinAddress, url, nodeAddress);
```

### [Get IPV4/IPV6 Nodes From DNS Seed](https://bitnodes.21.co/api/#dns-seeder)
```java
Bitnodes client = BitnodesFactory.create();
InetAddress[] nodeAddresses = client.getSeededNodeRecords();
InetAddress[] ipv4Nodes = client.getIPV4SeededNodeRecords();
InetAddress[] ipv6Nodes = client.getIPV6SeededNodeRecords();
```

### Dependencies

```
\ engineering.clientside:bitnodes-java-throttled-client
+--- project :bitnodes-java-api
|    \--- io.github.openfeign:feign-core
|         \--- org.jvnet:animal-sniffer-annotation
+--- project :bitnodes-java-dslplatform-data
|    +--- project :bitnodes-java-api 
|    +--- io.github.openfeign:feign-core 
|    \--- com.dslplatform:dsl-json-java8 
|         \--- com.dslplatform:dsl-json 
+--- io.github.openfeign:feign-core 
+--- com.fabahaba:throttle
\--- com.dslplatform:dsl-json-java8 
```
