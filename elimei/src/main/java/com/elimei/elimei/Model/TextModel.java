/*      */ package com.elimei.elimei.Model;
/*      */ 
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TextModel
/*      */ {
/*      */   private String result;
/*      */   private String msg;
/*      */   private DataBean data;
/*      */   
/*   26 */   public String getResult() { return this.result; }
/*      */ 
/*      */ 
/*      */   
/*   30 */   public void setResult(String result) { this.result = result; }
/*      */ 
/*      */ 
/*      */   
/*   34 */   public String getMsg() { return this.msg; }
/*      */ 
/*      */ 
/*      */   
/*   38 */   public void setMsg(String msg) { this.msg = msg; }
/*      */ 
/*      */ 
/*      */   
/*   42 */   public DataBean getData() { return this.data; }
/*      */ 
/*      */ 
/*      */   
/*   46 */   public void setData(DataBean data) { this.data = data; }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DataBean
/*      */   {
/*      */     private String poreSizeTips;
/*      */ 
/*      */     
/*      */     private String pigmentQuestion;
/*      */ 
/*      */     
/*      */     private String inflammationQuestion;
/*      */ 
/*      */     
/*      */     private String skinColorStallText;
/*      */ 
/*      */     
/*      */     private int synthesizeItem;
/*      */ 
/*      */     
/*      */     private String oilStallText;
/*      */ 
/*      */     
/*      */     private String waterStallText;
/*      */ 
/*      */     
/*      */     private SkinColorProtectSkinWIKIBean skinColorProtectSkinWIKI;
/*      */ 
/*      */     
/*      */     private String oilProductId;
/*      */ 
/*      */     
/*      */     private String waterSkinQuestion;
/*      */ 
/*      */     
/*      */     private String wrinkleQuestion;
/*      */ 
/*      */     
/*      */     private PoreSizeProtectSkinWIKIBean poreSizeProtectSkinWIKI;
/*      */ 
/*      */     
/*      */     private String skinColorProductId;
/*      */ 
/*      */     
/*      */     private String wrinkleTips;
/*      */ 
/*      */     
/*      */     private String oilTips;
/*      */ 
/*      */     
/*      */     private String pigmentStallText;
/*      */ 
/*      */     
/*      */     private String poreSizeQuestion;
/*      */     
/*      */     private OilProtectSkinWIKIBean oilProtectSkinWIKI;
/*      */     
/*      */     private String wrinkleStallText;
/*      */     
/*      */     private WaterProtectSkinWIKIBean waterProtectSkinWIKI;
/*      */     
/*      */     private String skinColorTips;
/*      */     
/*      */     private String poreSizeProductId;
/*      */     
/*      */     private String productImageDir;
/*      */     
/*      */     private TextureProtectSkinWIKIBean textureProtectSkinWIKI;
/*      */     
/*      */     private String textureStallText;
/*      */     
/*      */     private String textureQuestion;
/*      */     
/*      */     private String textureProductId;
/*      */     
/*      */     private String waterTips;
/*      */     
/*      */     private String wrinkleProductId;
/*      */     
/*      */     private String oilQuestion;
/*      */     
/*      */     private String textureTips;
/*      */     
/*      */     private String inflammationStallText;
/*      */     
/*      */     private String skinColorQuestion;
/*      */     
/*      */     private InflammationProtectSkinWIKIBean inflammationProtectSkinWIKI;
/*      */     
/*      */     private PigmentProtectSkinWIKIBean pigmentProtectSkinWIKI;
/*      */     
/*      */     private String pigmentProductId;
/*      */     
/*      */     private WrinkleProtectSkinWIKIBean wrinkleProtectSkinWIKI;
/*      */     
/*      */     private String inflammationTips;
/*      */     
/*      */     private String pigmentTips;
/*      */     
/*      */     private String poreSizeStallText;
/*      */     
/*      */     private String inflammationProductId;
/*      */     
/*      */     private String waterProductId;
/*      */     
/*      */     private List<PoreSizeProductListBean> poreSizeProductList;
/*      */     
/*      */     private List<TextureProductListBean> textureProductList;
/*      */     
/*      */     private List<OilProductListBean> oilProductList;
/*      */     
/*      */     private List<WaterProductListBean> waterProductList;
/*      */     
/*      */     private List<WrinkleProductListBean> wrinkleProductList;
/*      */     
/*      */     private List<PigmentProductListBean> pigmentProductList;
/*      */     
/*      */     private List<SkinColorProductListBean> skinColorProductList;
/*      */     
/*      */     private List<InflammationProductListBean> inflammationProductList;
/*      */ 
/*      */     
/*  169 */     public String getPoreSizeTips() { return this.poreSizeTips; }
/*      */ 
/*      */ 
/*      */     
/*  173 */     public void setPoreSizeTips(String poreSizeTips) { this.poreSizeTips = poreSizeTips; }
/*      */ 
/*      */ 
/*      */     
/*  177 */     public String getPigmentQuestion() { return this.pigmentQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  181 */     public void setPigmentQuestion(String pigmentQuestion) { this.pigmentQuestion = pigmentQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  185 */     public String getInflammationQuestion() { return this.inflammationQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  189 */     public void setInflammationQuestion(String inflammationQuestion) { this.inflammationQuestion = inflammationQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  193 */     public String getSkinColorStallText() { return this.skinColorStallText; }
/*      */ 
/*      */ 
/*      */     
/*  197 */     public void setSkinColorStallText(String skinColorStallText) { this.skinColorStallText = skinColorStallText; }
/*      */ 
/*      */ 
/*      */     
/*  201 */     public int getSynthesizeItem() { return this.synthesizeItem; }
/*      */ 
/*      */ 
/*      */     
/*  205 */     public void setSynthesizeItem(int synthesizeItem) { this.synthesizeItem = synthesizeItem; }
/*      */ 
/*      */ 
/*      */     
/*  209 */     public String getOilStallText() { return this.oilStallText; }
/*      */ 
/*      */ 
/*      */     
/*  213 */     public void setOilStallText(String oilStallText) { this.oilStallText = oilStallText; }
/*      */ 
/*      */ 
/*      */     
/*  217 */     public String getWaterStallText() { return this.waterStallText; }
/*      */ 
/*      */ 
/*      */     
/*  221 */     public void setWaterStallText(String waterStallText) { this.waterStallText = waterStallText; }
/*      */ 
/*      */ 
/*      */     
/*  225 */     public SkinColorProtectSkinWIKIBean getSkinColorProtectSkinWIKI() { return this.skinColorProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  229 */     public void setSkinColorProtectSkinWIKI(SkinColorProtectSkinWIKIBean skinColorProtectSkinWIKI) { this.skinColorProtectSkinWIKI = skinColorProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  233 */     public String getOilProductId() { return this.oilProductId; }
/*      */ 
/*      */ 
/*      */     
/*  237 */     public void setOilProductId(String oilProductId) { this.oilProductId = oilProductId; }
/*      */ 
/*      */ 
/*      */     
/*  241 */     public String getWaterSkinQuestion() { return this.waterSkinQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  245 */     public void setWaterSkinQuestion(String waterSkinQuestion) { this.waterSkinQuestion = waterSkinQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  249 */     public String getWrinkleQuestion() { return this.wrinkleQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  253 */     public void setWrinkleQuestion(String wrinkleQuestion) { this.wrinkleQuestion = wrinkleQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  257 */     public PoreSizeProtectSkinWIKIBean getPoreSizeProtectSkinWIKI() { return this.poreSizeProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  261 */     public void setPoreSizeProtectSkinWIKI(PoreSizeProtectSkinWIKIBean poreSizeProtectSkinWIKI) { this.poreSizeProtectSkinWIKI = poreSizeProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  265 */     public String getSkinColorProductId() { return this.skinColorProductId; }
/*      */ 
/*      */ 
/*      */     
/*  269 */     public void setSkinColorProductId(String skinColorProductId) { this.skinColorProductId = skinColorProductId; }
/*      */ 
/*      */ 
/*      */     
/*  273 */     public String getWrinkleTips() { return this.wrinkleTips; }
/*      */ 
/*      */ 
/*      */     
/*  277 */     public void setWrinkleTips(String wrinkleTips) { this.wrinkleTips = wrinkleTips; }
/*      */ 
/*      */ 
/*      */     
/*  281 */     public String getOilTips() { return this.oilTips; }
/*      */ 
/*      */ 
/*      */     
/*  285 */     public void setOilTips(String oilTips) { this.oilTips = oilTips; }
/*      */ 
/*      */ 
/*      */     
/*  289 */     public String getPigmentStallText() { return this.pigmentStallText; }
/*      */ 
/*      */ 
/*      */     
/*  293 */     public void setPigmentStallText(String pigmentStallText) { this.pigmentStallText = pigmentStallText; }
/*      */ 
/*      */ 
/*      */     
/*  297 */     public String getPoreSizeQuestion() { return this.poreSizeQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  301 */     public void setPoreSizeQuestion(String poreSizeQuestion) { this.poreSizeQuestion = poreSizeQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  305 */     public OilProtectSkinWIKIBean getOilProtectSkinWIKI() { return this.oilProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  309 */     public void setOilProtectSkinWIKI(OilProtectSkinWIKIBean oilProtectSkinWIKI) { this.oilProtectSkinWIKI = oilProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  313 */     public String getWrinkleStallText() { return this.wrinkleStallText; }
/*      */ 
/*      */ 
/*      */     
/*  317 */     public void setWrinkleStallText(String wrinkleStallText) { this.wrinkleStallText = wrinkleStallText; }
/*      */ 
/*      */ 
/*      */     
/*  321 */     public WaterProtectSkinWIKIBean getWaterProtectSkinWIKI() { return this.waterProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  325 */     public void setWaterProtectSkinWIKI(WaterProtectSkinWIKIBean waterProtectSkinWIKI) { this.waterProtectSkinWIKI = waterProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  329 */     public String getSkinColorTips() { return this.skinColorTips; }
/*      */ 
/*      */ 
/*      */     
/*  333 */     public void setSkinColorTips(String skinColorTips) { this.skinColorTips = skinColorTips; }
/*      */ 
/*      */ 
/*      */     
/*  337 */     public String getPoreSizeProductId() { return this.poreSizeProductId; }
/*      */ 
/*      */ 
/*      */     
/*  341 */     public void setPoreSizeProductId(String poreSizeProductId) { this.poreSizeProductId = poreSizeProductId; }
/*      */ 
/*      */ 
/*      */     
/*  345 */     public String getProductImageDir() { return this.productImageDir; }
/*      */ 
/*      */ 
/*      */     
/*  349 */     public void setProductImageDir(String productImageDir) { this.productImageDir = productImageDir; }
/*      */ 
/*      */ 
/*      */     
/*  353 */     public TextureProtectSkinWIKIBean getTextureProtectSkinWIKI() { return this.textureProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  357 */     public void setTextureProtectSkinWIKI(TextureProtectSkinWIKIBean textureProtectSkinWIKI) { this.textureProtectSkinWIKI = textureProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  361 */     public String getTextureStallText() { return this.textureStallText; }
/*      */ 
/*      */ 
/*      */     
/*  365 */     public void setTextureStallText(String textureStallText) { this.textureStallText = textureStallText; }
/*      */ 
/*      */ 
/*      */     
/*  369 */     public String getTextureQuestion() { return this.textureQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  373 */     public void setTextureQuestion(String textureQuestion) { this.textureQuestion = textureQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  377 */     public String getTextureProductId() { return this.textureProductId; }
/*      */ 
/*      */ 
/*      */     
/*  381 */     public void setTextureProductId(String textureProductId) { this.textureProductId = textureProductId; }
/*      */ 
/*      */ 
/*      */     
/*  385 */     public String getWaterTips() { return this.waterTips; }
/*      */ 
/*      */ 
/*      */     
/*  389 */     public void setWaterTips(String waterTips) { this.waterTips = waterTips; }
/*      */ 
/*      */ 
/*      */     
/*  393 */     public String getWrinkleProductId() { return this.wrinkleProductId; }
/*      */ 
/*      */ 
/*      */     
/*  397 */     public void setWrinkleProductId(String wrinkleProductId) { this.wrinkleProductId = wrinkleProductId; }
/*      */ 
/*      */ 
/*      */     
/*  401 */     public String getOilQuestion() { return this.oilQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  405 */     public void setOilQuestion(String oilQuestion) { this.oilQuestion = oilQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  409 */     public String getTextureTips() { return this.textureTips; }
/*      */ 
/*      */ 
/*      */     
/*  413 */     public void setTextureTips(String textureTips) { this.textureTips = textureTips; }
/*      */ 
/*      */ 
/*      */     
/*  417 */     public String getInflammationStallText() { return this.inflammationStallText; }
/*      */ 
/*      */ 
/*      */     
/*  421 */     public void setInflammationStallText(String inflammationStallText) { this.inflammationStallText = inflammationStallText; }
/*      */ 
/*      */ 
/*      */     
/*  425 */     public String getSkinColorQuestion() { return this.skinColorQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  429 */     public void setSkinColorQuestion(String skinColorQuestion) { this.skinColorQuestion = skinColorQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  433 */     public InflammationProtectSkinWIKIBean getInflammationProtectSkinWIKI() { return this.inflammationProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  437 */     public void setInflammationProtectSkinWIKI(InflammationProtectSkinWIKIBean inflammationProtectSkinWIKI) { this.inflammationProtectSkinWIKI = inflammationProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  441 */     public PigmentProtectSkinWIKIBean getPigmentProtectSkinWIKI() { return this.pigmentProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  445 */     public void setPigmentProtectSkinWIKI(PigmentProtectSkinWIKIBean pigmentProtectSkinWIKI) { this.pigmentProtectSkinWIKI = pigmentProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  449 */     public String getPigmentProductId() { return this.pigmentProductId; }
/*      */ 
/*      */ 
/*      */     
/*  453 */     public void setPigmentProductId(String pigmentProductId) { this.pigmentProductId = pigmentProductId; }
/*      */ 
/*      */ 
/*      */     
/*  457 */     public WrinkleProtectSkinWIKIBean getWrinkleProtectSkinWIKI() { return this.wrinkleProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  461 */     public void setWrinkleProtectSkinWIKI(WrinkleProtectSkinWIKIBean wrinkleProtectSkinWIKI) { this.wrinkleProtectSkinWIKI = wrinkleProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  465 */     public String getInflammationTips() { return this.inflammationTips; }
/*      */ 
/*      */ 
/*      */     
/*  469 */     public void setInflammationTips(String inflammationTips) { this.inflammationTips = inflammationTips; }
/*      */ 
/*      */ 
/*      */     
/*  473 */     public String getPigmentTips() { return this.pigmentTips; }
/*      */ 
/*      */ 
/*      */     
/*  477 */     public void setPigmentTips(String pigmentTips) { this.pigmentTips = pigmentTips; }
/*      */ 
/*      */ 
/*      */     
/*  481 */     public String getPoreSizeStallText() { return this.poreSizeStallText; }
/*      */ 
/*      */ 
/*      */     
/*  485 */     public void setPoreSizeStallText(String poreSizeStallText) { this.poreSizeStallText = poreSizeStallText; }
/*      */ 
/*      */ 
/*      */     
/*  489 */     public String getInflammationProductId() { return this.inflammationProductId; }
/*      */ 
/*      */ 
/*      */     
/*  493 */     public void setInflammationProductId(String inflammationProductId) { this.inflammationProductId = inflammationProductId; }
/*      */ 
/*      */ 
/*      */     
/*  497 */     public String getWaterProductId() { return this.waterProductId; }
/*      */ 
/*      */ 
/*      */     
/*  501 */     public void setWaterProductId(String waterProductId) { this.waterProductId = waterProductId; }
/*      */ 
/*      */ 
/*      */     
/*  505 */     public List<PoreSizeProductListBean> getPoreSizeProductList() { return this.poreSizeProductList; }
/*      */ 
/*      */ 
/*      */     
/*  509 */     public void setPoreSizeProductList(List<PoreSizeProductListBean> poreSizeProductList) { this.poreSizeProductList = poreSizeProductList; }
/*      */ 
/*      */ 
/*      */     
/*  513 */     public List<TextureProductListBean> getTextureProductList() { return this.textureProductList; }
/*      */ 
/*      */ 
/*      */     
/*  517 */     public void setTextureProductList(List<TextureProductListBean> textureProductList) { this.textureProductList = textureProductList; }
/*      */ 
/*      */ 
/*      */     
/*  521 */     public List<OilProductListBean> getOilProductList() { return this.oilProductList; }
/*      */ 
/*      */ 
/*      */     
/*  525 */     public void setOilProductList(List<OilProductListBean> oilProductList) { this.oilProductList = oilProductList; }
/*      */ 
/*      */ 
/*      */     
/*  529 */     public List<WaterProductListBean> getWaterProductList() { return this.waterProductList; }
/*      */ 
/*      */ 
/*      */     
/*  533 */     public void setWaterProductList(List<WaterProductListBean> waterProductList) { this.waterProductList = waterProductList; }
/*      */ 
/*      */ 
/*      */     
/*  537 */     public List<WrinkleProductListBean> getWrinkleProductList() { return this.wrinkleProductList; }
/*      */ 
/*      */ 
/*      */     
/*  541 */     public void setWrinkleProductList(List<WrinkleProductListBean> wrinkleProductList) { this.wrinkleProductList = wrinkleProductList; }
/*      */ 
/*      */ 
/*      */     
/*  545 */     public List<PigmentProductListBean> getPigmentProductList() { return this.pigmentProductList; }
/*      */ 
/*      */ 
/*      */     
/*  549 */     public void setPigmentProductList(List<PigmentProductListBean> pigmentProductList) { this.pigmentProductList = pigmentProductList; }
/*      */ 
/*      */ 
/*      */     
/*  553 */     public List<SkinColorProductListBean> getSkinColorProductList() { return this.skinColorProductList; }
/*      */ 
/*      */ 
/*      */     
/*  557 */     public void setSkinColorProductList(List<SkinColorProductListBean> skinColorProductList) { this.skinColorProductList = skinColorProductList; }
/*      */ 
/*      */ 
/*      */     
/*  561 */     public List<InflammationProductListBean> getInflammationProductList() { return this.inflammationProductList; }
/*      */ 
/*      */ 
/*      */     
/*  565 */     public void setInflammationProductList(List<InflammationProductListBean> inflammationProductList) { this.inflammationProductList = inflammationProductList; }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class SkinColorProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  582 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  586 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  590 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  594 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  598 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  602 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  606 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  610 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class PoreSizeProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  628 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  632 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  636 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  640 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  644 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  648 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  652 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  656 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class OilProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  674 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  678 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  682 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  686 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  690 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  694 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  698 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  702 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class WaterProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  720 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  724 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  728 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  732 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  736 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  740 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  744 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  748 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class TextureProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  766 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  770 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  774 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  778 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  782 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  786 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  790 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  794 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class InflammationProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  812 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  816 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  820 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  824 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  828 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  832 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  836 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  840 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class PigmentProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  858 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  862 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  866 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  870 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  874 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  878 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  882 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  886 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class WrinkleProtectSkinWIKIBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  904 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  908 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  912 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  916 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  920 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  924 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  928 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  932 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class PoreSizeProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/*  952 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  956 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  960 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  964 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  968 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/*  972 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/*  976 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  980 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  984 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  988 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class TextureProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/* 1008 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1012 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1016 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1020 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1024 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1028 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1032 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1036 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1040 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1044 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class OilProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/* 1064 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1068 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1072 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1076 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1080 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1084 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1088 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1092 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1096 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1100 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class WaterProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/* 1120 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1124 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1128 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1132 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1136 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1140 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1144 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1148 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1152 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1156 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class WrinkleProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/* 1176 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1180 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1184 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1188 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1192 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1196 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1200 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1204 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1208 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1212 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class PigmentProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/* 1232 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1236 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1240 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1244 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1248 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1252 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1256 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1260 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1264 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1268 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class SkinColorProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/* 1288 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1292 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1296 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1300 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1304 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1308 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1312 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1316 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1320 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1324 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static class InflammationProductListBean
/*      */     {
/*      */       private int id;
/*      */ 
/*      */       
/*      */       private String productName;
/*      */ 
/*      */       
/*      */       private String urlCode;
/*      */       
/*      */       private String fileName;
/*      */       
/*      */       private String guidName;
/*      */ 
/*      */       
/* 1344 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1348 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1352 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1356 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1360 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1364 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1368 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1372 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1376 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1380 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\Model\TextModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */