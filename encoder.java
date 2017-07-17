/*
Created by: Karan Dalvi
The Encoder program takes one value (filename) as input in command line arguments.
It will then build a frequency table for this file, build a pairing heap and then
using huffman coding to create a compressed file in binary form. It also creates
the huffman code table which should be used for reconstructing the original file.
*/
import java.util.Scanner;
import java.io.FileReader;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;

public class encoder {

  public static void main (String[] args) throws Exception {

    try {
    String filename = args[0];
    int count = 0;
    int heapSize;
    int[] frequencyTable = new int[256];
    Arrays.fill(frequencyTable, 0);

    //------------------------------------------------------------------//

    System.out.println("------------------------------------------------------");
    //Read the input file and update frequencies
    float total = 0;
    float start = System.nanoTime();
    Scanner scan = new Scanner(new FileReader(filename));
    while (scan.hasNext())
    {
		String scannedString = scan.next();
		for (int i=0; i<scannedString.length(); i++) {
			if ((frequencyTable[(int) scannedString.charAt(i)]++) == 0)
				count++;
		}
		if ((frequencyTable[10]++) == 0)
				count++;
    }
    scan.close();		
    
	float stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Frequency Table Build Time : " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a cache optimized heap using the frequency table
    start = System.nanoTime();
    DaryHeap myHeap = new DaryHeap(count, 4, 3);

    for (int i=0; i<128; i++) {
      if (frequencyTable[i] > 0)
        myHeap.insert(i, frequencyTable[i]);
    }
    frequencyTable = null;
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Heap Build Time            : " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//
	
    start = System.nanoTime();
    TreeBuilder myTreeBuilder = new TreeBuilder(myHeap);
    HuffmanTree huffTree = myTreeBuilder.getHuffmanTree();
    String[] huffCodes = myTreeBuilder.getHuffCodeTable();
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Huffman Tree Build Time    : " + Math.round(stop) + " milliseconds");

	
    start = System.nanoTime();
    CodeTableWriter myCodeTableWriter = new CodeTableWriter("code_table.txt", huffTree.getRoot());
    myCodeTableWriter.createFile();
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Write Code Table File Time : " + Math.round(stop) + " milliseconds");

    start = System.nanoTime();
    BinaryFileWriter myBinaryFileWriter = new BinaryFileWriter(filename,"encoded.bin",huffCodes);
    myBinaryFileWriter.encode();
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Write Encoded Bin File     : " + Math.round(stop) + " milliseconds");
    System.out.println("  Total Time                 : " + Math.round(total) + " milliseconds");
    System.out.println("------------------------------------------------------");
	
  }

  catch(FileNotFoundException e) {
    System.out.println("File not found. Please provide correct filename as command line argument.");
  }
  catch(ArrayIndexOutOfBoundsException e) {
    System.out.println("Please provide the filename which you want to encode");
  }
  catch (Exception e) {
    e.printStackTrace();
  }

  }
}
