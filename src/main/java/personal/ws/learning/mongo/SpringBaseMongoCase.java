//package personal.ws.learning.mongo;
//
//import org.apache.log4j.Logger;
//import org.apache.poi.ss.formula.functions.T;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @Description:
// * @author: 王上
// * @date: 2017/8/18
// * @project：WSService
// */
//public abstract class SpringBaseMongoCase {
//    private static Logger log = Logger.getLogger(SpringBaseMongoCase.class);
//
//    @Resource
//    private MongoTemplate mongoTemplate;
//
//    /**
//     * 保存或更新一个对象，
//     *
//     * @param t
//     *
//     * @return
//     */
//    public void save(T t) {
//        log.info("[Mongo Dao ]save:" + t);
//        this.mongoTemplate.save(t);
//    }
//
//    /**
//     * 保存一个对象
//     *
//     * @param t
//     */
//    public void insert(T t) {
//        log.info("[Mongo Dao ]insert:" + t);
//        this.mongoTemplate.insert(t);
//    }
//
//    /**
//     * 根据Id从Collection中查询对象
//     *
//     * @return
//     */
//    public T queryById(String id) {
//        Query query = new Query();
//        Criteria criteria = Criteria.where("_id").is(id);
//        query.addCriteria(criteria);
//        log.info("[Mongo Dao ]queryById:" + query);
//        return this.mongoTemplate.findOne(query, this.getEntityClass());
//    }
//
//    /**
//     * 根据条件查询集合
//     *
//     * @param query 查询条件
//     *
//     * @return 满足条件的集合
//     */
//    public List<T> queryList(Query query) {
//        log.info("[Mongo Dao ]queryList:" + query);
//        return this.mongoTemplate.find(query, this.getEntityClass());
//    }
//
//    /**
//     * 通过条件查询单个实体，有一个结果的话取第一个
//     *
//     * @param query
//     *
//     * @return
//     */
//    public T queryOne(Query query) {
//        log.info("[Mongo Dao ]queryOne:" + query);
//        return this.mongoTemplate.findOne(query, this.getEntityClass());
//    }
//
//    /**
//     * 通过条件进行分页查询
//     *
//     * @param query 查询条件
//     * @param start 查询起始值 ， 类似mysql查询中的 limit start, size 中的 start
//     * @param size  查询大小 ，类似mysql查询中的 limit start, size 中的 size
//     *
//     * @return 满足条件的集合
//     */
//    public List<T> getPage(Query query, int start, int size) {
//        query.skip(start);
//        query.limit(size);
//        log.info("[Mongo Dao ]queryPage:" + query + "(" + start + "," + size + ")");
//        List<T> lists = this.mongoTemplate.find(query, this.getEntityClass());
//        return lists;
//    }
//
//    /**
//     * 根据条件查询库中符合记录的总数,为分页查询服务
//     *
//     * @param query 查询条件
//     *
//     * @return 满足条件的记录总数
//     */
//    public Long getPageCount(Query query) {
//        log.info("[Mongo Dao ]queryPageCount:" + query);
//        return this.mongoTemplate.count(query, this.getEntityClass());
//    }
//
//    /**
//     * 根据Id删除用户
//     *
//     * @param id
//     */
//    public void deleteById(String id) {
//        Criteria criteria = Criteria.where("_id").in(id);
//        if(null != criteria){
//            Query query = new Query(criteria);
//            log.info("[Mongo Dao ]deleteById:" + query + ")");
//            if(null != query && this.queryOne(query) != null){
//                this.mongoTemplate.remove(query, this.getEntityClass());
//            }
//        }
//    }
//
//    /**
//     * 根据条件删除
//     *
//     * @param query
//     */
//    public void delete(Query query) {
//        log.info("[Mongo Dao ]delete:query(" + query + ")");
//        this.mongoTemplate.remove(query, this.getEntityClass());
//    }
//
//    /**
//     * 删除对象
//     *
//     * @param t
//     */
//    public void delete(T t) {
//        log.info("[Mongo Dao ]delete:" + t);
//        this.mongoTemplate.remove(t);
//    }
//
//    /**
//     * 更新满足条件的第一个记录
//     *
//     * @param query
//     * @param update
//     */
//    public void updateFirst(Query query, Update update) {
//        log.info("[Mongo Dao ]updateFirst:query(" + query + "),update(" + update + ")");
//        this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
//    }
//
//    /**
//     * 更新满足条件的所有记录
//     *
//     * @param query
//     * @param update
//     */
//    public void updateMulti(Query query, Update update) {
//        log.info("[Mongo Dao ]updateMulti:query(" + query + "),update(" + update + ")");
//        this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
//    }
//
//    /**
//     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
//     *
//     * @param query
//     * @param update
//     */
//    public void updateInser(Query query, Update update) {
//        log.info("[Mongo Dao ]updateInser:query(" + query + "),update(" + update + ")");
//        this.mongoTemplate.upsert(query, update, this.getEntityClass());
//    }
//
//    /**
//     * 钩子方法,由子类实现返回反射对象的类型
//     *
//     * @return
//     */
//    protected abstract Class<T> getEntityClass();
//}
