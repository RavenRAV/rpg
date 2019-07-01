package com.company;


import java.util.Random;

public class Main {
    public static int[] health = {1000, 250, 250, 250, 250, 250, 200, 260, 500};//здоровье
    public static int[] hits = {60, 20, 20, 20, 10, 20, 15, 25, 10};//урон и лечение
    public static String[] hitTypes = {"Physical ", "Physical ", "Magical ", "Mental ", "Healer ", "Tor", "Trickster", "Berserk", "Tank"};//тип защиты и типы атаки


    public static void main(String[] args) {
        int r = 1;
        while (!isFinished()) {
            System.out.println("round "+r );
            changeBossDefence();
            round();
            printStatistics();
            r++;
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNumber = r.nextInt(8) + 1;
        hitTypes[0] = hitTypes[randomNumber];//рандомная смена защиты босса
    }

    public static void round() {
        for (int i = 1; i <= 8; i++) {
            if (health[0] > 0) {
                int damagedHealth = playerHit(i);//урон здоровью равен урону игроков
                if (damagedHealth < 0) {
                    health[0] = 0;//если босс мертв - герои побеждают
                } else {
                    health[0] = damagedHealth;//если жив теряет здоровье
                }
            }
        }


        if (health[0] > 0) {
            for (int i = 1; i <= 8; i++) {
                if (hitTypes[0].equals(hitTypes[5])) {//тор
                        if (health[i] <= 0) {
                            health[i] = 0;
                        } else {
                            health[i] = bossHit(i);}

                }else if(hitTypes[0].equals(hitTypes[6])){//ловкач
                    if (health[i] <= 0) {
                        health[i] = 0;
                    }
                    if(health[i]==health[6]){
                        health[i]=health[i];
                    }

                } else if(hitTypes[0].equals(hitTypes[7])){//берсерк
                    if (health[i] <= 0) {
                        health[i] = 0;
                    }else {
                        health[i] = bossHit(7)+20;//блокирует урон(урон босса + 20(заблокированный))
                    }
                } else if(hitTypes[0].equals(hitTypes[8])){//танк
                    if (health[i] <= 0) {
                        health[i] = 0;
                    }else if (health[i]==health[8]){
                        health[8]= bossHit(8)-30*7;
                    }else{
                        health[i] = bossHit(i)+30;
                    }
                }
                else {
                    health[i] = bossHit(i);//пока босс жив - наносит урон всем героям
                }







                if (health[i] > 0 && health[4] > 0) {//хилер
                   health[i] = playerHeal(i);
                }
            }
        }

    }

    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won!!!");
            return true;// здоровье босса ниже или равно нулю - герои победили
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0 && health[5] <= 0 && health[6] <= 0 && health[7] <= 0 && health[8] <= 0) {
            System.out.println("Boss won!!!");
            return true;//если здоровье каждого героя ниже или равно нулю - босс победил
        }
        return false;//есть ли живые игроки
    }

    public static int playerHit(int playerIndex) {
        Random r = new Random();
        int randomNamber = r.nextInt(7) + 2;
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            System.out.println(hitTypes[playerIndex] + " hits: " + "x" + randomNamber + " " + hits[playerIndex] * randomNamber);

            if (hitTypes[0].equals(hitTypes[5])) {
                System.out.println("Тор оглушил босса");
            }
            if (hitTypes[0].equals(hitTypes[6])){
                System.out.println("Ловкач увернулся");
            }
            if (hitTypes[0].equals(hitTypes[7])){
                System.out.println("Берсерк заблокировал часть урона и вернул боссу");
                health[0]=health[0]-20;//возвращает часть урона
            }
            if (hitTypes[0].equals(hitTypes[8])){
                System.out.println("Танк забрал часть урона");
            }
            return health[0] - hits[playerIndex] * randomNamber;//В зависимости от защиты босса у разных ироков проходит крит урон.
        } else {
            return health[0] - hits[playerIndex];//здоровье босса - урон
        }
    }

    public static int playerHeal(int playerIndex) {
        return health[playerIndex] + 10;//здоровье игроков + 10
    }

    public static int bossHit(int playerIndex) {
        if (hitTypes[0].equals(hitTypes[5])) {
            return health[playerIndex];
        } else {
            return health[playerIndex] - hits[0];//урон боса по каждому игроку
        }
    }

    public static void printStatistics() {
        System.out.println("_______________");
        System.out.println("Boss health: " + health[0]);
        System.out.println("Warrior health: " + health[1]);
        System.out.println("Magic health: " + health[2]);
        System.out.println("Kinetic health: " + health[3]);
        System.out.println("Healer health: " + health[4]);
        System.out.println("Tor health: " + health[5]);
        System.out.println("Trickster health: " + health[6]);
        System.out.println("Berserk health: " + health[7]);
        System.out.println("Tank health: " + health[8]);
        System.out.println("_______________");
    }

}
