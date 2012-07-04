package nodepara;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodePara {

    private final static double[] home = {1.1, 2.3, 4.2, 6.7, 8.5, 9.0, 11.2, 13.4, 14.2, 16.4};
    private static double[] distances = new double[home.length - 1];
    private static List<Double> antennas = new <Double> ArrayList();

    public static double reduceDemicals(double value) {
        return reduceDemicals(value, 1);
    }

    private static double reduceDemicals(double value, int demicals) {
        BigDecimal bd = new BigDecimal(value).setScale(demicals, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    private static void printNodes(Home firstNde) {
        Home tmp = firstNde;
        System.out.print(tmp);
        while (tmp.hasNext()) {
            tmp = tmp.getNext();
            System.out.print(tmp);
        }
    }

    private static Home instansiateHomes(double[] homing) {
        double[] hme = homing;
        Home firstNod = new Home(hme[0]);
        Home temp = firstNod;

        for (int i = 1; i < hme.length; i++) {
            Home hom = new Home(hme[i]);
            temp.setNext(hom);
            hom.setPrevious(temp);
            temp = hom;
        }
        return firstNod;
    }

    private static double[] findDistances() {
        for (int i = 0; i < distances.length; i++) {
            double value = home[i + 1] - home[i];
            distances[i] = reduceDemicals(value);
        }
        return distances;
    }

    private static void findNearest(Home firstNde) {
        
        while (isFixed(firstNde)) {
            
            Home tmp = findFirst(firstNde);
            Home nHome = tmp.getNext();

            while (tmp.hasNext()) {
                tmp = tmp.getNext();
                if (nHome.getDistance() > tmp.getDistance() && !tmp.isEnganged()) {
                    nHome = tmp;
                }
            }

            System.out.printf("\n\nThe home with the nearest distance is home %s and the distance is %.1f\n", nHome, nHome.getDistance());

            setAntenna(nHome);
        }
    }

    private static Home findFirst(Home firstNde) {
        Home tmp = firstNde;
        if (!tmp.isEnganged()) {
            return tmp;
        }

        while (tmp.hasNext()) {
            tmp = tmp.getNext();
            if (!tmp.isEnganged()) {
                return tmp;
            }
        }
        return tmp;         //De ftanei pote
    }

    private static boolean isFixed(Home firstNde) { //Ti kano edo ????
        
        Home tmp = firstNde;
        if (!tmp.isEnganged()) {
            return true;
        }

        while (tmp.hasNext()) {
            tmp = tmp.getNext();
            if (!tmp.isEnganged()) {
                return true;
            }
        }
        return false;
    }

    private static void setAntenna(Home nHome) {
        antennas.add(nHome.getPlace());  // <- Bazei thn antenna
        System.out.printf("Set Antenna : %.1f nodes :",nHome.getPlace());
        double cdistance = nHome.getDistance();
        nHome.setEnganged(true);

        if (nHome.hasPrevious()) {
            Home pHome = nHome.getPrevious();  //Ti ginetai an to proto minei mono tou? //Symbainei mono ontan meinei mono eidallose de mporei giati pernoume to epomeno      
            pHome.setEnganged(true);
            System.out.printf(" %.1f",pHome.getPlace());
            anadromBack(pHome, cdistance);
        }

        if (nHome.hasNext()) {
            Home fHome = nHome.getNext();
            anadromForward(fHome, fHome.getDistance());
        }
    }

    private static void anadromBack(Home nHome, double cdistance) {
        if (nHome.hasPrevious()) {
            Home pHome = nHome.getPrevious();
            cdistance += pHome.getDistance();
            if (cdistance < 4 && !pHome.isEnganged()) {
                pHome.setEnganged(true);
                System.out.printf(" %.1f",pHome.getPlace());
                anadromBack(pHome, cdistance);
            }
        }

    }

    private static void anadromForward(Home nHome, double cdistance) {
        if (cdistance < 4 && !nHome.isEnganged()) {
            nHome.setEnganged(true);
            System.out.printf(" %.1f",nHome.getPlace());
            if (nHome.hasNext()) {
                Home fHome = nHome.getNext();
                cdistance += fHome.getDistance();
                anadromForward(fHome, cdistance);
            }
        }
    }

    private static void printEngagedNodes(Home firstNde) {
        Home tmp = firstNde;
        System.out.printf("\n( %s : %b) \n", tmp, tmp.isEnganged());
        while (tmp.hasNext()) {
            tmp = tmp.getNext();
            System.out.printf("( %s : %b) \n", tmp, tmp.isEnganged());
        }
    }

    private static void printAntennas() {
        for (Double dou : antennas) {
            System.out.printf("%.1f ", dou);
        }
    }

    public static void main(String[] args) {

        double[] distancs = findDistances();

        System.out.println(Arrays.toString(distancs));

        Home firstNode = instansiateHomes(home);

        printNodes(firstNode);

        findNearest(firstNode);

        printEngagedNodes(firstNode);
        
        printAntennas();

    }
}
