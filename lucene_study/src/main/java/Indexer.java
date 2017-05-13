import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;


/**
 * Created by hasee on 2017/5/12.
 */
public class Indexer {
    private IndexWriter writer;
    public static void main(String[] args) throws Exception{
        String indexDir = "I:/dataDir";
        String dataDir = "I:/";

        long start = System.currentTimeMillis();

        Indexer indexer = new Indexer(indexDir);
        int numIndexed;

        try {
            numIndexed = indexer.index(dataDir,new TextFileFilter());
        } finally {
            indexer.close();
        }

        long end = System.currentTimeMillis();

        System.out.println(end-start);
    }

    //关闭 IndexWriter
    public void close() throws Exception{
        writer.close();
    }

    //初始化，创建Directory 并创建一个writer
    public Indexer(String indexDir) throws Exception{
        //创建文档
        Directory dir = FSDirectory.open(new File(indexDir));
        // 创建 writer
        writer = new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true,IndexWriter.MaxFieldLength.UNLIMITED);
    }

    //遍历可阅读的文件并建立索引
    public int index(String dataDir, FileFilter filter) throws Exception{
        File[] files = new File(dataDir).listFiles();
        for (File file : files) {
            if(!file.isDirectory()&&
                    !file.isHidden()&&
                    file.exists()&& file.canRead()&&
                    (filter==null||filter.accept(file))){
                indexFile(file);
            }
        }
        //返回被索引文档数量
        return writer.numDocs();
    }

    // 给每个文件建立文档
    public Document getDocument(File file) throws Exception{
        Document doc = new Document();
        doc.add(new Field("content",new FileReader(file)));
        doc.add(new Field("fileName",file.getName(),Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("fullPath",file.getCanonicalPath(),Field.Store.YES, Field.Index.NOT_ANALYZED));
        return doc;
    }

    //将建立好的文档加入到Directory中
    public void indexFile(File f) throws Exception{
        System.out.println("开始为文件建立索引  :"+f.getCanonicalPath());
        Document doc = getDocument(f);
        writer.addDocument(doc);
    }

    private static class TextFileFilter implements FileFilter{
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().toLowerCase().endsWith(".txt");
        }
    }
}
