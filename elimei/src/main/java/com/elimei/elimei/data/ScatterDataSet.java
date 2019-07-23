/*     */ package com.elimei.elimei.data;
/*     */ 
/*     */ import com.elimei.elimei.charts.ScatterChart;
/*     */ import com.elimei.elimei.interfaces.datasets.IScatterDataSet;
/*     */ import com.elimei.elimei.renderer.scatter.ChevronDownShapeRenderer;
/*     */ import com.elimei.elimei.renderer.scatter.ChevronUpShapeRenderer;
/*     */ import com.elimei.elimei.renderer.scatter.CircleShapeRenderer;
/*     */ import com.elimei.elimei.renderer.scatter.CrossShapeRenderer;
/*     */ import com.elimei.elimei.renderer.scatter.IShapeRenderer;
/*     */ import com.elimei.elimei.renderer.scatter.SquareShapeRenderer;
/*     */ import com.elimei.elimei.renderer.scatter.TriangleShapeRenderer;
/*     */ import com.elimei.elimei.renderer.scatter.XShapeRenderer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScatterDataSet
/*     */   extends LineScatterCandleRadarDataSet<Entry>
/*     */   implements IScatterDataSet
/*     */ {
/*  25 */   private float mShapeSize = 15.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   protected IShapeRenderer mShapeRenderer = new SquareShapeRenderer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private float mScatterShapeHoleRadius = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private int mScatterShapeHoleColor = 1122867;
/*     */ 
/*     */   
/*  46 */   public ScatterDataSet(List<Entry> yVals, String label) { super(yVals, label); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSet<Entry> copy() {
/*  52 */     List<Entry> yVals = new ArrayList<Entry>();
/*     */     
/*  54 */     for (int i = 0; i < this.mValues.size(); i++) {
/*  55 */       yVals.add(((Entry)this.mValues.get(i)).copy());
/*     */     }
/*     */     
/*  58 */     ScatterDataSet copied = new ScatterDataSet(yVals, getLabel());
/*  59 */     copied.mDrawValues = this.mDrawValues;
/*  60 */     copied.mValueColors = this.mValueColors;
/*  61 */     copied.mColors = this.mColors;
/*  62 */     copied.mShapeSize = this.mShapeSize;
/*  63 */     copied.mShapeRenderer = this.mShapeRenderer;
/*  64 */     copied.mScatterShapeHoleRadius = this.mScatterShapeHoleRadius;
/*  65 */     copied.mScatterShapeHoleColor = this.mScatterShapeHoleColor;
/*  66 */     copied.mHighlightLineWidth = this.mHighlightLineWidth;
/*  67 */     copied.mHighLightColor = this.mHighLightColor;
/*  68 */     copied.mHighlightDashPathEffect = this.mHighlightDashPathEffect;
/*     */     
/*  70 */     return copied;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setScatterShapeSize(float size) { this.mShapeSize = size; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public float getScatterShapeSize() { return this.mShapeSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setScatterShape(ScatterChart.ScatterShape shape) { this.mShapeRenderer = getRendererForShape(shape); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setShapeRenderer(IShapeRenderer shapeRenderer) { this.mShapeRenderer = shapeRenderer; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public IShapeRenderer getShapeRenderer() { return this.mShapeRenderer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setScatterShapeHoleRadius(float holeRadius) { this.mScatterShapeHoleRadius = holeRadius; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public float getScatterShapeHoleRadius() { return this.mScatterShapeHoleRadius; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public void setScatterShapeHoleColor(int holeColor) { this.mScatterShapeHoleColor = holeColor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public int getScatterShapeHoleColor() { return this.mScatterShapeHoleColor; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IShapeRenderer getRendererForShape(ScatterChart.ScatterShape shape) {
/* 144 */     switch (shape) { case SQUARE:
/* 145 */         return new SquareShapeRenderer();
/* 146 */       case CIRCLE: return new CircleShapeRenderer();
/* 147 */       case TRIANGLE: return new TriangleShapeRenderer();
/* 148 */       case CROSS: return new CrossShapeRenderer();
/* 149 */       case X: return new XShapeRenderer();
/* 150 */       case CHEVRON_UP: return new ChevronUpShapeRenderer();
/* 151 */       case CHEVRON_DOWN: return new ChevronDownShapeRenderer(); }
/*     */ 
/*     */     
/* 154 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\data\ScatterDataSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */