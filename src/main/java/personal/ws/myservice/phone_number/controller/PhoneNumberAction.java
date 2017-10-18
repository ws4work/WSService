package personal.ws.myservice.phone_number.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.ws.myservice.phone_number.service.PhoneNumberService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/13
 * @project：LemengProject
 */
@Scope("prototype")
@Controller
public class PhoneNumberAction {
    protected Logger logger = LoggerFactory.getLogger(PhoneNumberAction.class);
    @Autowired
    private PhoneNumberService service;

    @RequestMapping("pushPhoneNumber")
    protected String begin() throws Exception {
        final Random random = new Random();
        final String[] starts = {"3", "4", "5", "6", "7", "8", "9"};

        final Thread thread = new Thread(() -> {
            int startIndex = 0;
            int startInt = 0;
            int middleInt = 0;
            while (true) {
                String start = "1" + starts[startIndex] + startInt;
                String middle = matchNumber(middleInt, 4);
                String end = "" + random.nextInt(9) + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
                String fadePhoneNumber = start + middle + end;
                boolean result = service.findAndSave(fadePhoneNumber);
                if(middleInt >= 9999 && startInt >= 9){
                    middleInt = 0;
                    startInt = 0;
                    startIndex++;
                } else if(middleInt >= 9999 && startInt < 9){
                    middleInt = 0;
                    startInt++;
                } else if(middleInt < 9999 && startInt < 9){
                    middleInt++;
                } else {
                    logger.info("???不会出现这种情况???");
                }
                if(result){
                    logger.info("------查询号码:" + fadePhoneNumber + "成功");
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e) {
                        logger.info("------查询号码:" + fadePhoneNumber + "休息失败");
                    }
                } else {
                    logger.info("------查询号码:" + fadePhoneNumber + "失败");
                    try {
                        TimeUnit.MINUTES.sleep(1L);
                    } catch (InterruptedException e) {
                        logger.info("------查询号码:" + fadePhoneNumber + "失败休息失败");
                    }
                }
            }
        });
        thread.run();
        return null;
    }

    public String matchNumber(int number, int num) {
        StringBuffer sb = new StringBuffer();
        String str = String.valueOf(number);
        int length = str.length();
        if(length <= num){
            for (int i = 0; i < num - length; i++) {
                sb.append(0);
            }
            sb.append(str);
        } else {
            sb.substring(sb.length() - 1 - num, sb.length() - 1);
        }
        return sb.toString();
    }
}
