#[Bitnodes](https://bitnodes.21.co/api/) Java client [![Build Status](https://travis-ci.org/client-side/bitnodes-java-client.svg?branch=master)](https://travis-ci.org/client-side/bitnodes-java-client) [![JCenter](https://api.bintray.com/packages/client-side/libs/bitnodes-java-client/images/download.svg) ](https://bintray.com/client-side/libs/bitnodes-java-client/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](http://www.apache.org/licenses/LICENSE-2.0)


###Usage
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

### List Snapshots



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
