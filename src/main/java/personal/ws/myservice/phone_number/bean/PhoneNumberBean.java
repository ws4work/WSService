package personal.ws.myservice.phone_number.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/13
 * @project：LemengProject
 */
@Entity
@Table(catalog = "lmrecharge", name = "recharge_province_mobile")
public class PhoneNumberBean implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String provinceCode;                                //地区编码
    private String type;                                        //运营商
    private String phoneNumber;                                //手机号段

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
