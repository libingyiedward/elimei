/*    */ package com.elimei.elimei.highlight;
/*    */ 
/*    */ import com.elimei.elimei.charts.RadarChart;
/*    */ import com.elimei.elimei.data.Entry;
/*    */ import com.elimei.elimei.data.RadarData;
/*    */ import com.elimei.elimei.interfaces.datasets.IDataSet;
/*    */ import com.elimei.elimei.utils.MPPointF;
/*    */ import com.elimei.elimei.utils.Utils;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RadarHighlighter
/*    */   extends PieRadarHighlighter<RadarChart>
/*    */ {
/* 17 */   public RadarHighlighter(RadarChart chart) { super(chart); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Highlight getClosestHighlight(int index, float x, float y) {
/* 23 */     List<Highlight> highlights = getHighlightsAtIndex(index);
/*    */     
/* 25 */     float distanceToCenter = ((RadarChart)this.mChart).distanceToCenter(x, y) / ((RadarChart)this.mChart).getFactor();
/*    */     
/* 27 */     Highlight closest = null;
/* 28 */     float distance = Float.MAX_VALUE;
/*    */     
/* 30 */     for (int i = 0; i < highlights.size(); i++) {
/*    */       
/* 32 */       Highlight high = (Highlight)highlights.get(i);
/*    */       
/* 34 */       float cdistance = Math.abs(high.getY() - distanceToCenter);
/* 35 */       if (cdistance < distance) {
/* 36 */         closest = high;
/* 37 */         distance = cdistance;
/*    */       } 
/*    */     } 
/*    */     
/* 41 */     return closest;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected List<Highlight> getHighlightsAtIndex(int index) {
/* 54 */     this.mHighlightBuffer.clear();
/*    */     
/* 56 */     float phaseX = ((RadarChart)this.mChart).getAnimator().getPhaseX();
/* 57 */     float phaseY = ((RadarChart)this.mChart).getAnimator().getPhaseY();
/* 58 */     float sliceangle = ((RadarChart)this.mChart).getSliceAngle();
/* 59 */     float factor = ((RadarChart)this.mChart).getFactor();
/*    */     
/* 61 */     MPPointF pOut = MPPointF.getInstance(0.0F, 0.0F);
/* 62 */     for (int i = 0; i < ((RadarData)((RadarChart)this.mChart).getData()).getDataSetCount(); i++) {
/*    */       
/* 64 */       IDataSet<?> dataSet = ((RadarData)((RadarChart)this.mChart).getData()).getDataSetByIndex(i);
/*    */       
/* 66 */       Entry entry = dataSet.getEntryForIndex(index);
/*    */       
/* 68 */       float y = entry.getY() - ((RadarChart)this.mChart).getYChartMin();
/*    */       
/* 70 */       Utils.getPosition(((RadarChart)this.mChart)
/* 71 */           .getCenterOffsets(), y * factor * phaseY, sliceangle * index * phaseX + ((RadarChart)this.mChart)
/* 72 */           .getRotationAngle(), pOut);
/*    */       
/* 74 */       this.mHighlightBuffer.add(new Highlight(index, entry.getY(), pOut.x, pOut.y, i, dataSet.getAxisDependency()));
/*    */     } 
/*    */     
/* 77 */     return this.mHighlightBuffer;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator.EIT-20120726YNS\Desktop\classes.jar!\com\elimei\elimei\highlight\RadarHighlighter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.5
 */