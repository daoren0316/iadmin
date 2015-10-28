/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.kokoko.server.ibutler.domain.JsonResult;
/*     */ 
/*     */ public class ObjectUtil
/*     */ {
/*  22 */   private static Logger log = LoggerFactory.getLogger(ObjectUtil.class);
/*     */ 
/*     */   public static JsonResult getJsonResult(int code, String errorMsg, Object result)
/*     */   {
/*  26 */     JsonResult jsonResult = new JsonResult();
/*  27 */     jsonResult.setCode(code);
/*  28 */     jsonResult.setErrorMsg(errorMsg);
/*  29 */     jsonResult.setResult(result);
/*  30 */     return jsonResult;
/*     */   }
/*     */ 
/*     */   public static String getJsonStr(Object obj) {
/*  34 */     ObjectMapper mapper = new ObjectMapper();
/*  35 */     mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*     */ 
/*  37 */     String json = null;
/*     */     try {
/*  39 */       json = mapper.writeValueAsString(obj);
/*     */     } catch (JsonGenerationException e) {
/*  41 */       log.error("[getJsonStr] " + e.getMessage());
/*  42 */       e.printStackTrace();
/*     */     } catch (JsonMappingException e) {
/*  44 */       log.error("[getJsonStr] " + e.getMessage());
/*  45 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  47 */       log.error("[getJsonStr] " + e.getMessage());
/*  48 */       e.printStackTrace();
/*     */     }
/*  50 */     return json;
/*     */   }
/*     */ 
/*     */   public static <T> T getObjectFromJson(String jsonStr, Class<T> clazz)
/*     */   {
/*  55 */     ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
/*     */ 
/*  57 */     mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*     */ 
/*  59 */     Object obj = null;
/*     */     try {
/*  61 */       obj = mapper.readValue(jsonStr, clazz);
/*     */     } catch (JsonParseException e) {
/*  63 */       log.error("[getObjectFromJson] " + e.getMessage());
/*  64 */       e.printStackTrace();
/*     */     } catch (JsonMappingException e) {
/*  66 */       log.error("[getObjectFromJson] " + e.getMessage());
/*  67 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  69 */       log.error("[getObjectFromJson] " + e.getMessage());
/*  70 */       e.printStackTrace();
/*     */     }
/*  72 */     return (T) obj;
/*     */   }
/*     */ 
/*     */   public static <T> List<T> getObjectListFromJson(String jsonStr, Class<T> clazz)
/*     */   {
/*  77 */     ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
/*     */ 
/*  79 */     mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*     */ 
/*  81 */     List objList = new ArrayList();
/*     */     try {
/*  83 */       TypeReference typeRef = new TypeReference()
/*     */       {
/*     */       };
/*  85 */       objList = (List)mapper.readValue(jsonStr, typeRef);
/*     */     } catch (JsonParseException e) {
/*  87 */       log.error("[getObjectFromJson] " + e.getMessage());
/*  88 */       e.printStackTrace();
/*     */     } catch (JsonMappingException e) {
/*  90 */       log.error("[getObjectFromJson] " + e.getMessage());
/*  91 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  93 */       log.error("[getObjectFromJson] " + e.getMessage());
/*  94 */       e.printStackTrace();
/*     */     }
/*  96 */     return objList;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getObjectMapFromJson(String jsonStr)
/*     */   {
/* 101 */     ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
/*     */ 
/* 103 */     mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
/*     */ 
/* 105 */     Map dataMap = new HashMap();
/*     */     try {
/* 107 */       TypeReference typeRef = new TypeReference()
/*     */       {
/*     */       };
/* 110 */       dataMap = (Map)mapper.readValue(jsonStr, typeRef);
/*     */     } catch (JsonParseException e) {
/* 112 */       log.error("[getObjectMapFromJson] JsonParseException err: " + e.getMessage());
/*     */     }
/*     */     catch (JsonMappingException e) {
/* 115 */       log.error("[getObjectMapFromJson] JsonMappingException err:" + e.getMessage());
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 119 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 122 */     return dataMap;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 127 */     String str = "[{\"attId\":\"0c877720-c113-4c1c-ba38-f5367aef3ebc\",\"attSize\":83166,\"fileName\":\"20141029_034932.jpeg\",\"fileUrl\":\"http://file.ibutler.cn/ibutler/00/00/03/24/20141029/0c877720-c113-4c1c-ba38-f5367aef3ebc.jpg\"}]";
/*     */ 
/* 135 */     List list1 = getObjectListFromJson(str, Map.class);
/* 136 */     for (int i = 0; i < list1.size(); i++) {
/* 137 */       Map map = (Map)list1.get(i);
/* 138 */       System.out.println(map.toString());
/* 139 */       System.out.println(map.get("fileUrl"));
/*     */     }
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.ObjectUtil
 * JD-Core Version:    0.6.0
 */