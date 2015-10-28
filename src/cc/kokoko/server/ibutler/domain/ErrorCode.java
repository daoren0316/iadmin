package cc.kokoko.server.ibutler.domain;

public class ErrorCode
{
  public static final int SUCCESS = 0;
  public static final int SERVER_NETWORK_ERROR = -400;
  public static final int TOKEN_ERROR = -403;
  public static final int SERVER_QUARTZ_ERROR = -410;
  public static final int DB_ERROR = -500;
  public static final int PARAM_ERROR = -501;
  public static final int SERVER_IO_ERROR = -502;
  public static final int DUPLICATE_KEY_EXCEPTION = -503;
  public static final int SERVER_JMS_ERROR = -504;
  public static final int SERVER_UNKNOWN_ERROR = -505;
  public static final int UID_NULL = -1000;
  public static final int USER_NOT_EXIST = -1002;
  public static final int EMAIL_EXIST = -1010;
  public static final int PASSWORD_ERROR = -1011;
  public static final int DEVICE_FORBIDDEN = -1012;
  public static final int AUTHCODE_ERROR = -1015;
  public static final int PHONE_NUMBER_EXIST = -1016;
  public static final int OLD_PASSWORD_ERROR = -1017;
  public static final int MONEY_NOT_FOUND = -1018;
  public static final int HOUSEID_NOT_SET = -1019;
  public static final int ORDER_NOT_FOUND = -10120;
  public static final int MONEY_NOT_ENOUGH = -1021;
  public static final int PAY_PSW_ERR = -1022;
  public static final int IS_NOT_HOUSE_OWNER = -1023;
  public static final int OLD_PAY_PASSWORD_ERROR = -1024;
  public static final int COMMODITY_AMOUNT_LIMIT_ERROR = -1025;
  public static final int DUPLICATE_TRADE_ID = -1026;
  public static final int NOT_PAY_ALLOWED_MEMBER = -1027;
  public static final int PAY_PASSWORD_INITED = -1028;
  public static final int CALL_LOG_NOT_FOUND = -1029;
  public static final int CALLBACK_FORBIDDEN = -1030;
  public static final int RD_ORDER_NOT_FOUND = -2000;
  public static final int RD_DUPLICATE_FLUSH = -2001;
  public static final int POS_NO_BIND_SHOPID = -2002;
}

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.ErrorCode
 * JD-Core Version:    0.6.0
 */