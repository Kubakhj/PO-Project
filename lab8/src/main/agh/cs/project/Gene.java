package agh.cs.project;

import java.util.Random;

public class Gene {
    private Integer[] genotype = new Integer[32];

    Gene(){
        randomizeGenotype();
    }
    public String toString(){
        StringBuilder genotypeString = new StringBuilder(" ");
        for(int i=0;i<31;i++){
            genotypeString.append(genotype[i].toString());
            genotypeString.append(", ");
        }
        genotypeString.append(genotype[31].toString());

        return genotypeString.toString();
    }

    private void randomizeGenotype() {
        Random random = new Random();
        for (int i = 0; i < 32; i++){
            genotype[i] = random.nextInt(8);
        }
        repairMyGenotype(); // Assures that genotype will be valid after randomization
    }
    private boolean isValid(){ // Checks if genotype has every number from 0-7 inside
        boolean[] genes = new boolean[8];

        for(int i=0;i<8;i++){
            genes[i] = false;
        }
        for(int i=0;i<32;i++){
            genes[genotype[i]]=true;
        }
        for(int i = 0;i<8;i++){
            if(!genes[i]) return false;
        }
        return true;
    }
    private void repairMyGenotype(){ // repairs genotype
    if(!isValid()){
        boolean[] genes = new boolean[8];
        for(int i=0;i<8;i++){
            genes[i] = false;
        }
        for(int i=0;i<32;i++){
            genes[genotype[i]]=true;
        }
        for(int i=0;i<8;i++){
            if(!genes[i]){
                Random random = new Random();
                genotype[random.nextInt(32)] = i;
                repairMyGenotype();
            }
        }
        }
    }
    int randomGene(){ // Returns random gene from the genotype array
        Random random = new Random();
        return genotype[random.nextInt(32)];
    }
}