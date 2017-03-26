public class HuffmanTree {

  HuffmanNode root;

  public HuffmanTree() {
    root = null;
  }

  public HuffmanTree(HuffmanNode root) {
    this.root = root;
  }

  //-------------------------------------------------------------//

  public HuffmanNode getRoot() {
    return root;
  }

  public void updateHuffCodes() {
    updateHuffCode(root, "");
  }

  public void updateHuffCode(HuffmanNode t, String s) {
    if (t == null)
      return;
    updateHuffCode(t.left, s + "0");
    if (t.value != -1)
      t.huffCode = s;
    updateHuffCode(t.right, s + "1");
  }

  //-------------------------------------------------------------//

  public void copyHuffCodes(String[] table) {
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
