/*
Created by: Karan Dalvi
DaryHeap is a Heap structure that can create a m-degree heap as well as m-degree cached optimized heap.
I use this structure to create a binary heap, 4 way heap and a 4 way cache optimized heap.
The HeapNode contains a pointer to a HuffmanNode so as to store a link to corresponding node in Huffman Tree during the building process.
It will otherwise have a null value.
The degree indicates the number of children each parent node will have in the heap whereas the shift will indicate
at which place will the root be located in the Vector.
*/

import java.util.Vector;

public class DaryHeap {

  int size;
  int degree;
  int shift;
  Vector<Node> heap;

  //Constructor prompting for size (initial size of the array), degree (number of children) and shift (index of root)
  public DaryHeap (int size, int degree, int shift) {
    this.size = size;
    this.degree = degree;
    this.shift = shift;
    heap = new Vector<Node>(size+shift);
    for (int i = 0; i < shift; i++) {
      heap.add(new Node(-1, -1));
    }
  }

  //Check if the heap is empty
  public boolean isEmpty() {
    return heap.isEmpty();
  }

  //Returns the size of the heap
  public int size() {
    return (heap.size() - shift);
  }

  //Print the values in the heap
  public void printHeap () {
    for (int i = 0; i<heap.size(); i++) {
      System.out.print(" - " + heap.get(i).key() + "|" + heap.get(i).frequency());
    }
  }

  //Return huffman node pointer at root node
  public HuffmanNode getHuffmanNodeAtRoot() {
      if (isEmpty())
        return null;
      else
        return heap.get(shift).pHuff;
  }

  //-------------------------------------------------------------------------

  //Return the parent index of the child index passed as argument
  public int parent (int child) {
    return ((child - shift - 1) / degree) + shift;
  }

  //Return the Kth child's index of the parent index passed as argument
  public int kthChild (int parent, int k) {
    return ((parent - shift) * degree) + shift + k;
  }

  //-------------------------------------------------------------------------

  //Empty the heap
  public void flush() {
    heap.removeAllElements();
    for (int i = 0; i < shift; i++) {
      heap.add(new Node(-1, -1));
    }
  }

  //Insert a new key-value pair
  public void insert (int k, int f) {
    heap.add(new Node(k,f));
    heapifyUp(heap.size() - 1);
  }

  public void insert (Node n) {
    heap.add(n);
    heapifyUp(heap.size() - 1);
  }

  //Deletes the min element and returns the node
  public Node deleteMin () {
    if (heap.size() <= shift) {
      System.out.println("Heap is empty.");
      return null;
    }
    else if (heap.size() - shift == 1)
    {
      return heap.remove(heap.size() - 1);
    }
    else {
      Node min = heap.get(shift);
      heap.set(shift, heap.get(heap.size() - 1));
      heap.remove(heap.size()-1);
      heapifyDown(shift);
      return min;
    }
  }

  //--------------------------------------------------------------------------

  //Returns the index of the minimum child
  public int minChild(int n) {
    int bestChild = kthChild(n, 1);
    if (bestChild >= heap.size())
      return bestChild;
    else {
        int child = 0;
        int iterator = 2;
        while ((iterator <= degree) && (kthChild(n,iterator) < heap.size())) {
          child = kthChild(n, iterator);
          if (heap.get(child).frequency() < heap.get(bestChild).frequency()) {
            bestChild = child;
          }
          iterator++;
        }
      }

    return bestChild;
  }

  //--------------------------------------------------------------------------

  //Functions that maintain the min heap property
  public void minHeapify (int p) {
    int c = minChild(p);
    Node parent = heap.get(p);
    if (heap.get(p).frequency() > heap.get(c).frequency()) {
      Node temp = heap.get(p);
      heap.set(p, heap.get(c));
      heap.set(c, temp);
      minHeapify(c);
    }
  }

  public void buildHeap() {
    for (int i = parent(size); i>=shift; i--) {
      minHeapify(i);
    }
  }

  //Function to bubble up values that so as to maintain heap property
  public void heapifyUp (int child) {
    Node temp = heap.get(child);
    while ((child > shift) && (temp.frequency() < heap.get(parent(child)).frequency())) {
      heap.set(child, heap.get(parent(child)));
      child = parent(child);
    }
    heap.set(child,temp);
  }

  //Function to push down values that so as to maintain heap property
  public void heapifyDown (int parent) {
    int child;
    Node temp = heap.get(parent);
    while (kthChild(parent, 1) < heap.size()) {
      child = minChild(parent);
      if (heap.get(child).frequency() < temp.frequency()) {
          heap.set(parent, heap.get(child));
      }
      else
        break;
      parent = child;
    }

    heap.set(parent, temp);
  }

  //--------------------------------------------------------------------------

}