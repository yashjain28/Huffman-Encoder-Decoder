
public class BinaryHeap implements Minheap{

	int shift,sizecount;
	TreeNode[] heap;
	public BinaryHeap() {
		shift=0;
		heap=new TreeNode[1000004];
		sizecount=shift;
	}
	@Override
	public void buildHeap(int[] freqArray) {
		if(freqArray==null) return;
		//fill the heap of TreeNode array
		for(int i=0;i<freqArray.length;i++){
			if(freqArray[i]>0){
				heap[sizecount++]=new TreeNode(i,freqArray[i]);
			}
		}
		// if there is atleast one element inserted in the heap array, then take care of the sizecount, which we increment
		if(sizecount>shift)
			sizecount--;
		// min heapify from the child
		for(int i=parent_Of_I(sizecount);i>=shift;i--){
			minHeapify(i);
		}
		
	}

	@Override
	public TreeNode extractMin() {
		if(sizecount<shift) return null;
		TreeNode temp=heap[shift];
		heap[shift]=heap[sizecount];
		heap[sizecount]=null;
		sizecount--;
		minHeapify(shift);
		return temp;
	}

	@Override
	public void minHeapify(int index) {
		int minChild=getMinChild(index);
		if(minChild==index) return;
		else{
			TreeNode temp=heap[index];
			heap[index]=heap[minChild];
			heap[minChild]=temp;
			minHeapify(minChild);
		}
		
	}

	@Override
	public void decreaseKey(int index, int newFreq) {
		// TODO Auto-generated method stub
		if(heap[index].freq<newFreq) return;
		int parentofi=parent_Of_I(index);
		heap[index].freq=newFreq;
		TreeNode temp;
		while(parentofi >= shift && heap[parentofi].freq>heap[index].freq){
			temp=heap[parentofi];
			heap[parentofi]=heap[index];
			heap[index]=temp;
			index=parentofi;
			parentofi=parent_Of_I(index);
		}
		
	}

	@Override
	public TreeNode getMin() {
		if(sizecount>=shift){
			return heap[shift];
		}
		return null;
	}

	@Override
	public int parent_Of_I(int i) {
		// TODO Auto-generated method stub
		return ((i+1)/2)-1;
	}

	@Override
	public int child_I_Of_J(int i, int j) {
		// ith child of j
		return 2*(j+1)+i-1;
	}

	@Override
	public int getMinChild(int index) {
		int smallest=index;
		for(int i=0;i<2;i++){
			if(child_I_Of_J(i,index)<=sizecount){
					if(heap[child_I_Of_J(i,index)].freq<heap[smallest].freq){
						smallest=child_I_Of_J(i, index);
					}
					
			}
		}
		return smallest;
	}

	@Override
	public TreeNode[] getHeapRef() {
		// TODO Auto-generated method stub
		return heap;
	}

	@Override
	public int getShift() {
		// TODO Auto-generated method stub
		
		return shift;
	}

	@Override
	public void setRoot(TreeNode minNode) {
		// TODO Auto-generated method stub
		heap[shift]=minNode;
		minHeapify(shift);
	}

	@Override
	public int getSizeCount() {
		// TODO Auto-generated method stub
		return sizecount;
	}
	@Override
	public TreeNode getRoot() {
		if(sizecount>=shift){
			return heap[shift];
		}
		return null;
	}

}
