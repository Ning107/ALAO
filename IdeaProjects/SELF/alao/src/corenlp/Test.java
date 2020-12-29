package corenlp;

import java.io.*;
import java.util.*;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;

public class Test {
	public static void main(String[] args) throws IOException, ClassNotFoundException
    {
		// TODO Auto-generated method stub
		//String text = "我被打了。";
		//System.out.println(new PosTag(text).getTextmot());
		//System.out.println(new PosTag(text).getPostext());
		//String text2 = "我还是一个不妈妈。";
		//System.out.println(new PosTag(text2).getTextmot());
		//System.out.println(new PosTag(text2).getPostext());
		String text3 = "你的汉字写很好。";
		System.out.println(new PosTag(text3).getTextmot());
		System.out.println(new PosTag(text3).getPostext());
		
		System.out.println(new PosTag(text3).getscore());
		
//		int intIndex = text.indexOf("春节");
//		System.out.println(intIndex);
//		String t1 = text.replaceAll("春节", "??");
//		System.out.println(t1);
    }

}
