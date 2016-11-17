package engineering.clientside.bitnodes;

final class DslJsonBitnodesInvArrivalStamp implements BitnodesInvArrivalStamp {

  private final String address;
  private final long timestamp;

  DslJsonBitnodesInvArrivalStamp(final String address, final long timestamp) {
    this.address = address;
    this.timestamp = timestamp;
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesInvArrivalStamp)) {
      return false;
    }
    final BitnodesInvArrivalStamp that = (BitnodesInvArrivalStamp) obj;
    return timestamp == that.getTimestamp() && address.equals(that.getAddress());
  }

  @Override
  public int hashCode() {
    int result = address.hashCode();
    result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesInvArrivalStamp{address='" + address + '\'' + ", timestamp=" + timestamp + '}';
  }
}
