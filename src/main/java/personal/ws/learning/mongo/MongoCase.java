//package personal.ws.learning.mongo;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.*;
//import com.mongodb.client.model.Filters;
//import org.bson.BasicBSONObject;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import personal.ws.util.mongo.MongoUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.mongodb.client.model.Filters.*;
//
///**
// * @Description:
// * @author: 王上
// * @date: 2017/8/17
// * @project：WSService
// */
//public class MongoCase {
//
//    public static MongoClient mongoClient;
//    public static MongoDatabase mongoDatabase;
//
//    //MongoIP
//    public static final String MONGO_IP = "192.168.1.224";
//    //MongoPort
//    public static final int MONGO_PORT = 27017;
//    //MongoDBName
//    public static final String MONGO_DBNAME = "ws";
//
//    public static void main(String args[]) {
//        MongoCase mc = new MongoCase();
//        mc.connectDB(MONGO_DBNAME);
//        MongoCollection<Document> collection = mc.getCollection(MONGO_DBNAME);
//        if(null == collection)
//            mc.createCollection(mongoDatabase);
//        mc.getAllCollections();
//        MongoCollection<Document> test = mc.getCollection(MONGO_DBNAME);
//        mockData(test);
//        BasicBSONObject bbso = new BasicBSONObject();
//        bbso.append("test", "4");
//
//        FindIterable<Document> eq = test.find(MongoUtil.methodChooser("=", "_id", "5995415beb798c90fc26fdb6"));
//
////        eq.map();
//
//        //检索所有文档
//        /**
//         * 1. 获取迭代器FindIterable<Document>
//         * 2. 获取游标MongoCursor<Document>
//         * 3. 通过游标遍历检索出的文档集合
//         * */
//        FindIterable<Document> findIterable = test.find();
//        MongoCursor<Document> mongoCursor = findIterable.iterator();
//        while (mongoCursor.hasNext()) {
//            System.out.println(mongoCursor.next());
//        }
//    }
//
//    private static void mockData(MongoCollection<Document> test) {
//        Document document1 = new Document();
//        document1.append("test", "3");
//        test.insertOne(document1);
//        Document document2 = new Document();
//        document2.append("test", "4");
//        test.insertOne(document2);
//        System.out.println(test.count());
//    }
//
//    /**
//     * 链接数据库
//     */
//    public void connectDB(String dbName) {
//        ServerAddress serverAddress = new ServerAddress(MONGO_IP, MONGO_PORT);
//        List<ServerAddress> addrs = new ArrayList<>();
//        addrs.add(serverAddress);
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//        //MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
//        List<MongoCredential> credentials = new ArrayList<>();
//        //credentials.add(credential);
//        //通过连接认证获取MongoDB连接
//        mongoClient = new MongoClient(addrs, credentials);
//        //连接到数据库
//        mongoDatabase = mongoClient.getDatabase(dbName);
//    }
//
//    /**
//     * 获取集合
//     */
//    private MongoCollection<Document> getCollection(String colName) {
//        return mongoDatabase.getCollection(colName);
//    }
//
//    /**
//     * 获取全部的集合名称
//     */
//    private MongoIterable getAllCollections() {
//        return mongoDatabase.listCollectionNames();
//    }
//
//    /**
//     * 创建集合
//     */
//    private void createCollection(MongoDatabase mongoDatabase) {
//        mongoDatabase.createCollection("test");
//    }
//
//
//
//
//}
