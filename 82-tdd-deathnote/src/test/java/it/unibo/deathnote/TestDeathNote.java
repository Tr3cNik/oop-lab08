package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final int DEATH_CAUSE_TIME = 100;
    private static final int DEATH_DETAILS_TIME = 6100;
    private DeathNote ryukDeathNote;

    @BeforeEach
    public void setUp() {
        ryukDeathNote = new DeathNoteImpl();
    }

    /**
     * Tests that 0 or negative number are not allowed.
     */
    @Test
    void exceptionsRuleNumberTest() {
        final var list = List.of(0, -1);
        for (final int i: list) {
            assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    ryukDeathNote.getRule(i);
                }
            });
        }
    }

    /**
     * Tests that all rules aren't empty or null.
     */
    @Test
    void emptyOrNullRuleTest() {
        for (final String rule: DeathNote.RULES) {
            assertNotNull(rule);
            assertNotEquals("", rule.strip());
        }
    }

    /**
     * Tests the conformity of the names.
     */
    @Test
    void writeNameTest() {
        final String l = "L Lawliet";
        final String n = "Nate River";
        assertFalse(ryukDeathNote.isNameWritten(l));
        ryukDeathNote.writeName(l);
        assertTrue(ryukDeathNote.isNameWritten(l));
        assertFalse(ryukDeathNote.isNameWritten(n));
        assertFalse(ryukDeathNote.isNameWritten(""));
    }

    /**
     * Tests the use of death's cause.
     * 
     * @throws InterruptedException qualcosa
     */
    @Test
    void causeOfDeathTest() {
        final String cause = "smerdo";
        final String cause2 = "sbincio";
        final String m = "Mello";
        final String light = "Yagami";
        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ryukDeathNote.writeDeathCause(cause);
            }
        });
        ryukDeathNote.writeName(m);
        assertEquals("Heart attack", ryukDeathNote.getDeathCause(m));
        ryukDeathNote.writeName(light);
        ryukDeathNote.writeDeathCause("karting accident");
        assertEquals("karting accident", ryukDeathNote.getDeathCause(light));
        try {
            Thread.sleep(DEATH_CAUSE_TIME);
        } catch (final InterruptedException e) {
            System.out.println("qualcosa"); // NOPMD
        }
        ryukDeathNote.writeDeathCause(cause2);
        assertNotEquals(cause2, ryukDeathNote.getDeathCause(light));
    }

    /**
     * Tests the use of death's details.
     * 
     * @throws InterruptedException qualcosa
     */
    @Test
    void detailsOfDeathTest() {
        final String detail = "Lost all its money into cryptos";
        final String detail2 = "Fell into a goth mommy's thighs";
        final String terrone = "Luca";
        final String terroneDoc = "Carmelo"; 
        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ryukDeathNote.writeDetails(detail);
            } 
        });
        ryukDeathNote.writeName(terrone);
        assertEquals("", ryukDeathNote.getDeathDetails(terrone));
        ryukDeathNote.writeDetails("ran for too long");
        assertEquals("ran for too long", ryukDeathNote.getDeathDetails(terrone));
        ryukDeathNote.writeName(terroneDoc);
        try {
            Thread.sleep(DEATH_DETAILS_TIME);
        } catch (final InterruptedException e) {
            System.out.println("qualcosaltro"); // NOPMD
        }
        ryukDeathNote.writeDetails(detail);
        assertNotEquals(detail2, ryukDeathNote.getDeathDetails(terroneDoc));
    }

}
