public class Node {

  int key;        // number
  int frequency;      // frequency

  public Node (int k, int v) {
    key = k;
    frequency = v;
  }

  public void incrementFrequency () {
    frequency++;
  }

  public int frequency() {
    return frequency;
  }

  public int key() {
    return key;
  }

}
