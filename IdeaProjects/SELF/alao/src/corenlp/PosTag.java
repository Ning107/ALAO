package corenlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;

public class PosTag {
	private List postext;
	private List textmot;
	private int score;
	
    public List getPostext() {
        return postext;
    }
    public List getTextmot() {
        return textmot;
    }
    public int getscore() {
        return score;
    }

    public PosTag(String text){

        CoreNLPHel coreNLPHel = CoreNLPHel.getInstance();
        StanfordCoreNLP pipeline = coreNLPHel.getPipeline();
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        //StringBuffer sb = new StringBuffer();
        List mot=new ArrayList();
        List pos=new ArrayList();
        for (CoreMap sentence:sentences){
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String postag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                mot.add(word);
                pos.add(postag);
                //sb.append(word);
               // sb.append("/");
                //sb.append(pos);
                //sb.append(" ");
            }
        }
        postext = pos;
        textmot = mot;
        
        long leng_pos = pos.size();		
        // ||(pos.get(i)!="BA"&& pos.get(i)!="LB"&& pos.get(i)!="VV"&& pos.get(i)!="VC")
        int sco=0;
        //for (int i = 0; i < leng_pos; i++) {
        //System.out.println(pos.get(i));
			if (pos.contains("PN")==false && pos.contains("NR")==false && pos.contains("NN")==false) {
				sco = sco;
			}else if (pos.contains("BA")==false && pos.contains("LB")==false && pos.contains("VV")==false && pos.contains("VC")==false && pos.contains("VE")==false){
				sco = sco;
			}else if (pos.contains("PU")==false){
				sco = sco;
			}else {
				if (pos.indexOf("NT") > pos.indexOf("BA") || pos.indexOf("NT") > pos.indexOf("LB") || pos.indexOf("NT") > pos.indexOf("VV") || pos.indexOf("NT") > pos.indexOf("VC")) {
					sco = sco ;
				}else if (pos.indexOf("P") > pos.indexOf("BA") || pos.indexOf("P") > pos.indexOf("LB") || pos.indexOf("P") > pos.indexOf("VV") || pos.indexOf("P") > pos.indexOf("VC")){
					sco = sco;
					
				}else if (pos.indexOf("DEG") +1 == pos.indexOf("JJ") || pos.indexOf("DEG") -1 == pos.indexOf("M") || pos.indexOf("DEG")-1 == pos.indexOf("VV")){
					sco = sco;
				}else if (pos.indexOf("DEV") -1 == pos.indexOf("VV")){
					sco = sco;
					
				}else if (pos.indexOf("VV") +1 == pos.indexOf("PU")){
					sco = sco;
					
					if (pos.indexOf("VV") +1 == pos.indexOf("AD")) {
						sco = sco ;
					}else {
					sco = sco + 1;
				}
				}
			}
			//}list.contains(o)
		score=sco;
		}
	private int len(List pos) {
		// TODO Auto-generated method stub
		return 0;
	}

}
