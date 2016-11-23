package engineering.clientside.bitnodes;

import com.dslplatform.json.CompiledJson;

@CompiledJson
public final class DslJsonBitnodesPostResponse implements BitnodesPostResponse {

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
}
