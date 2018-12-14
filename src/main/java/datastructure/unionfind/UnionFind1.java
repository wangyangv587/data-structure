package datastructure.unionfind;

/**
 * @author: Alex
 * @date: 2018/12/14 17:03
 * description:
 */
public class UnionFind1 implements UF {

    int[] id;

    public UnionFind1(int size){
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if(pId == qId)
            return;

        for (int i = 0; i < id.length; i++) {
            if(id[i] == pId)
                id[i] = qId;
        }
    }

    private int find(int index){
        if(index < 0 || index >= id.length){
            throw new IllegalArgumentException("index is out of range");
        }
        return id[index];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
