package personal.ws.learning.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/27
 * @project：dss-platform
 */
public abstract class AbstractCommonMongoTemplate implements ApplicationContextAware {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param obj
     */
    public void insert(Object obj) {
        mongoTemplate.insert(obj);
    }

    /**
     * 批量新增
     * <br>------------------------------<br>
     *
     * @param objs
     */
    public void insertAll(List<Object> objs) {
        mongoTemplate.insertAll(objs);
    }

    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     *
     * @param id
     */
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        Criteria criteria = Criteria.where("id").gt(id);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, getEntityClass());
    }

    /**
     * 按条件删除
     * <br>------------------------------<br>
     *
     * @param obj
     */
    public void delete(Object obj) {
        Query query = getQuery(obj);
        mongoTemplate.remove(query, getEntityClass());
    }

    public void delete(Map<String, Object> conditions) {
        Query query = new Query();
        for (Map.Entry<String, Object> entry : conditions.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).gt(entry.getValue()));
        }
        mongoTemplate.remove(query, getEntityClass());
    }

    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        // TODO Auto-generated method stub
        mongoTemplate.dropCollection(getEntityClass());
    }


    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     *
     * @param obj
     */
    public void updateById(String id, Object obj) {
        // TODO Auto-generated method stub
        Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        Update update = getUpdate(obj);
        mongoTemplate.updateFirst(query, update, getEntityClass());
    }

    /**
     * 修改多条
     * <br>------------------------------<br>
     *
     * @param conditions
     * @param obj
     */
    public void update(Object conditions, Object obj) {
        // TODO Auto-generated method stub
        Query query = getQuery(conditions);
        Update update = getUpdate(obj);
        mongoTemplate.updateMulti(query, update, getEntityClass());
    }

    /**
     * 根据主键查询
     * <br>------------------------------<br>
     *
     * @param id
     *
     * @return
     */
    public Object findById(String id) {
        return mongoTemplate.findById(id, getEntityClass());
    }

    /**
     * 查询全部
     * <br>------------------------------<br>
     *
     * @return
     */
    public List<Object> findAll() {
        return mongoTemplate.findAll(getEntityClass());
    }

    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     *
     * @param obj
     * @param skip
     * @param limit
     *
     * @return
     */
    public List<Object> findByPage(Object obj, int skip, int limit) {
        Query query = getQuery(obj);
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, getEntityClass());
    }

    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     *
     * @param obj
     *
     * @return
     */
    public Object findAndRemove(Object obj) {
        // TODO Auto-generated method stub
        Query query = getQuery(obj);
        return mongoTemplate.findAndRemove(query, getEntityClass());
    }


    /**
     * count
     * <br>------------------------------<br>
     *
     * @param obj
     *
     * @return
     */
    public long count(Object obj) {
        // TODO Auto-generated method stub
        Query query = getQuery(obj);
        return mongoTemplate.count(query, getEntityClass());
    }

    //查询条件
    protected abstract Query getQuery(Object obj);

    //更新
    protected abstract Update getUpdate(Object obj);

    //获取封装数据实体类
    protected abstract Class getEntityClass();
}
