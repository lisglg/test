package io.damo.config;

import io.damo.sms.utils.SmsConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigInitRunner implements CommandLineRunner {

    @Value("${smsnational.urlGL}")
    private String smsUrlGL;
    @Value("${smsnational.accountGL}")
    private String smsAccountGL;
    @Value("${smsnational.passwordGL}")
    private String smsPasswordGL;

    @Value("${smsnational.urlGW}")
    private String smsUrlGW;
    @Value("${smsnational.accountGW}")
    private String smsAccountGW;
    @Value("${smsnational.passwordGW}")
    private String smsPasswordGW;


    @Override
    public void run(String... args) {
        SmsConstant.urlGL = smsUrlGL;
        SmsConstant.accountGL = smsAccountGL;
        SmsConstant.passwordGL = smsPasswordGL;
        SmsConstant.urlGW = smsUrlGW;
        SmsConstant.accountGW = smsAccountGW;
        SmsConstant.passwordGW = smsPasswordGW;
    }
}
