import java.util.Iterator;
import java.util.LinkedList;

public class PairingHeap implements Minheap {
	TreeNode root=null;
	private TreeNode meld(TreeNode t1, TreeNode t2){
		if(t1==null){
			return t2;
		}
		if(t2==null){
			return t1;
		}
		if(t1.freq>t2.freq){	
			TreeNode temp=t2.child;
			t2.child=t1;
			t1.prev=t2;
			t1.next=temp;
			if(temp!=null)
				temp.prev=t1;
			t2.prev=null;
			t2.next=null;
			return t2;
		}
		else{
			TreeNode temp=t1.child;
			t1.child=t2;
			t2.prev=t1;
			t2.next=temp;
			if(temp!=null) 
				temp.prev=t2;
			t1.prev=null;
			t1.next=null;
			return	t1;
		}
	
	}
	private TreeNode insert(TreeNode node){
		return meld(root,node);
	}
	@Override
	public void buildHeap(int[] freqArray) {
		for(int i=0;i<freqArray.length;i++){
			if(freqArray[i]>0){
				root = insert(new TreeNode(i,freqArray[i]));
				//System.out.println(root.data+" "+root.freq+" ");
			}
		}
	}

	@Override
	public TreeNode extractMin() {
		LinkedList<TreeNode> multipassQueue=new LinkedList<>();
		
		if(root==null){
			return null;
		}
		TreeNode child=root.child;
		TreeNode min=new TreeNode(root.data,root.freq);
		min.left=root.left;
		min.right=root.right;
		if(child!=null){
			child.prev=null;
			TreeNode temp=null;
			while(child!=null){
				temp=child.next;
				child.next=null;
				child.prev=null;
				multipassQueue.add(child);
				child=temp;
			}
			while(multipassQueue.size()>1){	
					multipassQueue.offer(meld(multipassQueue.poll(),multipassQueue.poll()));
			}
			root=multipassQueue.poll();
		}
		else{
			root=null;
		}
		return min;
	}

	@Override
	public TreeNode getMin() {
		return extractMin();
	}
	@Override
	public int getShift() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRoot(TreeNode minNode) {
		// TODO Auto-generated method stub
		root=insert(minNode);
	}

	@Override
	public int getSizeCount() {
		// TODO Auto-generated method stub
		if(root!=null && root.child!=null){
			return 2;
		}
		return 0;
	}
	@Override
	public TreeNode getRoot() {
		// TODO Auto-generated method stub
		return root;
	}
	 
	@Override
	public void minHeapify(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseKey(int index, int newFreq) {
		// TODO Auto-generated method stub

	}

	@Override
	public int parent_Of_I(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int child_I_Of_J(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinChild(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode[] getHeapRef() {
		// TODO Auto-generated method stub
		return null;
	}


}
