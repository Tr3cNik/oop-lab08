package it.unibo.mvc.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Standard Output View of DrawNumber application.
 */
public class DrawNumberStandardOutputView implements DrawNumberView {

    private DrawNumberController controller;

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI2")
    @Override
    public void setController(final DrawNumberController observer) {
        this.controller = observer;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("UwF")
    @Override
    public void start() {
        System.out.println("Game started"); // NOPMD
        controller.resetGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription()); // NOPMD
    }

}
