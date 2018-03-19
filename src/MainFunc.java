import java.io.IOException;
import java.lang.*;
import java.io.*;
import java.util.*;

public class MainFunc {
	
	public static void main(String[] args) throws IOException {
		String readName = null;
		String writeName = null;
		String stopName = null;
		String fun[] = {" "," "," "," "};
		
		int count =0;
		WCount wc = new WCount(0,0,0,0,0,0,null);
		int typeFlags = 0;//0表示-c,-w,-l的状态，1表示读取得文件，2表示其他参数，3表示写出的文件,4表示停用词表
		int otype = 0;
		for (int i = 0; i < args.length; i++) { 
			if(args[i].equals("-c")||args[i].equals("-w")||args[i].equals("-l")||args[i].equals("-a")) {
				typeFlags = 0;
				fun[count]= args[i];
				count++;
			}
			else if(args[i].equals("-e")) {
				typeFlags = 4;
				wc.addStopList(args[i + 1]);
			}
				
			else if(args[i].equals("-o"))
			{
				typeFlags = 3;
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
			if(args[i].equals("-c")) {
				wc.readFileChar(readName);
				System.out.print(readName);
				System.out.println("，字符数：" + wc.getcNumber());
			}
			if(args[i].equals("-w")) {
				wc.readFileWord(readName);
				System.out.print(readName);
				System.out.println("，单词数： " + wc.getwNumber());
			}
			if(args[i].equals("-l")) {
				wc.readFileline(readName);
				System.out.print(readName);
				System.out.println("，行数："+ wc.getLine());
			}
			if(args[i].equals("-o")) {
				wc.InputFile(readName,writeName, fun);
			}
			if(args[i].equals("-a")) {
				wc.countALines(readName);
				System.out.print(readName);
				System.out.println("，代码行/空行/注释行："+ wc.getDemoLine()+"/"
														+ wc.getSpaceLine() + "/"
														+ wc.getQuLine());
			}
		}

		
	}
}
