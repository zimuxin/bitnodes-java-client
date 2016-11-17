# [Bitnodes](https://bitnodes.21.co/api/) Java Client [![Build Status](https://travis-ci.org/client-side/bitnodes-java-client.svg?branch=master)](https://travis-ci.org/client-side/bitnodes-java-client)  [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](http://www.apache.org/licenses/LICENSE-2.0)

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

### [Get IPV4/6 Nodes From DNS Seed](https://bitnodes.21.co/api/#dns-seeder)
```java
Bitnodes client = BitnodesFactory.create();
InetAddress[] nodeAddresses = client.getSeededNodeRecords();
InetAddress[] ipv4Nodes = client.getIPV4SeededNodeRecords();
InetAddress[] ipv6Nodes = client.getIPV6SeededNodeRecords();
```

## Dependencies

```
\ engineering.clientside:bitnodes-java-throttled-client
+--- project :bitnodes-java-api
|    \--- io.github.openfeign:feign-core
+--- project :bitnodes-java-dslplatform-data
|    +--- project :bitnodes-java-api (*)
|    \--- com.dslplatform:dsl-json
+--- io.github.openfeign:feign-core
+--- com.fabahaba:throttle
```

* [![:bitnodes-java-throttled-client:](https://api.bintray.com/packages/client-side/clients/bitnodes-java-throttled-client/images/download.svg) ](https://bintray.com/client-side/clients/bitnodes-java-throttled-client/_latestVersion)
  * [![:bitnodes-java-api:](https://api.bintray.com/packages/client-side/clients/bitnodes-java-api/images/download.svg) ](https://bintray.com/client-side/clients/bitnodes-java-api/_latestVersion)
  * [![:bitnodes-java-dslplatform-data:](https://api.bintray.com/packages/client-side/clients/bitnodes-java-dslplatform-data/images/download.svg) ](https://bintray.com/client-side/clients/bitnodes-java-dslplatform-data/_latestVersion)

## Developer Builds

This is primarily a Java project, however, the compilation dependency on `com.dslplatform:dsl-json-processor` requires [Mono](http://www.mono-project.com/).  The Docker image [comodal/alpine-jdk-mono](https://hub.docker.com/r/comodal/alpine-jdk-mono/) can be used so that you do not have to install Mono.

### Docker Compile Example
```sh
~/git/bitnodes-java-client > docker run --rm -it\
 -v $GRADLE_USER_HOME:/root/.gradle\
 -v $(pwd):/bitnodes/\
 -w /bitnodes\
 comodal/alpine-jdk-mono:latest sh
 
/bitnodes > ./gradlew compileJava
Starting a Gradle Daemon
...
```
