package org.ligson.searchbox;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.queries.CustomScoreProvider;
import org.apache.lucene.queries.CustomScoreQuery;
import org.apache.lucene.search.Query;

import java.io.IOException;

public class MyCustomScoreQuery extends CustomScoreQuery {
	public MyCustomScoreQuery(Query subQuery) {
		super(subQuery);
	}

	@Override
	protected CustomScoreProvider getCustomScoreProvider(LeafReaderContext context) throws IOException {
		return new MyCustomScoreProvider(context);
	}

}
