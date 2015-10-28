/*    */ package cc.kokoko.server.json;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.codehaus.jackson.JsonParser;
/*    */ import org.codehaus.jackson.JsonProcessingException;
/*    */ import org.codehaus.jackson.map.DeserializationContext;
/*    */ import org.codehaus.jackson.map.JsonDeserializer;
/*    */ 
/*    */ public class CustomDateDeSerializerMySQLDate extends JsonDeserializer<Date>
/*    */ {
/*    */   public Date deserialize(JsonParser jsonparser, DeserializationContext arg1)
/*    */     throws IOException, JsonProcessingException
/*    */   {
/* 22 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 23 */     String date = jsonparser.getText();
/*    */     try {
/* 25 */       return format.parse(date); } catch (ParseException e) {
/*    */     }
/* 27 */     return null;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-json-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.json.CustomDateDeSerializerMySQLDate
 * JD-Core Version:    0.6.0
 */