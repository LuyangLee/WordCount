import java.io.*;
import java.lang.*;
import java.util.*;

public class WCount {
	private  int cNumber;
	private  int wNumber;
	private  int line;
	public WCount(int cNumber, int wNumber, int line) {
		this.cNumber = cNumber;
		this.wNumber = wNumber;
		this.line = line;
	}
	public  void readFileChar(String fileName) throws IOException {
		FileReader reader =null;
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			reader = new FileReader(file);
			int len = reader.read();
			while(len != -1) {
//				System.out.print((char)len);
				if(len =='\n') 
					line++;
				cNumber++;
				len = reader.read();
			}
			line++;
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFileWord(String fileName)throws IOException{
		FileReader reader =null;
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			reader = new FileReader(file);
			int len = reader.read();
			int flags = 0;
			while(len != -1) {
//				System.out.print((char)len);
				if(isNormalChar(len)) 
					flags = 1;
				if(!isNormalChar(len)) {
					if(flags == 1) 
						wNumber++;
					flags = 0;
				}
				len = reader.read();
			}
			if(flags == 1)
				wNumber++;
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void InputFile(String readName,String fileName,String fun[]) throws IOException{
		File file = new File(fileName);
		try {
			if(!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bfw =new BufferedWriter(fw);
			for(int i=0;i<fun.length;i++) {
				if(fun[i].equals("c")) {
					bfw.write(fileName);
					bfw.write(",字符数:");
					bfw.write(String.valueOf(cNumber));
					bfw.newLine();
				}
				if(fun[i].equals("w")) {
					bfw.write(fileName);
					bfw.write(",单词数:");
					bfw.write(String.valueOf(wNumber));
					bfw.newLine();
				}
				if(fun[i].equals("l")) {
					bfw.write(fileName);
					bfw.write(",行数:");
					bfw.write(String.valueOf(line));
					bfw.newLine();
				}
			}
			bfw.close();
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isNormalChar(int ch) {
		if(ch == '\t'||ch == '\n'||ch == ','||ch == ' ')
			return false;
		else 
			return true;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public int getwNumber() {
		return wNumber;
	}
	public void setwNumber(int wNumber) {
		this.wNumber = wNumber;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
}
