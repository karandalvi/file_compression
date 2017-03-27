/*
Created by: Karan Dalvi
Node class provides node structure for objects in the DaryHeap.
It consist of key value and its frequency.
*/

public class Node {

  int key;
  int frequency;
  HuffmanNode pHuff;

  //Constructors
  public Node (int k, int v) {
    key = k;
    frequency = v;
    pHuff = null;
  }

  public Node (int k, int v, HuffmanNode p) {
    key = k;
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

  public int key() {
    return key;
  }

}
