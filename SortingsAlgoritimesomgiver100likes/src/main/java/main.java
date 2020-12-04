import processing.core.PApplet;
import processing.data.IntList;

public class main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("main");
    }

    IntList talListe = new IntList();
    boolean doSort = false;

    public IntList lavTal(IntList tal, int i, int count) {

        if (tal.size() < count) {
            tal.append(i);
            --i;
            lavTal(tal, i,count);
            return tal;
        } else {
            return tal;
        }
    }

    public IntList badShuffle(IntList tal, int i) {
        int ran = (int) random(0, tal.size());
        IntList randomlist = new IntList();
        randomlist.append(tal);
        int tallet = tal.get(i);
        randomlist.set(ran, tallet);
        randomlist.set(i, tal.get(ran));
        ++i;
        if (tal.size() > i) {
            return badShuffle(randomlist, i);
        } else {
            return randomlist;
        }

    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        talListe = lavTal(talListe, 28,28);
        talListe = badShuffle(talListe, 0);
        System.out.println(talListe);
        display(0, talListe);

    }

    @Override
    public void draw() {

    }

    @Override
    public void mousePressed() {
        clear();
        background(200);
        doSort = !doSort;
        if(doSort){
            talListe = sortering(talListe);
            display(0,talListe);
        }  else {
            talListe = badShuffle(talListe,0);
            display(0,talListe);
        }
    }

    void display(int i, IntList k) {
        int number = k.get(i);
        fill(255);
        rect(width / k.size() * i, height, width / k.size(), -20 * number);
        fill(0);
        text(number, width / k.size() * i + textWidth((char) number) / 2, height - 10 - 20 * number);
        if (i < k.size() - 1) {
            display(++i, k);
        }

    }

    public IntList sortering(IntList k) {
        if(k.size() == 2) {
            if (k.get(0) > k.get(1)) {
                int biggest = k.get(0);
                k.remove(0);
                k.append(biggest);
            }
            return k;
        } else {
            IntList tail = new IntList();
            tail = k.copy();

            int head = tail.get(0);
            tail.remove(0);

            tail = sortering(tail);
            if(head  < tail.get(0)) {
                IntList newList = new IntList();
                newList.append(head);
                newList.append(tail);
                k = newList;
            } else {
                IntList newList = new IntList();
                newList.append(tail);
                newList.append(head);
                k = sortering(newList);
            }

            return k;
        }
    }
}
