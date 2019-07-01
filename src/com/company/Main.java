package com.company;

import java.util.Random;

public class Main {
    public static int[] health = {850, 250, 250, 250, 210};
    public static int[] hits = {65, 20, 20, 20, 20};
    public static String[] hitTypes = {"Physical", "Physical",
            "Magical", "Mental", "Support"};


    public static void main(String[] args) {
        while (!isFinished()) {
            changeBossDefence();
            round();
            ptintStatistics();
        }
    }


    public static void changeBossDefence() {
        Random r = new Random();
        int randomNum = r.nextInt(4) + 1;
        hitTypes[0] = hitTypes[randomNum];
    }


    public static void round() {
        for (int i = 1; i <= 4; i++) {
            if (health[0] > 0) {
                int damagedHP = playerHit(i);//players beat>
                if (damagedHP < 0) {
                    health[0] = 0;
                } else {
                    health[0] = damagedHP;
                }
            }
        }

        if (health[0] > 0) {
            for (int i = 1; i <= 4; i++) {
                if (health[i] <= 0) {
                    health[i] = 0;
                } else {
                    health[i] = bossHit(i);//beats the boss>
                }

                if (health[i] > 0 && health[4] > 0 && health[i] != health[4]) {//if the player is alive after hitting the boss>
                    health[i] = health[i] + 15;
                }
            }
        }
    }


    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static int playerHit(int playerIndex) {
        if (hitTypes[0].equals(hitTypes[playerIndex])) {

            Random r = new Random();
            int randomNumber = r.nextInt(7) + 2; // 0,1,2,3,4
            System.out.println(hitTypes[playerIndex] + "hits:" + hits[playerIndex] * randomNumber);
            return health[0] - hits[playerIndex] * randomNumber;
        } else {
            return health[0] - hits[playerIndex];

        }
    }

    public static int bossHit(int playerIndex) {
        return health[playerIndex] - hits[0];
    }

    public static void ptintStatistics() {
        System.out.println("______________");
        System.out.println("Boss heath :" + health[0]);
        System.out.println("Warrior heath :" + health[1]);
        System.out.println("Magic heath :" + health[2]);
        System.out.println("Mental heath :" + health[3]);
        System.out.println("Support health :" + health[4]);
        System.out.println("______________");
    }
}