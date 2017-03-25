import java.util.Scanner;
import java.io.FileReader;

public class HuffmanTreeBuilder {

  public static void main (String[] args) throws Exception {

    //Initialize the frequency table that will be used to build the heap
    Scanner myScan = new Scanner(System.in);
    System.out.println("Input file name");
    String filename = myScan.next();
    myScan.close();
    filename = "sample_input_" + filename + ".txt";
    
    int count = 0;
    int heapSize;
    int[] frequencyTable = new int[1000000];
    for (int i=0; i<1000000; i++)
      frequencyTable[i] = 0;

    //------------------------------------------------------------------//

    //Read the input file and update frequencies
    System.out.println("\nBuilding the frequency table...");
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
    System.out.println("Built the frequency table successfully in " + Math.round(stop) + " time");

    //------------------------------------------------------------------//

    //Build a pairing heap using the frequency table
    System.out.println("\nBuilding Pairing Heap");
    start = System.nanoTime();
    PairingHeap pHeap = new PairingHeap();

    for (int i=0; i<1000000; i++) {
      if (frequencyTable[i] > 0)
        pHeap.insert(i, frequencyTable[i]);
    }
    frequencyTable = null;
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time: " + Math.round(stop));

    //------------------------------------------------------------------//

    HeapNode huffRoot = pHeap.buildHuffmanTree();

    String[] huffCodes = new String[1000000];
    pHeap.copyHuffCodes(huffCodes);

    CodeTableCreator myCodeTableCreator = new CodeTableCreator("code_table.txt", huffRoot);
    myCodeTableCreator.createFile();

    Encoder myEncoder = new Encoder(filename,"encoded.bin",huffCodes);
    myEncoder.encode();

  }
}
