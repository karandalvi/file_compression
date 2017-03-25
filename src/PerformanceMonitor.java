import java.util.Scanner;
import java.io.FileReader;
public class PerformanceMonitor {

  public static void main (String[] args) throws Exception {

    //------------------------------------------------------------------//

    System.out.println("Performance Monitor (Time in milliseconds)");

    //Initialize the frequency table that will be used to build the heap

    int count = 0;
    int heapSize;
    int[] frequencyTable = new int[1000000];
    for (int i=0; i<1000000; i++)
      frequencyTable[i] = 0;

    //------------------------------------------------------------------//

    //Read the input file and update frequencies
    System.out.println("\nBuilding the frequency table...");
    float start = System.nanoTime();
    Scanner scan = new Scanner(new FileReader("sample_input_large.txt"));
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

    //Build a binary heap using the frequency table
    System.out.println("\nBuilding Binary Heap");
    start = System.nanoTime();
    DaryHeap binaryheap = new DaryHeap(count, 2, 0);

    for (int j=0; j<10; j++) {
    for (int i=0; i<1000000; i++) {
      if (frequencyTable[i] > 0)
        binaryheap.insert(i, frequencyTable[i]);
    }
    if (j == 9)
      System.out.println("Heap Size: " + binaryheap.size());
    else
      binaryheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time:" + Math.round(stop));

    heapSize = binaryheap.size();
    start = System.nanoTime();

    for (int i=0; i<heapSize; i++)
      binaryheap.deleteMin();

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Delete Time:" + Math.round(stop));


    //------------------------------------------------------------------//

    //Build a 4-ary heap using the frequency table
    System.out.println("\nBuilding 4-ary Heap");
    start = System.nanoTime();
    DaryHeap fourheap = new DaryHeap(count, 4, 0);

    for (int j=0; j<10; j++) {
    for (int i=0; i<1000000; i++) {
      if (frequencyTable[i] > 0)
        fourheap.insert(i, frequencyTable[i]);
    }
    if (j == 9)
      System.out.println("Heap Size: " + fourheap.size());
    else
      fourheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time:" + Math.round(stop));

    heapSize = fourheap.size();
    start = System.nanoTime();

    for (int i=0; i<heapSize; i++)
      fourheap.deleteMin();

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Delete Time:" + Math.round(stop));

    //------------------------------------------------------------------//

    //Build a cache optimized 4-ary heap using the frequency table
    System.out.println("\nBuilding Cache Optimized 4-ary Heap");
    start = System.nanoTime();
    DaryHeap cacheheap = new DaryHeap(count, 4, 3);

    for (int j=0; j<10; j++) {
    for (int i=0; i<1000000; i++) {
      if (frequencyTable[i] > 0)
        cacheheap.insert(i, frequencyTable[i]);
    }
    if (j == 9)
      System.out.println("Heap Size: " + cacheheap.size());
    else
      cacheheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time:" + Math.round(stop));

    heapSize = cacheheap.size();
    start = System.nanoTime();

    for (int i=0; i<heapSize-3; i++)
      cacheheap.deleteMin();

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Delete Time:" + Math.round(stop));


    //------------------------------------------------------------------//

    //Build a pairing heap using the frequency table
    System.out.println("\nBuilding Pairing Heap");
    start = System.nanoTime();
    PairingHeap pHeap = new PairingHeap();

    for (int j=0; j<10; j++) {
    for (int i=0; i<1000000; i++) {
      if (frequencyTable[i] > 0)
        pHeap.insert(i, frequencyTable[i]);
    }
    if (j == 9)
      System.out.println("Heap Size: " + pHeap.size());
    else
      pHeap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Build Time:" + Math.round(stop));

    heapSize = pHeap.size();
    start = System.nanoTime();

    for (int i=0; i<heapSize; i++)
      pHeap.deleteMin();

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Total Delete Time:" + Math.round(stop));
  }
}
