import processing.core.PApplet;
import processing.data.IntList;

public class main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("main");
    }
    IntList talListe = new IntList();

    public IntList lavTal(IntList tal , int i){

        if(tal.size() < 10){
            tal.append(i);
            --i;
            lavTal(tal,i);
            return tal;
        }else {return tal;}
    }
    public  IntList badShuffle(IntList tal, int i){
        int ran = (int) random(0,tal.size());
        IntList randomlist = new IntList();
        randomlist.append(tal);
        int tallet = tal.get(i);
        randomlist.set(ran,tallet);
        randomlist.set(i,tal.get(ran));
        ++i;
        if(tal.size() > i){

            return badShuffle(randomlist,i);
        }else{
            return randomlist;
        }

    }

    @Override
    public void settings() {
        size(500,500);
    }

    @Override
    public void setup() {
     talListe = lavTal(talListe, 10);
     talListe = badShuffle(talListe,0);
        System.out.println(talListe);
        display(0,talListe);

    }



    void display(int i, IntList k){
        int number = k.get(i);
        fill(255);
        rect(width/10*i,height,width/10,-20*number);
        fill(0);
        text(number,width/10*i + textWidth((char) number)/2,height -10-20*number);
        if(i < k.size()-1){
            display(++i,k);
        }

    }


}
