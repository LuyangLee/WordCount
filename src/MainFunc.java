import java.io.IOException;
import java.io.*;

public class MainFunc {
	public static void main(String[] args) throws IOException {
		String readName = null;
		String writeName = null;
		String stopName = null;
		String fun[] = {" "," "," "," "};
		int count =0;
		WCount wc = new WCount(0,0,0);
		int typeFlags = 0;//0��ʾ-c,-w,-l��״̬��1��ʾ��ȡ���ļ���2��ʾ����������3��ʾд�����ļ�,4��ʾͣ�ôʱ�
		int otype = 0;
		for (int i = 0; i < args.length; i++) { 
			if(args[i].equals("-c")||args[i].equals("-w")||args[i].equals("-l")||args[i].equals("-a")) {
				typeFlags = 0;
				fun[count]= args[i];
				count++;
			}
				
			else if(args[i].equals("-e"))
				typeFlags = 4;
			else if(args[i].equals("-o"))
			{
				typeFlags = 3;
				otype = 1;
			}
				
			if(args[i].length() > 1) {
				if(typeFlags ==0)
					readName = new String(args[i]);
				else if(typeFlags == 3)
					writeName = new String(args[i]);
				else if(typeFlags == 4)
					stopName = new String(args[i]);
			}
		}
		for (int i = 0; i < args.length; i++) { 
			if(args[i].equals("c")) {
				wc.readFileChar(readName);
				System.out.print(readName);
				System.out.println("���ַ�����" + wc.getcNumber());
				typeFlags = 0;
//				if(otype == 1)
//					wc.InputFile(readName,writeName, "c");
			}
			if(args[i].equals("w")) {
				wc.readFileWord(readName);
				System.out.print(readName);
				System.out.println("���������� " + wc.getwNumber());
				typeFlags = 0;
//				if(otype == 1)
//					wc.InputFile(readName,writeName, "w");
			}
			if(args[i].equals("l")) {
				wc.readFileChar(readName);
				System.out.print(readName);
				System.out.println("��������"+ wc.getLine());
				typeFlags = 0;
//				if(otype == 1)
//					wc.InputFile(readName,writeName, "l");
			}
		}
		wc.InputFile(readName,writeName, fun);
		
		
	}
}
