package cc.kokoko.server.ibutler.web.lifetime.domain.commons;

public class KdtCommons
{
  public static class Methods
  {
    public static String KAT_TRADE_GET = "kdt.trade.get";
    public static String KAT_TRADES_SOLD_GET = "kdt.trades.sold.get";
  }

  public static class TradeStatus
  {
    public static String TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";
    public static String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    public static String WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
    public static String WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
    public static String TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";
    public static String TRADE_CLOSED = "TRADE_CLOSED";
    public static String TRADE_CLOSED_BY_USER = "TRADE_CLOSED_BY_USER";
    public static String ALL_WAIT_PAY = "TRADE_CLOSED";
    public static String ALL_CLOSED = "TRADE_CLOSED_BY_USER";
  }
}