package engineering.clientside.bitnodes;

final class DslJsonBitnodesNode implements BitnodesNode {

  private final long protocolVersion;
  private final String userAgent;
  private final long connectedSince;
  private final long services;
  private final long height;
  private final String hostname;
  private final String city;
  private final String countryCode;
  private final double latitude;
  private final double longitude;
  private final String timezone;
  private final String asn;
  private final String organizationName;

  DslJsonBitnodesNode(final long protocolVersion, final String userAgent,
      final long connectedSince, final long services, final long height, final String hostname,
      final String city, final String countryCode, final double latitude, final double longitude,
      final String timezone, final String asn, final String organizationName) {

    this.protocolVersion = protocolVersion;
    this.userAgent = userAgent;
    this.connectedSince = connectedSince;
    this.services = services;
    this.height = height;
    this.hostname = hostname;
    this.city = city;
    this.countryCode = countryCode;
    this.latitude = latitude;
    this.longitude = longitude;
    this.timezone = timezone;
    this.asn = asn;
    this.organizationName = organizationName;
  }

  @Override
  public long getProtocolVersion() {
    return protocolVersion;
  }

  @Override
  public String getUserAgent() {
    return userAgent;
  }

  @Override
  public long getServices() {
    return services;
  }

  @Override
  public long getConnectedSince() {
    return connectedSince;
  }

  @Override
  public long getHeight() {
    return height;
  }

  @Override
  public String getHostname() {
    return hostname;
  }

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public String getCountryCode() {
    return countryCode;
  }

  @Override
  public double getLatitude() {
    return latitude;
  }

  @Override
  public double getLongitude() {
    return longitude;
  }

  @Override
  public String getTimezone() {
    return timezone;
  }

  @Override
  public String getAsn() {
    return asn;
  }

  @Override
  public String getOrganizationName() {
    return organizationName;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesNode)) {
      return false;
    }
    final BitnodesNode that = (BitnodesNode) obj;
    if (protocolVersion != that.getProtocolVersion()) {
      return false;
    }
    if (connectedSince != that.getConnectedSince()) {
      return false;
    }
    if (services != that.getServices()) {
      return false;
    }
    if (height != that.getHeight()) {
      return false;
    }
    if (Double.compare(that.getLatitude(), latitude) != 0) {
      return false;
    }
    if (Double.compare(that.getLongitude(), longitude) != 0) {
      return false;
    }
    if (userAgent != null ? !userAgent.equals(that.getUserAgent()) : that.getUserAgent() != null) {
      return false;
    }
    if (hostname != null ? !hostname.equals(that.getHostname()) : that.getHostname() != null) {
      return false;
    }
    if (city != null ? !city.equals(that.getCity()) : that.getCity() != null) {
      return false;
    }
    if (countryCode != null ? !countryCode.equals(that.getCountryCode()) :
        that.getCountryCode() != null) {
      return false;
    }
    if (timezone != null ? !timezone.equals(that.getTimezone()) : that.getTimezone() != null) {
      return false;
    }
    if (asn != null ? !asn.equals(that.getAsn()) : that.getAsn() != null) {
      return false;
    }
    return organizationName != null ? organizationName.equals(that.getOrganizationName()) :
        that.getOrganizationName() == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (protocolVersion ^ (protocolVersion >>> 32));
    result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
    result = 31 * result + (int) (connectedSince ^ (connectedSince >>> 32));
    result = 31 * result + (int) (services ^ (services >>> 32));
    result = 31 * result + (int) (height ^ (height >>> 32));
    result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
    result = 31 * result + (city != null ? city.hashCode() : 0);
    result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
    long temp = Double.doubleToLongBits(latitude);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(longitude);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
    result = 31 * result + (asn != null ? asn.hashCode() : 0);
    result = 31 * result + (organizationName != null ? organizationName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesNode{protocolVersion=" + protocolVersion
        + ", userAgent='" + userAgent + '\''
        + ", connectedSince=" + connectedSince
        + ", services=" + services
        + ", height=" + height
        + ", hostname='" + hostname + '\''
        + ", city='" + city + '\''
        + ", countryCode='" + countryCode + '\''
        + ", latitude=" + latitude
        + ", longitude=" + longitude
        + ", timezone='" + timezone + '\''
        + ", asn='" + asn + '\''
        + ", organizationName='" + organizationName + '\'' + '}';
  }
}
