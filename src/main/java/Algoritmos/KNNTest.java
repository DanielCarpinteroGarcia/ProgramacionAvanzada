package Algoritmos;

import LecturaCSV.CSV;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {
    KNN objKNN = new KNN();
    CSV objCSV = new CSV();

    @Test
    void estimate() {
        List<Double> list1 = new ArrayList<>();
        list1.add(4.6);
        list1.add(3.1);
        list1.add(1.4);
        list1.add(0.3);
        List<Double> list2 = new ArrayList<>();
        list2.add(7.0);
        list2.add(3.3);
        list2.add(4.6);
        list2.add(1.2);
        List<Double> list3 = new ArrayList<>();
        list3.add(0.0);
        list3.add(0.0);
        list3.add(0.0);
        list3.add(0.0);
        objKNN.train(objCSV.readTableWithLabels("iris.csv"));
        assertEquals(0, objKNN.estimate(list1));
        assertEquals(1, objKNN.estimate(list2));
        assertEquals(0,objKNN.estimate(list3));

    }

    @Test
    void distancia(){
        assertEquals( 2.3, objKNN.distancia(3.40, 1.10));
        assertEquals(0.9, objKNN.distancia(4.3, 5.2), 0.3d);
        assertEquals(0, objKNN.distancia(0.0, 0.0));
    }

}
