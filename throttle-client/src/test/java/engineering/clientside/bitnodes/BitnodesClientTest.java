package engineering.clientside.bitnodes;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static java.nio.charset.StandardCharsets.UTF_8;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BitnodesClientTest {

  @ClassRule
  public static final MockWebServer server = new MockWebServer();

  private static AsyncBitnodes client = null;

  @BeforeClass
  public static void before() {
    System.setProperty(BitnodesConfig.API_URL.getPropName(),
        "http://localhost:" + server.getPort());
    System.setProperty(ThrottleBitnodesConfig.DEFAULT_REQUESTS_PER_SECOND.getPropName(), "32");
    client = BitnodesFactory.create();
  }

  @Test
  public void testOtherFactoryMethods() {
    BitnodesFactory.create(1);
  }

  @Test
  public void getSnapshots() throws IOException, InterruptedException, URISyntaxException {
    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesSnapshots.json").toURI())), UTF_8));
    server.enqueue(response);

    final int numSnapshots = 7;
    final BitnodesSnapshots snapshotsPage = client.getSnapshots(numSnapshots, 2);
    assertEquals(60356, snapshotsPage.getCount());
    assertEquals("https://bitnodes.21.co/api/v1/snapshots/?limit=7",
        snapshotsPage.getPrevious());
    assertEquals("https://bitnodes.21.co/api/v1/snapshots/?limit=7&page=3",
        snapshotsPage.getNext());

    final List<? extends BitnodesSnapshot> snapshots = snapshotsPage.getResults();
    assertEquals(numSnapshots, snapshots.size());
    final BitnodesSnapshot snapshot = snapshots.get(numSnapshots - 1);
    assertEquals("https://bitnodes.21.co/api/v1/snapshots/1479098506/", snapshot.getUrl());
    assertEquals(1479098506L, snapshot.getTimestamp());
    assertEquals(5334, snapshot.getTotalNodes());
    assertEquals(438807L, snapshot.getLatestHeight());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("/api/v1/snapshots/?limit=7&page=2", request.getPath());

    server.enqueue(response);
    assertEquals(snapshotsPage, client.getSnapshotsFuture(numSnapshots, 2).join());
    server.takeRequest();
  }

  @Test
  public void getNodes() throws IOException, InterruptedException, URISyntaxException {
    final long snapshotTimestamp = 1479098506L;
    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesNodes_1479098506.json").toURI())), UTF_8));
    server.enqueue(response);

    final BitnodesNodes nodes = client.getNodes(snapshotTimestamp);
    assertEquals(1479098506L, nodes.getTimestamp());
    final int numNodes = nodes.getTotalNodes();
    assertEquals(5334, numNodes);
    assertEquals(numNodes, nodes.getNodes().size());
    assertEquals(438807L, nodes.getLatestHeight());

    final BitnodesNode node = nodes.getNodes().get("162.248.4.26:8333");
    assertEquals(70014L, node.getProtocolVersion());
    assertEquals("/Satoshi:0.13.1/", node.getUserAgent());
    assertEquals(1478975177L, node.getConnectedSince());
    assertEquals(13L, node.getServices());
    assertEquals(438807L, node.getHeight());
    assertEquals("162.248.4.26", node.getHostname());
    assertEquals("Las Vegas", node.getCity());
    assertEquals("US", node.getCountryCode());
    assertEquals(36.0877, node.getLatitude(), 0.0);
    assertEquals(-115.1485, node.getLongitude(), 0.0);
    assertEquals("America/Los_Angeles", node.getTimezone());
    assertEquals("AS62838", node.getAsn());
    assertEquals("Reprise Hosting", node.getOrganizationName());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("/api/v1/snapshots/" + snapshotTimestamp, request.getPath());

    server.enqueue(response);
    assertEquals(nodes, client.getNodesFuture(snapshotTimestamp).join());
    server.takeRequest();
  }

  @Test
  public void getNodeStatus() throws IOException, InterruptedException, URISyntaxException {
    final String nodeAddress = "23.108.83.12";
    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesNodeStatus.json").toURI())), UTF_8));
    server.enqueue(response);

    final BitnodesNodeStatus nodeStatus = client.getNodeStatus(nodeAddress);
    assertEquals("", nodeStatus.getHostname());
    assertEquals(nodeAddress, nodeStatus.getAddress());
    assertEquals(BitnodesNode.Status.UP, nodeStatus.getStatus());
    assertEquals("1BiTNoDEjPAq819owJ2wSa8ZqoJc6ff8vk", nodeStatus.getBitcoinAddress());
    assertEquals("http://23.108.83.12:411", nodeStatus.getUrl());
    assertTrue(nodeStatus.isVerified());

    final BitnodesNode node = nodeStatus.getData();
    assertEquals(70014L, node.getProtocolVersion());
    assertEquals("/Satoshi:0.13.1/", node.getUserAgent());
    assertEquals(1479134182L, node.getConnectedSince());
    assertEquals(13L, node.getServices());
    assertEquals(438894L, node.getHeight());
    assertEquals("1BiTNoDEjPAq819owJ2wSa8ZqoJc6ff8vk", node.getHostname());
    assertEquals("Hayward", node.getCity());
    assertEquals("US", node.getCountryCode());
    assertEquals(37.6745, node.getLatitude(), 0.0);
    assertEquals(-122.0883, node.getLongitude(), 0.0);
    assertEquals("America/Los_Angeles", node.getTimezone());
    assertEquals("AS15003", node.getAsn());
    assertEquals("Nobis Technology Group, LLC", node.getOrganizationName());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("/api/v1/nodes/" + nodeAddress + '-' + Bitnodes.DEFAULT_NODE_PORT + '/',
        request.getPath());

    server.enqueue(response);
    assertEquals(nodeStatus, client.getNodeStatusFuture(nodeAddress).join());
    server.takeRequest();
  }

  @Test
  public void getNodeLatency() throws IOException, InterruptedException, URISyntaxException {
    final String nodeAddress = "23.108.83.12";
    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesNodeLatency.json").toURI())), UTF_8));
    server.enqueue(response);

    final BitnodesNodeLatency nodeLatency = client.getNodeLatency(nodeAddress);
    final List<? extends BitnodesStampedLatency> dailyLatency = nodeLatency.getDailyLatency();
    assertEquals(97, dailyLatency.size());
    BitnodesStampedLatency stampedLatency = dailyLatency.get(0);
    assertEquals(1479051900L, stampedLatency.getTimestamp());
    assertEquals(102, stampedLatency.getLatency());
    stampedLatency = dailyLatency.get(dailyLatency.size() - 1);
    assertEquals(1479138300L, stampedLatency.getTimestamp());
    assertEquals(103, stampedLatency.getLatency());

    final List<? extends BitnodesStampedLatency> weeklyLatency = nodeLatency.getWeeklyLatency();
    assertEquals(169, weeklyLatency.size());
    stampedLatency = weeklyLatency.get(0);
    assertEquals(1478530800L, stampedLatency.getTimestamp());
    assertEquals(102, stampedLatency.getLatency());
    stampedLatency = weeklyLatency.get(weeklyLatency.size() - 1);
    assertEquals(1479135600L, stampedLatency.getTimestamp());
    assertEquals(104, stampedLatency.getLatency());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("/api/v1/nodes/" + nodeAddress + '-' + Bitnodes.DEFAULT_NODE_PORT + "/latency/",
        request.getPath());

    server.enqueue(response);
    assertEquals(nodeLatency, client.getNodeLatencyFuture(nodeAddress).join());
    server.takeRequest();
  }

  @Test
  public void getLeaderboard() throws IOException, InterruptedException, URISyntaxException {
    final String nodeAddress = "23.108.83.12";
    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesLeaderboard.json").toURI())), UTF_8));
    server.enqueue(response);

    final BitnodesLeaderboard leaderboard = client.getLeaderboard(5, 3);
    assertEquals(2979, leaderboard.getCount());
    assertEquals("https://bitnodes.21.co/api/v1/nodes/leaderboard/?limit=5&page=4",
        leaderboard.getNext());
    assertEquals("https://bitnodes.21.co/api/v1/nodes/leaderboard/?limit=5&page=2",
        leaderboard.getPrevious());

    final List<? extends BitnodesPeerIndexData> results = leaderboard.getResults();
    assertEquals(5, results.size());
    final BitnodesPeerIndexData peerIndexData = results.get(results.size() - 1);
    assertEquals(nodeAddress + ":8333", peerIndexData.getNode());
    assertEquals(1.0, peerIndexData.getProtocolVersionIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getServicesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getHeightIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getASNIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getPortIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageDailyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getDailyUptimeIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageWeeklyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getWeeklyUptimeIndex(), 0.0);
    assertEquals(0.0234, peerIndexData.getNodesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getBlockIndex(), 0.0);
    assertEquals(9.1122, peerIndexData.getPeerIndex(), 0.0);
    assertEquals(13, peerIndexData.getRank());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("/api/v1/nodes/leaderboard/?limit=5&page=3", request.getPath());

    server.enqueue(response);
    assertEquals(leaderboard, client.getLeaderboardFuture(5, 3).join());
    server.takeRequest();
  }

  @Test
  public void getNodeRanking() throws IOException, InterruptedException, URISyntaxException {
    final String nodeAddress = "23.108.83.12";
    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesNodeRanking.json").toURI())), UTF_8));
    server.enqueue(response);

    final BitnodesPeerIndexData peerIndexData = client.getNodeRanking(nodeAddress);
    assertEquals(nodeAddress + ":8333", peerIndexData.getNode());
    assertEquals(1.0, peerIndexData.getProtocolVersionIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getServicesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getHeightIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getASNIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getPortIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageDailyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getDailyUptimeIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getAverageWeeklyLatencyIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getWeeklyUptimeIndex(), 0.0);
    assertEquals(0.0234, peerIndexData.getNodesIndex(), 0.0);
    assertEquals(1.0, peerIndexData.getBlockIndex(), 0.0);
    assertEquals(9.1122, peerIndexData.getPeerIndex(), 0.0);
    assertEquals(13, peerIndexData.getRank());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("/api/v1/nodes/leaderboard/"
        + nodeAddress + '-' + Bitnodes.DEFAULT_NODE_PORT + '/', request.getPath());

    server.enqueue(response);
    assertEquals(peerIndexData, client.getNodeRankingFuture(nodeAddress).join());
    server.takeRequest();
  }

  @Test
  public void getInvPropagation() throws IOException, InterruptedException, URISyntaxException {
    final String invHash = "ad3b660182c85c4b2246ddfcf855df0a894d7d44ceefa59711060a08dad785af";
    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesInvPropagation.json").toURI())), UTF_8));
    server.enqueue(response);

    final BitnodesInvPropagation invPropagation = client.getInvPropagation(invHash);
    assertEquals(invHash, invPropagation.getInvHash());

    final List<BitnodesInvArrivalStamp> arrivalStamps = invPropagation.getHeadInvArrival();
    assertEquals(1_000, arrivalStamps.size());
    BitnodesInvArrivalStamp arrivalStamp = arrivalStamps.get(0);
    assertEquals("88.198.60.110:8333", arrivalStamp.getAddress());
    assertEquals(1479093757421L, arrivalStamp.getTimestamp());
    arrivalStamp = arrivalStamps.get(arrivalStamps.size() - 1);
    assertEquals("85.25.93.70:8333", arrivalStamp.getAddress());
    assertEquals(1479093758356L, arrivalStamp.getTimestamp());

    final BitnodesInvArrivalStats arrivalStats = invPropagation.getInvArrivalStats();
    assertEquals(245, arrivalStats.getStdDev());
    assertEquals(7, arrivalStats.getMin());
    assertEquals(935, arrivalStats.getMax());
    assertEquals(417, arrivalStats.getFiftiethPercentile());
    assertEquals(814, arrivalStats.getNinetiethPercentile());
    assertEquals(453, arrivalStats.getMean());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("/api/v1/inv/" + invHash + '/', request.getPath());

    server.enqueue(response);
    assertEquals(invPropagation, client.getInvPropagationFuture(invHash).join());
    server.takeRequest();
  }

  @Test
  public void postNodeBitcoinAddress() throws IOException, InterruptedException,
      URISyntaxException {
    final String nodeAddress = "2a01:4f8:202:81b1::2";
    final String bitcoinAddress = "17vWico7jLKQoXtfWHpvEhHzzremZqQnNM";
    final String url = "http://[" + nodeAddress + ']';

    final MockResponse response = new MockResponse()
        .addHeader("Content-Type", "application/json")
        .setBody(new String(Files.readAllBytes(Paths.get(BitnodesClientTest.class
            .getResource("/BitnodesPostResponse.json").toURI())), UTF_8));
    server.enqueue(response);

    final BitnodesPostResponse postResponse = client.postNodeBitcoinAddress(bitcoinAddress, url,
        nodeAddress);
    assertTrue(postResponse.isSuccess());

    final RecordedRequest request = server.takeRequest();
    assertEquals("application/json", request.getHeader("Accept"));
    assertEquals("application/x-www-form-urlencoded", request.getHeader("Content-Type"));
    assertEquals("/api/v1/nodes/" + URLEncoder.encode(nodeAddress, "UTF-8")
        + '-' + Bitnodes.DEFAULT_NODE_PORT + '/', request.getPath());

    server.enqueue(response);
    assertEquals(postResponse, client.postNodeBitcoinAddressFuture(bitcoinAddress, url,
        nodeAddress).join());
    server.takeRequest();
  }
}
