package org.ligson.searchbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindThread {
	public static List<File> pathList = Collections.synchronizedList(new ArrayList<File>());
	public static final File searchToolRoot =  new File(System.getProperty("user.home") + "/.searchtool");
	
	
	public static void index() throws Exception{
		System.out.println(System.getProperty("java.io.tmpdir"));
		File[] roots = File.listRoots();
		List<Thread> threads = new ArrayList<>();
		for (File root : roots) {
			FindFile findFile = new FindFile(root);
			Thread thread = new Thread(findFile);
			thread.start();
			threads.add(thread);
		}

		for (Thread thread : threads) {
			thread.join();
		}

		//searchService.index(pathList);
	}
	
	public static void search(){
		

	}
	public static void main(String[] args) throws Exception{
		search();
		//index();
	}

}
