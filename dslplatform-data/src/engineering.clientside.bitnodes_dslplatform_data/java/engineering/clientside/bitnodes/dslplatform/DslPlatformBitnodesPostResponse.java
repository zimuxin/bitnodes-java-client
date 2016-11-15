package engineering.clientside.bitnodes.dslplatform;

import com.dslplatform.json.CompiledJson;
import engineering.clientside.bitnodes.BitnodesPostResponse;

@CompiledJson(baseReaders = {BitnodesPostResponse.class})
public final class DslPlatformBitnodesPostResponse implements BitnodesPostResponse {

  public boolean success;

  @Override
  public boolean isSuccess() {
    return success;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof BitnodesPostResponse)) {
      return false;
    }
    final BitnodesPostResponse that = (BitnodesPostResponse) obj;
    return success == that.isSuccess();
  }

  @Override
  public int hashCode() {
    return success ? 1 : 0;
  }

  @Override
  public String toString() {
    return "BitnodesPostResponse{success=" + success + '}';
  }
}
