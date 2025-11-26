package it.unibo.deathnote.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of {@Link DeathNote}.
 */
public class DeathNoteImpl implements DeathNote {

    private static final int TIME_FOR_DEATH_CAUSES = 40;
    private static final int TIME_FOR_DEATH_DETAILS = 6040;
    private final Map<String, Death> deaths = new LinkedHashMap<>();
    private String lastName;
    private long timeLastName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException(ruleNumber + "is not a valid number");
        }
        return RULES.get(ruleNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {
        Objects.requireNonNull(name, name + "is null");
        if (isNameWritten(name)) {
            throw new IllegalArgumentException(name + "was already written");
        }
        deaths.put(name, new Death());
        this.lastName = name;
        this.timeLastName = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (cause == null || lastName == null || !isNameWritten(lastName)) {
            throw new IllegalStateException(cause + "is null, or the name is not written");
        }
        if (System.currentTimeMillis() - timeLastName > TIME_FOR_DEATH_CAUSES) {
            return false;
        }
        deaths.get(lastName).setCause(cause);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        if (details == null || lastName == null || !isNameWritten(lastName)) {
            throw new IllegalStateException(details + "is null, or the name is not written");
        }
        if (System.currentTimeMillis() - timeLastName > TIME_FOR_DEATH_DETAILS) {
            return false;
        }
        deaths.get(lastName).setDetails(details);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException(name + "is not written");
        }
        return deaths.get(name).deathCause;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException(name + "is not written");
        }
        return deaths.get(name).deathDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        return deaths.containsKey(name);
    }

    private static class Death {
        private String deathCause;
        private String deathDetails;

        Death() {
            this.deathCause = "Heart attack";
            this.deathDetails = "";
        }

        public void setCause(final String cause) {
            this.deathCause = cause;
        }

        public void setDetails(final String details) {
            this.deathDetails = details;
        }
    }
}
