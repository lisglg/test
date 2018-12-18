package io.damo.common.message;


import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageUtil {

    public static MessageSource source;

    public static Locale locale;


    public static String getMessage(String msgKey, Object... args) {
        if(locale == null){
            locale = new Locale("zh", "CN");
        }

        if(source == null){
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("i18n/message");
            source = messageSource;
        }

        return source.getMessage(msgKey, args, locale);
    }
}
