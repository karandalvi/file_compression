public class HuffmanNode {

  int value;
  HuffmanNode left, right;
  String huffCode;
  boolean isLeaf;


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

  // public void updateHuffCodes(String s) {
  //   if (left != null)
  //     left.updateHuffCodes(s + "0");
  //   if (value != -1)
  //     huffCode = s;
  //   if (right != null)
  //     right.updateHuffCodes(s + "1");
  // }

  public void print() {
    if (left != null)
      left.print();
    System.out.println(value + " - " + huffCode);
    if (right != null)
      right.print();
  }

  // public void copyToTable(String[] table) {
  //   if (left != null)
  //     left.copyToTable(table);
  //   if (value >= 0)
  //     table[value] = huffCode;
  //   if (right != null)
  //     right.copyToTable(table);
  // }

}
