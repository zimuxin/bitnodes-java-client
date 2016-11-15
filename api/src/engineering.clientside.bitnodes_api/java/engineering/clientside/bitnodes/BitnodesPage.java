package engineering.clientside.bitnodes;

import java.util.List;

public interface BitnodesPage<R> {

  int getCount();

  String getNext();

  String getPrevious();

  List<? extends R> getResults();
}
