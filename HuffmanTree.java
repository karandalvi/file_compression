/*
Created by: Karan Dalvi
Huffman Tree provides add functionality functionality that takes as input a value and a huffCode
which indicates the path where the node is to be added.
Example: Insert(5,100) would indicate -> Start at root and visit right child, left child and
insert 5 as left child.
0 ==> left path
1 ==> right path
It also provides build function which reconstructs a text from a string of 0s and 1s
*/
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

  public void print() {
    root.print();
  }

  public boolean isRootNull() {
    return (root == null);
  }

  //-------------------------------------------------------------//

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

  //--------------------------------------------------------------//

  public void add(int value, String huffcode) {
    insertAt(root, value, huffcode, huffcode);
  }

  public void insertAt(HuffmanNode root, int value, String huffcode, String path) {
    if (root == null) {
        root = new HuffmanNode(-1);
    }

    if (huffcode.equals("0")) {
      if (root.left == null) {
        root.left = new HuffmanNode(value);
        root.left.huffCode = path;
      }
      else {
        root.left.value = value;
        root.left.huffCode = path;
      }
    }
    else if (huffcode.equals("1")) {
      if (root.right == null) {
        root.right = new HuffmanNode(value);
        root.right.huffCode = path;
      }
      else {
        root.right.value = value;
        root.right.huffCode = path;
      }
    }
    else {
      if (huffcode.substring(0,1).equals("0")) {
        if (root.left == null)
          root.left = new HuffmanNode(-1);
        insertAt(root.left, value, huffcode.substring(1), path);
      }
      else if (huffcode.substring(0,1).equals("1")) {
        if (root.right == null)
          root.right = new HuffmanNode(-1);
        insertAt(root.right, value, huffcode.substring(1), path);
      }
    }

  }

  //-------------------------------------------------------------//

  public StringBuilder build (String in_text) {
    HuffmanNode current = root;
    StringBuilder out_text = new StringBuilder("");
    for (int i=-1, j=0; j<in_text.length(); i++, j++) {
      if (current.value != -1) {
        out_text.append(current.value + "\n");
        current = root;
        i--;
        j--;
      }
      else {
        if (in_text.charAt(j) == '0') {
          current = current.left;
        }
        else {
          current = current.right;
        }
      }
    }
    if (current.value != -1) {
      out_text.append(current.value + "\n");
    }
    return out_text;
  }

  //-------------------------------------------------------------//

  // public StringBuilder buildString(String in_text) {
  //   StringBuilder out_text = new StringBuilder("");
  //   build(root, in_text, out_text);
  //   return out_text;
  // }
  //
  // public void build(HuffmanNode root, String in_text, StringBuilder out_text) {
  //   if (root.value != -1) {
  //     out_text.append(root.value + "\n");
  //     if (in_text.length()>0)
  //       build(root, in_text, out_text);
  //   }
  //   else {
  //     if (in_text.substring(0,1).equals("0")) {
  //       build(root.left, in_text.substring(1), out_text);
  //     }
  //     else { // (in_text.substring(0,1).equals("1")) {
  //       build(root.left, in_text.substring(1), out_text);
  //     }
  //   }
  // }

}
