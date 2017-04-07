
public class HuffmanTreeConstructor {
		public HuffmanTreeConstructor() {
			// TODO Auto-generated constructor stub
		}
		public Minheap createHuffmanTree(Minheap tree){
			// construct the tree now
			while(tree.getSizeCount()>tree.getShift()){
				TreeNode node1=tree.extractMin();
				TreeNode node2=tree.getMin(); // No need to remove the second just peek it, then overwrite it with new TreeNode
				TreeNode node3=new TreeNode();
				node3.freq=node1.freq+node2.freq;
				node3.left=node1;
				node3.right=node2;
				tree.setRoot(node3); // set root calls minHeapify
			}
			//System.out.println("Huffman Tree Constructed");
			return tree;
		}
}
