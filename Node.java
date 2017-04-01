/*
Created by: Karan Dalvi
Node class provides node structure for objects in the DaryHeap.
It consist of value value and its frequency.
*/

public class Node {

  int value;
  int frequency;
  HuffmanNode pHuff;

  //Constructors
  public Node (int k, int v) {
    value = k;
    frequency = v;
    pHuff = null;
  }

  public Node (int k, int v, HuffmanNode p) {
    value = k;
    frequency = v;
    pHuff = p;
  }

  //Setter Function
  public void incrementFrequency () {
    frequency++;
  }

  //Getter Functions
  public int frequency() {
    return frequency;
  }

  public int value() {
    return value;
  }

}
