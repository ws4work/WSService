package personal.ws.myservice.phone_number.service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.ws.myservice.phone_number.bean.PhoneNumberBean;
import personal.ws.myservice.phone_number.dao.PhoneNumberDao;
import personal.ws.util.FindPhoneNumberUtil;

import java.io.IOException;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/13
 * @project：LemengProject
 */
@Service
@Transactional
public class PhoneNumberService {

    @Autowired
    private PhoneNumberDao newDao;

    public boolean findAndSave(String phoneNumber){
        JSONObject info = null;
        try {
            info = FindPhoneNumberUtil.findPhoneInfo(phoneNumber);
        } catch (IOException e) {
            System.out.println("----查询号码(新)异常----");
            return false;
        }
        String corp = info.optString("Corp");
        String isp = "";
        if(corp.contains("移动")){
            isp="10";
        }else if(corp.contains("联通")){
            isp="20";
        }else if(corp.contains("电信")){
            isp="30";
        }
        String city = info.optString("City");
        String city_code = findProvinceCode(city);
        PhoneNumberBean bean = new PhoneNumberBean();
        bean.setPhoneNumber(phoneNumber.substring(0,7));
        bean.setType(isp);
        bean.setProvinceCode(city_code);
        try {
            save(bean);
        } catch (Exception e) {
            System.out.println("----新号码存储失败!!!");
            return false;
        }
        return true;
    }

    //查询当前写到旧表的多少条数据了
//    public Long getCurrentId(){
//        String sql = "select count(1) from recharge_province_mobile_new";
//        return jdbc.queryForLong(sql);
//    }

    //查询地区编码
    public String findProvinceCode(String name){
        String code = newDao.findProvinceCode(name);
        return code;
    }

    public void save(PhoneNumberBean bean){
        newDao.save(bean);
    }

}
