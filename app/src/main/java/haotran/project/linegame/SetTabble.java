package haotran.project.linegame;

public class SetTabble extends MainActivity {


    // Xoa tat ca thanh Invisible
    public void setBlank(Position a[][]){
        for(int i=0;i<9;i++){
            for (int j =0;j<9;j++){
                a[i][j].status1=0;
            }
        }
    }
}
