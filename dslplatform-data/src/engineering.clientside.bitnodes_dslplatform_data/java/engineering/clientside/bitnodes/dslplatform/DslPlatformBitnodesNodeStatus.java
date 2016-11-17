package engineering.clientside.bitnodes.dslplatform;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import engineering.clientside.bitnodes.BitnodesNode;
import engineering.clientside.bitnodes.BitnodesNodeStatus;

@CompiledJson
public final class DslPlatformBitnodesNodeStatus implements BitnodesNodeStatus {

  @JsonAttribute(nullable = false)
  public String hostname;
  @JsonAttribute(nullable = false)
  public String address;
  @JsonAttribute(nullable = false)
  public BitnodesNode.Status status;
  @JsonAttribute(nullable = false)
  public BitnodesNode data;
  @JsonAttribute(name = "bitcoin_address", nullable = false)
  public String bitcoinAddress;
  @JsonAttribute(nullable = false)
  public String url;
  public boolean verified;

  @Override
  public String getHostname() {
    return hostname;
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public BitnodesNode.Status getStatus() {
    return status;
  }

  @Override
  public BitnodesNode getData() {
    return data;
  }

  @Override
  public String getBitcoinAddress() {
    return bitcoinAddress;
  }

  @Override
  public String getUrl() {
    return url;
  }

  @Override
  public boolean isVerified() {
    return verified;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesNodeStatus)) {
      return false;
    }
    final BitnodesNodeStatus that = (BitnodesNodeStatus) obj;
    if (verified != that.isVerified()) {
      return false;
    }
    if (!hostname.equals(that.getHostname())) {
      return false;
    }
    if (!address.equals(that.getAddress())) {
      return false;
    }
    if (status != that.getStatus()) {
      return false;
    }
    if (!data.equals(that.getData())) {
      return false;
    }
    if (!bitcoinAddress.equals(that.getBitcoinAddress())) {
      return false;
    }
    return url.equals(that.getUrl());
  }

  @Override
  public int hashCode() {
    int result = hostname.hashCode();
    result = 31 * result + address.hashCode();
    result = 31 * result + status.hashCode();
    result = 31 * result + data.hashCode();
    result = 31 * result + bitcoinAddress.hashCode();
    result = 31 * result + url.hashCode();
    result = 31 * result + (verified ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BitnodesNodeStatus{"
        + "hostname='" + hostname + '\''
        + ", address='" + address + '\''
        + ", status=" + status
        + ", data=" + data
        + ", bitcoinAddress='" + bitcoinAddress + '\''
        + ", url='" + url + '\''
        + ", verified=" + verified
        + '}';
  }
}
