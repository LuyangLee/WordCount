import java.io.*;
import java.lang.*;
import java.util.*;

public class WCount {
	private  int cNumber;//表示字符数
	private  int wNumber;//表示单词数
	private  int line;//表示总行数
	private  int demoLine;//表示代码行
	private  int spaceLine;//表示空行
	private  int quLine;//表示注释行
	private HashSet<String> stopList;
	public WCount(int cNumber, int wNumber, int line, int demoLine, int spaceLine, int quLine,
			HashSet<String> stopList) {
		this.cNumber = cNumber;
		this.wNumber = wNumber;
		this.line = line;
		this.demoLine = demoLine;
		this.spaceLine = spaceLine;
		this.quLine = quLine;
		this.stopList = stopList;
	}

	public void addStopList(String filename) {
	        stopList = new HashSet<String>();
	        try {
	            FileReader file = new FileReader(filename);
	            int cha = file.read();
	            while (cha != -1) {
	                String word = "";
	                while(cha != -1 && isNormalChar(cha)) {
	                    word += String.valueOf((char)cha);
	                    cha = file.read();
	                }
	                stopList.add(word);
	                if (cha == -1)break;
	                	cha = file.read();
	            }
	            file.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
			while(len != -1) {
				String word = "";
				while(len != -1 && !isNormalChar(len)) {
					len = reader.read();
				}
				while (len != -1 && isNormalChar(len)) {
                    word += String.valueOf((char)len);
                    len = reader.read();
                }
				wNumber++;
				if (this.stopList != null && this.stopList.contains(word)) {
                    wNumber--;
                }
                if (len == -1) break;
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public  void readFileline(String fileName) throws IOException {
		FileReader reader =null;
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			reader = new FileReader(file);
			int len = reader.read();
			while(len != -1) {
				if(len =='\n') 
					line++;
				len = reader.read();
			}
			line++;
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public  void readFileAll(String fileName) throws IOException {
		FileReader reader =null;
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			reader = new FileReader(file);
			int len = reader.read();
			while(len != -1) {
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
	
	public void InputFile(String readName,String fileName,String fun[]) throws IOException{
		File file = new File(fileName);
		try {
			if(!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bfw =new BufferedWriter(fw);
			for(int i=0;i<fun.length;i++) {
				if(fun[i].equals("-c")) {
					bfw.write(fileName);
					bfw.write(",字符数:");
					bfw.write(String.valueOf(cNumber));
					bfw.newLine();
				}
				if(fun[i].equals("-w")) {
					bfw.write(fileName);
					bfw.write(",单词数:");
					bfw.write(String.valueOf(wNumber));
					bfw.newLine();
				}
				if(fun[i].equals("-l")) {
					bfw.write(fileName);
					bfw.write(",行数:");
					bfw.write(String.valueOf(line));
					bfw.newLine();
				}
				if(fun[i].equals("-c")) {
					bfw.write(fileName);
					bfw.write("，代码行/空行/注释行：");
					bfw.write(String.valueOf(demoLine) +"/"+ String.valueOf(spaceLine) +"/"+ String.valueOf(quLine));
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
	
	public void countALines(String filename) {
        
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            String line = file.readLine();
            boolean intoQuotation = false;
            while (line != null) {
                long charCount = 0;
                boolean hasQuotation = false;
                if (intoQuotation) hasQuotation = intoQuotation;
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch == ' ' || ch == '\t' || ch == '\n') {
                        continue;
                    }
                    else if (line.charAt(i) == '/' && intoQuotation == false) {
                        if (i + 1 != line.length()) {
                            if (line.charAt(i+1) == '*') {
                                intoQuotation = true;
                                hasQuotation = true;
                                i++;
                            } 
                            else if (line.charAt(i+1) == '/' ) {
                                hasQuotation = true;
                                break;
                            } else charCount++;
                        }
                    }
                    else if (line.charAt(i) == '*' && intoQuotation == true) {
                        if (i + 1 != line.length()) {
                            if (line.charAt(i+1) == '/') {
                                intoQuotation = false;
                                i++;
                            }
                        } else charCount++;
                    }
                    else {
                        if (intoQuotation == false) {
                            charCount++;
                        }
                    }
                }
                if (charCount == 0) {
                    if (hasQuotation) {
                        quLine++;
                    } else {
                        spaceLine++;
                    }
                } else if (charCount == 1) {
                    if (hasQuotation) {
                        spaceLine++;quLine++;
                    } else spaceLine++;
                } else {
                    demoLine++;
                }
                line = file.readLine();
            }

        } catch (IOException e) {
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
	
	public int getwNumber() {
		return wNumber;
	}

	public int getLine() {
		return line;
	}

	public int getDemoLine() {
		return demoLine;
	}

	public int getSpaceLine() {
		return spaceLine;
	}

	public int getQuLine() {
		return quLine;
	}

	public HashSet<String> getStopList() {
		return stopList;
	}

}
