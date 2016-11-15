package engineering.clientside.bitnodes;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import feign.RequestTemplate;
import feign.Response;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DecodeBitnodesJsonTest {

  private static String readResource(final String name) {
    try (final BufferedReader buffer = new BufferedReader(new InputStreamReader(
        DecodeBitnodesJsonTest.class.getResourceAsStream(name), UTF_8))) {
      return buffer.lines().collect(Collectors.joining());
    } catch (final IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static final Response PROTOTYPE_RESPONSE = Response.builder()
      .status(200)
      .headers(Collections.emptyMap())
      .build();

  private static final BitnodesCoder coder = BitnodesCoder.get();

  @Test
  public void decodeSnapshotsJson() throws IOException {
    final String json = readResource("/BitnodesSnapshots.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();
    final BitnodesSnapshots snapshots = (BitnodesSnapshots) coder.decode(response,
        BitnodesSnapshots.class);
    assertEquals(54682, snapshots.getCount());
    assertEquals("https://bitnodes.21.co/api/v1/snapshots/?page=2", snapshots.getNext());
    assertNull(snapshots.getPrevious());

    final List<? extends BitnodesSnapshot> snapshotList = snapshots.getResults();
    assertEquals(2, snapshotList.size());
    BitnodesSnapshot snapshot = snapshotList.get(0);
    assertEquals("https://bitnodes.21.co/api/v1/snapshots/1418443208/", snapshot.getUrl());
    assertEquals(1418443208L, snapshot.getTimestamp());
    assertEquals(6162, snapshot.getTotalNodes());
    assertEquals(334083L, snapshot.getLatestHeight());
    snapshot = snapshotList.get(1);
    assertEquals("https://bitnodes.21.co/api/v1/snapshots/1418440197/", snapshot.getUrl());
    assertEquals(1418440197L, snapshot.getTimestamp());
    assertEquals(6169, snapshot.getTotalNodes());
    assertEquals(334077L, snapshot.getLatestHeight());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(snapshots, snapshots.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(snapshots, coder.decode(recodedResponse, BitnodesSnapshots.class));
  }

  @Test
  public void decodeNodesJson() throws IOException {
    final String json = readResource("/BitnodesNodes.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();
    final BitnodesNodes nodes = (BitnodesNodes) coder.decode(response,
        BitnodesNodes.class);

    assertEquals(1418442876L, nodes.getTimestamp());
    assertEquals(6148, nodes.getTotalNodes());
    assertEquals(334083L, nodes.getLatestHeight());

    final Map<String, BitnodesNode> nodeMap = nodes.getNodes();
    assertEquals(2, nodeMap.size());

    BitnodesNode node = nodeMap.get("148.251.238.178:8333");
    assertEquals(70002L, node.getProtocolVersion());
    assertEquals("/Satoshi:0.9.2.1/", node.getUserAgent());
    assertEquals(1418186362L, node.getConnectedSince());
    assertEquals(1L, node.getServices());
    assertEquals(334083L, node.getHeight());
    assertEquals("dazzlepod.com", node.getHostname());
    assertNull(node.getCity());
    assertEquals("DE", node.getCountryCode());
    assertEquals(51.0, node.getLatitude(), 0.0);
    assertEquals(9.0, node.getLongitude(), 0.0);
    assertEquals("Europe/Berlin", node.getTimezone());
    assertEquals("AS24940", node.getAsn());
    assertEquals("Hetzner Online AG", node.getOrganizationName());

    node = nodeMap.get("208.118.235.153:8333");
    assertEquals(70001L, node.getProtocolVersion());
    assertEquals("/Satoshi:0.8.1/", node.getUserAgent());
    assertEquals(1418440191L, node.getConnectedSince());
    assertEquals(1L, node.getServices());
    assertEquals(334083L, node.getHeight());
    assertEquals("bitcoin.fsf.org", node.getHostname());
    assertEquals("Boston", node.getCity());
    assertEquals("US", node.getCountryCode());
    assertEquals(42.3584, node.getLatitude(), 0.0);
    assertEquals(-71.0598, node.getLongitude(), 0.0);
    assertEquals("America/New_York", node.getTimezone());
    assertEquals("AS22989", node.getAsn());
    assertEquals("Free Software Foundation, Inc.", node.getOrganizationName());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(nodes, nodes.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(nodes, coder.decode(recodedResponse, BitnodesNodes.class));
  }

  @Test
  public void decodeNodeStatus() throws IOException {
    final String json = readResource("/BitnodesNodeStatus.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();
    final BitnodesNodeStatus nodeStatus = (BitnodesNodeStatus) coder.decode(response,
        BitnodesNodeStatus.class);

    assertEquals("bitcoin.fsf.org", nodeStatus.getHostname());
    assertEquals("208.118.235.153", nodeStatus.getAddress());
    assertEquals(BitnodesNode.Status.UP, nodeStatus.getStatus());
    assertEquals("", nodeStatus.getBitcoinAddress());
    assertEquals("", nodeStatus.getUrl());
    assertFalse(nodeStatus.isVerified());

    final BitnodesNode node = nodeStatus.getData();
    assertEquals(70001L, node.getProtocolVersion());
    assertEquals("/Satoshi:0.8.1/", node.getUserAgent());
    assertEquals(1418440191L, node.getConnectedSince());
    assertEquals(1L, node.getServices());
    assertEquals(334083L, node.getHeight());
    assertEquals("bitcoin.fsf.org", node.getHostname());
    assertEquals("Boston", node.getCity());
    assertEquals("US", node.getCountryCode());
    assertEquals(42.3584, node.getLatitude(), 0.0);
    assertEquals(-71.0598, node.getLongitude(), 0.0);
    assertEquals("America/New_York", node.getTimezone());
    assertEquals("AS22989", node.getAsn());
    assertEquals("Free Software Foundation, Inc.", node.getOrganizationName());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(nodeStatus, nodeStatus.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(nodeStatus, coder.decode(recodedResponse, BitnodesNodeStatus.class));
  }

  @Test
  public void decodeNodeLatency() throws IOException {
    final String json = readResource("/BitnodesNodeLatency.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();
    final BitnodesNodeLatency nodeLatency = (BitnodesNodeLatency) coder.decode(response,
        BitnodesNodeLatency.class);

    final List<? extends BitnodesStampedLatency> dailyLatency = nodeLatency.getDailyLatency();
    assertEquals(2, dailyLatency.size());
    BitnodesStampedLatency stampedLatency = dailyLatency.get(0);
    assertEquals(1421451000L, stampedLatency.getTimestamp());
    assertEquals(101, stampedLatency.getLatency());
    stampedLatency = dailyLatency.get(1);
    assertEquals(1421537400L, stampedLatency.getTimestamp());
    assertEquals(99, stampedLatency.getLatency());

    final List<? extends BitnodesStampedLatency> weeklyLatency = nodeLatency.getWeeklyLatency();
    assertEquals(2, weeklyLatency.size());
    stampedLatency = weeklyLatency.get(0);
    assertEquals(1420930800L, stampedLatency.getTimestamp());
    assertEquals(100, stampedLatency.getLatency());
    stampedLatency = weeklyLatency.get(1);
    assertEquals(1421535600L, stampedLatency.getTimestamp());
    assertEquals(99, stampedLatency.getLatency());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(nodeLatency, nodeLatency.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(nodeLatency, coder.decode(recodedResponse, BitnodesNodeLatency.class));
  }

  @Test
  public void decodeLeaderboard() throws IOException {
    final String json = readResource("/BitnodesLeaderboard.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();
    final BitnodesLeaderboard leaderboard = (BitnodesLeaderboard) coder.decode(response,
        BitnodesLeaderboard.class);

    assertEquals(4860, leaderboard.getCount());
    assertEquals("https://bitnodes.21.co/api/v1/nodes/leaderboard/?page=2",
        leaderboard.getNext());
    assertNull(leaderboard.getPrevious());

    final List<? extends BitnodesPeerIndexData> results = leaderboard.getResults();
    assertEquals(2, results.size());
    BitnodesPeerIndexData peerIndexData = results.get(0);
    assertEquals("74.124.62.108:8333", peerIndexData.getNode());
    assertEquals(1.0, peerIndexData.getProtocolVersionIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getServicesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getHeightIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getASNIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getPortIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageDailyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getDailyUptimeIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageWeeklyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getWeeklyUptimeIndex(), 0.0);
    assertEquals(0.0587, peerIndexData.getNodesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getBlockIndex(), 0.0);
    assertEquals(9.1443, peerIndexData.getPeerIndex(), 0.0);
    assertEquals(1, peerIndexData.getRank());

    peerIndexData = results.get(1);
    assertEquals("107.155.107.88:8333", peerIndexData.getNode());
    assertEquals(1.0, peerIndexData.getProtocolVersionIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getServicesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getHeightIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getASNIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getPortIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageDailyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getDailyUptimeIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageWeeklyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getWeeklyUptimeIndex(), 0.0);
    assertEquals(0.0081, peerIndexData.getNodesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getBlockIndex(), 0.0);
    assertEquals(9.0983, peerIndexData.getPeerIndex(), 0.0);
    assertEquals(10, peerIndexData.getRank());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(leaderboard, leaderboard.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(leaderboard, coder.decode(recodedResponse, BitnodesLeaderboard.class));
  }

  @Test
  public void decodeNodeRanking() throws IOException {
    final String json = readResource("/BitnodesNodeRanking.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();
    final BitnodesPeerIndexData peerIndexData = (BitnodesPeerIndexData) coder.decode(response,
        BitnodesPeerIndexData.class);

    assertEquals("74.124.62.108:8333", peerIndexData.getNode());
    assertEquals(1.0, peerIndexData.getProtocolVersionIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getServicesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getHeightIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getASNIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getPortIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageDailyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getDailyUptimeIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageWeeklyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getWeeklyUptimeIndex(), 0.0);
    assertEquals(0.0587, peerIndexData.getNodesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getBlockIndex(), 0.0);
    assertEquals(9.1443, peerIndexData.getPeerIndex(), 0.0);
    assertEquals(1, peerIndexData.getRank());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(peerIndexData, peerIndexData.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(peerIndexData, coder.decode(recodedResponse, BitnodesPeerIndexData.class));
  }

  @Test
  public void decodeInvPropagation() throws IOException {
    final String json = readResource("/BitnodesInvPropagation.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();
    final BitnodesInvPropagation invPropagation = (BitnodesInvPropagation) coder
        .decode(response, BitnodesInvPropagation.class);

    assertEquals("1e0942ab85c1800b3cfa3997af73803ee0cda22b657fd307285ee8f08045654a",
        invPropagation.getInvHash());
    final List<BitnodesInvArrivalStamp> arrivalStamps = invPropagation.getHeadInvArrival();
    assertEquals(10, arrivalStamps.size());
    BitnodesInvArrivalStamp arrivalStamp = arrivalStamps.get(0);
    assertEquals("37.59.121.19:8333", arrivalStamp.getAddress());
    assertEquals(1469655511942L, arrivalStamp.getTimestamp());
    arrivalStamp = arrivalStamps.get(1);
    assertEquals("[2a01:4f8:c17:392a::2]:8333", arrivalStamp.getAddress());
    assertEquals(1469655511942L, arrivalStamp.getTimestamp());
    arrivalStamp = arrivalStamps.get(9);
    assertEquals("178.85.167.122:8333", arrivalStamp.getAddress());
    assertEquals(1469655511944L, arrivalStamp.getTimestamp());

    final BitnodesInvArrivalStats arrivalStats = invPropagation.getInvArrivalStats();
    assertEquals(143, arrivalStats.getStdDev());
    assertEquals(0, arrivalStats.getMin());
    assertEquals(518, arrivalStats.getMax());
    assertEquals(193, arrivalStats.getFiftiethPercentile());
    assertEquals(433, arrivalStats.getNinetiethPercentile());
    assertEquals(208, arrivalStats.getMean());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(invPropagation, invPropagation.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(invPropagation, coder.decode(recodedResponse, BitnodesInvPropagation.class));
  }

  @Test
  public void decodePostResponse() throws IOException {
    final String json = readResource("/BitnodesPostResponse.json");
    final Response response = PROTOTYPE_RESPONSE.toBuilder().body(json, UTF_8).build();

    final BitnodesPostResponse postResponse = (BitnodesPostResponse) coder.decode(response,
        BitnodesPostResponse.class);
    assertTrue(postResponse.isSuccess());

    final RequestTemplate template = new RequestTemplate();
    coder.encode(postResponse, postResponse.getClass(), template);
    final Response recodedResponse = PROTOTYPE_RESPONSE.toBuilder().body(template.body()).build();
    assertEquals(postResponse, coder.decode(recodedResponse, BitnodesPostResponse.class));
  }
}
