/*
Created by: Karan Dalvi
PerformanceMonitor was used to analyze the performance of different heap structures
and select the structure that gives optimum performance. This code takes a filename
as input which will be analyzed. It builds a frequency table and uses this array to
build different heaps and the Huffman Tree using those heap structures. The construction
of heap & tree is run in a loop ten times so as to get reliable analysis times.
*/

import java.util.Scanner;
import java.io.FileReader;
import java.lang.Exception;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
public class PerformanceMonitor {

  public static void main (String[] args) throws Exception {

    try {
    //------------------------------------------------------------------//

    //Initialize the frequency table that will be used to build the heap
    int count = 0;
    int heapSize;
    int[] frequencyTable = new int[1000000];
    Arrays.fill(frequencyTable, 0);

    //------------------------------------------------------------------//

    //Read the input file and update frequencies
    Scanner scan = new Scanner(new FileReader(args[0]));
    System.out.println("Performance Monitor (All times in milliseconds)");
    System.out.println("Analysis: Time taken to build heap and subsequently the Huffman Tree ten times");
    System.out.println("\nBuilding Frequency Table...");
    float start = System.nanoTime();
    while (scan.hasNext())
    {
      if ((frequencyTable[scan.nextInt()]++) == 0)
        count++;
    }
    scan.close();
    float stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Frequency Table Build Time: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a binary heap using the frequency table
    System.out.println("\nAnalyzing Binary Heap...");
    DaryHeap binaryheap = new DaryHeap(count, 2, 0);
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the binary heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            binaryheap.insert(i, frequencyTable[i]);
        }

        //Build the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        Node heapleft, heapright, heapparent;

        while (binaryheap.size() > 1) {
           heapleft = binaryheap.deleteMin();
           heapright = binaryheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new Node(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           binaryheap.insert(heapparent);
        }
        treeparent = binaryheap.getHuffmanTreeAtRoot();

        //Empty the binary heap
        binaryheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a four heap using the frequency table
    System.out.println("\nAnalyzing Four Heap...");
    DaryHeap fourheap = new DaryHeap(count, 4, 0);
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the four heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            fourheap.insert(i, frequencyTable[i]);
        }

        //Build the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        Node heapleft, heapright, heapparent;

        while (fourheap.size() > 1) {
           heapleft = fourheap.deleteMin();
           heapright = fourheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new Node(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           fourheap.insert(heapparent);
        }
        treeparent = fourheap.getHuffmanTreeAtRoot();

        //Empty the four heap
        fourheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a cache optimized four heap using the frequency table
    System.out.println("\nAnalyzing Cache Optimized Four Heap...");
    DaryHeap cacheheap = new DaryHeap(count, 4, 3);
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the cache optimized four heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            cacheheap.insert(i, frequencyTable[i]);
        }

        //Build the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        Node heapleft, heapright, heapparent;

        while (cacheheap.size() > 1) {
           heapleft = cacheheap.deleteMin();
           heapright = cacheheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new Node(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           cacheheap.insert(heapparent);
        }
        treeparent = cacheheap.getHuffmanTreeAtRoot();

        //Empty the cache optimized four heap
        cacheheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a pairing  heap using the frequency table
    System.out.println("\nAnalyzing Pairing Heap...");
    PairingHeap pairingheap = new PairingHeap();
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the pairing heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            pairingheap.insert(i, frequencyTable[i]);
        }

        //Build the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        HeapNode heapleft, heapright, heapparent;

        while (pairingheap.size() > 1) {
           heapleft = pairingheap.deleteMin();
           heapright = pairingheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new HeapNode(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           pairingheap.insert(heapparent);
        }
        treeparent = pairingheap.getHuffmanTreeAtRoot();

        //Empty the pairing heap
        pairingheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//
  }
  catch(FileNotFoundException e) {
    System.out.println("File not found. Please provide correct filename as command line argument.");
  }
  catch(ArrayIndexOutOfBoundsException e) {
    System.out.println("Please provide input filename");
  }
  catch (Exception e) {
    e.printStackTrace();
  }
  }
}
