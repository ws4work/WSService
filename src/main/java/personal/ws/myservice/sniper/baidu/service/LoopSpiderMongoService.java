//package personal.ws.myservice.sniper.baidu.service;
//
//import com.mongodb.*;
//import com.mongodb.util.JSON;
//import net.sf.json.JSONObject;
//import personal.ws.myservice.sniper.baidu.config.Configuration;
//
//public class LoopSpiderMongoService {
//
//	public static Mongo mg	= null;
//	public static DB db	= null;
//
//	/**
//	 * 创建数据库连接
//	 */
//	public void createDB() {
//		try {
//			mg = new Mongo(Configuration.MONGO_IP, Configuration.MONGO_PORT);
//			db = mg.getDB("baidu");
//		} catch (MongoException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 存储到DB
//	 */
//	public void toMongoSave(String collection_name, String url, String title, String floor, String author, String textType, String text) {
//		//获取集合
//		DBCollection collection = db.getCollection(collection_name);
//		DBCursor cur = collection.find();
//		//数据插入
//		JSONObject json = new JSONObject();
//		json.put("id", String.valueOf(cur.count()));
//		json.put("src", url);
//		json.put("title", title);
//		json.put("floor", floor);
//		json.put("author", author);
//		json.put("textType", textType);
//		json.put(textType, text);
//		String string = json.toString();
//		DBObject chemistry = (DBObject) JSON.parse(string);
//		collection.save(chemistry);
//	}
//
//	/**
//	 * 关闭数据库服务
//	 */
//	public void closeDB() {
//		if (mg != null) {
//			mg.close();
//		}
//		db = null;
//	}
//}
