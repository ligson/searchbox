package org.ligson.searchbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FindFile implements Runnable {
	

	private File root;
	private static Logger logger = LoggerFactory.getLogger(FindFile.class);

	public FindFile(File root) {
		super();
		this.root = root;
	}
	


	public void find(File root) {
		if (root.getName().startsWith(".")) {
			return;
		}
		File tmpDir = new File(System.getProperty("java.io.tmpdir"));
		if (tmpDir.equals(root.getAbsolutePath())) {
			return;
		}
		FindThread.pathList.add(root);
		logger.debug(root.getAbsolutePath());
		if (root.isDirectory()) {
			File[] files = root.listFiles();
			if (files != null) {
				for (File file : files) {
					find(file);
				}
			}
		}

	}

	@Override
	public void run() {
		find(this.root);
	}

}
