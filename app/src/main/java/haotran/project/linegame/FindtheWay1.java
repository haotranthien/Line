package haotran.project.linegame;

import java.util.ArrayList;

class FindtheWay1 {
    private ArrayList<String> route1 = new ArrayList<>(); //Tao list toa do duong di
    private String lybRoute[] = {"urd", "uld", "dru", "dlu", "ldr", "lur", "rul", "rdl", "r", "l", "r", "l", "d", "u", "u", "d"};


    ArrayList<String> findRoute(Position[][] aa, int a, int b, int c, int d) {
        int x = a, y = b;
        String text1 = "";

        // Gắn giá trị để làm việc trong status2
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                aa[i][j].status2 = aa[i][j].status1;
                if (aa[i][j].status2==1){
                    aa[i][j].status2=0;
                }
            }
        }
        returnBlank(aa);


        route1.add("start ");
        do {

            switch (checkCover(aa, x, y, c, d)) {
                case 1:
                    x = x + 1;
                    break;
                case 2:
                    y = y + 1;
                    break;
                case 3:

                    x = x - 1;
                    break;
                case 4:
                    y = y - 1;
                    break;
                case 0:
                    route1.clear();
                    route1.add("start ");
                    x = a;
                    y = b;
                    if (!checkNull(aa, a, b)) {
                        x = c;
                        y = d;
                    }
                    returnBlank(aa);
                    break;
            }
        } while ((x != c) || (y != d));


        for (int i = 1; i < route1.size(); i++) {
            text1 = text1 + route1.get(i);
        }
        text1 = improveRoute(text1);
        route1.clear();
        route1.add("start ");
        for (int i = 0; i < text1.length(); i++) {
            route1.add(text1.charAt(i) + "");
        }


        return route1; // Trả về kết quả là 1 chuỗi
    }


    //Check cac o xung quanh. status=3 la tam thoi, status = 4 la khoa. Neu đi vào đường cụt thì ô đó là 4.
    private int checkCover(Position[][] aa, int a, int b, int c, int d) {  //c tu 4 den 7    c/4 +x(1~4)
        int status3 = 0;
        int a1 = a;
        int b1 = b;
        int c1 = c;
        int d1 = d;
        int cb = changeCount1(a1, b1, c1, d1);
        if (cb == 1) {
            if (checkSlot(aa, a + 1, b) && (route1.get(route1.size() - 1) != "u")) {
                route1.add("d");
                status3 = 1;
            }else if (checkSlot(aa, a, b + 1) && (route1.get(route1.size() - 1) != "l")) {
                route1.add("r");
                status3 = 2;
            } else if (checkSlot(aa, a - 1, b) && (route1.get(route1.size() - 1) != "d")) {
                route1.add("u");
                status3 = 3;
            } else if (checkSlot(aa, a, b - 1) && (route1.get(route1.size() - 1) != "r")) {
                route1.add("l");
                status3 = 4;
            } else {
                aa[a][b].status2 = 2;
            }
        } else if (cb == 2) {
             if (checkSlot(aa, a, b + 1) && (route1.get(route1.size() - 1) != "l")) {
                 route1.add("r");
                 status3 = 2;
             } else if (checkSlot(aa, a - 1, b) && (route1.get(route1.size() - 1) != "d")) {
                route1.add("u");
                status3 = 3;
            } else if (checkSlot(aa, a, b - 1) && (route1.get(route1.size() - 1) != "r")) {
                route1.add("l");
                status3 = 4;
            } else if (checkSlot(aa, a + 1, b) && (route1.get(route1.size() - 1) != "u")) {
                route1.add("d");
                status3 = 1;
            } else {
                aa[a][b].status2 = 2;
            }
        } else if (cb == 3) {
            if (checkSlot(aa, a - 1, b) && (route1.get(route1.size() - 1) != "d")) {
                route1.add("u");
                status3 = 3;
            } else if (checkSlot(aa, a, b - 1) && (route1.get(route1.size() - 1) != "r")) {
                route1.add("l");
                status3 = 4;
            } else if (checkSlot(aa, a + 1, b) && (route1.get(route1.size() - 1) != "u")) {
                route1.add("d");
                status3 = 1;
            } else if (checkSlot(aa, a, b + 1) && (route1.get(route1.size() - 1) != "l")) {
                route1.add("r");
                status3 = 2;

            } else {
                aa[a][b].status2 = 2;
            }


        } else if (cb == 4) {
            if (checkSlot(aa, a, b - 1) && (route1.get(route1.size() - 1) != "r")) {
                route1.add("l");
                status3 = 4;
            } else if (checkSlot(aa, a + 1, b) && (route1.get(route1.size() - 1) != "u")) {
                route1.add("d");
                status3 = 1;
            } else if (checkSlot(aa, a, b + 1) && (route1.get(route1.size() - 1) != "l")) {
                route1.add("r");
                status3 = 2;
            } else if (checkSlot(aa, a - 1, b) && (route1.get(route1.size() - 1) != "d")) {
                route1.add("u");
                status3 = 3;
            } else {
                aa[a][b].status2 = 2;
            } //end

        }


        return status3;


    }


    private boolean checkSlot(Position[][] aa, int a, int b) {
        boolean posible;

        //Check 1 o
        //
        if (a >= 0 && a < 9 && b >= 0 && b < 9) {
            if (aa[a][b].status2 == 0) {
                aa[a][b].status2 = 3;
                posible = true;


            } else {
                posible = false;
            }
        } else {
            posible = false;
        }
        return posible;

    }

    private void returnBlank(Position[][] aa) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (aa[i][j].status2 == 3) {
                    aa[i][j].status2 = 0;
                }
            }
        }
    }

    //         ddd
    /// start 6789
    //  0     1234

    private String improveRoute(String aa) {
        boolean b;
        String ab = aa;
        do {


            for (int i = 0; i < 8; i++) {   //start
                do {
                    if (ab.indexOf(lybRoute[i]) != -1) {
                        ab = ab.replace(lybRoute[i], lybRoute[i + 8]);
                        b = true;
                    } else {
                        b = false;
                    }
                } while (b);
            } //end

        } while (!subImproveRoute(ab));


        return ab;
    }


    private boolean subImproveRoute(String aa) {
        boolean c = true;
        String bb = aa;

        for (int i = 0; i < 8; i++) {   //start

            if (bb.indexOf(lybRoute[i]) != -1) {
                c = false;
            }

        } //end


        return c;
    }

    //true : di tiep duoc
    //false : khong di tiep duoc
    private boolean checkNull(Position[][] aa, int a, int b) {
        boolean c;
        if (!checkSlot1(aa, a + 1, b) && !checkSlot1(aa, a - 1, b) && !checkSlot1(aa, a, b + 1) && !checkSlot1(aa, a, b - 1)) {
            c = false;
        } else c = true;
        return c;
    }

    private boolean checkSlot1(Position[][] aa, int a, int b) {
        boolean posible;

        //Check 1 o
        //
        if (a >= 0 && a < 9 && b >= 0 && b < 9) {
            if (aa[a][b].status2 == 2) {
                posible = false;


            } else {
                posible = true;
            }
        } else {
            posible = false;
        }
        return posible;

    }

//    1 = down
//    2 = up
//    3 = right
//    4 = left

    private int changeCount1(int x, int y, int c, int d) {

//        if ((x < c )&& (y <= d)) {
//            return  1;
//        }else if ((x >= c) && (y < d)) {
//            return 2;
//        }else if ((x > c) && (y >= d)) {
//            return  3;
//        }else if ((x <= c) && (y > d)) {
//            return  4;
//        } else {
//            return  1;
//        }

        if (x < c) {
            if (y < d) {
                return 1;
            } else if (y == d) {
                return 1;
            } else {
                return 4;
            }
        } else if (x > c) {
            if (y < d) {
                return 2;
            } else if (y == d) {
                return 3;
            } else {
                return 3;
            }
        } else {
            if (y < d) {
                return 2;
            }
            if (y > d) {
                return 4;
            } else {
                return 1;
            }
        }


    }


}
