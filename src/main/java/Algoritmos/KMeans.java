package Algoritmos;

import Rows.Row;
import Rows.RowWithLabel;
import Tables.Table;
import Tables.TableWithLabels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {

    private int numClusters;
    private int numIterations;
    private int seed;

    public KMeans(int numClusters, int numIterations, long seed){
        numClusters = numClusters;
        numIterations = numIterations;
        seed = seed;
    }


    public Integer estimate(List<Double> dato){
        int grupo = asignar_grupo();
    }

    public void train(Table datos){
        List<Row> representantes = new ArrayList<>();
        int tam = datos.getRows().size();
        for(int i = 0; i < 3; i++){
            Random random = new Random(seed);
            Row representante = datos.getRowAt(random.nextInt(tam));
            representantes.add(representante);
        }
        for(int j = 0; j < datos.getRows().size(); j++){
            estimate(datos.getRowAt(j).getData());
        }
    }

    public int asignar_grupo(List<Row> r, Table d){
        for(){

        }
    }


}
