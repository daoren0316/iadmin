package cc.kokoko.server.common.util;

public class ActionResult {
    public static final String SERVICE_ERROR = "serviceError";
    public static final String LIST = "list";
    public static final String ADMIN_USER_LIST = "user_list";
    public static final String COMMON_MSG = "common_msg";

    public static class Dwz {
        public static final String SUCCESS = "200";
        public static final String FAILURE = "300";
        public static final String TIMEOUT = "301";

        public static final String SHOP_LOCATION = "ShopLocation";// 接入商户
        public static final String SHOP = "shop";// 商户管理
        public static final String SYS_MONEY = "SysMoney";// 监管账户（系统总资金）
        public static final String SYS_ACTIVITY = "sysActivity";// 系统活动
        public static final String OPERATOR = "operator";// 操作员
        public static final String FEEDBACK_ZX = "zxFeedback"; // 咨询
        public static final String FEEDBACK_TS = "tsFeedback";// 投诉
        public static final String FEEDBACK_BX = "bxFeedback"; // 保修
        public static final String CARD_GRANT = "toGrant";// 发放卡片
        public static final String CARD_CANCEL = "toCancel"; // 注销卡片
        public static final String REFUND = "toRefund"; // 退款
        public static final String CHARGE = "toCharge"; // 线下充值
        public static final String FEE = "toWyFee"; // 缴纳物业费/停车费
        public static final String POS = "pos"; // 绑定pos机
        public static final String ADVERTISEMENT = "advertisement"; // 广告
        public static final String COMMUNITY = "community"; // 小区
        public static final String BUILDING = "building"; // 幢
        public static final String UNIT = "unit"; // 单元
        public static final String HOUSE = "house"; // 户号
        public static final String OWNER = "owner"; // 业主信息

        public static final String LIFETIME = "lifetime"; //

    }
}
