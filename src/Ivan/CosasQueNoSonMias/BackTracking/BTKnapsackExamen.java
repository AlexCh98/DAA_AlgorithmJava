package Ivan.CosasQueNoSonMias.BackTracking;

import java.util.ArrayList;

public class BTKnapsackExamen {
    private Data data;
    private ArrayList<Integer> bestSol;
    private ArrayList<Integer> sol;
    private double pesoMax;

    public BTKnapsackExamen(Data d){
        this.data = d;
        this.bestSol = new ArrayList<>();
        this.sol = new ArrayList<>();
        this.pesoMax = this.data.getMaxWeight();
    }

    public ArrayList<Integer> algorithm(){
        BTrec(0);
        return this.bestSol;
    }

    private void BTrec(int ie){
        if(getSolValue() > getBestSolValue()){
            copy();
        }
        if(ie == this.data.size()){
            return;
        }

        if(this.data.getWeight(ie) + getPeso() < pesoMax){
            this.sol.add(ie);
            BTrec(ie+1);
            this.sol.remove(this.sol.indexOf(ie));
        }
        BTrec(ie+1);
    }

    private int getSolValue(){
        int valor = 0;
        for(int i = 0; i<this.sol.size(); i++){
            valor += this.data.getProfit(this.sol.get(i));
        }
        return valor;
    }

    private int getBestSolValue(){
        int valor = 0;
        for(int i = 0; i<this.bestSol.size(); i++){
            valor += this.data.getProfit(this.bestSol.get(i));
        }
        return valor;
    }

    private void copy(){
        this.bestSol.clear();
       // this.bestSol.addAll(this.sol);
        for(int i = 0; i<this.sol.size(); i++){
            this.bestSol.add(this.sol.get(i));
        }
    }

    private int getPeso(){
        int peso = 0;
        for(int i = 0; i<this.sol.size(); i++){
            peso += this.data.getWeight(this.sol.get(i));
        }
        return peso;
    }
}
