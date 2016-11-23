package engineering.clientside.bitnodes;

public abstract class DslJsonBitnodesPage {

  public int count;
  public String next;
  public String previous;

  public int getCount() {
    return count;
  }

  public String getNext() {
    return next;
  }

  public String getPrevious() {
    return previous;
  }
}
