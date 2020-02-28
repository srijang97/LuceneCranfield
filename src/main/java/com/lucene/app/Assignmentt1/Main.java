package com.lucene.app.Assignmentt1;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.similarities.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        runModel(new MyAnalyzer(), new BM25Similarity());
    }

    private static void runModel(Analyzer analyzer, Similarity similarity) {
        Indexer indexer = new Indexer();
        indexer.setAnalyzer(analyzer);
        indexer.setSimilarity(similarity);
        indexer.createIndex();
        QuerySearcher searcher = new QuerySearcher();
        searcher.setAnalyzer(analyzer);
        searcher.readIndex();
        
        try {
			searcher.getResults();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
