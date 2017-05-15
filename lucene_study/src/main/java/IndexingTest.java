import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * Created by hasee on 2017/5/14.
 */
public class IndexingTest {

    protected String[] ids = {"1","2"};
    protected String[] names = {"zhangsan","lisi"};
    protected String[] ages = {"12","21"};

    private Directory directory;
    private IndexWriter writer;
    //构建索引
    private void setUp() throws Exception{
        directory = new RAMDirectory();
        writer = getIndexWriter();
        for (int i = 0; i<ids.length; i++) {
            Document document = new Document();
            document.add(new Field("id",ids[i],Field.Store.YES,Field.Index.NOT_ANALYZED));
            document.add(new Field("name",names[i],Field.Store.YES,Field.Index.ANALYZED));
            document.add(new Field("age",ages[i],Field.Store.YES,Field.Index.ANALYZED));
            writer.addDocument(document);
        }
        writer.close();
    }

    private IndexWriter getIndexWriter() throws Exception{
        writer = new IndexWriter(directory, new WhitespaceAnalyzer(),IndexWriter.MaxFieldLength.UNLIMITED);
        return writer;
    }

    public static void main(String[] args) throws Exception{
        IndexingTest test = new IndexingTest();
        test.setUp();
//        test.testIndexWriter();
//        test.getHitCount("id","1");
        test.testDeleteBeforeOptimize();
    }

    public void testIndexWriter() throws Exception{
        IndexWriter writer = getIndexWriter();
        System.out.println(writer.numDocs());
    }

    public  int getHitCount(String fileName, String searchString) throws Exception{
        IndexSearcher searcher = new IndexSearcher(directory);
        Query query = new TermQuery(new Term(fileName,searchString));
        int hitCount = searcher.search(query,10).totalHits;
        searcher.close();
        System.out.println(hitCount);
        return hitCount;
    }

    public void testDeleteBeforeOptimize() throws Exception{
        IndexWriter writer = getIndexWriter();
        System.out.println(writer.numDocs());
        writer.deleteDocuments(new Term("id","1"));
        System.out.println(writer.numDocs());
        writer.optimize();
        //当没有commit的时候 并不会删除
        writer.commit();
        System.out.println(writer.hasDeletions());
        System.out.println(writer.maxDoc());
        System.out.println(writer.numDocs());
    }

}
