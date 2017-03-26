public class TreeBuilder {

  PairingHeap pHeap;
  HuffmanNode hRoot;
  HuffmanTree hTree;
  String[] table;

  public TreeBuilder(PairingHeap heap) {
    pHeap = heap;
    table = new String[1000000];
    buildHuffmanTree();
    buildCodeTable();
  }

  public void buildHuffmanTree() {

    HuffmanNode treeleft, treeright, treeparent;
    HeapNode heapleft, heapright, heapparent;

    while (pHeap.size() > 1) {
       heapleft = pHeap.deleteMin();
       heapright = pHeap.deleteMin();

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
       pHeap.insert(heapparent);
    }

    hRoot = pHeap.getHuffmanTreeAtRoot();
    hTree = new HuffmanTree(hRoot);
    hTree.updateHuffCodes();
  }

  public void buildCodeTable() {
    for (int i=0; i<1000000; i++)
      table[i] = "";
    hTree.copyHuffCodes(table);
  }

  public HuffmanTree getHuffmanTree() {
    return hTree;
  }

  public String[] getHuffCodeTable() {
    return table;
  }

}
