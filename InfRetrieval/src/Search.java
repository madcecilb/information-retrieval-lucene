import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;


public class Search {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
			    
	    ArrayList<Article> articles = XMLParser.getArticles("d:\\reut2-000.xml");
	    Indexer indexer = new Indexer(articles);

	    // query
	    //System.out.print("Enter query:");
	    String querystr = args.length > 0 ? args[0] : "information retrieval";
	    //String querystr = "information retrieval";

	    // the "title" arg specifies the default field to use
	    // when no field is explicitly specified in the query.
	    Query q = new QueryParser(indexer.getVersion(), "text", indexer.getAnalyzer()).parse(querystr);

	    // search
	    int hitsPerPage = 10;
	    IndexReader reader = IndexReader.open(indexer.getIndexDirectory());
	    IndexSearcher searcher = new IndexSearcher(reader);
	    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
	    searcher.search(q, collector);
	    ScoreDoc[] hits = collector.topDocs().scoreDocs;
	    
	    //  display results
	    System.out.println("Found " + hits.length + " hits.");
	    for(int i=0;i<hits.length;++i) {
	      int docId = hits[i].doc;
	      Document d = searcher.doc(docId);
	      System.out.println((i + 1) + ". " + d.get("id") + "\t" + d.get("title"));
	    }

	    // reader can only be closed when there
	    // is no need to access the documents any more.
	    reader.close();
	    searcher.close();
	}

}