import java.util.Scanner;
import java.io.FileReader;

public class Encoder {

  public static void main (String[] args) throws Exception {

    String filename = args[0];
    filename = "../input/sample_input_" + filename + ".txt";

    int count = 0;
    int heapSize;
    int[] frequencyTable = new int[1000000];
    for (int i=0; i<1000000; i++)
      frequencyTable[i] = 0;

    //------------------------------------------------------------------//

    //Read the input file and update frequencies
    float start = System.nanoTime();
    Scanner scan = new Scanner(new FileReader(filename));
    while (scan.hasNext())
    {
      if ((frequencyTable[scan.nextInt()]++) == 0)
        count++;
    }
    scan.close();
    float stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Frequency Table Build Time: " + Math.round(stop) + " millisecs");

    //------------------------------------------------------------------//

    //Build a pairing heap using the frequency table
    start = System.nanoTime();
    PairingHeap pHeap = new PairingHeap();

    for (int i=0; i<1000000; i++) {
      if (frequencyTable[i] > 0)
        pHeap.insert(i, frequencyTable[i]);
    }
    frequencyTable = null;
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Pairing Heap Build Time: " + Math.round(stop) + " millisecs");

    //------------------------------------------------------------------//

    start = System.nanoTime();
    TreeBuilder myTreeBuilder = new TreeBuilder(pHeap);
    HuffmanTree huffTree = myTreeBuilder.getHuffmanTree();
    String[] huffCodes = myTreeBuilder.getHuffCodeTable();

    CodeTableWriter myCodeTableWriter = new CodeTableWriter("code_table.txt", huffTree.getRoot());
    myCodeTableWriter.createFile();

    BinaryFileWriter myBinaryFileWriter = new BinaryFileWriter(filename,"encoded.bin",huffCodes);
    myBinaryFileWriter.encode();

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Encode Time: " + Math.round(stop) + " millisecs");

  }
}
