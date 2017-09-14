package personal.ws.learning.structure.bean;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/14
 * @project：WSService
 */
public class TestBean {
    private Long id;
    private String name;
    private boolean sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
