/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * @author patri
 */
public class projeto3 {
    public static void main(String[] args) {
        
        ArrayList<String> Cliente1 = new ArrayList<String>();
        ArrayList<String> Cliente = new ArrayList<String>();
        ArrayList<Double> Cliente2 = new ArrayList<Double>();
        ArrayList<Double> Cliente3 = new ArrayList<Double>();
        ArrayList<String> Fornecedor1 = new ArrayList<String>();
        ArrayList<Double> Fornecedor2 = new ArrayList<Double>();
        ArrayList<Double> Fornecedor3 = new ArrayList<Double>();
        ArrayList<Pair> A = new ArrayList<Pair>();
        ArrayList<Pair> B = new ArrayList<Pair>();
        ArrayList<Pair> C = new ArrayList<Pair>();
        ArrayList<Pair> D = new ArrayList<Pair>();
        ArrayList<Pair> E = new ArrayList<Pair>();
        ArrayList<Pair> F = new ArrayList<Pair>();
        ArrayList<Pair> G = new ArrayList<Pair>();
        
        Fornecedor1.add("A 0.0715 - 0.5984");
        Fornecedor1.add("B 0.2336 - 0.2094");
        Fornecedor1.add("C 0.0612 - 0.8530");
        Fornecedor1.add("D 0.5088 - 0.4992");
        Fornecedor1.add("E 0.5567 - 0.8742");
        Fornecedor1.add("F 0.0944 - 0.0894");
        Fornecedor1.add("G 0.9028 - 0.4606");
        
        SimpleReader input = new SimpleReader("C:\\Users\\patri\\Downloads\\clients.txt");
        String line1 = input.readLine();
        
        //Preencher Arrays
        while (line1 != null){
            Cliente1.add(line1);
            line1 = input.readLine();
        }
        String k = null;
        Pattern pattern;
        for (String a : Cliente1){
            String g = a;
            pattern = Pattern.compile("(\\D\\D\\d\\d\\d\\d)");
            Matcher matcher = pattern.matcher(g);
            while (matcher.find()){
                k = matcher.group(0);
                g = k;
                Cliente.add(g);
            }
        }
        for (String a : Fornecedor1){
            String g = a;
            pattern = Pattern.compile("(\\d.\\d\\d\\d\\d).");
            Matcher matcher = pattern.matcher(g);
            while (matcher.find()){
                k = matcher.group(0);
                g = k;
                Fornecedor2.add(Double.parseDouble(g));
            }
        }
        for (String a : Fornecedor1){
            String g = a;
            pattern = Pattern.compile("\\-+(.)+(\\d.\\d\\d\\d\\d)");
            Matcher matcher = pattern.matcher(g);
            while (matcher.find()){
                k = matcher.group(0);
                g = k;
                pattern = Pattern.compile("(\\d.\\d\\d\\d\\d)");
                matcher = pattern.matcher(g);
                while (matcher.find()){
                    k = matcher.group(0);
                    g = k;
                    Fornecedor3.add(Double.parseDouble(g));
                }
            }
        }
        for (String a : Cliente1){
            String g = a;
            String h = null;
            pattern = Pattern.compile("\\d.\\d\\d\\d\\d\\s\\d.\\d\\d\\d\\d");
            Matcher matcher = pattern.matcher(g);
            while (matcher.find()){
                k = matcher.group(0);
                g = k;
                h = g;
                pattern = Pattern.compile("\\d.\\d\\d\\d\\d.");
                matcher = pattern.matcher(g);
                while (matcher.find()){
                    k = matcher.group(0);
                    g = k;
                    Cliente2.add(Double.parseDouble(g));
                }
                pattern = Pattern.compile("\\s\\d.\\d\\d\\d\\d");
                matcher = pattern.matcher(h);
                while (matcher.find()){
                    k = matcher.group(0);
                    g = k;
                    Cliente3.add(Double.parseDouble(g));
                }
            }
        }
        
        //Calcular distancia entre cliente e fornecedor
        Double dis1 = null;
        Double dis2 = null;
        Double distancia = null;
        Double x = 10.0;
        int b = 0;
        int j = 0;
        Double longitude1 = null;
        Double longitude2 = null;
        Double latitude1 = null;
        Double latitude2 = null;
        for (int i = 0; i<Cliente1.size(); i++){
            b = 0;
            x = 10.0;
            while ( b <Fornecedor1.size() ){
                longitude1 = Cliente2.get(i);
                longitude2 = Fornecedor2.get(b);
                latitude1 = Cliente3.get(i);
                latitude2 = Fornecedor3.get(b);
                dis1 = Math.pow((longitude1 - longitude2),2);
                dis2 = Math.pow((latitude1 - latitude2),2);
                distancia = Math.sqrt(dis1+dis2);
                if (distancia < x){
                    x = distancia;
                    j = b ;
                }
                b++;
            }
            if (j == 0){ A.add(new Pair(x, Cliente.get(i)));}
            else if (j == 1){ B.add(new Pair(x, Cliente.get(i))); } 
            else if (j == 2){ C.add(new Pair(x, Cliente.get(i))); }
            else if (j == 3){ D.add(new Pair(x, Cliente.get(i))); }
            else if (j == 4){ E.add(new Pair(x, Cliente.get(i))); }
            else if (j == 5){ F.add(new Pair(x, Cliente.get(i))); }
            else if (j == 6){ G.add(new Pair(x, Cliente.get(i))); }
        }
        
        //Fazendo a ordenação
        Collections.sort(A, new SortByDistancia());
        Collections.sort(B, new SortByDistancia());
        Collections.sort(C, new SortByDistancia());
        Collections.sort(D, new SortByDistancia());
        Collections.sort(E, new SortByDistancia());
        Collections.sort(F, new SortByDistancia());
        Collections.sort(G, new SortByDistancia());
        
        // Plotando na tela
        PlotComponent1 plot = new PlotComponent1(1000, 700);
        plot.addPoint(500, 200);
        plot.setVisible(true);
        SwingUtilities.invokeLater(()-> new Plot(Cliente2, Cliente3, Fornecedor2, Fornecedor3));
        
        //imprimir arrays
        System.out.println("Fornecedor A:");
        for (Pair p : A){ System.out.println("Cliente: "+ p.w + " está a uma distância de: " + p.c); }
        System.out.println("");
        System.out.println("Fornecedor B:");
        for (Pair p : B){ System.out.println("Cliente: "+ p.w + " está a uma distância de: " + p.c); }
        System.out.println("");
        System.out.println("Fornecedor C:");
        for (Pair p : C){ System.out.println("Cliente: "+ p.w + " está a uma distância de: " + p.c); }
        System.out.println("");
        System.out.println("Fornecedor D:");
        for (Pair p : D){ System.out.println("Cliente: "+ p.w + " está a uma distância de: " + p.c); }
        System.out.println("");
        System.out.println("Fornecedor E:");
        for (Pair p : E){ System.out.println("Cliente: "+ p.w + " está a uma distância de: " + p.c); }
        System.out.println("");
        System.out.println("Fornecedor F:");
        for (Pair p : F){ System.out.println("Cliente: "+ p.w + " está a uma distância de: " + p.c); }
        System.out.println("");
        System.out.println("Fornecedor G:");
        for (Pair p : G){ System.out.println("Cliente: "+ p.w + " está a uma distância de: " + p.c); }
    }
    
    private static class Pair {
        public Double c;
        public String w;
        public Pair(Double c, String w) { this.c = c; this.w = w; }
        public String toString() { return this.w + ": " + this.c; }
    }
    
    public static class SortByDistancia implements Comparator<Pair> {
        public int compare(Pair p1, Pair p2) { return Double.compare(p2.c , p1.c); }
    }
}
