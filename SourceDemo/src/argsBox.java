import java.lang.*;
import java.io.*;
import java.util.*;

public class argsBox {
	public HashMap<String, String> aBox = new HashMap<>();
	public String readFoldName = null;
	void addArgs(String[] args) {
		int flags = 0;
		for(int i=0;i<args.length;i++) {
			if(args[i].charAt(0) == '-') {
				char now_char = args[i].charAt(1);
				if(now_char == 'c'||now_char == 'w'||now_char == 'l'||now_char == 'a') {
					aBox.put(String.valueOf(now_char), "");
					flags = 1;
				}
				else if(now_char == 's'){
					aBox.put(String.valueOf(now_char), "");
				}
				else if(now_char == 'o'||now_char == 'e') {
					flags = 0;
//					if(args[i+1].charAt(0) == '-'||i+1 < args.length)    回去会亲自查看自定义异常类；	
					aBox.put(String.valueOf(now_char), args[i + 1]);
				}
			}
			else if(flags == 1) 
				readFoldName = new String(args[i]);
		}
	}
	
	public boolean isInBox(String key) {
		return aBox.containsKey(key);
	}
	
	public String getFileName() {
		return readFoldName;
	}
    void createFolder(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file:files) {
                if (file.isDirectory()) {
                    createFolder(file.getPath());
                }
                if (file.isFile()) {
                    String folderName = file.getPath();
                    
                }
            }
        }
    }
}
