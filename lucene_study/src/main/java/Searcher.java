import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

/**
 * Created by hasee on 2017/5/12.
 */
public class Searcher {
    public static void main(String[] args) throws Exception{
        String indexDir = "I:/dataDir";
        String q = "xixi";

        search(indexDir, q);
    }

    public static void search(String indexDir, String q) throws Exception{
        Directory dir = FSDirectory.open(new File(indexDir));
        IndexSearcher searcher = new IndexSearcher(dir);
        //解析查询字符串
//        QueryParser parser = new QueryParser(Version.LUCENE_30,"content",new StandardAnalyzer(Version.LUCENE_30));
//        Query query = parser.parse(q);
        Query query = new TermQuery(new Term("content","123"));
        long start = System.currentTimeMillis();
        TopDocs hits = searcher.search(query, 10);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        //返回匹配的文本
        for (ScoreDoc scoreDoc: hits.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.get("fullPath"));
        }
        searcher.close();
    }
}


/*
*   Query有其他的子类
*   TermQuery
*   BooleanQuery
*   PhraseQuery
*   PrefixQuery
*   PhrasePrefixQuery
*   TermRangeQuery
*   NumericRangeQuery
*   FilteredQuery
*   SpanQuery
* */
