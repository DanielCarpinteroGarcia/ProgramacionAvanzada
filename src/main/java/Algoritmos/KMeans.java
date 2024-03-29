package Algoritmos;

import Distancias.Distance;
import Distancias.DistanceClient;
import Distancias.EuclideanDistance;
import Rows.Row;
import Tables.Table;

import java.util.*;

public class KMeans implements DistanceClient, Algorithm<Table, Integer, List<Double>> {
    private int numClusters;
    private int numIterations;
    private long seed;
    private Distance distancia;
    private List<Row> representantes = new ArrayList<>();

    public KMeans() {
        this.distancia = new EuclideanDistance();
    }

    public void setRepresentantes(List<Row> representantes) {
        this.representantes = representantes;
    }
    public KMeans(int numClusters, int numIterations, long seed, Distance distance) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.distancia = distance;
    }

    public void train(Table datos) throws KMeansException {

        if(numClusters < 1 || numClusters > datos.size()) {
            throw new KMeansException("El número Clusters es mayor que el número de Datos");
        }

        List<Integer> listaIndices = new ArrayList<>();
        for(int i = 0; i < datos.size(); i++) {
            listaIndices.add(i);
        }
        Random random = new Random(seed);
        Collections.shuffle(listaIndices, random);
        for(int i = 0; i<numClusters; i++) {
            representantes.add(datos.getRowAt(listaIndices.get(i)));
        }

        List<Integer> asignacionesGrupos = new ArrayList<>();
        for(int i = 0; i< numIterations; i++) {
            asignacionesGrupos.clear();
            for(int j = 0; j<datos.size(); j++) {
                asignacionesGrupos.add(asignarGrupo(datos.getData(j)));
            }
            calculoCentroide(asignacionesGrupos,datos);
        }
    }

    public Integer estimate(List<Double> dato) {
        return asignarGrupo(dato);
    }

    public Integer asignarGrupo(List<Double> dato) {
        Integer rep = 0;
        double menor = distancia.calculateDistance(dato,representantes.get(0).getData());

        for(int i = 0; i < representantes.size(); i++){
            double distance = distancia.calculateDistance(dato,representantes.get(i).getData());
            if(distance < menor){
                menor = distance;
                rep = i;
            }
        }
        return rep;
    }

    public void calculoCentroide(List<Integer> asignacionesGrupos, Table datos) {
        List<Integer> tamCentroide = new ArrayList<>();
        for( int i = 0; i<representantes.size(); i++) {
            tamCentroide.add(0);
        }
        for( int i = 0; i<asignacionesGrupos.size(); i++) {
            int grupo = asignacionesGrupos.get(i);
            representantes.set(grupo, sumar(datos.getData(i),representantes.get(grupo).getData()));
            tamCentroide.set(grupo, tamCentroide.get(grupo) + 1);
        }
        for(int i = 0; i < representantes.size(); i++) {
            representantes.set(i, dividir(representantes.get(i).getData(),tamCentroide.get(i)));
        }
    }

    public Row sumar(List<Double> dato, List<Double> representante) {
        List<Double> suma = new ArrayList<>();
        for(int i = 0; i<representante.size(); i++) {
            suma.add(dato.get(i) + representante.get(i));
        }
        return new Row(suma);
    }

    public Row dividir(List<Double> representante, int tamCentroide) {
        List<Double> division = new ArrayList<>();
        for(Double datos : representante) {
            division.add(datos/tamCentroide);
        }
        return new Row(division);
    }

    @Override
    public void setDistance(Distance distance) {
        this.distancia = distance;
    }
}



