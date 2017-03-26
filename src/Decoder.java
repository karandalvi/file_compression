import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class Decoder {

  int huffCodes[];
  PairingHeap pheap;
  String code_filename;
  String binary_filename;
  File binary_file;
  File code_file;
  int data;
  String path;

  public Decoder(String code_filename, String binary_filename) {
    pheap = new PairingHeap();
    pheap.root = new HeapNode(-1, -1);
    huffCodes = new int[999999];
    this.code_filename = "../output/" + code_filename;
    this.binary_filename = "../output/" + binary_filename;

  }

  public void buildHuffmanTree() {
    try {
      code_file = new File(code_filename);
      binary_file = new File(binary_filename);
      Scanner scan = new Scanner(code_file);

      while(scan.hasNext()) {
        data = scan.nextInt();
        path = scan.next();
        System.out.println(data + " - " + path);
      }
      scan.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
