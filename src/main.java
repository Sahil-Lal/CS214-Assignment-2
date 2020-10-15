import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws InterruptedException {

        // Create adjacency matrix
        int size = 0;
        double [][]sample = new double[0][0];

        try{
            File sample_data = new File("C:\\Users\\Admin\\Documents\\SoloLearn\\intellij idea\\CS214_Assignment_2\\src\\testDataFiles\\br17.atsp");
            Scanner sc = new Scanner(sample_data);
            double inf = Double.POSITIVE_INFINITY;
            //System.out.println("File found!");
            String value;
            double Value;

            value = sc.next();
            if (0 == value.compareTo("DIMENSION:")){
                value = sc.next();
                size = (int)Double.parseDouble(value);


                sample = new double[size][size];
                //System.out.println(size);
                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        Value = sc.nextDouble();
                        if (Value == 9999 || Value >= 9999){
                            Value = Double.POSITIVE_INFINITY;
                        }
                        else {
                            sample[i][j] = Value;
                        }
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int startNode = 0;

        int x ;
        ArrayList<Double> TSP_DP = new ArrayList<>();
        ArrayList<Double> TSP_PraticleSO = new ArrayList<>();
        Scanner choice = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1\t\tRun algorithms");
            System.out.println("2\t\tPlot Graph");
            System.out.println("3\t\tExit");

            switch (choice.nextInt()) {
                case 1:
                    Thread DP = new Thread(new TSP_Dynamic_Programming(startNode, sample));
                    Thread PSO = new Thread(new TSP_PSO());

                    DP.start();
                    PSO.start();

                    DP.sleep(100);
                    PSO.sleep(100);

                    break;
                case 2:

                    TSP_DP.add(0.0);
                    TSP_DP.add(TSP_Dynamic_Programming.getTourDistance());
                    TSP_PraticleSO = TSP_PSO.data_collector;



                    //graphing data
                    graph chart = new graph("TSP", "Multithreading Result", "No.of Iteration", TSP_DP, TSP_PraticleSO);
                    chart.pack();
                    RefineryUtilities.centerFrameOnScreen(chart);
                    chart.setVisible(true);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    break;
            }
        }

    }
}
