package personal.ws.Demo.mongo;

import com.mongodb.BasicDBObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.ws.learning.mongo.AbstractCommonMongoTemplate;
import personal.ws.myservice.WSServiceApplication;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/11
 * @project：WSService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WSServiceApplication.class)
public class MongoTest extends AbstractCommonMongoTemplate {

    @Before
    public void createTestDatas() {
        Random random = new Random();
        for (int i = 1; i < 100; i++) {
            MongoTestBean bean = new MongoTestBean();
            bean.setName("Test" + System.currentTimeMillis());
            bean.setBirthday(new Date());
            bean.setMale(random.nextBoolean());
            bean.setStudent(random.nextBoolean());
            bean.setSalary((double) random.nextInt(100));
            mongoTemplate.insert(bean);
        }
    }

    //分组查询
    @Test
    public void groupByTest() {
        Aggregation agg = Aggregation.newAggregation(
                //match查询条件
                Aggregation.match(Criteria.where("salary").gt(50)),
                //group分组条件
                Aggregation.group("isMale", "isStudent").count().as("count"),
                //project显示参数
                Aggregation.project("count").and("result").previousOperation()
        );
        AggregationResults<BasicDBObject> aggregate = mongoTemplate.aggregate(agg, MongoTestBean.class, BasicDBObject.class);
        List<BasicDBObject> results = aggregate.getMappedResults();
        System.out.println(results.toString());
    }

    //区间查询
    @Test
    public void bucketTest() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.bucket("salary").withBoundaries(50, 75, 100).withDefaultBucket("lowbi")
                        .andOutput("count").sum(1).as("count")
                        //.andOutput("name").as("title")
                        //.andOutput("isStudent").as("student")
        );
        AggregationResults<BasicDBObject> aggregate = mongoTemplate.aggregate(agg, MongoTestBean.class, BasicDBObject.class);
        List<BasicDBObject> results = aggregate.getMappedResults();
        System.out.println(results.toString());
    }



//    @After
//    public void destroyDatas(){
//        mongoTemplate.dropCollection(MongoTestBean.class);
//    }

    @Override
    protected Query getQuery(Object obj) {
        return null;
    }

    @Override
    protected Update getUpdate(Object obj) {
        return null;
    }

    @Override
    protected Class getEntityClass() {
        return MongoTestBean.class;
    }
}
