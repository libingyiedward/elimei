/*     */ package com.elimei.elimei.Model;
/*     */ 
/*     */ import android.text.TextUtils;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MemBerModel
/*     */ {
/*     */   private int code;
/*     */   private String msg;
/*     */   private ResultBean result;
/*     */   
/*  27 */   public int getCode() { return this.code; }
/*     */ 
/*     */ 
/*     */   
/*  31 */   public void setCode(int code) { this.code = code; }
/*     */ 
/*     */ 
/*     */   
/*  35 */   public String getMsg() { return this.msg; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public void setMsg(String msg) { this.msg = msg; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public ResultBean getResult() { return this.result; }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public void setResult(ResultBean result) { this.result = result; }
/*     */ 
/*     */   
/*     */   public static class ResultBean
/*     */   {
/*     */     private List<MemberBean> member;
/*     */     
/*  54 */     public List<MemberBean> getMember() { return this.member; }
/*     */ 
/*     */ 
/*     */     
/*  58 */     public void setMember(List<MemberBean> member) { this.member = member; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class MemberBean
/*     */       implements Serializable
/*     */     {
/*  90 */       private String profession = "";
/*  91 */       private String country = "";
/*  92 */       private String jf = "";
/*  93 */       private String relname = "";
/*  94 */       private String dealer_id = "";
/*  95 */       private String commemorate = "";
/*  96 */       private String sex = "";
/*  97 */       private String email = "";
/*  98 */       private String birthday = "";
/*  99 */       private String subscribe_time = "";
/* 100 */       private String city = "";
/* 101 */       private String mobile = "";
/* 102 */       private String province = "";
/* 103 */       private String qq = "";
/* 104 */       private String age = "";
/* 105 */       private String district = "";
/* 106 */       private String address = "";
/* 107 */       private String language = "";
/* 108 */       private String phone = "";
/* 109 */       private String headimgurl = "";
/* 110 */       private String marriage = "";
/* 111 */       private String ctime = "";
/* 112 */       private String dealeridto = "";
/* 113 */       private String wxid = "";
/* 114 */       private String cid = "";
/*     */       
/*     */       public String getProfession() {
/* 117 */         if (TextUtils.isEmpty(this.profession)) {
/* 118 */           return "";
/*     */         }
/* 120 */         return this.profession;
/*     */       }
/*     */ 
/*     */       
/* 124 */       public void setProfession(String profession) { this.profession = profession; }
/*     */ 
/*     */       
/*     */       public String getCountry() {
/* 128 */         if (TextUtils.isEmpty(this.country)) {
/* 129 */           return "";
/*     */         }
/* 131 */         return this.country;
/*     */       }
/*     */ 
/*     */       
/* 135 */       public void setCountry(String country) { this.country = country; }
/*     */ 
/*     */       
/*     */       public String getJf() {
/* 139 */         if (TextUtils.isEmpty(this.jf)) {
/* 140 */           return "";
/*     */         }
/* 142 */         return this.jf;
/*     */       }
/*     */ 
/*     */       
/* 146 */       public void setJf(String jf) { this.jf = jf; }
/*     */ 
/*     */       
/*     */       public String getRelname() {
/* 150 */         if (TextUtils.isEmpty(this.relname)) {
/* 151 */           return "";
/*     */         }
/* 153 */         return this.relname;
/*     */       }
/*     */ 
/*     */       
/* 157 */       public void setRelname(String relname) { this.relname = relname; }
/*     */ 
/*     */       
/*     */       public String getDealer_id() {
/* 161 */         if (TextUtils.isEmpty(this.dealer_id)) {
/* 162 */           return "";
/*     */         }
/* 164 */         return this.dealer_id;
/*     */       }
/*     */ 
/*     */       
/* 168 */       public void setDealer_id(String dealer_id) { this.dealer_id = dealer_id; }
/*     */ 
/*     */       
/*     */       public String getCommemorate() {
/* 172 */         if (TextUtils.isEmpty(this.commemorate)) {
/* 173 */           return "";
/*     */         }
/* 175 */         return this.commemorate;
/*     */       }
/*     */ 
/*     */       
/* 179 */       public void setCommemorate(String commemorate) { this.commemorate = commemorate; }
/*     */ 
/*     */       
/*     */       public String getSex() {
/* 183 */         if (TextUtils.isEmpty(this.sex)) {
/* 184 */           return "";
/*     */         }
/* 186 */         return this.sex;
/*     */       }
/*     */ 
/*     */       
/* 190 */       public void setSex(String sex) { this.sex = sex; }
/*     */ 
/*     */       
/*     */       public String getEmail() {
/* 194 */         if (TextUtils.isEmpty(this.email)) {
/* 195 */           return "";
/*     */         }
/* 197 */         return this.email;
/*     */       }
/*     */ 
/*     */       
/* 201 */       public void setEmail(String email) { this.email = email; }
/*     */ 
/*     */       
/*     */       public String getBirthday() {
/* 205 */         if (TextUtils.isEmpty(this.birthday)) {
/* 206 */           return "";
/*     */         }
/* 208 */         return this.birthday;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 213 */       public void setBirthday(String birthday) { this.birthday = birthday; }
/*     */ 
/*     */       
/*     */       public String getSubscribe_time() {
/* 217 */         if (TextUtils.isEmpty(this.subscribe_time)) {
/* 218 */           return "";
/*     */         }
/* 220 */         return this.subscribe_time;
/*     */       }
/*     */ 
/*     */       
/* 224 */       public void setSubscribe_time(String subscribe_time) { this.subscribe_time = subscribe_time; }
/*     */ 
/*     */       
/*     */       public String getCity() {
/* 228 */         if (TextUtils.isEmpty(this.city)) {
/* 229 */           return "";
/*     */         }
/* 231 */         return this.city;
/*     */       }
/*     */ 
/*     */       
/* 235 */       public void setCity(String city) { this.city = city; }
/*     */ 
/*     */       
/*     */       public String getMobile() {
/* 239 */         if (TextUtils.isEmpty(this.mobile)) {
/* 240 */           return "";
/*     */         }
/* 242 */         return this.mobile;
/*     */       }
/*     */ 
/*     */       
/* 246 */       public void setMobile(String mobile) { this.mobile = mobile; }
/*     */ 
/*     */       
/*     */       public String getProvince() {
/* 250 */         if (TextUtils.isEmpty(this.province)) {
/* 251 */           return "";
/*     */         }
/* 253 */         return this.province;
/*     */       }
/*     */ 
/*     */       
/* 257 */       public void setProvince(String province) { this.province = province; }
/*     */ 
/*     */       
/*     */       public String getQq() {
/* 261 */         if (TextUtils.isEmpty(this.qq)) {
/* 262 */           return "";
/*     */         }
/* 264 */         return this.qq;
/*     */       }
/*     */ 
/*     */       
/* 268 */       public void setQq(String qq) { this.qq = qq; }
/*     */ 
/*     */       
/*     */       public String getAge() {
/* 272 */         if (TextUtils.isEmpty(this.age)) {
/* 273 */           return "";
/*     */         }
/* 275 */         return this.age;
/*     */       }
/*     */ 
/*     */       
/* 279 */       public void setAge(String age) { this.age = age; }
/*     */ 
/*     */       
/*     */       public String getDistrict() {
/* 283 */         if (TextUtils.isEmpty(this.district)) {
/* 284 */           return "";
/*     */         }
/* 286 */         return this.district;
/*     */       }
/*     */ 
/*     */       
/* 290 */       public void setDistrict(String district) { this.district = district; }
/*     */ 
/*     */       
/*     */       public String getAddress() {
/* 294 */         if (TextUtils.isEmpty(this.address)) {
/* 295 */           return "";
/*     */         }
/* 297 */         return this.address;
/*     */       }
/*     */ 
/*     */       
/* 301 */       public void setAddress(String address) { this.address = address; }
/*     */ 
/*     */       
/*     */       public String getLanguage() {
/* 305 */         if (TextUtils.isEmpty(this.language)) {
/* 306 */           return "";
/*     */         }
/* 308 */         return this.language;
/*     */       }
/*     */ 
/*     */       
/* 312 */       public void setLanguage(String language) { this.language = language; }
/*     */ 
/*     */       
/*     */       public String getPhone() {
/* 316 */         if (TextUtils.isEmpty(this.phone)) {
/* 317 */           return "";
/*     */         }
/* 319 */         return this.phone;
/*     */       }
/*     */ 
/*     */       
/* 323 */       public void setPhone(String phone) { this.phone = phone; }
/*     */ 
/*     */       
/*     */       public String getHeadimgurl() {
/* 327 */         if (TextUtils.isEmpty(this.headimgurl)) {
/* 328 */           return "";
/*     */         }
/* 330 */         return this.headimgurl;
/*     */       }
/*     */ 
/*     */       
/* 334 */       public void setHeadimgurl(String headimgurl) { this.headimgurl = headimgurl; }
/*     */ 
/*     */       
/*     */       public String getMarriage() {
/* 338 */         if (TextUtils.isEmpty(this.marriage)) {
/* 339 */           return "";
/*     */         }
/* 341 */         return this.marriage;
/*     */       }
/*     */ 
/*     */       
/* 345 */       public void setMarriage(String marriage) { this.marriage = marriage; }
/*     */ 
/*     */       
/*     */       public String getCtime() {
/* 349 */         if (TextUtils.isEmpty(this.ctime)) {
/* 350 */           return "";
/*     */         }
/* 352 */         return this.ctime;
/*     */       }
/*     */ 
/*     */       
/* 356 */       public void setCtime(String ctime) { this.ctime = ctime; }
/*     */ 
/*     */       
/*     */       public String getDealeridto() {
/* 360 */         if (TextUtils.isEmpty(this.dealeridto)) {
/* 361 */           return "";
/*     */         }
/* 363 */         return this.dealeridto;
/*     */       }
/*     */ 
/*     */       
/* 367 */       public void setDealeridto(String dealeridto) { this.dealeridto = dealeridto; }
/*     */ 
/*     */       
/*     */       public String getWxid() {
/* 371 */         if (TextUtils.isEmpty(this.wxid)) {
/* 372 */           return "";
/*     */         }
/* 374 */         return this.wxid;
/*     */       }
/*     */ 
/*     */       
/* 378 */       public void setWxid(String wxid) { this.wxid = wxid; }
/*     */ 
/*     */       
/*     */       public String getCid() {
/* 382 */         if (TextUtils.isEmpty(this.cid)) {
/* 383 */           return "";
/*     */         }
/* 385 */         return this.cid;
/*     */       }
/*     */ 
/*     */       
/* 389 */       public void setCid(String cid) { this.cid = cid; }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\Model\MemBerModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */