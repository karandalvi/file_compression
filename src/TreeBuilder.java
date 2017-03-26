public class TreeBuilder {

  PairingHeap pHeap;
  HuffmanNode hTree;
  int flag = 0;

  public TreeBuilder(PairingHeap heap) {
    pHeap = heap;
  }

  public HuffmanNode buildHuffmanTree() {

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
       heapparent = new HeapNode(-1, heapleft.value() + heapright.value(), treeparent);
       pHeap.insert(heapparent);
    }

    hTree = pHeap.getHuffmanTreeAtRoot();
    flag = 1;
    return hTree;
  }

  public String[] buildCodeTable() {
    String[] table = new String[1000000];
    if (flag == 1) {
      hTree.updateHuffCodes("");
    }
    else {
      buildHuffmanTree();
      hTree.updateHuffCodes("");
    }
    hTree.copyToTable(table);
    return table;
  }

}
