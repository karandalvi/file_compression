import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeTableCreator {

  BufferedWriter output;
  File file;
  String filename;
  HeapNode root;

  public CodeTableCreator(String filename, HeapNode root) {
    this.filename = "../output/" + filename;
    this.root = root;
  }

  public void createFile() {

    try {
      file = new File(filename);
      output = new BufferedWriter(new FileWriter(file));
      printInOrder(root);
      output.close();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void printInOrder(HeapNode t) {
    if (t == null)
      return;
    printInOrder(t.treeLeft);
    if (t.value != -1) {
      try {
        output.write(t.value + " " + t.huffCode);
        output.newLine();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    printInOrder(t.treeRight);
  }

}
