package org.ligson.searchbox;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.SortedDocValues;
import org.apache.lucene.queries.CustomScoreProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyCustomScoreProvider extends CustomScoreProvider {
	private static Logger logger = LoggerFactory.getLogger(MyCustomScoreProvider.class);
	private static boolean enabled = false;
	private static Map<String, Float> suffixScoreMap = new HashMap<>();
	static {
		suffixScoreMap.put("exe", 9f);
		suffixScoreMap.put("cmd", 8f);
		suffixScoreMap.put("bat", 7f);
		suffixScoreMap.put("msc", 6f);
	}

	private SortedDocValues pathValues;

	public MyCustomScoreProvider(LeafReaderContext context) {
		super(context);
		try {
			pathValues = context.reader().getSortedDocValues("path");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * @param doc
	 *            文档id
	 * @param subQueryScore
	 *            指的是普通Query查询的评分
	 * @param valSrcScore
	 *            指的是FunctionQuery查询的评分
	 */
	@Override
	public float customScore(int doc, float subQueryScore, float valSrcScore) throws IOException {
		float boost = subQueryScore * valSrcScore;
		if (!enabled) {
			return boost;
		}
		if ((pathValues == null) || (pathValues.get(doc) == null)) {
			return boost;
		}

		File file = new File(pathValues.get(doc).utf8ToString());
		if (file.isFile()) {
			// boost += 10f;
			int idx = file.getName().lastIndexOf('.');
			if (idx > 0) {
				String suffix = file.getName().substring(idx + 1);
				Float score = suffixScoreMap.get(suffix);
				if (score != null) {
					boost += score;
					logger.debug("后缀:" + suffix + "(" + file.getAbsolutePath() + ")评分" + boost);
				}
			}
		}
		return boost;

	}

}
