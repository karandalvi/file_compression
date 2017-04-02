/*
Created by: Karan Dalvi
CodeTableWriter is a helper class that takes as input the huff code table and
creates a file consisting of tokens with their huff code values.
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeTableWriter {

  BufferedWriter output;
  String filename;
  HuffmanNode root;

  public CodeTableWriter(String filename, HuffmanNode root) {
    this.filename = filename;
    this.root = root;
  }

  public void createFile() {

    try {
      output = new BufferedWriter(new FileWriter(new File(filename)));
      printInOrder(root);
      output.close();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void printInOrder(HuffmanNode t) {
    if (t == null)
      return;
    printInOrder(t.left);
    if (t.value != -1) {
      try {
        output.write(t.value + " " + t.huffCode + "\n");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    printInOrder(t.right);
  }

}
