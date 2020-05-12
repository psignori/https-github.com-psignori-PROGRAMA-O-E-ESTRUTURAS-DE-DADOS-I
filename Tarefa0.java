/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sound.midi.MidiDevice.Info;


/**
 *
 * @author patri
 */
public class Tarefa0 {
    
    private static final String VIRGULA = ",";
    
    public static void main (String args []){
        parte1();
    }
    
    private static class nomes {
        ArrayList<String> nome = new ArrayList <>();
        ArrayList<String> genero_nintendo = new ArrayList <>();
        ArrayList<Double> score = new ArrayList<>();
        Double nota_por_genero = 0.0;
        String nome_genero = null;
        int cont = 0;
        ArrayList<String> s = new ArrayList<>();
        int y = 0;
        int numeroScore = 0;
        String MelhorJogo = null;
        Double notaMelhorJogo = 0.0;
        String PiorJogo = null;
        Double notaPiorJogo = 10.0;
    }

    
    public static void parte1 (){
        Map<String, Integer> map_genero = new TreeMap<String, Integer>();
        Map<String, nomes> map_genero_jogo = new TreeMap<String, nomes>();
        ArrayList<Double> ut = new ArrayList<>();
        ArrayList<Integer> porcentagem1 = new ArrayList<>();
        ArrayList<Integer> porcentagem2 = new ArrayList<>();
        ArrayList<Double> desvio_populacional = new ArrayList<>();


        SimpleReader file = new SimpleReader("C:\\Users\\patri\\Downloads\\game-reviews.csv");
        
        String Line = file.readLine();
        
        int cont_genero = 0;
        int cont_score = 0;
        Double cont = 0.0;
        Double scor = 0.0;
        String sco = null;
        String teste = null;
        Double test = 0.0;
        Double tes = 0.0;
        int y = 0;
        
        while (Line != null){
            String [] col = Line.split(";");
            
            String title = col[0];
            String plataform = col[1];
            String score_frase = col[2];
            String score = col[3];
            
            sco = score;
            Pattern pattern = Pattern.compile("[\\d]+[,|.]+[\\d]+");
            Matcher matcher = pattern.matcher(sco);
            String k = null;
            String g = null;
            while (matcher.find()){
                k = matcher.group(0);
                scor = Double.parseDouble(k);
            }
            
            String genero = col[4];
            String editors_choice = col[5];
            String release_year = col[2];
                        
            if (! map_genero.containsKey(genero)){
                nomes i = new nomes();
                i.nome.add(col[4]);
                i.nome.add(col[0]);
                i.s.add(col[2]);
                i.nome_genero = col[4];
                if (col[2].contains("Amazing")){
                    i.y = i.y + 1;
                }
                if (! col[3].isEmpty()){
                    i.numeroScore++;
                }
                String conferir = col[3];  
                if (! conferir.equals("score")){
                    String notamelhor = col[3];
                    Double notamelhor1 = Double.parseDouble(notamelhor);
                    if (i.notaMelhorJogo <= notamelhor1){
                        i.MelhorJogo = col[0];
                        i.notaMelhorJogo = notamelhor1;
                    }
                    String notapior = col[3];
                    Double notapior1 = Double.parseDouble(notapior);
                    if (i.notaPiorJogo >= notapior1){
                        i.PiorJogo = col[0];
                        i.notaPiorJogo = notapior1;
                    }
                }
                if (col[1].equals("Nintendo DSi")||col[1].equals("Nintendo 3DS")||col[1].equals("Wii U")||col[1].equals("New Nintendo 3DS")||col[1].equals("Nintendo 64")||col[1].equals("Nintendo DS")||col[1].equals("Nintendo 64DD")||col[1].equals("Wii")||col[1].equals("Super NES")||col[1].equals("Game Boy")||col[1].equals("Game Boy Color")||col[1].equals("NES")||col[1].equals("Game Boy Advance")||col[1].equals("GameCube")){
                    i.genero_nintendo.add(col[4]);
                }
                i.score.add(scor);
                i.nota_por_genero = scor;
                i.cont = 1;
                map_genero.put(genero, 1);
                map_genero_jogo.put(genero, i);
            } else {
                nomes i = map_genero_jogo.get(genero); 
                i.nome.add(col[0]);
                i.s.add(col[2]);
                i.nome_genero = col[4];
                if (col[2].contains("Amazing")){
                    i.y = i.y + 1;
                }
                if (! col[3].isEmpty()){
                    i.numeroScore++;
                }
                String conferir1 = col[3];
                if (conferir1 != "score"){
                    String notamelhor = col[3];
                    Double notamelhor1 = Double.parseDouble(notamelhor);
                    if (i.notaMelhorJogo <= notamelhor1){
                        i.MelhorJogo = col[0];
                        i.notaMelhorJogo = notamelhor1;
                    }
                    String notapior = col[3];
                    Double notapior1 = Double.parseDouble(notapior);
                    if (i.notaPiorJogo >= notapior1){
                        i.PiorJogo = col[0];
                        i.notaPiorJogo = notapior1;
                    }
                }
                if (col[1].equals("Nintendo DSi")||col[1].equals("Nintendo 3DS")||col[1].equals("Wii U")||col[1].equals("New Nintendo 3DS")||col[1].equals("Nintendo 64")||col[1].equals("Nintendo DS")||col[1].equals("Nintendo 64DD")||col[1].equals("Wii")||col[1].equals("Super NES")||col[1].equals("Game Boy")||col[1].equals("Game Boy Color")||col[1].equals("NES")||col[1].equals("Game Boy Advance")||col[1].equals("GameCube")){
                    i.genero_nintendo.add(col[4]);
                }
                i.score.add(scor);
                i.nota_por_genero = i.nota_por_genero + scor;
                i.cont = i.cont +1;
                map_genero.put(col[4], map_genero.get(genero)+1);
                map_genero_jogo.put(genero, i);
            }
            
            Line = file.readLine();
        }
        
        /*Mostrar todos os jogos por categoria*/
        /**for (nomes n : map_genero_jogo.values()){
            System.out.println(" "+n.nome );
        }*/
        /*Termina aqui*/
        
        /*Mostrando número de jogos deste gênero*/
        /*for (String w: map_genero.keySet()){
            System.out.println(w + " = " + map_genero.get(w));   
        }*/
        /*Termina aqui*/
        
        /*Mostrando número de scores deste gênero*/
        System.out.println("* Mostrando número de reviews deste gênero:");
        for (nomes n : map_genero_jogo.values()){
            porcentagem1.add(n.numeroScore);
        }
        int contador = 0;
        for (String w: map_genero.keySet()){
            System.out.println(w + " = " + porcentagem1.get(contador));
            contador++;
        }
        /*Termina aqui*/
        
        System.out.println("");
        
        /*Mostrando Porcentagem de Amazing*/
        System.out.println("* Percentual de ‘Amazing’ reviews (sobre o número de reviews deste gênero):");
        for (nomes n : map_genero_jogo.values()){
            porcentagem2.add(n.y);
        }
        int conti = 0;
        ArrayList<Integer> var = new ArrayList<>();
        for (String w: map_genero.keySet()){
            int a = porcentagem1.get(conti);
            int b = porcentagem2.get(conti);
            boolean x = false;
            if (b == 0){
                x = true;
            }
            if ( x == true){
                var.add(0);
            } else {
                var.add(a / b );
            }
            conti++;
        }
        int conti2 = 0;
        for (String w: map_genero.keySet()){
            System.out.println("Porcentagem de Amazing na categoria "+w + " = "+ var.get(conti2) + "%");
            conti2++;
        }
        /* Termina aqui*/
        
        System.out.println("");
        
        /*Mostrando média aritmética dos scores*/ 
        for (nomes n : map_genero_jogo.values()){
            Double h = 0.0;
            h = n.nota_por_genero/n.cont;
            ut.add(h);
        }
        System.out.println("* Mostrando média aritmética dos scores:");
        for (String w: map_genero.keySet()){
            System.out.print("Média aritmética: "+w + " = ");
            System.out.printf("%.2f %n",ut.get(y));
            desvio_populacional.add(ut.get(y));
            y++;
        }
        /*Termina aqui*/
        
        System.out.println("");
        
        /*Mostrando desvio padrão populacional dos scores*/
        System.out.println("* Mostrando desvio padrão populacional dos scores:");
        Double x = 0.0;
        Double mediaX = 0.0;
        for (int j = 0; j < 115; j++){
            x = x + desvio_populacional.get(j);
        }
        x = x / desvio_populacional.size();
        Double x1 = 0.0;
        Double x2 = 0.0;
        for (int j = 0; j < 115; j++) {
            x1 = (desvio_populacional.get(j)- x);
            x1 = Math.pow(x1, 2);
            x2 = x2 + x1;
        }
        x2 = x2 / desvio_populacional.size();
        x2 = Math.sqrt(x2);
        System.out.printf("O desvio padrão populacional dos scores é: %.3f %n",x2);
        /*Termina aqui*/
        
        System.out.println("");
        
        /*Mostra quantos "Amazing" tem dentro da categoria*/
        /*for (nomes n : map_genero_jogo.values()){
            System.out.println(" "+ n.s);
            System.out.println(" "+ n.y);
        }*/
        /*Termina aqui*/
                
        /*Mostrando melhor jogo*/
        System.out.println("* Melhor jogo:");
        String melhorJogoNome = null;
        Double melhorJogoNota = 0.0;
        for (nomes n : map_genero_jogo.values()){
            if (n.notaMelhorJogo >= melhorJogoNota){
                melhorJogoNome = n.MelhorJogo;
                melhorJogoNota = n.notaMelhorJogo;
            }
        }
        System.out.println("Nome: "+melhorJogoNome+" | Nota: "+melhorJogoNota);
        /*Termina aqui*/
        
        System.out.println("");
        
        /*Mostrando pior jogo*/
        System.out.println("* Pior jogo:");
        String piorJogoNome = null;
        Double piorJogoNota = 10.0;
        for (nomes n : map_genero_jogo.values()){
            if (n.notaPiorJogo <= piorJogoNota){
                piorJogoNome = n.PiorJogo;
                piorJogoNota = n.notaPiorJogo;
            }
        }
        System.out.println("Nome: "+piorJogoNome+" | Nota: "+piorJogoNota);
        /*Termina aqui*/
        
        System.out.println("");
        
        /*Mostrando qual o gênero de jogo mais comum na família de consoles ‘Nintendo’*/
        System.out.println("* Qual o gênero de jogo mais comum na família de consoles ‘Nintendo’:");
        int tamanho = 0;
        String seila = null;
        for (nomes n : map_genero_jogo.values()){
            if (n.genero_nintendo.size() >= tamanho){
                tamanho = n.genero_nintendo.size();
                seila = n.nome_genero;
            }
        }
        System.out.println("Nome do gênero: "+seila+" | número de jogos com este gênero na família Nintendo: "+tamanho);
        /*termina aqui*/
    }
}
