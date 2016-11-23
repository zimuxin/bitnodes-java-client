package engineering.clientside.bitnodes;

public enum BitnodesConfig {

  API_URL("api_url"),
  DNS_SEED_URL("dns_seed_url"),
  DEFAULT_NODE_PORT("default_node_port");

  private final String propName;

  BitnodesConfig(final String propName) {
    this.propName = BitnodesConfig.class.getPackage().getName() + '.' + propName;
  }

  public String getPropName() {
    return propName;
  }
}
