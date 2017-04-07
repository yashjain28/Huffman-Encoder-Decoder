import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;

public class decoder {
	public static void main(String args[]) throws IOException{
		
		File encodedBinaryFile=new File(args[0]);
		File codeTable=new File(args[1]);
		File DecodedFile=new File("decoded.txt");

		FileInputStream fileinputStream=null;

		BufferedWriter bW=null;
		BufferedReader br=null;
		try{
			long start=System.currentTimeMillis();
			br=new BufferedReader(new FileReader(codeTable));
			DecoderTree dTree=new DecoderTree();
			codeTableToDecoderTree(br,dTree);
			bW=new BufferedWriter(new FileWriter(DecodedFile));
			fileinputStream=new FileInputStream(encodedBinaryFile);
			long size=encodedBinaryFile.length();
			writeDecodeDataToFile(bW,fileinputStream,dTree,(int)size);
			//System.out.println("Time taken: "+(System.currentTimeMillis()-start));
			//System.out.println("Data Decoded");
		}
		finally{
			if (fileinputStream != null) {
				fileinputStream.close();
			}
			if(bW!=null){
				bW.close();
			}
			if(br!=null){
				br.close();
			}
		}
	}
	public static void writeDecodeDataToFile(BufferedWriter bW, FileInputStream fis, DecoderTree dTree, int size) throws IOException{
		DecoderNode dNode=dTree.getRoot();		
		byte[] b=new byte[size];
		fis.read(b);
		BitSet bitset=BitSet.valueOf(b);
		bitset.set((b.length)*8);
		for(int i=0;i<bitset.length()-1;i++){
			if(!bitset.get(i)){
				dNode=dNode.left;
			}
			else{
				dNode=dNode.right;
			}
			if(dNode.isEnd){	
				bW.write(""+dNode.data);
				bW.newLine();
				dNode=dTree.getRoot();
			}
		}
	}
	public static void codeTableToDecoderTree(BufferedReader br, DecoderTree dTree) throws IOException{
		String line=null;
		String[] dataValue=null;
		while ((line=br.readLine())!=null) {
			dataValue=line.split(" ");
			dTree.insert(dataValue[1],dTree.getRoot(),Integer.parseInt(dataValue[0]));
		}
	}
}
