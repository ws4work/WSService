package personal.ws.learning.thread;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCacheLearning {
    private static Map<String, Object> caches = new HashMap<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        rwl.readLock().lock();
        Object obj = null;
        obj = caches.get(key);
        try {
            if (null == obj) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (null == obj) {
                        obj = new String("Data");
                        System.out.println("从数据库读取数据");
                        caches.put(key,obj);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rwl.readLock().lock();
                    rwl.writeLock().unlock();
                }
            }else{
                System.out.println("从缓存中读取数据："+obj.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().lock();
        }
        return obj;
    }

    public static void main(String[] args) {
        ReadWriteLockCacheLearning r = new ReadWriteLockCacheLearning();
        Object data = r.getData("WS");
        Object data1 = r.getData("WS");
        System.out.println(data.toString());
        System.out.println(data1.toString());
    }
}
