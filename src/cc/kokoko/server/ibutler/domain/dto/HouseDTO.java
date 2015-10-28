/*    */ package cc.kokoko.server.ibutler.domain.dto;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.Building;
/*    */ import cc.kokoko.server.ibutler.domain.City;
/*    */ import cc.kokoko.server.ibutler.domain.Community;
/*    */ import cc.kokoko.server.ibutler.domain.Unit;
/*    */ 
/*    */ public class HouseDTO
/*    */ {
/*    */   private Long houseId;
/*    */   private String houseTitle;
/*    */   private String houseName;
/*    */   private City city;
/*    */   private Community community;
/*    */   private Building building;
/*    */   private Unit unit;
/*    */ 
/*    */   public Long getHouseId()
/*    */   {
/* 40 */     return this.houseId;
/*    */   }
/*    */ 
/*    */   public void setHouseId(Long houseId) {
/* 44 */     this.houseId = houseId;
/*    */   }
/*    */ 
/*    */   public String getHouseTitle() {
/* 48 */     return this.houseTitle;
/*    */   }
/*    */ 
/*    */   public void setHouseTitle(String houseTitle) {
/* 52 */     this.houseTitle = houseTitle;
/*    */   }
/*    */ 
/*    */   public City getCity() {
/* 56 */     return this.city;
/*    */   }
/*    */ 
/*    */   public void setCity(City city) {
/* 60 */     this.city = city;
/*    */   }
/*    */ 
/*    */   public Community getCommunity() {
/* 64 */     return this.community;
/*    */   }
/*    */ 
/*    */   public void setCommunity(Community community) {
/* 68 */     this.community = community;
/*    */   }
/*    */ 
/*    */   public Building getBuilding() {
/* 72 */     return this.building;
/*    */   }
/*    */ 
/*    */   public void setBuilding(Building building) {
/* 76 */     this.building = building;
/*    */   }
/*    */ 
/*    */   public Unit getUnit() {
/* 80 */     return this.unit;
/*    */   }
/*    */ 
/*    */   public void setUnit(Unit unit) {
/* 84 */     this.unit = unit;
/*    */   }
/*    */ 
/*    */   public String getHouseName() {
/* 88 */     return this.houseName;
/*    */   }
/*    */ 
/*    */   public void setHouseName(String houseName) {
/* 92 */     this.houseName = houseName;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.HouseDTO
 * JD-Core Version:    0.6.0
 */