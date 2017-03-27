/*
Created by: Karan Dalvi
Pairing Heap implementation.
*/

public class PairingHeap {

  HeapNode root;
  int size;

  //------------------------------------------------------------//

  public PairingHeap () {
    root = null;
    size = 0;
  }

  //------------------------------------------------------------//

  public boolean isEmpty() {
    return (root == null);
  }

  public void flush() {
    root = null;
    size = 0;
  }

  public void printHeap() {
    System.out.print("\n");
    print(root);
    System.out.print("\n");
  }

  public void print(HeapNode t) {
    if (t == null)
      return;
    print(t.child);
    System.out.print(" " + t.frequency());
    print(t.right);
  }

  //------------------------------------------------------------//

  public int getMinFrequency() {
    return root.frequency();
  }

  public int size() {
    return size;
  }

  public HuffmanNode getHuffmanTreeAtRoot () {
    return root.pHuff;
  }

  //------------------------------------------------------------//


    public void insert (int v, int f) {
        HeapNode t = new HeapNode(v,f);
        insert(t);
    }

    public void insert (HeapNode t) {

        if (isEmpty()) {
          root = t;
        }

        else if (getMinFrequency() > t.frequency()) {
          t.setChild(root);
          root.left = t;
          root = t;
        }

        else {
            t.right = root.child;
            if (root.child != null)
              root.child.left = t;
            root.child = t;
            t.left = root;
        }
        size++;
      }

    public HeapNode deleteMin() {
      if (isEmpty()) {
        System.out.println("Heap is empty");
        return null;
      }

      else if (root.child == null) {
          HeapNode min = root;
          root = null;
          size--;
          return min;
      }

      HeapNode min = root;
      HeapNode child = root.child;
      HeapNode temp = child;
      int count = 0;

      while (temp != null) {
        count++;
        temp = temp.right;
      }

      if (count%2 != 0)
        count++;

      HeapNode[] passOne = new HeapNode[count];

      temp = child;
      for (int i=0; i<count; i++) {
        passOne[i] = temp;
        if (temp != null)
          temp = temp.right;
      }

      for (int i=0; i<count; i++) {
        if (passOne[i] != null) {
          passOne[i].left = null;
          passOne[i].right = null;
        }
      }

      for (int i = 0; i < count; i=i+2) {
        passOne[i] = meldHeaps(passOne[i], passOne[i+1]);
      }

      temp = passOne[count-2];
      for (int i=count-4; i>=0; i=i-2) {
        temp = meldHeaps(temp, passOne[i]);
      }
      root = temp;
      size--;
      return min;
    }

    //------------------------------------------------------------//

    public HeapNode meldHeaps(HeapNode heap1, HeapNode heap2) {
      if (heap1 == null)
        return heap2;
      else if (heap2 == null)
        return heap1;
      else {
        if (heap1.frequency() < heap2.frequency()) {
          heap2.right = heap1.child;
          if (heap1.child != null)
            heap1.child.left = heap2;
          heap1.child = heap2;
          heap2.left = heap1;
          return heap1;
        }
        else {
          heap1.right = heap2.child;
          if (heap2.child != null)
            heap2.child.left = heap1;
          heap2.child = heap1;
          heap1.left = heap2;
          return heap2;
        }
      }
    }

    //------------------------------------------------------------//


}
