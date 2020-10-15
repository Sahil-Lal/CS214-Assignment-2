/*
    The code below was taken from http://tutorialspoint.com and was redesign and modified
    to be suitable for our program
*/

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.ArrayList;

public class graph extends ApplicationFrame {

    public graph(String applicationTitle, String chartTitle, String xlabel, ArrayList<Double> thread_1, ArrayList<Double> thread_2){
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle ,
                xlabel ,
                "Distance" ,
                createDataset(thread_1,thread_2) ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new Dimension( 560 , 560 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );

        renderer.setSeriesStroke( 0 , new BasicStroke( 3f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 3f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset( ArrayList<Double> thread_1,ArrayList<Double> thread_2) {

        final XYSeries TSP_dynamic_pro = new XYSeries( "TSP_DP" );
        for(int i = 0; i < thread_1.size()-1; i++){
            TSP_dynamic_pro.add( i , thread_1.get(i) );
        }
        TSP_dynamic_pro.add( TSP_Dynamic_Programming.counter , thread_1.get(1) );

        final XYSeries TSP_pratical = new XYSeries( "TSP_PSO" );
        for(int i = 0; i < thread_2.size(); i++){
            TSP_pratical.add( i , thread_2.get(i) );
        }

        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( TSP_dynamic_pro);
        dataset.addSeries( TSP_pratical);
        return dataset;
    }
}



