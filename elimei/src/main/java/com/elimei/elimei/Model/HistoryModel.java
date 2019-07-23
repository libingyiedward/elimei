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
/*      */ public class HistoryModel
/*      */ {
/*      */   private String result;
/*      */   private String msg;
/*      */   private List<DataBean> data;
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
/*   42 */   public List<DataBean> getData() { return this.data; }
/*      */ 
/*      */ 
/*      */   
/*   46 */   public void setData(List<DataBean> data) { this.data = data; }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DataBean
/*      */   {
/*      */     private double CompositeScore;
/*      */ 
/*      */     
/*      */     private String PoreSizeStall;
/*      */ 
/*      */     
/*      */     private String InflammationStall;
/*      */ 
/*      */     
/*      */     private String poreSizeTips;
/*      */ 
/*      */     
/*      */     private String Guid;
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
/*      */     private double TextureValue;
/*      */ 
/*      */     
/*      */     private String oilStallText;
/*      */ 
/*      */     
/*      */     private String waterStallText;
/*      */ 
/*      */     
/*      */     private TextModel.DataBean.SkinColorProtectSkinWIKIBean skinColorProtectSkinWIKI;
/*      */ 
/*      */     
/*      */     private String WrinkleStall;
/*      */ 
/*      */     
/*      */     private double PigmentValue;
/*      */ 
/*      */     
/*      */     private String oilProductId;
/*      */ 
/*      */     
/*      */     private String index;
/*      */     
/*      */     private String waterSkinQuestion;
/*      */     
/*      */     private String wrinkleQuestion;
/*      */     
/*      */     private TextModel.DataBean.PoreSizeProtectSkinWIKIBean poreSizeProtectSkinWIKI;
/*      */     
/*      */     private String skinColorProductId;
/*      */     
/*      */     private double WaterDegreeValue;
/*      */     
/*      */     private double PoreSizeValue;
/*      */     
/*      */     private String SkinColorStall;
/*      */     
/*      */     private String wrinkleTips;
/*      */     
/*      */     private String oilTips;
/*      */     
/*      */     private String pigmentStallText;
/*      */     
/*      */     private double WrinkleValue;
/*      */     
/*      */     private String poreSizeQuestion;
/*      */     
/*      */     private String testImageDir;
/*      */     
/*      */     private TextModel.DataBean.OilProtectSkinWIKIBean oilProtectSkinWIKI;
/*      */     
/*      */     private double SkinColorValue;
/*      */     
/*      */     private String WaterDegreeStall;
/*      */     
/*      */     private String wrinkleStallText;
/*      */     
/*      */     private int RealAge;
/*      */     
/*      */     private TextModel.DataBean.WaterProtectSkinWIKIBean waterProtectSkinWIKI;
/*      */     
/*      */     private String skinColorTips;
/*      */     
/*      */     private String poreSizeProductId;
/*      */     
/*      */     private String productImageDir;
/*      */     
/*      */     private TextModel.DataBean.TextureProtectSkinWIKIBean textureProtectSkinWIKI;
/*      */     
/*      */     private String textureStallText;
/*      */     
/*      */     private String PigmentStall;
/*      */     
/*      */     private String textureQuestion;
/*      */     
/*      */     private String textureProductId;
/*      */     
/*      */     private String waterTips;
/*      */     
/*      */     private String ResultCompleteTime;
/*      */     
/*      */     private String wrinkleProductId;
/*      */     
/*      */     private String oilQuestion;
/*      */     
/*      */     private String textureTips;
/*      */     
/*      */     private String inflammationStallText;
/*      */     
/*      */     private String TextureStall;
/*      */     
/*      */     private String skinColorQuestion;
/*      */     
/*      */     private TextModel.DataBean.InflammationProtectSkinWIKIBean inflammationProtectSkinWIKI;
/*      */     
/*      */     private TextModel.DataBean.PigmentProtectSkinWIKIBean pigmentProtectSkinWIKI;
/*      */     
/*      */     private String pigmentProductId;
/*      */     
/*      */     private TextModel.DataBean.WrinkleProtectSkinWIKIBean wrinkleProtectSkinWIKI;
/*      */     
/*      */     private String inflammationTips;
/*      */     
/*      */     private String MathAge;
/*      */     
/*      */     private String pigmentTips;
/*      */     
/*      */     private String OilStall;
/*      */     
/*      */     private String ImageName;
/*      */     
/*      */     private String poreSizeStallText;
/*      */     
/*      */     private double InflammationValue;
/*      */     
/*      */     private String UserId;
/*      */     
/*      */     private String inflammationProductId;
/*      */     
/*      */     private double OilValue;
/*      */     
/*      */     private String waterProductId;
/*      */     
/*      */     private List<TextModel.DataBean.PoreSizeProductListBean> poreSizeProductList;
/*      */     
/*      */     private List<TextModel.DataBean.TextureProductListBean> textureProductList;
/*      */     
/*      */     private List<TextModel.DataBean.OilProductListBean> oilProductList;
/*      */     
/*      */     private List<TextModel.DataBean.WaterProductListBean> waterProductList;
/*      */     
/*      */     private List<TextModel.DataBean.WrinkleProductListBean> wrinkleProductList;
/*      */     
/*      */     private List<TextModel.DataBean.PigmentProductListBean> pigmentProductList;
/*      */     
/*      */     private List<TextModel.DataBean.SkinColorProductListBean> skinColorProductList;
/*      */     
/*      */     private List<TextModel.DataBean.InflammationProductListBean> inflammationProductList;
/*      */ 
/*      */     
/*  219 */     public double getCompositeScore() { return this.CompositeScore; }
/*      */ 
/*      */ 
/*      */     
/*  223 */     public void setCompositeScore(double CompositeScore) { this.CompositeScore = CompositeScore; }
/*      */ 
/*      */ 
/*      */     
/*  227 */     public String getPoreSizeStall() { return this.PoreSizeStall; }
/*      */ 
/*      */ 
/*      */     
/*  231 */     public void setPoreSizeStall(String PoreSizeStall) { this.PoreSizeStall = PoreSizeStall; }
/*      */ 
/*      */ 
/*      */     
/*  235 */     public String getInflammationStall() { return this.InflammationStall; }
/*      */ 
/*      */ 
/*      */     
/*  239 */     public void setInflammationStall(String InflammationStall) { this.InflammationStall = InflammationStall; }
/*      */ 
/*      */ 
/*      */     
/*  243 */     public String getPoreSizeTips() { return this.poreSizeTips; }
/*      */ 
/*      */ 
/*      */     
/*  247 */     public void setPoreSizeTips(String poreSizeTips) { this.poreSizeTips = poreSizeTips; }
/*      */ 
/*      */ 
/*      */     
/*  251 */     public String getGuid() { return this.Guid; }
/*      */ 
/*      */ 
/*      */     
/*  255 */     public void setGuid(String Guid) { this.Guid = Guid; }
/*      */ 
/*      */ 
/*      */     
/*  259 */     public String getPigmentQuestion() { return this.pigmentQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  263 */     public void setPigmentQuestion(String pigmentQuestion) { this.pigmentQuestion = pigmentQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  267 */     public String getInflammationQuestion() { return this.inflammationQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  271 */     public void setInflammationQuestion(String inflammationQuestion) { this.inflammationQuestion = inflammationQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  275 */     public String getSkinColorStallText() { return this.skinColorStallText; }
/*      */ 
/*      */ 
/*      */     
/*  279 */     public void setSkinColorStallText(String skinColorStallText) { this.skinColorStallText = skinColorStallText; }
/*      */ 
/*      */ 
/*      */     
/*  283 */     public int getSynthesizeItem() { return this.synthesizeItem; }
/*      */ 
/*      */ 
/*      */     
/*  287 */     public void setSynthesizeItem(int synthesizeItem) { this.synthesizeItem = synthesizeItem; }
/*      */ 
/*      */ 
/*      */     
/*  291 */     public double getTextureValue() { return this.TextureValue; }
/*      */ 
/*      */ 
/*      */     
/*  295 */     public void setTextureValue(double TextureValue) { this.TextureValue = TextureValue; }
/*      */ 
/*      */ 
/*      */     
/*  299 */     public String getOilStallText() { return this.oilStallText; }
/*      */ 
/*      */ 
/*      */     
/*  303 */     public void setOilStallText(String oilStallText) { this.oilStallText = oilStallText; }
/*      */ 
/*      */ 
/*      */     
/*  307 */     public String getWaterStallText() { return this.waterStallText; }
/*      */ 
/*      */ 
/*      */     
/*  311 */     public void setWaterStallText(String waterStallText) { this.waterStallText = waterStallText; }
/*      */ 
/*      */ 
/*      */     
/*  315 */     public TextModel.DataBean.SkinColorProtectSkinWIKIBean getSkinColorProtectSkinWIKI() { return this.skinColorProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  319 */     public void setSkinColorProtectSkinWIKI(TextModel.DataBean.SkinColorProtectSkinWIKIBean skinColorProtectSkinWIKI) { this.skinColorProtectSkinWIKI = skinColorProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  323 */     public String getWrinkleStall() { return this.WrinkleStall; }
/*      */ 
/*      */ 
/*      */     
/*  327 */     public void setWrinkleStall(String WrinkleStall) { this.WrinkleStall = WrinkleStall; }
/*      */ 
/*      */ 
/*      */     
/*  331 */     public double getPigmentValue() { return this.PigmentValue; }
/*      */ 
/*      */ 
/*      */     
/*  335 */     public void setPigmentValue(double PigmentValue) { this.PigmentValue = PigmentValue; }
/*      */ 
/*      */ 
/*      */     
/*  339 */     public String getOilProductId() { return this.oilProductId; }
/*      */ 
/*      */ 
/*      */     
/*  343 */     public void setOilProductId(String oilProductId) { this.oilProductId = oilProductId; }
/*      */ 
/*      */ 
/*      */     
/*  347 */     public String getIndex() { return this.index; }
/*      */ 
/*      */ 
/*      */     
/*  351 */     public void setIndex(String index) { this.index = index; }
/*      */ 
/*      */ 
/*      */     
/*  355 */     public String getWaterSkinQuestion() { return this.waterSkinQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  359 */     public void setWaterSkinQuestion(String waterSkinQuestion) { this.waterSkinQuestion = waterSkinQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  363 */     public String getWrinkleQuestion() { return this.wrinkleQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  367 */     public void setWrinkleQuestion(String wrinkleQuestion) { this.wrinkleQuestion = wrinkleQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  371 */     public TextModel.DataBean.PoreSizeProtectSkinWIKIBean getPoreSizeProtectSkinWIKI() { return this.poreSizeProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  375 */     public void setPoreSizeProtectSkinWIKI(TextModel.DataBean.PoreSizeProtectSkinWIKIBean poreSizeProtectSkinWIKI) { this.poreSizeProtectSkinWIKI = poreSizeProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  379 */     public String getSkinColorProductId() { return this.skinColorProductId; }
/*      */ 
/*      */ 
/*      */     
/*  383 */     public void setSkinColorProductId(String skinColorProductId) { this.skinColorProductId = skinColorProductId; }
/*      */ 
/*      */ 
/*      */     
/*  387 */     public double getWaterDegreeValue() { return this.WaterDegreeValue; }
/*      */ 
/*      */ 
/*      */     
/*  391 */     public void setWaterDegreeValue(double WaterDegreeValue) { this.WaterDegreeValue = WaterDegreeValue; }
/*      */ 
/*      */ 
/*      */     
/*  395 */     public double getPoreSizeValue() { return this.PoreSizeValue; }
/*      */ 
/*      */ 
/*      */     
/*  399 */     public void setPoreSizeValue(double PoreSizeValue) { this.PoreSizeValue = PoreSizeValue; }
/*      */ 
/*      */ 
/*      */     
/*  403 */     public String getSkinColorStall() { return this.SkinColorStall; }
/*      */ 
/*      */ 
/*      */     
/*  407 */     public void setSkinColorStall(String SkinColorStall) { this.SkinColorStall = SkinColorStall; }
/*      */ 
/*      */ 
/*      */     
/*  411 */     public String getWrinkleTips() { return this.wrinkleTips; }
/*      */ 
/*      */ 
/*      */     
/*  415 */     public void setWrinkleTips(String wrinkleTips) { this.wrinkleTips = wrinkleTips; }
/*      */ 
/*      */ 
/*      */     
/*  419 */     public String getOilTips() { return this.oilTips; }
/*      */ 
/*      */ 
/*      */     
/*  423 */     public void setOilTips(String oilTips) { this.oilTips = oilTips; }
/*      */ 
/*      */ 
/*      */     
/*  427 */     public String getPigmentStallText() { return this.pigmentStallText; }
/*      */ 
/*      */ 
/*      */     
/*  431 */     public void setPigmentStallText(String pigmentStallText) { this.pigmentStallText = pigmentStallText; }
/*      */ 
/*      */ 
/*      */     
/*  435 */     public double getWrinkleValue() { return this.WrinkleValue; }
/*      */ 
/*      */ 
/*      */     
/*  439 */     public void setWrinkleValue(double WrinkleValue) { this.WrinkleValue = WrinkleValue; }
/*      */ 
/*      */ 
/*      */     
/*  443 */     public String getPoreSizeQuestion() { return this.poreSizeQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  447 */     public void setPoreSizeQuestion(String poreSizeQuestion) { this.poreSizeQuestion = poreSizeQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  451 */     public String getTestImageDir() { return this.testImageDir; }
/*      */ 
/*      */ 
/*      */     
/*  455 */     public void setTestImageDir(String testImageDir) { this.testImageDir = testImageDir; }
/*      */ 
/*      */ 
/*      */     
/*  459 */     public TextModel.DataBean.OilProtectSkinWIKIBean getOilProtectSkinWIKI() { return this.oilProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  463 */     public void setOilProtectSkinWIKI(TextModel.DataBean.OilProtectSkinWIKIBean oilProtectSkinWIKI) { this.oilProtectSkinWIKI = oilProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  467 */     public double getSkinColorValue() { return this.SkinColorValue; }
/*      */ 
/*      */ 
/*      */     
/*  471 */     public void setSkinColorValue(double SkinColorValue) { this.SkinColorValue = SkinColorValue; }
/*      */ 
/*      */ 
/*      */     
/*  475 */     public String getWaterDegreeStall() { return this.WaterDegreeStall; }
/*      */ 
/*      */ 
/*      */     
/*  479 */     public void setWaterDegreeStall(String WaterDegreeStall) { this.WaterDegreeStall = WaterDegreeStall; }
/*      */ 
/*      */ 
/*      */     
/*  483 */     public String getWrinkleStallText() { return this.wrinkleStallText; }
/*      */ 
/*      */ 
/*      */     
/*  487 */     public void setWrinkleStallText(String wrinkleStallText) { this.wrinkleStallText = wrinkleStallText; }
/*      */ 
/*      */ 
/*      */     
/*  491 */     public int getRealAge() { return this.RealAge; }
/*      */ 
/*      */ 
/*      */     
/*  495 */     public void setRealAge(int RealAge) { this.RealAge = RealAge; }
/*      */ 
/*      */ 
/*      */     
/*  499 */     public TextModel.DataBean.WaterProtectSkinWIKIBean getWaterProtectSkinWIKI() { return this.waterProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  503 */     public void setWaterProtectSkinWIKI(TextModel.DataBean.WaterProtectSkinWIKIBean waterProtectSkinWIKI) { this.waterProtectSkinWIKI = waterProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  507 */     public String getSkinColorTips() { return this.skinColorTips; }
/*      */ 
/*      */ 
/*      */     
/*  511 */     public void setSkinColorTips(String skinColorTips) { this.skinColorTips = skinColorTips; }
/*      */ 
/*      */ 
/*      */     
/*  515 */     public String getPoreSizeProductId() { return this.poreSizeProductId; }
/*      */ 
/*      */ 
/*      */     
/*  519 */     public void setPoreSizeProductId(String poreSizeProductId) { this.poreSizeProductId = poreSizeProductId; }
/*      */ 
/*      */ 
/*      */     
/*  523 */     public String getProductImageDir() { return this.productImageDir; }
/*      */ 
/*      */ 
/*      */     
/*  527 */     public void setProductImageDir(String productImageDir) { this.productImageDir = productImageDir; }
/*      */ 
/*      */ 
/*      */     
/*  531 */     public TextModel.DataBean.TextureProtectSkinWIKIBean getTextureProtectSkinWIKI() { return this.textureProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  535 */     public void setTextureProtectSkinWIKI(TextModel.DataBean.TextureProtectSkinWIKIBean textureProtectSkinWIKI) { this.textureProtectSkinWIKI = textureProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  539 */     public String getTextureStallText() { return this.textureStallText; }
/*      */ 
/*      */ 
/*      */     
/*  543 */     public void setTextureStallText(String textureStallText) { this.textureStallText = textureStallText; }
/*      */ 
/*      */ 
/*      */     
/*  547 */     public String getPigmentStall() { return this.PigmentStall; }
/*      */ 
/*      */ 
/*      */     
/*  551 */     public void setPigmentStall(String PigmentStall) { this.PigmentStall = PigmentStall; }
/*      */ 
/*      */ 
/*      */     
/*  555 */     public String getTextureQuestion() { return this.textureQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  559 */     public void setTextureQuestion(String textureQuestion) { this.textureQuestion = textureQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  563 */     public String getTextureProductId() { return this.textureProductId; }
/*      */ 
/*      */ 
/*      */     
/*  567 */     public void setTextureProductId(String textureProductId) { this.textureProductId = textureProductId; }
/*      */ 
/*      */ 
/*      */     
/*  571 */     public String getWaterTips() { return this.waterTips; }
/*      */ 
/*      */ 
/*      */     
/*  575 */     public void setWaterTips(String waterTips) { this.waterTips = waterTips; }
/*      */ 
/*      */ 
/*      */     
/*  579 */     public String getResultCompleteTime() { return this.ResultCompleteTime; }
/*      */ 
/*      */ 
/*      */     
/*  583 */     public void setResultCompleteTime(String ResultCompleteTime) { this.ResultCompleteTime = ResultCompleteTime; }
/*      */ 
/*      */ 
/*      */     
/*  587 */     public String getWrinkleProductId() { return this.wrinkleProductId; }
/*      */ 
/*      */ 
/*      */     
/*  591 */     public void setWrinkleProductId(String wrinkleProductId) { this.wrinkleProductId = wrinkleProductId; }
/*      */ 
/*      */ 
/*      */     
/*  595 */     public String getOilQuestion() { return this.oilQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  599 */     public void setOilQuestion(String oilQuestion) { this.oilQuestion = oilQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  603 */     public String getTextureTips() { return this.textureTips; }
/*      */ 
/*      */ 
/*      */     
/*  607 */     public void setTextureTips(String textureTips) { this.textureTips = textureTips; }
/*      */ 
/*      */ 
/*      */     
/*  611 */     public String getInflammationStallText() { return this.inflammationStallText; }
/*      */ 
/*      */ 
/*      */     
/*  615 */     public void setInflammationStallText(String inflammationStallText) { this.inflammationStallText = inflammationStallText; }
/*      */ 
/*      */ 
/*      */     
/*  619 */     public String getTextureStall() { return this.TextureStall; }
/*      */ 
/*      */ 
/*      */     
/*  623 */     public void setTextureStall(String TextureStall) { this.TextureStall = TextureStall; }
/*      */ 
/*      */ 
/*      */     
/*  627 */     public String getSkinColorQuestion() { return this.skinColorQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  631 */     public void setSkinColorQuestion(String skinColorQuestion) { this.skinColorQuestion = skinColorQuestion; }
/*      */ 
/*      */ 
/*      */     
/*  635 */     public TextModel.DataBean.InflammationProtectSkinWIKIBean getInflammationProtectSkinWIKI() { return this.inflammationProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  639 */     public void setInflammationProtectSkinWIKI(TextModel.DataBean.InflammationProtectSkinWIKIBean inflammationProtectSkinWIKI) { this.inflammationProtectSkinWIKI = inflammationProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  643 */     public TextModel.DataBean.PigmentProtectSkinWIKIBean getPigmentProtectSkinWIKI() { return this.pigmentProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  647 */     public void setPigmentProtectSkinWIKI(TextModel.DataBean.PigmentProtectSkinWIKIBean pigmentProtectSkinWIKI) { this.pigmentProtectSkinWIKI = pigmentProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  651 */     public String getPigmentProductId() { return this.pigmentProductId; }
/*      */ 
/*      */ 
/*      */     
/*  655 */     public void setPigmentProductId(String pigmentProductId) { this.pigmentProductId = pigmentProductId; }
/*      */ 
/*      */ 
/*      */     
/*  659 */     public TextModel.DataBean.WrinkleProtectSkinWIKIBean getWrinkleProtectSkinWIKI() { return this.wrinkleProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  663 */     public void setWrinkleProtectSkinWIKI(TextModel.DataBean.WrinkleProtectSkinWIKIBean wrinkleProtectSkinWIKI) { this.wrinkleProtectSkinWIKI = wrinkleProtectSkinWIKI; }
/*      */ 
/*      */ 
/*      */     
/*  667 */     public String getInflammationTips() { return this.inflammationTips; }
/*      */ 
/*      */ 
/*      */     
/*  671 */     public void setInflammationTips(String inflammationTips) { this.inflammationTips = inflammationTips; }
/*      */ 
/*      */ 
/*      */     
/*  675 */     public String getMathAge() { return this.MathAge; }
/*      */ 
/*      */ 
/*      */     
/*  679 */     public void setMathAge(String MathAge) { this.MathAge = MathAge; }
/*      */ 
/*      */ 
/*      */     
/*  683 */     public String getPigmentTips() { return this.pigmentTips; }
/*      */ 
/*      */ 
/*      */     
/*  687 */     public void setPigmentTips(String pigmentTips) { this.pigmentTips = pigmentTips; }
/*      */ 
/*      */ 
/*      */     
/*  691 */     public String getOilStall() { return this.OilStall; }
/*      */ 
/*      */ 
/*      */     
/*  695 */     public void setOilStall(String OilStall) { this.OilStall = OilStall; }
/*      */ 
/*      */ 
/*      */     
/*  699 */     public String getImageName() { return this.ImageName; }
/*      */ 
/*      */ 
/*      */     
/*  703 */     public void setImageName(String ImageName) { this.ImageName = ImageName; }
/*      */ 
/*      */ 
/*      */     
/*  707 */     public String getPoreSizeStallText() { return this.poreSizeStallText; }
/*      */ 
/*      */ 
/*      */     
/*  711 */     public void setPoreSizeStallText(String poreSizeStallText) { this.poreSizeStallText = poreSizeStallText; }
/*      */ 
/*      */ 
/*      */     
/*  715 */     public double getInflammationValue() { return this.InflammationValue; }
/*      */ 
/*      */ 
/*      */     
/*  719 */     public void setInflammationValue(double InflammationValue) { this.InflammationValue = InflammationValue; }
/*      */ 
/*      */ 
/*      */     
/*  723 */     public String getUserId() { return this.UserId; }
/*      */ 
/*      */ 
/*      */     
/*  727 */     public void setUserId(String UserId) { this.UserId = UserId; }
/*      */ 
/*      */ 
/*      */     
/*  731 */     public String getInflammationProductId() { return this.inflammationProductId; }
/*      */ 
/*      */ 
/*      */     
/*  735 */     public void setInflammationProductId(String inflammationProductId) { this.inflammationProductId = inflammationProductId; }
/*      */ 
/*      */ 
/*      */     
/*  739 */     public double getOilValue() { return this.OilValue; }
/*      */ 
/*      */ 
/*      */     
/*  743 */     public void setOilValue(double OilValue) { this.OilValue = OilValue; }
/*      */ 
/*      */ 
/*      */     
/*  747 */     public String getWaterProductId() { return this.waterProductId; }
/*      */ 
/*      */ 
/*      */     
/*  751 */     public void setWaterProductId(String waterProductId) { this.waterProductId = waterProductId; }
/*      */ 
/*      */ 
/*      */     
/*  755 */     public List<TextModel.DataBean.PoreSizeProductListBean> getPoreSizeProductList() { return this.poreSizeProductList; }
/*      */ 
/*      */ 
/*      */     
/*  759 */     public void setPoreSizeProductList(List<TextModel.DataBean.PoreSizeProductListBean> poreSizeProductList) { this.poreSizeProductList = poreSizeProductList; }
/*      */ 
/*      */ 
/*      */     
/*  763 */     public List<TextModel.DataBean.TextureProductListBean> getTextureProductList() { return this.textureProductList; }
/*      */ 
/*      */ 
/*      */     
/*  767 */     public void setTextureProductList(List<TextModel.DataBean.TextureProductListBean> textureProductList) { this.textureProductList = textureProductList; }
/*      */ 
/*      */ 
/*      */     
/*  771 */     public List<TextModel.DataBean.OilProductListBean> getOilProductList() { return this.oilProductList; }
/*      */ 
/*      */ 
/*      */     
/*  775 */     public void setOilProductList(List<TextModel.DataBean.OilProductListBean> oilProductList) { this.oilProductList = oilProductList; }
/*      */ 
/*      */ 
/*      */     
/*  779 */     public List<TextModel.DataBean.WaterProductListBean> getWaterProductList() { return this.waterProductList; }
/*      */ 
/*      */ 
/*      */     
/*  783 */     public void setWaterProductList(List<TextModel.DataBean.WaterProductListBean> waterProductList) { this.waterProductList = waterProductList; }
/*      */ 
/*      */ 
/*      */     
/*  787 */     public List<TextModel.DataBean.WrinkleProductListBean> getWrinkleProductList() { return this.wrinkleProductList; }
/*      */ 
/*      */ 
/*      */     
/*  791 */     public void setWrinkleProductList(List<TextModel.DataBean.WrinkleProductListBean> wrinkleProductList) { this.wrinkleProductList = wrinkleProductList; }
/*      */ 
/*      */ 
/*      */     
/*  795 */     public List<TextModel.DataBean.PigmentProductListBean> getPigmentProductList() { return this.pigmentProductList; }
/*      */ 
/*      */ 
/*      */     
/*  799 */     public void setPigmentProductList(List<TextModel.DataBean.PigmentProductListBean> pigmentProductList) { this.pigmentProductList = pigmentProductList; }
/*      */ 
/*      */ 
/*      */     
/*  803 */     public List<TextModel.DataBean.SkinColorProductListBean> getSkinColorProductList() { return this.skinColorProductList; }
/*      */ 
/*      */ 
/*      */     
/*  807 */     public void setSkinColorProductList(List<TextModel.DataBean.SkinColorProductListBean> skinColorProductList) { this.skinColorProductList = skinColorProductList; }
/*      */ 
/*      */ 
/*      */     
/*  811 */     public List<TextModel.DataBean.InflammationProductListBean> getInflammationProductList() { return this.inflammationProductList; }
/*      */ 
/*      */ 
/*      */     
/*  815 */     public void setInflammationProductList(List<TextModel.DataBean.InflammationProductListBean> inflammationProductList) { this.inflammationProductList = inflammationProductList; }
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
/*  832 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  836 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  840 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  844 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  848 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  852 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  856 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  860 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/*  878 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  882 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  886 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  890 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  894 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  898 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  902 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  906 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/*  924 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  928 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  932 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  936 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  940 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  944 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  948 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  952 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/*  970 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/*  974 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/*  978 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/*  982 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/*  986 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/*  990 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/*  994 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/*  998 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1016 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1020 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1024 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1028 */       public void setProductName(String productName) { this.productName = productName; }
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
/* 1062 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1066 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1070 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1074 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1078 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1082 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1086 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1090 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1108 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1112 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1116 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1120 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1124 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1128 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1132 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1136 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1154 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1158 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1162 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1166 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1170 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1174 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1178 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1182 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1202 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1206 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1210 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1214 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1218 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1222 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1226 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1230 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1234 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1238 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1258 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1262 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1266 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1270 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1274 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1278 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1282 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1286 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1290 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1294 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1314 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1318 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1322 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1326 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1330 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1334 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1338 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1342 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1346 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1350 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1370 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1374 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1378 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1382 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1386 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1390 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1394 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1398 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1402 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1406 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1426 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1430 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1434 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1438 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1442 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1446 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1450 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1454 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1458 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1462 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1482 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1486 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1490 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1494 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1498 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1502 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1506 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1510 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1514 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1518 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1538 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1542 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1546 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1550 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1554 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1558 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1562 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1566 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1570 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1574 */       public void setGuidName(String guidName) { this.guidName = guidName; }
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
/* 1594 */       public int getId() { return this.id; }
/*      */ 
/*      */ 
/*      */       
/* 1598 */       public void setId(int id) { this.id = id; }
/*      */ 
/*      */ 
/*      */       
/* 1602 */       public String getProductName() { return this.productName; }
/*      */ 
/*      */ 
/*      */       
/* 1606 */       public void setProductName(String productName) { this.productName = productName; }
/*      */ 
/*      */ 
/*      */       
/* 1610 */       public String getUrlCode() { return this.urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1614 */       public void setUrlCode(String urlCode) { this.urlCode = urlCode; }
/*      */ 
/*      */ 
/*      */       
/* 1618 */       public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1622 */       public void setFileName(String fileName) { this.fileName = fileName; }
/*      */ 
/*      */ 
/*      */       
/* 1626 */       public String getGuidName() { return this.guidName; }
/*      */ 
/*      */ 
/*      */       
/* 1630 */       public void setGuidName(String guidName) { this.guidName = guidName; }
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\Model\HistoryModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */