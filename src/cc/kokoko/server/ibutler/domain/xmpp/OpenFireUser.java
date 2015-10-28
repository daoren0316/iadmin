/*     */ package cc.kokoko.server.ibutler.domain.xmpp;
/*     */ 
/*     */ public class OpenFireUser
/*     */ {
/*     */   private String username;
/*     */   private String plainPassword;
/*     */   private String encryptedPassword;
/*     */   private String name;
/*     */   private String email;
/*     */   private String creationDate;
/*     */   private String modificationDate;
/*     */ 
/*     */   public String getUsername()
/*     */   {
/*  69 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/*  81 */     this.username = (username == null ? null : username.trim());
/*     */   }
/*     */ 
/*     */   public String getPlainPassword()
/*     */   {
/*  93 */     return this.plainPassword;
/*     */   }
/*     */ 
/*     */   public void setPlainPassword(String plainPassword)
/*     */   {
/* 105 */     this.plainPassword = (plainPassword == null ? null : plainPassword.trim());
/*     */   }
/*     */ 
/*     */   public String getEncryptedPassword()
/*     */   {
/* 117 */     return this.encryptedPassword;
/*     */   }
/*     */ 
/*     */   public void setEncryptedPassword(String encryptedPassword)
/*     */   {
/* 129 */     this.encryptedPassword = (encryptedPassword == null ? null : encryptedPassword.trim());
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 141 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 153 */     this.name = (name == null ? null : name.trim());
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 165 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 177 */     this.email = (email == null ? null : email.trim());
/*     */   }
/*     */ 
/*     */   public String getCreationDate()
/*     */   {
/* 189 */     return this.creationDate;
/*     */   }
/*     */ 
/*     */   public void setCreationDate(String creationDate)
/*     */   {
/* 201 */     this.creationDate = (creationDate == null ? null : creationDate.trim());
/*     */   }
/*     */ 
/*     */   public String getModificationDate()
/*     */   {
/* 213 */     return this.modificationDate;
/*     */   }
/*     */ 
/*     */   public void setModificationDate(String modificationDate)
/*     */   {
/* 225 */     this.modificationDate = (modificationDate == null ? null : modificationDate.trim());
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.xmpp.OpenFireUser
 * JD-Core Version:    0.6.0
 */