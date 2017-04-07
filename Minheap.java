
public interface Minheap {
	
	public void buildHeap(int[] freqArray);
	
	public TreeNode extractMin();
	
	public void minHeapify(int index);
	
	public void decreaseKey(int index, int newFreq);
	
	public TreeNode getMin();
	
	public int parent_Of_I(int i);
	
	public int child_I_Of_J(int i,int j);
	
	public int getMinChild(int index);
	
	public TreeNode[] getHeapRef();
	
	public int getShift();
	
	public void setRoot(TreeNode minNode);
	
	public int getSizeCount();
	
	public TreeNode getRoot();
}
