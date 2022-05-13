package com.example.ers;

import com.example.ers.biz.IExperimentBiz;
import com.example.ers.biz.INoticeBiz;
import com.example.ers.biz.IUserBiz;
import com.example.ers.biz.impl.NoticeBizImpl;
import com.example.ers.entity.Experiment;
import com.example.ers.entity.Notice;
import com.example.ers.utils.Util;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class test {
    //依赖业务层对象
    @Autowired
    INoticeBiz noticeBiz;
    @Autowired
    IExperimentBiz experimentBiz;
    @Autowired
    IUserBiz userBiz;

    @Test
    void getFiveNews() {
        List<Experiment> experimentsList = experimentBiz.getFiveExperiments();
        for (int i = 0; i < experimentsList.size(); i++) {
            System.out.println("=====================================================================================");
            System.out.println(experimentsList.get(i).toString());
        }
    }


    @Test
    void testPattern() {
        String phone = "12123424263";
        System.out.println(Util.isMobilePhone(phone));
        String email = "1s-UYU_48dsf3@qq.com";
        System.out.println(Util.isEmail(email));
        String password = "@#$%^&*!_qQ123-";
        System.out.println(Util.isVerifyPassword(password));
        for (int i = 0; i < 10; i++) {
            String s = Util.createCode(6);
            System.out.println(s);
        }
    }


    @Test
    void sendEmail() {
        userBiz.sendEmail("952158354@qq.com", "123666");
    }

    @Test
    void insertUser() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));//获取当前时间
        userBiz.register("1232","kjfahsdk", "fsad", "rewao",1, time);
    }

    @Test
    void PageHelper(){
        PageInfo pageInfo = noticeBiz.getNewsPage(1,10);
    }
}
