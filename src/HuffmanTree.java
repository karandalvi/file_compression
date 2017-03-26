public class HuffmanTree {

  HuffmanNode root;

  public HuffmanTree() {
    root = null;
  }

  public HuffmanTree(HuffmanNode root) {
    this.root = root;
  }

  public void updateHuffCodes() {
    printInOrder(root, "");
  }

  public HuffmanNode getRoot() {
    return root;
  }
  
  public void printInOrder(HuffmanNode t, String s) {
    if (t == null)
      return;
    printInOrder(t.left, s + "0");
    if (t.value != -1)
      t.huffCode = s;
    printInOrder(t.right, s + "1");
  }

  public void copyHuffCodes(String[] table) {
    for (int i=0; i<1000000; i++)
      table[i] = "-1";
    copy(root, table);
  }

  public void copy(HuffmanNode t, String[] table) {
    if (t == null)
      return;
    copy(t.left, table);
    if (t.value != -1) {
      table[t.value] = t.huffCode;
    }
    copy(t.right, table);
  }

}
