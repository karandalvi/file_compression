import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeTableWriter {

  BufferedWriter output;
  File file;
  String filename;
  HuffmanNode root;

  public CodeTableWriter(String filename, HuffmanNode root) {
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

  public void printInOrder(HuffmanNode t) {
    if (t == null)
      return;
    printInOrder(t.left);
    if (t.value != -1) {
      try {
        output.write(t.value + " " + t.huffCode);
        output.newLine();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    printInOrder(t.right);
  }

}
