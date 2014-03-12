package examples._1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 
 * @author Administrator
 *
 */
public class LuceneTest {
	
	//�ִ���
	private Analyzer analyzer = null;
	
	private Directory directory = null;
	
	public  LuceneTest(){
		analyzer = new IKAnalyzer(true);
		
		//����һ������Ŀ¼
		File file = new File("indexs");
		
		
		
		try {
			directory = FSDirectory.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws IOException, ParseException, InvalidTokenOffsetsException {
//		new LuceneTest().testCreateIndex();
		new LuceneTest().testSearchBook();
	}
	
	public void testCreateIndex() throws IOException {
        
        //����һ��IndexWriter����,ָ��ƥ��İ汾,�Լ��ִ���
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_36,analyzer);
         
        //����IndexWriter,�����������Ĵ�����ά��
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
         
        //��ȡͼ����Ϣ
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Java���˼��");
        book1.setAuthor("Bruce Eckel");
        book1.setContent("Thinking in Java should be read cover to cover by every Java programmer, then kept close at hand for frequent reference.");
         
        Book book2 = new Book();
        book2.setId(2);
        book2.setName("����������֮��");
        book2.setAuthor("����ɽ��");
        book2.setContent("������������֮���������һ�����ڽ�����ơ������͹滮���µ����ۡ�˼�룬�����۵ĺ���������Ա���������Լ��Ĵ���״̬�趨�������������������һ���Ϸ�ʽ�Ӹ����Ϲ������µĺ�ҵʱ�������Ļ�������Щ���������Ǵ��졣");
         
        //����Document
        Document doc1 = new Document();
         
        //Storeָ��Field�Ƿ���Ҫ�洢,Indexָ��Field�Ƿ���Ҫ�ִ�����
        doc1.add(new Field("id",book1.getId().toString(),Store.YES,Index.NOT_ANALYZED));
        doc1.add(new Field("title",book1.getName(),Store.YES,Index.ANALYZED));
        doc1.add(new Field("author",book1.getAuthor(),Store.YES,Index.ANALYZED));
        doc1.add(new Field("content",book1.getContent(),Store.YES,Index.ANALYZED));
         
        //����Document
        Document doc2 = new Document();
         
        //Storeָ��Field�Ƿ���Ҫ�洢,Indexָ��Field�Ƿ���Ҫ�ִ�����
        doc2.add(new Field("id",book2.getId().toString(),Store.YES,Index.NOT_ANALYZED));
        doc2.add(new Field("title",book2.getName(),Store.YES,Index.ANALYZED));
        doc2.add(new Field("author",book2.getAuthor(),Store.YES,Index.ANALYZED));
        doc2.add(new Field("content",book2.getContent(),Store.YES,Index.ANALYZED));
         
        //��Document���뵽������
        indexWriter.addDocument(doc1);
        indexWriter.addDocument(doc2);
         
        //�ύ�ı䵽����,Ȼ��ر�
        indexWriter.close();
    }
	
	 /**
     * ����ͼ��
     * @throws ParseException 
     * @throws IOException 
     * @throws CorruptIndexException 
     * @throws InvalidTokenOffsetsException 
     */
    public void testSearchBook() throws ParseException, CorruptIndexException, IOException, InvalidTokenOffsetsException {
        //�����Ĺؼ���
        String queryKeyWord = "˼��";
         
        //������ѯ������,�Ѳ�ѯ�ؼ���ת��Ϊ��ѯ����Query(����Field������)
        //QueryParser queryParser = new QueryParser(Version.LUCENE_36,"author",analyzer);//�����ߵ�����������
         
         
        String[] fields = {"title","content"};
        //(�ڶ��Filed������)
        QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,fields,analyzer);
        Query query = queryParser.parse(queryKeyWord);
         
        //��ȡ���������Ľӿ�,��������
        IndexReader indexReader  = IndexReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
         
        //TopDocs �������صĽ��
        TopDocs topDocs = indexSearcher.search(query, 100);//ֻ����ǰ100����¼
         
        int totalCount = topDocs.totalHits; // �������������
        System.out.println("�������Ľ��������Ϊ��" + totalCount);
         
        ScoreDoc[] scoreDocs = topDocs.scoreDocs; // �����Ľ���б�
         
        //����������,ʹ�����Ĺؼ���ͻ����ʾ
//        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
//        Scorer fragmentScore = new QueryScorer(query);
//        Highlighter highlighter = new Highlighter(formatter,fragmentScore);
//        Fragmenter fragmenter = new SimpleFragmenter(100);
//        highlighter.setTextFragmenter(fragmenter);
         
        List<Book> books = new ArrayList<Book>();
        //���������ȡ�����뵽������
        for(ScoreDoc scoreDoc : scoreDocs) {
            int docID = scoreDoc.doc;//��ǰ������ĵ����
            float score = scoreDoc.score;//��ǰ�������ضȵ÷�
            System.out.println("score is : "+score);
             
            Document document = indexSearcher.doc(docID);
            Book book = new Book();
            book.setId(Integer.parseInt(document.get("id")));
             
            //������ʾtitle
            String title =  document.get("title");
            String highlighterTitle = null;//highlighter.getBestFragment(analyzer, "title", title);
             
            //���title��û���ҵ��ؼ���
            if(highlighterTitle == null) {
                highlighterTitle = title;
            }
            book.setName(highlighterTitle);
             
            book.setAuthor(document.get("author"));
             
            //������ʾcontent
            String content =  document.get("content");
            String highlighterContent = null;//highlighter.getBestFragment(analyzer, "content", content);
             
            //���content��û���ҵ��ؼ���
            if(highlighterContent == null) {
                highlighterContent = content;
            }
            book.setContent(highlighterContent);
             
            books.add(book);
        }
        //�ر�
        indexReader.close();
        indexSearcher.close();
        for(Book book : books) {
            System.out.println("book'id is : "+book.getId());
            System.out.println("book'title is : "+book.getName());
            System.out.println("book'author is : "+book.getAuthor());
            System.out.println("book'content is : "+book.getContent());
        }
    }
	
}
