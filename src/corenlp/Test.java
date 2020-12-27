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
		String text = "我还不是一个妈妈。";
		System.out.println(new PosTag(text).getTextmot());
		System.out.println(new PosTag(text).getPostext());
		String text2 = "我还是一个不妈妈。";
		System.out.println(new PosTag(text2).getTextmot());
		System.out.println(new PosTag(text2).getPostext());
//		int intIndex = text.indexOf("春节");
//		System.out.println(intIndex);
//		String t1 = text.replaceAll("春节", "??");
//		System.out.println(t1);
    }

}
