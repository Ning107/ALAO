package corenlp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class Liretext {
	public static List txt2String(File file){
	     
        List l= new ArrayList();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                l.add(s.trim());
                //System.out.println(HanLP.segment(s.trim()));
                //System.out.println(l);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return l;
        //return result.toString();
	}
	
	// filtrer tous les textes dans le livre choisi par l'utilisateur
	public static List<File> searchFiles(File folder, String keyword){
		List<File> result = new ArrayList<File>();
		if (folder.isFile())
            result.add(folder);
		
		File[] subFolders = folder.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().contains(keyword)) {
                    return true;
                }
                return false;
            }
        });
		
		if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // Si c'est un fichier,ajouter directement dans le resultat
                    result.add(file);
                } else {
                    // Si c'est un dossier，utiliser la récursivité  pour ajouter tous les fichiers dans le résultat
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
	}
	
	public static void main(String[] args){
		//choisir un livre
		String text;
		Scanner ip = new Scanner(System.in);
		System.out.print("Choisir un livre: ");
		text = ip.nextLine();
		File path = new File("./Corpus/");
		List<File> files = searchFiles(path, text);
		int score = 0;
		
		// selon le levre choisi, le système va prendre 5 différents textes aleatoirement.
		if(files.size()>=5) {
			Random r = new Random();
			Vector<Integer> v = new Vector<Integer>();
			int count = 0;
			
			while(count < 5) {
				int number = r.nextInt(files.size());
				if(!v.contains(number)) {
					v.add(number);
	                count++;
	                File file = files.get(number);
	                List contenu = txt2String(file);
//	                System.out.println(contenu);
//	                System.out.println(contenu.get(0));
	                
	                int cpt = r.nextInt(contenu.size()-1);
	                String reference = contenu.get(cpt).toString();
	                while (reference.length()<5) {
	                	cpt = r.nextInt(contenu.size());
		                reference = contenu.get(cpt).toString();
	                }
//	                System.out.println(reference+"\n");
	                // tokeniser la phrase choisie et generer une liste de mots
	                List mot = new PosTag(reference).getTextmot();
	                List<String> motCopy = new ArrayList<String>();
	                motCopy.addAll(mot);
	                
	                
	                Collections.shuffle(mot);//打乱顺序
	                contenu.set(cpt, mot);
	                System.out.println("Question "+count+" : ");
					for(Object phrase : contenu) {
	                	System.out.println(phrase);
	                }
					String reponse;
					System.out.print("Donnez votre reponse: ");
					final Scanner input = new Scanner(System.in);
					reponse = input.nextLine();
					
					
					if (reference.equals(reponse)) {
						System.out.print("Bravo!\n");
						score=score+1;
					}else {
						// comparer la longueur
						if(reference.length()!= reponse.length()) {
							System.out.println("La longueur de la phrase n'est pas correcte. La reponse est : "+reference+"\n");
							score=score;
						}else {
							// Si la longueure est la même, on compare la structure de POS
							// une liste de POS
							// Cas 中国啊，中国1 : on prend chaque elt dans la liste motCopy et vérifier s'il est utilisé dans la reponse et donner les POS correspondants
//			                List pos= new PosTag(reference).getPostext();
//							int i = 0;
//							boolean motConnu = true;
//							
//							while(i < motCopy.size()&& motConnu) {
//								 
//								int intIndex = reponse.indexOf(motCopy.get(i));
//								if(intIndex == -1) {
//									System.out.println("Vous n'avez pas utilisé tous les éléments. La reponse est : "+reference+"\n");
//									motConnu = false;
//								}else {
//									reponse = reponse.replaceAll(motCopy.get(i),pos.get(i)+"+");
//									i++;
//								}
//							}
//							System.out.println(reponse.substring(0,reponse.length()-1)+"\n");
							// Cas 2 : on prend en compte uniquement la reponse de l'utilisateur
							List pos= new PosTag(reponse).getPostext();
							List tokens= new PosTag(reponse).getTextmot();
							String structure = "";
							for (Object elt : pos) {
								structure = structure+elt+"+";
							}
							//System.out.println(structure.substring(0,structure.length()-1)+"\n");
							//System.out.println(pos);
							//long leng_pos = pos.size();
							//System.out.println(leng_pos); 
														
							//if (structure != null) {								
							//for (int i = 0; i < leng_pos; i++) {
							//System.out.println(pos.get(i));
							
				//开始写判断用户给的句子的 错误条件
							//情况一，成分缺乏 主谓宾或者标点
								if (pos.contains("PN")==false && pos.contains("NR")==false && pos.contains("NN")==false) {
									score = score;
									System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
								}else if (pos.contains("BA")==false && pos.contains("LB")==false && pos.contains("VV")==false && pos.contains("VC")==false && pos.contains("VE")==false){
									score = score;
									System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
								}else if (pos.contains("PU")==false){
									score = score;
									System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
								}else {
									//情况二，语序不当
										//时间地点状语后置
									if (pos.indexOf("NT") > pos.indexOf("BA") || pos.indexOf("NT") > pos.indexOf("LB") || pos.indexOf("NT") > pos.indexOf("VV") || pos.indexOf("NT") > pos.indexOf("VC")) {
										score = score ;
										System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
									}else if (pos.indexOf("P") > pos.indexOf("BA") || pos.indexOf("P") > pos.indexOf("LB") || pos.indexOf("P") > pos.indexOf("VV") || pos.indexOf("P") > pos.indexOf("VC")){
										score = score;
										System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
										//“的” 和“地” 的位置
									}else if (pos.indexOf("DEG") +1 == pos.indexOf("JJ") || pos.indexOf("DEG") -1 == pos.indexOf("M") || pos.indexOf("DEG")-1 == pos.indexOf("VV")){
										score = score;
										System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
									}else if (pos.indexOf("DEV") -1 == pos.indexOf("VV")){
										score = score;
										System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
									}else {
										//情况三，搭配不当
											//动词直接接副词
										if (pos.indexOf("VV") +1 == pos.indexOf("AD")) {
											score = score ;
											System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
											//动词不能直接加符号 但是有很多特殊情况 此条存疑
										}else if (pos.indexOf("VV") +1 == pos.indexOf("PU")){
											score = score;
											System.out.print("Désolé, votre réponse est incorrecte! La reponse est : "+reference+"\n");
										}else {
											score = score + 1;
											System.out.print("Bravo!\n");
										}
									}
								}
							}
							
						}
					
					
				}
				//System.out.println(score);
			}
			
		}
		System.out.println("Votre score total est :" + score);
		ip.close();
	}

	private static char[] length(List pos) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int len(List tokens) {
		// TODO Auto-generated method stub
		return 0;
	}	
		
       
  }

