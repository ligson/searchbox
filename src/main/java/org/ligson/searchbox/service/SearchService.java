package org.ligson.searchbox.service;


import org.ligson.searchbox.model.PageModel;

import java.io.File;
import java.util.List;

public interface SearchService {
	void index(List<File> dirs);

	PageModel<File> search(String key, int offset, int max);
}
