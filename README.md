# [Bitnodes](https://bitnodes.21.co/api/) Java Client [![Build Status](https://travis-ci.org/client-side/bitnodes-java-client.svg?branch=master)](https://travis-ci.org/client-side/bitnodes-java-client) [![:bitnodes-java-api:](https://api.bintray.com/packages/client-side/clients/bitnodes-java-api/images/download.svg) ](https://bintray.com/client-side/clients/bitnodes-java-api/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](http://www.apache.org/licenses/LICENSE-2.0)

## [Throttle](https://github.com/jamespedwards42/throttle) Client [![:bitnodes-java-throttle-client:](https://api.bintray.com/packages/client-side/clients/bitnodes-java-throttled-client/images/download.svg) ](https://bintray.com/client-side/clients/bitnodes-java-throttled-client/_latestVersion)

```java
int permistPerSecond = 2;
Bitnodes client = BitnodesFactory.create(permistPerSecond);
```

```
\ engineering.clientside:bitnodes-java-throttled-client:
+--- project :bitnodes-java-api:
|    \--- io.github.openfeign:feign-core:
+--- project :bitnodes-java-dslplatform-data:
|    +--- project :bitnodes-java-api: (*)
|    \--- com.dslplatform:dsl-json:
+--- io.github.openfeign:feign-core:
+--- com.fabahaba:throttle:
```

## [Hystrix](https://github.com/Netflix/Hystrix) Client [![:bitnodes-java-hystrix-client:](https://api.bintray.com/packages/client-side/clients/bitnodes-java-hystrix-client/images/download.svg) ](https://bintray.com/client-side/clients/bitnodes-java-hystrix-client/_latestVersion)

```java
HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties
  .Setter()
  .withExecutionTimeoutEnabled(true)
  .withExecutionTimeoutInMilliseconds(7000);
Bitnodes client = HystrixBitnodes.create(commandProperties);
```

```
\ engineering.clientside:bitnodes-java-hystrix-client:
+--- project :bitnodes-java-api:
|    \--- io.github.openfeign:feign-core:
+--- project :bitnodes-java-dslplatform-data:
|    +--- project :bitnodes-java-api: (*)
|    \--- com.dslplatform:dsl-json:
+--- io.github.openfeign:feign-hystrix:
|    +--- io.github.openfeign:feign-core:
|    \--- com.netflix.hystrix:hystrix-core:
|         +--- org.slf4j:slf4j-api:
|         +--- com.netflix.archaius:archaius-core:
|         |    +--- commons-configuration:commons-configuration:
|         |    |    +--- commons-lang:commons-lang:
|         |    |    \--- commons-logging:commons-logging:
|         |    \--- org.slf4j:slf4j-api:
|         +--- io.reactivex:rxjava:
|         \--- org.hdrhistogram:HdrHistogram:
\--- com.netflix.hystrix:hystrix-core: (*)
```

## API Methods

### [List Snapshots](https://bitnodes.21.co/api/#list-snapshots) & [Nodes](https://bitnodes.21.co/api/#list-nodes)
```java
BitnodesSnapshots snapshotsPage = client.getSnapshots();
BitnodesSnapshot snapshot = snapshotsPage.getResults().get(0);
BitnodesNodes nodes = client.getNodes(snapshot);
nodes.getNodes().forEach((nodeAddress, node) -> {
  System.out.format("%s: %s%n", nodeAddress, node);
});
```

### [Get Node Status](https://bitnodes.21.co/api/#node-status) & [Latency](https://bitnodes.21.co/api/#node-latency)
```java
BitnodesNodeStatus nodeStatus = client.getNodeStatus(nodeAddress);
BitnodesNode node = nodeStatus.getData();
BitnodesNodeLatency nodeLatency = client.getNodeLatency(nodeAddress);
List<? extends BitnodesStampedLatency> dailyLatency = nodeLatency.getDailyLatency();
List<? extends BitnodesStampedLatency> weeklyLatency = nodeLatency.getWeeklyLatency();
```

### [Get Leaderboard](https://bitnodes.21.co/api/#leaderboard)
```java
BitnodesLeaderboard leaderboard = client.getLeaderboard();
List<? extends BitnodesPeerIndexData> results = leaderboard.getResults();
```

### [Get Data Propagation](https://bitnodes.21.co/api/#data-propagation)
```java
BitnodesInvPropagation invPropagation = client.getInvPropagation(invHash);
List<BitnodesInvArrivalStamp> arrivalStamps = invPropagation.getHeadInvArrival();
BitnodesInvArrivalStats arrivalStats = invPropagation.getInvArrivalStats();
```

### [Post Node Bitcoin Address & URL](https://bitnodes.21.co/api/#node-bitcoin-address)
```java
String bitcoinAddress = "17vWico7jLKQoXtfWHpvEhHzzremZqQnNM";
String nodeAddress = "2a01:4f8:202:81b1::2";
String url = "http://[" + nodeAddress + ']';
client.postNodeBitcoinAddress(bitcoinAddress, url, nodeAddress);
```

### [Get IPV4/6 Nodes From DNS Seed](https://bitnodes.21.co/api/#dns-seeder)
```java
InetAddress[] nodeAddresses = client.getSeededNodeRecords();
InetAddress[] ipv4Nodes = client.getIPV4SeededNodeRecords();
InetAddress[] ipv6Nodes = client.getIPV6SeededNodeRecords();
```

## Developer Builds

The compilation dependency on `com.dslplatform:dsl-json-processor` requires [Mono](http://www.mono-project.com/).  The Docker image [comodal/alpine-jdk-mono](https://hub.docker.com/r/comodal/alpine-jdk-mono/) can be used so that you do not have to install Mono.

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
