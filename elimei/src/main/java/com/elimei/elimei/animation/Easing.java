/*     */ package com.elimei.elimei.animation;
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
/*     */ public class Easing
/*     */ {
/*     */   public enum EasingOption
/*     */   {
/*  16 */     Linear,
/*  17 */     EaseInQuad,
/*  18 */     EaseOutQuad,
/*  19 */     EaseInOutQuad,
/*  20 */     EaseInCubic,
/*  21 */     EaseOutCubic,
/*  22 */     EaseInOutCubic,
/*  23 */     EaseInQuart,
/*  24 */     EaseOutQuart,
/*  25 */     EaseInOutQuart,
/*  26 */     EaseInSine,
/*  27 */     EaseOutSine,
/*  28 */     EaseInOutSine,
/*  29 */     EaseInExpo,
/*  30 */     EaseOutExpo,
/*  31 */     EaseInOutExpo,
/*  32 */     EaseInCirc,
/*  33 */     EaseOutCirc,
/*  34 */     EaseInOutCirc,
/*  35 */     EaseInElastic,
/*  36 */     EaseOutElastic,
/*  37 */     EaseInOutElastic,
/*  38 */     EaseInBack,
/*  39 */     EaseOutBack,
/*  40 */     EaseInOutBack,
/*  41 */     EaseInBounce,
/*  42 */     EaseOutBounce,
/*  43 */     EaseInOutBounce;
/*     */   }
/*     */   
/*     */   public static EasingFunction getEasingFunctionFromOption(EasingOption easing) {
/*  47 */     switch (easing)
/*     */     
/*     */     { default:
/*  50 */         return EasingFunctions.Linear;
/*     */       case EaseInQuad:
/*  52 */         return EasingFunctions.EaseInQuad;
/*     */       case EaseOutQuad:
/*  54 */         return EasingFunctions.EaseOutQuad;
/*     */       case EaseInOutQuad:
/*  56 */         return EasingFunctions.EaseInOutQuad;
/*     */       case EaseInCubic:
/*  58 */         return EasingFunctions.EaseInCubic;
/*     */       case EaseOutCubic:
/*  60 */         return EasingFunctions.EaseOutCubic;
/*     */       case EaseInOutCubic:
/*  62 */         return EasingFunctions.EaseInOutCubic;
/*     */       case EaseInQuart:
/*  64 */         return EasingFunctions.EaseInQuart;
/*     */       case EaseOutQuart:
/*  66 */         return EasingFunctions.EaseOutQuart;
/*     */       case EaseInOutQuart:
/*  68 */         return EasingFunctions.EaseInOutQuart;
/*     */       case EaseInSine:
/*  70 */         return EasingFunctions.EaseInSine;
/*     */       case EaseOutSine:
/*  72 */         return EasingFunctions.EaseOutSine;
/*     */       case EaseInOutSine:
/*  74 */         return EasingFunctions.EaseInOutSine;
/*     */       case EaseInExpo:
/*  76 */         return EasingFunctions.EaseInExpo;
/*     */       case EaseOutExpo:
/*  78 */         return EasingFunctions.EaseOutExpo;
/*     */       case EaseInOutExpo:
/*  80 */         return EasingFunctions.EaseInOutExpo;
/*     */       case EaseInCirc:
/*  82 */         return EasingFunctions.EaseInCirc;
/*     */       case EaseOutCirc:
/*  84 */         return EasingFunctions.EaseOutCirc;
/*     */       case EaseInOutCirc:
/*  86 */         return EasingFunctions.EaseInOutCirc;
/*     */       case EaseInElastic:
/*  88 */         return EasingFunctions.EaseInElastic;
/*     */       case EaseOutElastic:
/*  90 */         return EasingFunctions.EaseOutElastic;
/*     */       case EaseInOutElastic:
/*  92 */         return EasingFunctions.EaseInOutElastic;
/*     */       case EaseInBack:
/*  94 */         return EasingFunctions.EaseInBack;
/*     */       case EaseOutBack:
/*  96 */         return EasingFunctions.EaseOutBack;
/*     */       case EaseInOutBack:
/*  98 */         return EasingFunctions.EaseInOutBack;
/*     */       case EaseInBounce:
/* 100 */         return EasingFunctions.EaseInBounce;
/*     */       case EaseOutBounce:
/* 102 */         return EasingFunctions.EaseOutBounce;
/*     */       case EaseInOutBounce:
/* 104 */         break; }  return EasingFunctions.EaseInOutBounce;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class EasingFunctions
/*     */   {
/* 115 */     public static final EasingFunction Linear = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 123 */           return input;
/*     */         }
/*     */       };
/*     */     
/* 127 */     public static final EasingFunction EaseInQuad = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 136 */           return input * input;
/*     */         }
/*     */       };
/*     */     
/* 140 */     public static final EasingFunction EaseOutQuad = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 149 */           return -input * (input - 2.0F);
/*     */         }
/*     */       };
/*     */     
/* 153 */     public static final EasingFunction EaseInOutQuad = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 167 */           float position = input / 0.5F;
/*     */           
/* 169 */           if (position < 1.0F) {
/* 170 */             return 0.5F * position * position;
/*     */           }
/*     */           
/* 173 */           return -0.5F * (--position * (position - 2.0F) - 1.0F);
/*     */         }
/*     */       };
/*     */     
/* 177 */     public static final EasingFunction EaseInCubic = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 186 */           return input * input * input;
/*     */         }
/*     */       };
/*     */     
/* 190 */     public static final EasingFunction EaseOutCubic = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 201 */           input--;
/* 202 */           return input * input * input + 1.0F;
/*     */         }
/*     */       };
/*     */     
/* 206 */     public static final EasingFunction EaseInOutCubic = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 222 */           float position = input / 0.5F;
/* 223 */           if (position < 1.0F) {
/* 224 */             return 0.5F * position * position * position;
/*     */           }
/* 226 */           position -= 2.0F;
/* 227 */           return 0.5F * (position * position * position + 2.0F);
/*     */         }
/*     */       };
/*     */     
/* 231 */     public static final EasingFunction EaseInQuart = new EasingFunction()
/*     */       {
/*     */         public float getInterpolation(float input) {
/* 234 */           return input * input * input * input;
/*     */         }
/*     */       };
/*     */     
/* 238 */     public static final EasingFunction EaseOutQuart = new EasingFunction()
/*     */       {
/*     */         public float getInterpolation(float input) {
/* 241 */           input--;
/* 242 */           return -(input * input * input * input - 1.0F);
/*     */         }
/*     */       };
/*     */     
/* 246 */     public static final EasingFunction EaseInOutQuart = new EasingFunction()
/*     */       {
/*     */         public float getInterpolation(float input)
/*     */         {
/* 250 */           float position = input / 0.5F;
/* 251 */           if (position < 1.0F) {
/* 252 */             return 0.5F * position * position * position * position;
/*     */           }
/* 254 */           position -= 2.0F;
/* 255 */           return -0.5F * (position * position * position * position - 2.0F);
/*     */         }
/*     */       };
/*     */     
/* 259 */     public static final EasingFunction EaseInSine = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 267 */           return -((float)Math.cos(input * 1.5707963267948966D)) + 1.0F;
/*     */         }
/*     */       };
/*     */     
/* 271 */     public static final EasingFunction EaseOutSine = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 279 */           return (float)Math.sin(input * 1.5707963267948966D);
/*     */         }
/*     */       };
/*     */     
/* 283 */     public static final EasingFunction EaseInOutSine = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 292 */           return -0.5F * ((float)Math.cos(Math.PI * input) - 1.0F);
/*     */         }
/*     */       };
/*     */     
/* 296 */     public static final EasingFunction EaseInExpo = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 304 */           return (input == 0.0F) ? 0.0F : (float)Math.pow(2.0D, (10.0F * (input - 1.0F)));
/*     */         }
/*     */       };
/*     */     
/* 308 */     public static final EasingFunction EaseOutExpo = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 318 */           return (input == 1.0F) ? 1.0F : -((float)Math.pow(2.0D, (-10.0F * (input + 1.0F))));
/*     */         }
/*     */       };
/*     */     
/* 322 */     public static final EasingFunction EaseInOutExpo = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 346 */           if (input == 0.0F)
/*     */           {
/* 348 */             return 0.0F;
/*     */           }
/* 350 */           if (input == 1.0F)
/*     */           {
/* 352 */             return 1.0F;
/*     */           }
/*     */           
/* 355 */           float position = input / 0.5F;
/* 356 */           if (position < 1.0F)
/*     */           {
/* 358 */             return 0.5F * (float)Math.pow(2.0D, (10.0F * (position - 1.0F)));
/*     */           }
/* 360 */           return 0.5F * (-((float)Math.pow(2.0D, (-10.0F * --position))) + 2.0F);
/*     */         }
/*     */       };
/*     */     
/* 364 */     public static final EasingFunction EaseInCirc = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 373 */           return -((float)Math.sqrt((1.0F - input * input)) - 1.0F);
/*     */         }
/*     */       };
/*     */     
/* 377 */     public static final EasingFunction EaseOutCirc = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 386 */           input--;
/* 387 */           return (float)Math.sqrt((1.0F - input * input));
/*     */         }
/*     */       };
/*     */     
/* 391 */     public static final EasingFunction EaseInOutCirc = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 408 */           float position = input / 0.5F;
/* 409 */           if (position < 1.0F)
/*     */           {
/* 411 */             return -0.5F * ((float)Math.sqrt((1.0F - position * position)) - 1.0F);
/*     */           }
/* 413 */           return 0.5F * ((float)Math.sqrt((1.0F - (position -= 2.0F) * position)) + 1.0F);
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 418 */     public static final EasingFunction EaseInElastic = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 444 */           if (input == 0.0F)
/*     */           {
/* 446 */             return 0.0F;
/*     */           }
/*     */           
/* 449 */           float position = input;
/* 450 */           if (position == 1.0F)
/*     */           {
/* 452 */             return 1.0F;
/*     */           }
/*     */           
/* 455 */           float p = 0.3F;
/* 456 */           float s = p / 6.2831855F * (float)Math.asin(1.0D);
/* 457 */           return 
/*     */             
/* 459 */             -((float)Math.pow(2.0D, (10.0F * --position)) * (float)Math.sin((position - s) * 6.283185307179586D / p));
/*     */         }
/*     */       };
/*     */     
/* 463 */     public static final EasingFunction EaseOutElastic = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 488 */           if (input == 0.0F)
/*     */           {
/* 490 */             return 0.0F;
/*     */           }
/*     */           
/* 493 */           float position = input;
/* 494 */           if (position == 1.0F)
/*     */           {
/* 496 */             return 1.0F;
/*     */           }
/*     */           
/* 499 */           float p = 0.3F;
/* 500 */           float s = p / 6.2831855F * (float)Math.asin(1.0D);
/* 501 */           return (float)Math.pow(2.0D, (-10.0F * position)) * 
/* 502 */             (float)Math.sin((position - s) * 6.283185307179586D / p) + 1.0F;
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 507 */     public static final EasingFunction EaseInOutElastic = new EasingFunction()
/*     */       {
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
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 541 */           if (input == 0.0F)
/*     */           {
/* 543 */             return 0.0F;
/*     */           }
/*     */           
/* 546 */           float position = input / 0.5F;
/* 547 */           if (position == 2.0F)
/*     */           {
/* 549 */             return 1.0F;
/*     */           }
/*     */           
/* 552 */           float p = 0.45000002F;
/* 553 */           float s = p / 6.2831855F * (float)Math.asin(1.0D);
/* 554 */           if (position < 1.0F)
/*     */           {
/* 556 */             return -0.5F * 
/* 557 */               (float)Math.pow(2.0D, (10.0F * --position)) * 
/* 558 */               (float)Math.sin((position * 1.0F - s) * 6.283185307179586D / p);
/*     */           }
/* 560 */           return (float)Math.pow(2.0D, (-10.0F * --position)) * 
/* 561 */             (float)Math.sin((position * 1.0F - s) * 6.283185307179586D / p) * 0.5F + 1.0F;
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */     
/* 567 */     public static final EasingFunction EaseInBack = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 578 */           float s = 1.70158F;
/* 579 */           float position = input;
/* 580 */           return position * position * (2.70158F * position - 1.70158F);
/*     */         }
/*     */       };
/*     */     
/* 584 */     public static final EasingFunction EaseOutBack = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 596 */           float s = 1.70158F;
/* 597 */           float position = input;
/* 598 */           position--;
/* 599 */           return position * position * (2.70158F * position + 1.70158F) + 1.0F;
/*     */         }
/*     */       };
/*     */     
/* 603 */     public static final EasingFunction EaseInOutBack = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 621 */           float s = 1.70158F;
/* 622 */           float position = input / 0.5F;
/* 623 */           if (position < 1.0F)
/*     */           {
/* 625 */             return 0.5F * position * position * ((s *= 1.525F + 1.0F) * position - s);
/*     */           }
/*     */           
/* 628 */           return 0.5F * ((position -= 2.0F) * position * ((s *= 1.525F + 1.0F) * position + s) + 2.0F);
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 633 */     public static final EasingFunction EaseInBounce = new EasingFunction()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public float getInterpolation(float input)
/*     */         {
/* 643 */           return 1.0F - Easing.EasingFunctions.EaseOutBounce.getInterpolation(1.0F - input);
/*     */         }
/*     */       };
/*     */     
/* 647 */     public static final EasingFunction EaseOutBounce = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 675 */           float position = input;
/* 676 */           if (position < 0.36363637F)
/*     */           {
/* 678 */             return 7.5625F * position * position;
/*     */           }
/* 680 */           if (position < 0.72727275F)
/*     */           {
/* 682 */             return 7.5625F * (position -= 0.54545456F) * position + 0.75F;
/*     */           }
/* 684 */           if (position < 0.90909094F)
/*     */           {
/* 686 */             return 7.5625F * (position -= 0.8181818F) * position + 0.9375F;
/*     */           }
/*     */ 
/*     */           
/* 690 */           return 7.5625F * (position -= 0.95454544F) * position + 0.984375F;
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */     
/* 696 */     public static final EasingFunction EaseInOutBounce = new EasingFunction()
/*     */       {
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
/*     */         public float getInterpolation(float input)
/*     */         {
/* 711 */           if (input < 0.5F)
/*     */           {
/* 713 */             return Easing.EasingFunctions.EaseInBounce.getInterpolation(input * 2.0F) * 0.5F;
/*     */           }
/* 715 */           return Easing.EasingFunctions.EaseOutBounce.getInterpolation(input * 2.0F - 1.0F) * 0.5F + 0.5F;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\animation\Easing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */