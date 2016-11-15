package engineering.clientside.bitnodes;

public interface BitnodesNode {

  long getProtocolVersion();

  String getUserAgent();

  long getServices();

  long getConnectedSince();

  long getHeight();

  String getHostname();

  String getCity();

  String getCountryCode();

  double getLatitude();

  double getLongitude();

  String getTimezone();

  String getAsn();

  String getOrganizationName();

  enum Status {
    PENDING, UP, DOWN
  }
}
