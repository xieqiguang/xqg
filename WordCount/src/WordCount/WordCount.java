package WordCount;

import java.io.*;
import java.util.Scanner;

/**
 * ͳ���ı��ļ��������������飬�ַ���
 */
public class WordCount {
    public static int words = 1;//������
    public static int lines = 1;//����
    public static int chars = 0;//�ַ���
 
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
		//��ȡ�������е�ָ��
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
            if (args.length == 0) { // ����Ƿ����
            	Scanner input1 =new Scanner(System.in);
            	String str=input1.next();
                f = new FileInputStream(str);
                wc(f);
            } else { // �ж���
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
        	String content = "�ַ�����"+chars;	
        	fos.write(content.getBytes());
        }
        else if(w){
        	String content = "��������"+words;	
        	fos.write(content.getBytes());
        	//���������
        }
        else if(l){
        	String content = "������"+lines;	
        	fos.write(content.getBytes());
        	//�������   ָ��������ļ���output
        }
        fos.flush();
        fos.close();
		}
}


