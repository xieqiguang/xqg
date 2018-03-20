package WordCount;

import java.io.*;
import java.util.Scanner;

/**
 * 统计文本文件的行数，单词书，字符数
 */
public class WordCount {
    public static int words = 1;//单词数
    public static int lines = 1;//行数
    public static int chars = 0;//字符数
 
    public static void wc(InputStream f) throws IOException {
        int c = 0;
        boolean lastNotWhite = false;
        String whiteSpace = " \t\n\r";
        while ((c = f.read()) != -1) {
        	if(c!='\n'&&c!='\0'&&c!='\r')
        		chars++;
            if (c == '\n') {
                lines++;
            }
            if (whiteSpace.indexOf(c) != -1) {
                if (lastNotWhite) {
                    words++;
                }
                lastNotWhite = false;
            } else {
                lastNotWhite = true;
            }
        }
    }
 
    public static void main(String args[]) throws IOException {
    	int size=args.length;
		boolean l=false,o=false,w=false,c=false;
		String output="result.txt";
		String filepath;
		//读取命令行中的指令
		for(int i=0;i<size;i++){
			if(args[i].length()==2){
				switch(args[i].charAt(0)){
				case 'l':l=true;break;
				case 'o':o=true;
						output=args[++i];
						break;
				case 'w':w=true;break;
				case 'c':c=true;break;
				}
			}
			else
			filepath=args[i];
		}
        FileInputStream f;
        
        try {
            if (args.length == 0) { // 检查是否读入
            	Scanner input1 =new Scanner(System.in);
            	String str=input1.next();
                f = new FileInputStream(str);
                wc(f);
            } else { // 判断行
                for (int i = 0; i < args.length; i++) {
                    f = new FileInputStream(args[i]);
                    wc(f);
                }
            }
        } catch (IOException e) {
            return;
        }
        File file = new File(output);
        FileOutputStream fos = new FileOutputStream(file);
        
        
        if(c){
        	String content = "字符数："+chars;	
        	fos.write(content.getBytes());
        }
        else if(w){
        	String content = "单词数："+words;	
        	fos.write(content.getBytes());
        	//输出单词数
        }
        else if(l){
        	String content = "行数："+lines;	
        	fos.write(content.getBytes());
        	//输出行数   指定的输出文件用output
        }
        fos.flush();
        fos.close();
		}
}


