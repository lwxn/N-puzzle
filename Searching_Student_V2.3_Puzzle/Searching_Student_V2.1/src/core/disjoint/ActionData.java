package core.disjoint;

import core.problem.Action;

public class ActionData {
    private int blankPos;
    private int dir;//

    public ActionData(int blankpos,int dir){
        this.blankPos = blankpos;
        this.dir = dir;

    }

    public int getBlankPos() {
        return blankPos;
    }

    public void setBlankPos(int blankPos) {
        this.blankPos = blankPos;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
