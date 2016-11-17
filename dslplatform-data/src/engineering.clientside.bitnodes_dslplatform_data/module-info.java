module engineering.clientside.bitnodes_dslplatform_data {
  requires engineering.clientside.bitnodes_api;
  provides engineering.clientside.bitnodes.BitnodesCoder with engineering.clientside.bitnodes.DslJsonBitnodesCoder;
}
