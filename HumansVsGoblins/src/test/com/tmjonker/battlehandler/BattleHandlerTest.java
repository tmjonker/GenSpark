package com.tmjonker.battlehandler;

import com.tmjonker.humanoid.Human;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BattleHandlerTest {

    Human player;
    BattleHandler battleHandler;

    @BeforeEach
    void setUp() {

        player = new Human("Dave");
        player.setX(5);
        player.setY(5);
    }

    @AfterEach
    void tearDown() {

        System.out.println("Test Completed");
    }

    @Test
    void fleeShouldResultInFive() {

        String ins = "flee";
        System.setIn(new ByteArrayInputStream(ins.getBytes()));
        Scanner scanner = new Scanner(System.in);
        battleHandler = new BattleHandler(scanner);
        assertEquals(5, battleHandler.commenceBattle(player)[0], "Expected 5");

        String ins1 = "flee";
        System.setIn(new ByteArrayInputStream(ins1.getBytes()));
        Scanner scanner1 = new Scanner(System.in);
        battleHandler = new BattleHandler(scanner1);
        assertEquals(5, battleHandler.commenceBattle(player)[1], "Expected 5");
    }

    @Test
    void fightShouldResultInNull() {

        String ins = "fight";
        System.setIn(new ByteArrayInputStream(ins.getBytes()));
        Scanner scanner = new Scanner(System.in);
        battleHandler = new BattleHandler(scanner);
        player.setDamage(15);
        assertNull(battleHandler.commenceBattle(player), "Expected null");
    }
}