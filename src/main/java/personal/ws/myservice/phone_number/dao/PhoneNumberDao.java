package personal.ws.myservice.phone_number.dao;


import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.ws.myservice.phone_number.bean.PhoneNumberBean;

@Repository
@Transactional
public interface PhoneNumberDao extends JpaRepository<PhoneNumberBean, Long> {

    @Query("select code from recharge_province_city where name like %?1")
    String findProvinceCode(String name);
}
