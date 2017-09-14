//package personal.ws.util.mongo;
//
//import com.mongodb.client.model.Filters;
//import org.bson.conversions.Bson;
//
//import static com.mongodb.client.model.Filters.*;
//
///**
// * @Description:
// * @author: 王上
// * @date: 2017/8/17
// * @project：WSService
// */
//public class MongoUtil {
//
//    /*选择mongo操作*/
//    public static Bson methodChooser(String oper, String fieldName, String... filter) {
//        Bson bson;
//        switch (oper) {
//            case "=":
//                bson = eq(fieldName, filter[0]);
//                break;
//            case "!=":
//                bson = ne(fieldName, filter[0]);
//                break;
//            case ">":
//                bson = gt(fieldName, filter[0]);
//                break;
//            case "<":
//                bson = lt(fieldName, filter[0]);
//                break;
//            case ">=":
//                bson = gte(fieldName, filter[0]);
//                break;
//            case "<=":
//                bson = lte(fieldName, filter[0]);
//                break;
//            case "in":
//                bson = in(fieldName, filter);
//                break;
//            case "not in":
//                bson = Filters.nin(fieldName, filter);
//                break;
//            default:
//                bson = null;
//                break;
//        }
//        return bson;
//    }
//}
