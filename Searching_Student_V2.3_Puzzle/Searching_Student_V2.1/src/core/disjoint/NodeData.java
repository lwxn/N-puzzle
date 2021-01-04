package core.disjoint;

import core.astar.Node;
import core.problem.State;

import java.util.ArrayList;

public class NodeData extends State {

    public int getBlankpos() {
        return blankpos;
    }

    public void setBlankpos() {
        for(int i = 0;i<size*size;i++){
            if(this.status[i] == 0){
                this.blankpos = i;
                break;
            }
        }
        return;
    }


    public long getZobristValue() {
        return zobristValue;
    }

    public void setZobristValue(long zobristValue) {
        this.zobristValue = zobristValue;
    }

    public byte[] getStatus() {
        return status;
    }

    private byte []status;//״̬����
    private int size;//����
    private int blankpos;//�ո����ڵ�λ��

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    private int pathCost;//·��ֵ
    private static int dx[] = {0,0,-1,1};//x����
    private static int dy[] = {-1,1,0,0};//y����


    private long zobristValue;

    public NodeData(byte[] status,int size,int pathCost){
        this.status = status;
        this.size = size;
        this.pathCost = pathCost;
        this.setBlankpos();
    }

    //����dataBase����������Id.
    public String getId(){
        String s = "";
        int []piece = {9,10,13,14};//{11,12,15};////{3,4,7,8};//{1,2,5,6};//////
        for(int i = 0;i<piece.length;i++){
            for(int j = 0;j<size*size;j++){
                if(status[j] == piece[i]){
                    s = s+ j/size + j%size;
                }
            }
        }
        return s;
    }



    @Override
    public boolean equals(Object obj) {
        NodeData node = (NodeData)obj;
        return this.getZobristValue() == node.getZobristValue() && this.getBlankpos() == node.getBlankpos();
    }


    @Override
    public void draw() {
        for (int i = 0; i < size; i++) {
            drawRow(i);
        }
        drawLine(); // ������
    }

    private void drawRow(int row) {
        drawLine();
        for (int j = 0; j < size; j++) {
            int index = row * size + j;
            if (status[index] != 0)
                System.out.printf("|" + "%2d" + " ", status[index]);
            else
                System.out.print("| # ");
        }
        System.out.println("|");
    }

    private void drawLine() {
        for (int j = 0; j < size; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
