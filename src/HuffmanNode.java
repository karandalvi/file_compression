/*
Created by: Karan Dalvi
HuffmanNode class provides node structure for objects in the HuffmanTree.
It consist of key value. It also consists of pointers to the left and right child.
Additionally, it consist of a huffCode which is a String value representing the
address of the node from the root of the Huffman Tree.
*/

public class HuffmanNode {

  int value;
  HuffmanNode left, right;
  String huffCode;
  boolean isLeaf;

  //Constructors
  public HuffmanNode(int value) {
    this.value = value;
    left = null;
    right = null;
    huffCode = "";
    isLeaf = false;
  }

  public HuffmanNode(int value, String huff) {
    this.value = value;
    left = null;
    right = null;
    huffCode = huff;
    isLeaf = false;
  }

  public HuffmanNode(int value, HuffmanNode left, HuffmanNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
    huffCode = "";
    isLeaf = false;
  }

  //Print the huffman tree rooted at this node
  public void print() {
    if (left != null)
      left.print();
    System.out.println(value + " - " + huffCode);
    if (right != null)
      right.print();
  }

}
