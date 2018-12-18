package io.damo.common.utils;

public class CoinUtils {

    public static String createAccount(String coinSymbol, String username){
        return "w"+coinSymbol+username;
    }
}
