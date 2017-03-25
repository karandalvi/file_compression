import java.util.ArrayList;
import java.util.Vector;

public class DaryHeap {

  int size;
  int degree;
  int shift;
  Vector<Node> heap;

  public DaryHeap (int size, int degree, int shift) {
    this.size = size;
    this.degree = degree;
    this.shift = shift;
    heap = new Vector<Node>(size+shift);
    for (int i = 0; i < shift; i++) {
      heap.add(new Node(-1, -1));
    }
  }

  public void flush() {
    heap.removeAllElements();
    for (int i = 0; i < shift; i++) {
      heap.add(new Node(-1, -1));
    }
  }
  public boolean isEmpty() {
    return heap.isEmpty();
  }

  public int size() {
    return heap.size();
  }

  public int parent (int child) {
    return ((child - shift - 1) / degree) + shift;
  }

  public int kthChild (int parent, int k) {
    return ((parent - shift) * degree) + shift + k;
  }

  public void insert (int k, int f) {
    heap.add(new Node(k,f));
    heapifyUp(heap.size() - 1);
  }

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

  public void heapifyUp (int child) {
    Node temp = heap.get(child);
    while ((child > shift) && (temp.frequency() < heap.get(parent(child)).frequency())) {
      heap.set(child, heap.get(parent(child)));
      child = parent(child);
    }
    heap.set(child,temp);
  }

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

  public void printHeap () {
    for (int i = 0; i<heap.size(); i++) {
      System.out.print(" - " + heap.get(i).key() + "|" + heap.get(i).frequency());
    }
  }
}
