package com.elimei.elimei.data;

import com.elimei.elimei.highlight.Highlight;
import com.elimei.elimei.interfaces.datasets.IRadarDataSet;

import java.util.Arrays;
import java.util.List;


public class RadarData
        extends ChartData<IRadarDataSet> {
    private List<String> mLabels;

    public RadarData() {
    }

    public RadarData(List<IRadarDataSet> dataSets) {
        super(dataSets);
    }

    public RadarData(IRadarDataSet... dataSets) {
        super(dataSets);
    }


    public void setLabels(List<String> labels) {
        this.mLabels = labels;
    }


    public void setLabels(String... labels) {
        this.mLabels = Arrays.asList(labels);
    }


    public List<String> getLabels() {
        return this.mLabels;
    }


    public Entry getEntryForHighlight(Highlight highlight) {
        return ((IRadarDataSet) getDataSetByIndex(highlight.getDataSetIndex())).getEntryForIndex((int) highlight.getX());
    }
}
