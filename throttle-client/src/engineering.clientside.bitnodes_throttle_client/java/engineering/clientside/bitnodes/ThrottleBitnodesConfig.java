package engineering.clientside.bitnodes;

public enum ThrottleBitnodesConfig {

  DEFAULT_REQUESTS_PER_SECOND("default_requests_per_second");

  private final String propName;

  ThrottleBitnodesConfig(final String propName) {
    this.propName = ThrottleBitnodesConfig.class.getPackage().getName() + '.' + propName;
  }

  public String getPropName() {
    return propName;
  }
}
