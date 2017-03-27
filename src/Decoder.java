import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Decoder {

  public static void main(String[] args) {

    int data;
    String path;
    HuffmanNode hRoot = new HuffmanNode(-1);
    HuffmanTree hTree = new HuffmanTree(hRoot);

    File outputfile = new File("../output/decoded.txt");
    File code_file = new File("../output/code_table.txt");
    String binary_filename = "../output/encoded.bin";

    try {
      long start = System.nanoTime();
      Scanner scan = new Scanner(code_file);

      while(scan.hasNext()) {
        data = scan.nextInt();
        path = scan.next();
        hTree.add(data, path);
      }
      scan.close();

      BinaryFileReader myBinaryFileReader = new BinaryFileReader(binary_filename);
      StringBuilder binaryString = myBinaryFileReader.read();

      StringBuilder s = hTree.build(binaryString.toString());

      BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("../output/decoded.txt")));

      bwr.write(s.toString());
      bwr.flush();
      bwr.close();
      long stop = System.nanoTime();
      stop = (stop - start) / 1000000000;
      System.out.println("Total Decode Time: " + Math.round(stop) + " secs");
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
