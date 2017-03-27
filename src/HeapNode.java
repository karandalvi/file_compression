/*
Created by: Karan Dalvi
HeapNode class provides node structure for objects in the Pairing Heap.
It consist of key value and its frequency. It also consists of a pointer to a HuffmanNode
to assist during the construction of the Huffman tree.
*/

public class HeapNode {

  int value;
  int frequency;
  HeapNode child;
  HeapNode left;
  HeapNode right;
  HuffmanNode pHuff;

  //Constructors
  public HeapNode(int value, int frequency) {
    this.value = value;
    this.frequency = frequency;
    child = null;
    left = null;
    right = null;
    pHuff = null;
  }

  public HeapNode(int value, int frequency, HuffmanNode pHuff) {
    this.value = value;
    this.frequency = frequency;
    child = null;
    left = null;
    right = null;
    this.pHuff = pHuff;
  }

  //Getter Functions
  public int value() {
    return value;
  }

  public int frequency() {
    return frequency;
  }

  public HeapNode child() {
    return child;
  }

  public HeapNode left() {
    return left;
  }

  public HeapNode right() {
    return right;
  }

  //Setter Functions
  public void setChild(HeapNode child) {
    this.child = child;
  }

  public void setLeft(HeapNode left) {
    this.left = left;
  }

  public void setRight(HeapNode right) {
    this.right = right;
  }

}
