/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ 
/*    */ @XmlRootElement(name="jsonResult")
/*    */ public class JsonResult
/*    */ {
/*    */   private int code;
/*    */   private Object result;
/*    */   private String errorMsg;
/*    */ 
/*    */   public JsonResult()
/*    */   {
/*    */   }
/*    */ 
/*    */   public JsonResult(int code, Object result, String errorMsg)
/*    */   {
/* 19 */     this.code = code;
/* 20 */     this.errorMsg = errorMsg;
/* 21 */     this.result = result;
/*    */   }
/*    */   @XmlElement
/*    */   public String getErrorMsg() {
/* 26 */     return this.errorMsg;
/*    */   }
/*    */ 
/*    */   public void setErrorMsg(String errorMsg) {
/* 30 */     this.errorMsg = errorMsg;
/*    */   }
/*    */   @XmlElement
/*    */   public int getCode() {
/* 35 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(int code) {
/* 39 */     this.code = code;
/*    */   }
/*    */   @XmlElement
/*    */   public Object getResult() {
/* 44 */     return this.result;
/*    */   }
/*    */ 
/*    */   public void setResult(Object result) {
/* 48 */     this.result = result;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.JsonResult
 * JD-Core Version:    0.6.0
 */