package core.disjoint;


import core.astar.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

//����save�Ľ��,���ȶ���?
public class ExploreData {

    public void insert(NodeData state){
        maps.add(state.getZobristValue());
        //Fix me
    }

    public boolean contains(NodeData state) {
        return maps.contains(state.getZobristValue()); //Fix me
    }


    private HashSet<Long> maps = new HashSet<>();//hashֵ    heuristic
//    private PriorityQueue<NodeData> nodes = new PriorityQueue<>();
}
