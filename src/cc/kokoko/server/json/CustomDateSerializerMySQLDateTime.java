/*    */ package cc.kokoko.server.json;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.codehaus.jackson.JsonGenerator;
/*    */ import org.codehaus.jackson.JsonProcessingException;
/*    */ import org.codehaus.jackson.map.JsonSerializer;
/*    */ import org.codehaus.jackson.map.SerializerProvider;
/*    */ 
/*    */ public class CustomDateSerializerMySQLDateTime extends JsonSerializer<Date>
/*    */ {
/*    */   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
/*    */     throws IOException, JsonProcessingException
/*    */   {
/* 20 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 21 */     String formattedDate = formatter.format(value);
/* 22 */     jgen.writeString(formattedDate);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-json-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.json.CustomDateSerializerMySQLDateTime
 * JD-Core Version:    0.6.0
 */