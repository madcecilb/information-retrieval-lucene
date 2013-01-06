import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
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
		
		String file = args.length > 0 ? args[0] : "file\\reut2-000.xml";
		ArrayList<Article> articles = XMLParser.getArticles(file);
	    Indexer indexer = new Indexer(articles);

	    // query
	    String querystr = args.length > 1 ? args[1] : "date:[19870301 TO 19870302] AND information retrieval";
	    
	    //Searches in all fields
	    Query textQuery = new MultiFieldQueryParser(
	    		indexer.getVersion(),
                new String[] {"text", "title", "date"},
                indexer.getAnalyzer()).parse(querystr);
	    
	    // search
	    int hitsPerPage = 10;
	    IndexReader reader = IndexReader.open(indexer.getIndexDirectory());
	    IndexSearcher searcher = new IndexSearcher(reader);
	    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
	    searcher.search(textQuery, collector);
	    ScoreDoc[] hits = collector.topDocs().scoreDocs;
	    
	    
	    //  display results
	    System.out.println("Found " + hits.length + " hits.");
	    for(int i=0;i<hits.length;++i) {
	      int docId = hits[i].doc;
	      Document d = searcher.doc(docId);
	      System.out.println((i + 1) + ".  ID-" + d.get("id") + "  Score-" + hits[i].score + "\t" + d.get("title"));
	    }

	    // reader can only be closed when there
	    // is no need to access the documents any more.
	    reader.close();
	    searcher.close();
	}

}
