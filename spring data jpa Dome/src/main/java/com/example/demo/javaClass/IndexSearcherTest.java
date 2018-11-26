package com.example.demo.javaClass;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
public class IndexSearcherTest {
    //搜索索引
    public static  void testSearcher() throws Exception{
        Logger logger = LoggerFactory.getLogger(IndexSearcherTest.class);
        File file = new File("D:\\ceshi\\index");
        //        Directory directory =  new RAMDirectory();
        //创建一个Directory对象；也就是索引库存放的位置
        Directory directory = FSDirectory.open(file.toPath());
        //提交索引，不提交会出现找不到索引的bug
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory,config);
        //创建索引，在commit前面，打开indexReader时才会有对应的索引存储存在。
        setIndex(writer);
        writer.commit();
        //创建一个IndexReader 和directory结合，用于创建IndexSearcher
        IndexReader indexReader = DirectoryReader.open(directory);
        //创建IndexSearcher,指定查询区域和查询的关键字
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Query query = new TermQuery(new Term("fileName","a.txt"));
        //执行查询
        TopDocs topDocs =indexSearcher.search(query,1);
        //返回结果。
        ScoreDoc[] scoreDocs =topDocs.scoreDocs;
        for(ScoreDoc scoreDoc :scoreDocs){
            int doc =scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            String fileName = document.get("fileName");
            System.out.println(fileName);
            logger.info("查询出的文件名称："+fileName);
            String fileContent =document.get("fileContent");
            System.out.println(fileContent);
            logger.info("查询出的文件内容："+fileContent);
            String fileSize =document.get("fileSize");
            logger.info("查询出的文件大小："+fileSize);
            System.out.println(fileSize);
            String filePath =document.get("filePath");
            System.out.println(filePath);
            logger.info("查询出的文件路径："+filePath);
        }
        indexReader.close();
        writer.close();
    }
    public  void indexReaderTest() throws Exception{
//         String INDEX_STORE_PATH = "D:\\ceshi\\index2";
//        IndexWriter writer = null;
//        IndexReader reader =  null;
//        Analyzer a = new StandardAnalyzer();
//        IndexWriterConfig config = new IndexWriterConfig(a);
//        Directory directory = FSDirectory.open(new File(INDEX_STORE_PATH).toPath());
//        IndexWriter  writer = new IndexWriter(directory,config);
//        Document doc1 = new Document();
//        Document doc2 = new Document();
//        Document doc3 = new Document();
//        Field f1 = new Field("bookname", "钢铁是怎样炼成的", new FieldType());
//        Field f2 = new Field("bookname", "英雄儿女",  new FieldType());
//        Field f3 = new Field("bookname", "篱笆女人和狗",  new FieldType());
//        doc1.add(f1);
//        doc2.add(f2);
//        doc3.add(f3);
//        writer.addDocument(doc1);
//        writer.addDocument(doc2);
//        writer.addDocument(doc3);
//        writer.commit();
//        writer.close();
//
//    //使用IndexReader读取索引
//        reader = MultiReader.open(INDEX_STORE_PATH);
//
//        System.out.println("索引文档列表 : ");
//        for(int i = 0; i < reader.numDocs(); i++){
//            System.out.println(reader.document(i));
//        }
//
////输出当前索引的版本信息
//        System.out.println("索引版本： " + reader.getVersion());
//
////输出当前的索引文件的数量
//        System.out.println("索引的文本数量： "  + reader.numDocs());
//
////构造一个词条并在索引中查找
//        System.out.println("============================");
//        System.out.println("查找词条女---------->开始查找");
//        Term term1 = new Term("bookname", "女");
//        TermDocs docs = reader.termDocs(term1);
//        while(docs.next()){
//            System.out.println("---------查找中------------");
//            System.out.println("含有查找的<"+ term1 +">的Document的编号为" + docs.doc());
//            System.out.println("Term在文档中出现的次数" + docs.freq());
//            System.out.println("----------------------------");
//        }
//        reader.close();
    }
    private static void setIndex(IndexWriter  writer)throws Exception{
        //循环文件创索引
        File file = new File("D:\\ceshi\\test");
        File [] files = file.listFiles();
        for(File file1 :files){
            Document doc1 = new Document();
//            Document doc2 = new Document();
//            Document doc3 = new Document();
//            Document doc4 = new Document();
//        Field f1 = new Field("bookname", "钢铁是怎样炼成的", new FieldType());
//        Field f2 = new Field("bookname", "英雄儿女",  new FieldType());
//        Field f3 = new Field("bookname", "篱笆女人和狗", new FieldType());
            String file_name = file1.getName();
            long file_size = FileUtils.sizeOf(file1);
            String file_path = file1.getPath();
            String file_content =FileUtils.readFileToString(file1,"UTF-8");
            doc1.add(new TextField("fileName", file_name, Field.Store.YES));
            doc1.add(new LongField("fileSize", file_size, Field.Store.YES));
            doc1.add(new TextField("fileContent", file_content, Field.Store.YES));
            doc1.add(new StoredField("filePath", file_path));
//            writer.addDocument(doc1);
//            writer.addDocument(doc1);
//            writer.addDocument(doc1);
            writer.addDocument(doc1);
        }
    }
    public static void main(String [] args){
        try {
            testSearcher();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
