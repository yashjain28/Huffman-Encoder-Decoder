 import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.Iterator;

public class encoder {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		long start1= System.currentTimeMillis();
		int[] freqTable=new int[1000004];
		File foutcode=new File("code_table.txt");
		File sampleinput=new File(args[0]);
		OutputStream encodedBinData = null;
		
		BufferedWriter bW=null;
		BufferedReader br=null;
		BufferedReader brs=null;
	    Minheap tree=null;
		try {
		    br = new BufferedReader(new FileReader(sampleinput));
		    readDataFromFile(br, freqTable);
		    //System.out.println("TimePOst reading file " +(System.currentTimeMillis()-start));
		    //build heap first
		    /*int useXheap=Integer.parseInt(args[1]);
		    
		    // generate code table and store it in hashmap and then write to file
			 int sum=0;
		    	start =System.currentTimeMillis();
		     	for(int i=0;i<10;i++){
		     		switch(useXheap){
					case 1: 
						tree=new FourWayHeap();
						break;
					case 2:
						tree=new BinaryHeap();
						break;
					case 3:
						tree=new PairingHeap();
						break;
					default:
						tree=new FourWayHeap();
						break;
				    }
		     		tree.buildHeap(freqTable);
		    	}
		    	sum+=(System.currentTimeMillis()-start);  
		    */
		    tree=new FourWayHeap();
		    tree.buildHeap(freqTable);

		  	//System.out.println("Time to buildHeap " +(sum/10));
		  	start=System.currentTimeMillis();
			HuffmanTreeConstructor hmtc=new HuffmanTreeConstructor();
			hmtc.createHuffmanTree(tree);
			CodeValueGenerator cvg=new CodeValueGenerator(tree);
			//System.out.println("Time to generate tree: "+(System.currentTimeMillis()-start));			CodeValueGenerator cvg=new CodeValueGenerator(tree);
		    bW=new BufferedWriter(new FileWriter(foutcode));
			writeCodeTableOnFile(bW,cvg);
	    	bW.close();
	    	//encode the data in a binary file
	    	start =System.currentTimeMillis();
	    	encodedBinData = new BufferedOutputStream(new FileOutputStream("encoded.bin"));
	    	brs = new BufferedReader(new FileReader(sampleinput));
	    	encodeInputDataToBinary(brs,cvg,encodedBinData);
	    	//System.out.println("Data written to bin");
	    	//System.out.println("TimePOst writing table to file " +(System.currentTimeMillis()-start));
	    	//System.out.println("Total Time: " +(System.currentTimeMillis()-start1));
	   		// decoding it here for check !!

		}
		catch(Exception E){
			System.out.println("Exception occured  "+E);
		}
		finally{
			if(encodedBinData!=null){
		    	encodedBinData.close();
		    }
		    if (br != null) {
		        br.close();
		    }
		    if(brs!=null){
		    	brs.close(); 
		    }
		}
	}
	public static void writeCodeTableOnFile(BufferedWriter bW, CodeValueGenerator cvg) throws IOException{
		Iterator<Integer> it=cvg.dataCode.keySet().iterator();
			while(it.hasNext()){
				Integer temp=it.next();
				bW.write(temp+ " "+cvg.dataCode.get(temp));
				bW.newLine();
			}
	}
	
	public static void readDataFromFile(BufferedReader br, int[] freqTable) throws IOException{
		String line; 
		while ((line=br.readLine())!=null) {
		        freqTable[(Integer.parseInt(line))]++;
		   }
	}
	
public static void encodeInputDataToBinary(BufferedReader br, CodeValueGenerator cvg, OutputStream bW) throws IOException{
		StringBuilder sb=new StringBuilder(); 
		String line;
		BitSet bs=new BitSet();
		int length=0;
		//System.out.println(br.hasNextLine());
		while((line=br.readLine())!=null){
			
			sb.append(cvg.dataCode.get(Integer.parseInt(line)));
			for(int i=0;i<sb.length();i++){
				if(sb.charAt(i)=='1'){
					bs.set(length+i);
				}
			}
			length+=sb.length();
			sb.setLength(0);
		}	
		bW.write(bs.toByteArray());	
	}
}
