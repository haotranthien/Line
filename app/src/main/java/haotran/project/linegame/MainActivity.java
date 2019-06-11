package haotran.project.linegame;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Px;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    Position[][] ballPosition = new Position[9][9];
    ImageButton[][] ball = new ImageButton[9][9];
    Animation MoveUp, MoveDown, MoveLeft, MoveRight;
    TextView tv1;
    RelativeLayout Rlay;
    boolean taptap = false;
    int a1, b1, c1, d1;
    float frXdel = 0, frYdel = 0, toXdel = 0, toYdel = 0;
    int tee = 0, gamestatus = 0, scoreStatus=0;
    int mainPoint = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        Animation();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ballPosition[i][j] = new Position();
            }
        }


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTabble setTabble = new SetTabble();
                setTabble.setBlank(ballPosition);
                firstAddBall();
                setVisibleBall();
                mainPoint=0;
                tv1.setText(mainPoint+"");
            }
        });


        chooseBall(ball, 0, 0);
        chooseBall(ball, 0, 1);
        chooseBall(ball, 0, 2);
        chooseBall(ball, 0, 3);
        chooseBall(ball, 0, 4);
        chooseBall(ball, 0, 5);
        chooseBall(ball, 0, 6);
        chooseBall(ball, 0, 7);
        chooseBall(ball, 0, 8);
        chooseBall(ball, 1, 0);
        chooseBall(ball, 1, 1);
        chooseBall(ball, 1, 2);
        chooseBall(ball, 1, 3);
        chooseBall(ball, 1, 4);
        chooseBall(ball, 1, 5);
        chooseBall(ball, 1, 6);
        chooseBall(ball, 1, 7);
        chooseBall(ball, 1, 8);
        chooseBall(ball, 2, 0);
        chooseBall(ball, 2, 1);
        chooseBall(ball, 2, 2);
        chooseBall(ball, 2, 3);
        chooseBall(ball, 2, 4);
        chooseBall(ball, 2, 5);
        chooseBall(ball, 2, 6);
        chooseBall(ball, 2, 7);
        chooseBall(ball, 2, 8);
        chooseBall(ball, 3, 0);
        chooseBall(ball, 3, 1);
        chooseBall(ball, 3, 2);
        chooseBall(ball, 3, 3);
        chooseBall(ball, 3, 4);
        chooseBall(ball, 3, 5);
        chooseBall(ball, 3, 6);
        chooseBall(ball, 3, 7);
        chooseBall(ball, 3, 8);
        chooseBall(ball, 4, 0);
        chooseBall(ball, 4, 1);
        chooseBall(ball, 4, 2);
        chooseBall(ball, 4, 3);
        chooseBall(ball, 4, 4);
        chooseBall(ball, 4, 5);
        chooseBall(ball, 4, 6);
        chooseBall(ball, 4, 7);
        chooseBall(ball, 4, 8);
        chooseBall(ball, 5, 0);
        chooseBall(ball, 5, 1);
        chooseBall(ball, 5, 2);
        chooseBall(ball, 5, 3);
        chooseBall(ball, 5, 4);
        chooseBall(ball, 5, 5);
        chooseBall(ball, 5, 6);
        chooseBall(ball, 5, 7);
        chooseBall(ball, 5, 8);
        chooseBall(ball, 6, 0);
        chooseBall(ball, 6, 1);
        chooseBall(ball, 6, 2);
        chooseBall(ball, 6, 3);
        chooseBall(ball, 6, 4);
        chooseBall(ball, 6, 5);
        chooseBall(ball, 6, 6);
        chooseBall(ball, 6, 7);
        chooseBall(ball, 6, 8);
        chooseBall(ball, 7, 0);
        chooseBall(ball, 7, 1);
        chooseBall(ball, 7, 2);
        chooseBall(ball, 7, 3);
        chooseBall(ball, 7, 4);
        chooseBall(ball, 7, 5);
        chooseBall(ball, 7, 6);
        chooseBall(ball, 7, 7);
        chooseBall(ball, 7, 8);
        chooseBall(ball, 8, 0);
        chooseBall(ball, 8, 1);
        chooseBall(ball, 8, 2);
        chooseBall(ball, 8, 3);
        chooseBall(ball, 8, 4);
        chooseBall(ball, 8, 5);
        chooseBall(ball, 8, 6);
        chooseBall(ball, 8, 7);
        chooseBall(ball, 8, 8);


    }

    private void chooseBall(ImageButton[][] ball1, final int i, final int j) {

        ball1[i][j].setOnClickListener(new View.OnClickListener() {
            ArrayList<String> cloneArr = new ArrayList<>();

            @Override
            public void onClick(View v) {
                String abc = "";

                if (taptap) {
                    if (ballPosition[i][j].status1 == 2) {
                        taptap = false;

                    } else {
                        c1 = i;
                        d1 = j;


                        // Cho chạy hàm dời quả bóng !!!
                        FindtheWay1 findtheWay = new FindtheWay1();
                        cloneArr = findtheWay.findRoute(ballPosition, a1, b1, c1, d1);
                        for (int i = 0; i < cloneArr.size(); i++) {
                            abc = abc + cloneArr.get(i);
                        }

                        if (cloneArr.size() > 1) {
                            ballPosition[a1][b1].status1 = 0;
                            ballPosition[c1][d1].status1 = 2;
                            ballPosition[c1][d1].color1 = ballPosition[a1][b1].color1;
                            MoveBall(ballPosition, a1, b1, c1, d1, cloneArr);

                        }

                        taptap = false;
                    }


                } else if (!taptap) {
                    if (ballPosition[i][j].status1 != 2) {
                        taptap = false;
                    } else {
                        taptap = true;
                        a1 = i;
                        b1 = j;

                    }


                }


            }
        });
    }


    public void AnhXa() {
        Rlay = findViewById(R.id.game_layout);
        bt1 = findViewById(R.id.button1);
        tv1 = findViewById(R.id.tv1);
        ball[0][0] = findViewById(R.id.ball00);
        ball[0][1] = findViewById(R.id.ball01);
        ball[0][2] = findViewById(R.id.ball02);
        ball[0][3] = findViewById(R.id.ball03);
        ball[0][4] = findViewById(R.id.ball04);
        ball[0][5] = findViewById(R.id.ball05);
        ball[0][6] = findViewById(R.id.ball06);
        ball[0][7] = findViewById(R.id.ball07);
        ball[0][8] = findViewById(R.id.ball08);
        ball[1][0] = findViewById(R.id.ball10);
        ball[1][1] = findViewById(R.id.ball11);
        ball[1][2] = findViewById(R.id.ball12);
        ball[1][3] = findViewById(R.id.ball13);
        ball[1][4] = findViewById(R.id.ball14);
        ball[1][5] = findViewById(R.id.ball15);
        ball[1][6] = findViewById(R.id.ball16);
        ball[1][7] = findViewById(R.id.ball17);
        ball[1][8] = findViewById(R.id.ball18);
        ball[2][0] = findViewById(R.id.ball20);
        ball[2][1] = findViewById(R.id.ball21);
        ball[2][2] = findViewById(R.id.ball22);
        ball[2][3] = findViewById(R.id.ball23);
        ball[2][4] = findViewById(R.id.ball24);
        ball[2][5] = findViewById(R.id.ball25);
        ball[2][6] = findViewById(R.id.ball26);
        ball[2][7] = findViewById(R.id.ball27);
        ball[2][8] = findViewById(R.id.ball28);
        ball[3][0] = findViewById(R.id.ball30);
        ball[3][1] = findViewById(R.id.ball31);
        ball[3][2] = findViewById(R.id.ball32);
        ball[3][3] = findViewById(R.id.ball33);
        ball[3][4] = findViewById(R.id.ball34);
        ball[3][5] = findViewById(R.id.ball35);
        ball[3][6] = findViewById(R.id.ball36);
        ball[3][7] = findViewById(R.id.ball37);
        ball[3][8] = findViewById(R.id.ball38);
        ball[4][0] = findViewById(R.id.ball40);
        ball[4][1] = findViewById(R.id.ball41);
        ball[4][2] = findViewById(R.id.ball42);
        ball[4][3] = findViewById(R.id.ball43);
        ball[4][4] = findViewById(R.id.ball44);
        ball[4][5] = findViewById(R.id.ball45);
        ball[4][6] = findViewById(R.id.ball46);
        ball[4][7] = findViewById(R.id.ball47);
        ball[4][8] = findViewById(R.id.ball48);
        ball[5][0] = findViewById(R.id.ball50);
        ball[5][1] = findViewById(R.id.ball51);
        ball[5][2] = findViewById(R.id.ball52);
        ball[5][3] = findViewById(R.id.ball53);
        ball[5][4] = findViewById(R.id.ball54);
        ball[5][5] = findViewById(R.id.ball55);
        ball[5][6] = findViewById(R.id.ball56);
        ball[5][7] = findViewById(R.id.ball57);
        ball[5][8] = findViewById(R.id.ball58);
        ball[6][0] = findViewById(R.id.ball60);
        ball[6][1] = findViewById(R.id.ball61);
        ball[6][2] = findViewById(R.id.ball62);
        ball[6][3] = findViewById(R.id.ball63);
        ball[6][4] = findViewById(R.id.ball64);
        ball[6][5] = findViewById(R.id.ball65);
        ball[6][6] = findViewById(R.id.ball66);
        ball[6][7] = findViewById(R.id.ball67);
        ball[6][8] = findViewById(R.id.ball68);
        ball[7][0] = findViewById(R.id.ball70);
        ball[7][1] = findViewById(R.id.ball71);
        ball[7][2] = findViewById(R.id.ball72);
        ball[7][3] = findViewById(R.id.ball73);
        ball[7][4] = findViewById(R.id.ball74);
        ball[7][5] = findViewById(R.id.ball75);
        ball[7][6] = findViewById(R.id.ball76);
        ball[7][7] = findViewById(R.id.ball77);
        ball[7][8] = findViewById(R.id.ball78);
        ball[8][0] = findViewById(R.id.ball80);
        ball[8][1] = findViewById(R.id.ball81);
        ball[8][2] = findViewById(R.id.ball82);
        ball[8][3] = findViewById(R.id.ball83);
        ball[8][4] = findViewById(R.id.ball84);
        ball[8][5] = findViewById(R.id.ball85);
        ball[8][6] = findViewById(R.id.ball86);
        ball[8][7] = findViewById(R.id.ball87);
        ball[8][8] = findViewById(R.id.ball88);


    }


    private void Animation() {
        MoveUp = AnimationUtils.loadAnimation(this, R.anim.trans_up);
        MoveDown = AnimationUtils.loadAnimation(this, R.anim.trans_down);
        MoveLeft = AnimationUtils.loadAnimation(this, R.anim.trans_left);
        MoveRight = AnimationUtils.loadAnimation(this, R.anim.trans_right);
    }


    //Tạo 5 bóng có sẵn
    public void firstAddBall() {
        String day1 = new String();
        Random random;
        random = new Random();
        int a, b, c, x, y, z;
        for (int i = 0; i < 5; i++) {
            do {
                a = random.nextInt(9);
                b = random.nextInt(9);
                c = random.nextInt(5);
            }
            while (ballPosition[a][b].status1 != 0);
            ballPosition[a][b].status1 = 2;
            ballPosition[a][b].color1 = c;
            day1 += "(" + a + "," + b + ")";


        }

        for (int i = 0; i < 3; i++) {
            do {
                x = random.nextInt(9);
                y = random.nextInt(9);
                z = random.nextInt(5);
            }
            while (ballPosition[x][y].status1 != 0);
            ballPosition[x][y].status1 = 1;
            ballPosition[x][y].color1 = z;


        }
//        Toast.makeText(this, day1, Toast.LENGTH_SHORT).show();
    }

    public void setBall() {

    }

    //Set visible
    public void setVisibleBall() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (ballPosition[i][j].status1 == 0) {
                    ball[i][j].setImageResource(0);

                } else if (ballPosition[i][j].status1 == 1) {
                    switch (ballPosition[i][j].color1) {
                        case 0:
                            ball[i][j].setImageResource(R.drawable.sphere_blue_a);
                            break;
                        case 1:
                            ball[i][j].setImageResource(R.drawable.sphere_green_a);
                            break;
                        case 2:
                            ball[i][j].setImageResource(R.drawable.sphere_orange_a);
                            break;
                        case 3:
                            ball[i][j].setImageResource(R.drawable.sphere_red_a);
                            break;
                        case 4:
                            ball[i][j].setImageResource(R.drawable.sphere_yellow_a);
                            break;
                    }

                } else {
                    switch (ballPosition[i][j].color1) {
                        case 0:
                            ball[i][j].setImageResource(R.drawable.sphere_blue);
                            break;
                        case 1:
                            ball[i][j].setImageResource(R.drawable.sphere_green);
                            break;
                        case 2:
                            ball[i][j].setImageResource(R.drawable.sphere_orange);
                            break;
                        case 3:
                            ball[i][j].setImageResource(R.drawable.sphere_red);
                            break;
                        case 4:
                            ball[i][j].setImageResource(R.drawable.sphere_yellow);
                            break;
                    }
                }
            }
        }
    }

    private void MoveBall(Position[][] aa, final int a, final int b, int c, int d, final ArrayList<String> ab) {
        if (ab.size() > 1) {
            tee = 2;
            frXdel = 0;
            frYdel = 0;
            switch (ab.get(1)) {
                case "u":
                    toXdel = 0;
                    toYdel = -0.10833f;
                    break;
                case "d":
                    toXdel = 0;
                    toYdel = 0.10833f;
                    break;
                case "l":
                    toXdel = -0.10833f;
                    toYdel = 0;
                    break;
                case "r":
                    toXdel = 0.10833f;
                    toYdel = 0;
                    break;
            }
            MoveBall1(aa, a, b, c, d, ab);
        }

    }

    private void MoveBall1(final Position[][] aa, final int a, final int b, final int c, final int d, final ArrayList<String> ab) {
        LinearInterpolator abb = new LinearInterpolator();
        RelativeLayout.LayoutParams para = (RelativeLayout.LayoutParams) ball[a][b].getLayoutParams();


        final TranslateAnimation move1 = new TranslateAnimation(2, frXdel, 2, toXdel, 2, frYdel, 2, toYdel);
        move1.setDuration(30);
        move1.setFillAfter(true);
        move1.setInterpolator(abb);
        ball[a][b].startAnimation(move1);


        move1.setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {
                if (tee < ab.size()) {
                    frXdel = toXdel;
                    frYdel = toYdel;
                    switch (ab.get(tee)) {
                        case "u":
                            toYdel = toYdel - 0.10833f;
                            break;
                        case "d":

                            toYdel = toYdel + 0.10833f;
                            break;
                        case "l":
                            toXdel = toXdel - 0.10833f;

                            break;
                        case "r":
                            toXdel = toXdel + 0.10833f;
                            break;
                    }
                }
                if (tee == ab.size()) {
                    frXdel = toXdel;
                    frYdel = toYdel;
                    toXdel = 0;
                    toYdel = 0;
                }
                deactiveBall();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (tee < ab.size() && tee > 0) {
                    MoveBall1(aa, a, b, c, d, ab);
                    tee++;
                } else if (tee == 0) {

                } else {


//


                    MoveBall1(aa, a, b, c, d, ab);
                    upgradeBall();

//                    setVisibleBall();
//                    ball[a][b].setImageResource(0);
                    checkScore();
                    if(scoreStatus==1){
                        reverseScoreBall();
                    }
                    setVisibleBall();
                    tv1.setText(mainPoint+"");
                    tee = 0;
                }
                activeBall();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });


    }

    private void upgradeBall() {
        int tt;
        Random random = new Random();
        int x, y, z;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ballPosition[i][j].lastBallStatus =0;
                if (ballPosition[i][j].status1 == 1) {
                    ballPosition[i][j].status1 = 2;
                    ballPosition[i][j].lastBallStatus =2;
                }
            }
        }
        tt = lateGame();


        for (int i = 0; i < tt; i++) {
            do {
                x = random.nextInt(9);
                y = random.nextInt(9);
                z = random.nextInt(5);
            }
            while (ballPosition[x][y].status1 != 0);

            if (gamestatus == 0) {
                ballPosition[x][y].status1 = 1;
                ballPosition[x][y].lastBallStatus =1;
            } else {
                ballPosition[x][y].status1 = 2;
                ballPosition[x][y].lastBallStatus =2;
            }
            ballPosition[x][y].color1 = z;


        }
//        setVisibleBall();
        if (lateGame() == 0) {
            gamestatus = 1;
        } else {
            gamestatus = 0;
        }
    }

    private int lateGame() {
        int slot = 0, pts;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (ballPosition[i][j].status1 == 0) {
                    slot++;
                }
            }
        }
        if (slot > 2) {
            pts = 3;
        } else {
            pts = slot;
        }

        return pts;
    }

    private void checkScore() {
        int score = 0;
        scoreStatus=0;

        //chieu ngang
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                int hor = 0;
                int k=100;
                if (ballPosition[i][j].status1 == 2) {
                    k = ballPosition[i][j].color1;
                }
                for (int l = 0; l < 4; l++) {
                    if ((ballPosition[i][j + 1 + l].color1 == k) && (ballPosition[i][j + 1 + l].status1 == 2)) {
                        hor++;
                    }
                }
                if (hor == 4) {
                    for (int p = 0; p < 5; p++) {
                        ballPosition[i][j + p].tick = 1;
                    }

                }
            }
        }

        //chieu doc

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                int ver = 0;
                int k=100;
                if (ballPosition[i][j].status1 == 2) {
                    k = ballPosition[i][j].color1;
                }
                for (int l = 0; l < 4; l++) {
                    if ((ballPosition[i + 1 + l][j].color1 == k) && (ballPosition[i + 1 + l][j].status1) == 2) {
                        ver++;
                    }
                }
                if (ver == 4) {

                    for (int p = 0; p < 5; p++) {
                        ballPosition[i + p][j].tick = 1;
                    }
                }
            }
        }

// cheo 1
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int cro = 0;
                int k=100;
                if (ballPosition[i][j].status1 == 2) {
                    k = ballPosition[i][j].color1;
                }
                for (int l = 0; l < 4; l++) {
                    if ((ballPosition[i + 1 + l][j + 1 + l].color1 == k) && (ballPosition[i + 1 + l][j + 1 + l].status1) == 2) {
                        cro++;
                    }
                }
                if (cro == 4) {

                    for (int p = 0; p < 5; p++) {
                        ballPosition[i + p][j + p].tick = 1;
                    }
                }
            }
        }


        //cheo 2

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int cro = 0;
                int k=100;
                if (ballPosition[8 - i][j].status1 == 2) {
                    k = ballPosition[8 - i][j].color1;
                }
                for (int l = 0; l < 4; l++) {
                    if ((ballPosition[8 - i - 1 - l][j + 1 + l].color1 == k) && (ballPosition[8 - i - 1 - l][j + 1 + l].status1) == 2) {
                        cro++;
                    }
                }
                if (cro == 4) {

                    for (int p = 0; p < 5; p++) {
                        ballPosition[8 - i - p][j + p].tick = 1;
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (ballPosition[i][j].tick == 1) {
                    score++;
                    ballPosition[i][j].tick = 0;
                    ballPosition[i][j].status1 = 0;
                }

            }
        }
        if (score ==5) {
            scoreStatus=1;
            mainPoint = mainPoint + 50;
        } else if (score >5 && score<9) {
            scoreStatus=1;
            mainPoint = mainPoint + 50 + (score-5)*15;
        } else if (score==9){
            scoreStatus=1;
            mainPoint =mainPoint + 125;
        } else if (score>9){
            scoreStatus=1;
            mainPoint=mainPoint+125+25*(score-9);
        }
    }
    private void reverseScoreBall(){

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(ballPosition[i][j].status1!=0){
                    if (ballPosition[i][j].lastBallStatus==1){
                        ballPosition[i][j].status1=0;
                    }else if (ballPosition[i][j].lastBallStatus==2){
                        ballPosition[i][j].status1=1;
                    }
                }
            }
        }
    }


    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private void deactiveBall(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ball[i][j].setEnabled(false);
            }
        }
    }
    private void activeBall(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ball[i][j].setEnabled(true);
            }
        }
    }

}
