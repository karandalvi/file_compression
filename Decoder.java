/*
Created by: Karan Dalvi
The Decoder program uses the files encoded.bin and code_table.txt to reconstruct
the huffman tree and the original file.
*/
import java.io.File;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class decoder {

  public static void main(String[] args) {

    int data;
    String path;
    HuffmanNode hRoot = new HuffmanNode(-1);
    HuffmanTree hTree = new HuffmanTree(hRoot);

    File outputfile = new File("decoded.txt");
    File code_file = new File("code_table.txt");
    String binary_filename = "encoded.bin";

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

      BufferedWriter bwr = new BufferedWriter(new FileWriter(outputfile));

      bwr.write(s.toString());
      bwr.flush();
      bwr.close();
      long stop = System.nanoTime();
      stop = (stop - start) / 1000000;
      System.out.println("Total Decode Time: " + Math.round(stop) + " milliseconds");
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
