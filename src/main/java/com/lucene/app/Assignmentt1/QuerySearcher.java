package com.lucene.app.Assignmentt1;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
class QuerySearcher {

    private Analyzer analyzer = new StandardAnalyzer();
    private IndexSearcher searcher;
    private ArrayList<String> queries = CranParser.readQueries();
    private static final int NUM_RESULTS = 1000;
    private ArrayList<String> docs = new ArrayList<>();
    Analyzer getAnalyzer() {
        return analyzer;
    }

    void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    void readIndex() {
        try {
            DirectoryReader reader = DirectoryReader.open(FSDirectory.open(CranParser.INDEX_DIR));
            searcher = new IndexSearcher(reader);
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "Read index failed");
            System.exit(1);
        }
    }
    
    void getResults() throws IOException {
    	System.out.println("Running Queries...");
    	
    	for (int i = 0; i < queries.size(); i++) {
    		search(i+1, queries.get(i), NUM_RESULTS);
    	}
    	
    	try {
			FileWriter mywriter = new FileWriter("src/results.out");
			for(String result: docs) {
				mywriter.write(result);
			}
			mywriter.close();
			System.out.println("Query results written to file src/res/results.out");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    void search(int queryId, String queryStr, int numResults) {
        String fields[] = new String[] { DocumentModel.TITLE, DocumentModel.AUTHOR, DocumentModel.SOURCE, DocumentModel.CONTENT };
        QueryParser parser = new MultiFieldQueryParser(fields, analyzer);

        try {
            Query query = parser.parse(queryStr);
            ScoreDoc[] hits = searcher.search(query, numResults).scoreDocs;
            int rank=1;
            for (ScoreDoc hit: hits) {
                Document doc = searcher.doc(hit.doc);
                int id = Integer.parseInt(doc.get("id"));
                docs.add(queryId + " 0 " + id + " "+rank+ " "+hit.score + " EXP\n");
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "Can't parse query");
            System.exit(1);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
