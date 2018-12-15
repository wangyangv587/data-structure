package datastructure.unionfind;

/**
 * @author: Alex
 * @date: 2018/12/14 17:01
 * description: 并查集接口
 */
public interface UF {

    int getSize();
    void unionElements(int p,int q);
    boolean isConnected(int p, int q);
}
