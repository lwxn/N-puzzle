package core.disjoint;

import core.astar.Node;
import core.uitlity.Zobrist;


import java.util.*;

public class BFS {
    public static byte[] maze1 = {1, 2, -1, -1,
            5, 6, -1, -1,
            -1, -1, -1, -1,
            -1, -1, -1, 0};

    //    byte[] maze1 = {1,-1,-1,-1,
//                    5,6,-1,-1,
//                    9,10,-1,-1,
//                    13,-1,-1,0};
    public static byte[] maze2 = {-1, -1, 3, 4,
                    -1, -1, 7, 8,
                    -1, -1, -1, -1,
                    -1, -1, -1, 0};

    public static byte[] maze3 = {-1, -1, -1, -1,
            -1, -1, -1, -1,
            9, 10, -1, -1,
            13, 14, -1, 0};

    public static byte[] maze4 = {-1, -1, -1, -1,
            -1, -1, -1, -1,
            -1, -1, 11, 12,
            -1, -1, 15, 0};

    private static int size = 4;

    private Zobrist z;
    private static int dx[] = {0, 0, -1, 1};//x����
    private static int dy[] = {-1, 1, 0, 0};//y����

    private static byte V1[] = {1,2,5,6};
    private static byte V2[] = {3,4,7,8};
    private static byte V3[] = {9,10,13,14};
    private static byte V4[] = {11,12,15};

    private ExploreData save;
    private long ans = 0;
    private ReadTxt txt = new ReadTxt();

    //�õ��ӽڵ�
    public NodeData childNode(NodeData parent, ActionData action,long hash) {
        int empty = action.getBlankPos();
        int dir = action.getDir();//l r u d
        int n = this.size;
        byte[] s = Arrays.copyOf(parent.getStatus(), n * n);

        int sx = empty / n;    //�ո��λ��
        int sy = empty % n;

        int tx = sx + dx[dir];//�仯���λ��
        int ty = sy + dy[dir];

        //����Value����
        byte temp = s[sx * n + sy];
        s[sx * n + sy] = s[tx * n + ty];
        s[tx * n + ty] = temp;

        int pathCost = parent.getPathCost();
        if(parent.getStatus()[tx * n + ty] != -1){
            pathCost++;
        }
        NodeData result = new NodeData(s, size, pathCost);

        long newValue = z.newHash_empty(hash, empty, (byte) 0, tx*n+ty, parent.getStatus()[tx*n+ty]);
        result.setZobristValue(newValue);//�����ո�
        return result;

    }


    //bfs
    public HashMap<Long, Byte> bfs(int type,byte[] V,byte[] maze) {
        NodeData init = new NodeData(maze, size, 0);
        init.setZobristValue(z.hash_empty(init.getStatus(),V));
        Queue<NodeData>queue = new LinkedList<>();
        HashSet<Long> visited=new HashSet<>();
        HashMap<Long, Byte> result=new HashMap<>();

        String path = "output" + type + ".txt";
        ReadTxt.clearInfoForFile(path);

        //queue�ŵ���û�пո��hashֵ
        queue.offer(init);
        while (!queue.isEmpty()) {
            NodeData cur = queue.poll();
            long hash = z.hash_empty(cur.getStatus(),V);

            //visited�ŵ��ǰ����˿ո��hashֵ
            if(!visited.contains(hash)){
                long cutHash = z.hashCut(cur.getStatus(),V);
                Byte pathCost = result.get(cutHash);
                if(pathCost == null){
                    ans++;
                    result.put(cutHash,(byte) cur.getPathCost());
                    ReadTxt.fileChaseFW(path,cutHash,cur.getPathCost());
                }else if(cur.getPathCost() < pathCost){
                    result.remove(cutHash);
                    result.put(cutHash,(byte)cur.getPathCost());
                }
            }
            visited.add(hash);
            for (ActionData action : Actions(cur)) {
                NodeData child = childNode(cur, action,hash);
                if (!visited.contains(child.getZobristValue())) {
                   queue.offer(child);
                }
            }


        }
        return result;
    }

    public boolean IsSafe(int x, int y) {
        return x >= 0 && x < this.size && y >= 0 && y < this.size;
    }

    //��ȡ֮��Ķ���
    public ArrayList<ActionData> Actions(NodeData state) {
        // TODO Auto-generated method stub
        ArrayList<ActionData> arrayList = new ArrayList<>();

        int sx = (state.getBlankpos()) / this.size;
        int sy = (state.getBlankpos()) % this.size;

        //��������
        for (int i = 0; i < 4; i++) {
            int tx = sx + dx[i];
            int ty = sy + dy[i];

            if (IsSafe(tx, ty)) {
                arrayList.add(new ActionData(sx*size+sy, i));
            }
        }
        return arrayList;
    }

    //��hashTable
    public void saveZob(){
        z = new Zobrist(size * size, size * size,0);
        ReadTxt.fileChaseFW_hash(Zobrist.table_15,size);
    }

    //��ȡdatabase��������
    public static void getInit(){
        BFS pro = new BFS();
        pro.saveZob();

        byte V[] = null;
        HashMap<Long, Byte>map = null;
        for(int i = 1;i<=4;i++){

            if(i == 1){
                map = pro.bfs(i,V1,maze1);
            }
            else if(i == 2){
                map = pro.bfs(i,V2,maze2);
            }
            else if(i == 3){
                map = pro.bfs(i,V3,maze3);
            }else {
                map = pro.bfs(i, V4, maze4);
            }
            System.out.println(pro.ans);
            ReadTxt.write(i,map);
        }
    }


    public static void main(String[] args) {
        getInit();
    }
}
