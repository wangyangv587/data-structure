package datastructure.unionfind;

import java.util.Random;

/**
 * @author: Alex
 * @date: 2018/12/14 17:42
 * description:
 */
public class Main {

    public static double testUF(UF uf,int m){
        int size = uf.getSize();
        Random random = new Random();
        long start = System.nanoTime();
        for(int i = 0; i < m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }

        for(int i = 0; i < m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 1000000;
        int m = 1000000;
        UnionFind1 uf1 = new UnionFind1(size);
        UnionFind2 uf2 = new UnionFind2(size);
        UnionFind3 uf3 = new UnionFind3(size);
        double d1 = testUF(uf1, m);
        double d2 = testUF(uf2, m);
        double d3 = testUF(uf2, m);
        System.out.println("UnionFind1 : " + d1 + "s");
        System.out.println("UnionFind2 : " + d2 + "s");
        System.out.println("UnionFind3 : " + d3 + "s");
    }
}
