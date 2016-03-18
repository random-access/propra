package ess.algorithm.modules;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;
import ess.rules.IRule;
import ess.rules.sets.IRuleSet;
import ess.rules.sets.SolveRuleSet;

/**
 * This class is an implementation of IRuleChecker that checks all rules in parallel.
 * 
 * @author monika
 *
 */
public class ParallelRuleChecker implements IRuleChecker {

    private IRuleSet ruleSet;
    private Executor executor;
    private CompletionService<Boolean> completionService;
    private final int threadPoolSize;
    private ArrayList<Future<Boolean>> futureResults;
    private boolean result;
    private int expectedResults;
    
    // wrapper class for a single rule check
    private class IRuleWrapper implements Callable<Boolean> {

        private Tile tile;
        private Position pos;
        private IRule rule;

        IRuleWrapper(IRule rule, Tile tile, Position pos) {
            this.rule = rule;
            this.tile = tile;
            this.pos = pos;
        }

        @Override
        public Boolean call() {
            return rule.check(tile, pos);
        }
    }

    /**
     * Instantiates a ParallelRuleChecker.
     * 
     * @param composite
     *            the composite
     * @throws PropertyException
     *             if the config.properties file cannot be read or if it
     *             contains invalid parameters
     */
    public ParallelRuleChecker(Composite composite) throws PropertyException {
        ruleSet = new SolveRuleSet(composite);
        threadPoolSize = ruleSet.getExplicitRules().size();
        executor = Executors.newFixedThreadPool(threadPoolSize);
        completionService = new ExecutorCompletionService<Boolean>(executor);
        futureResults = new ArrayList<>();
    }

    /**
     * Checks all explicit rules in parallel, currently waiting for every rule check to finish.
     * Could be optimized by only running a single check until one of the results outputs false
     *
     * @see IRuleChecker#checkExplicitRules(ess.data.Composite, ess.data.Tile,
     *      ess.data.Position)
     */
    @Override
    public boolean checkExplicitRules(Composite c, Tile tile, Position pos) {
        futureResults.clear();
        for (IRule rule : ruleSet.getExplicitRules()) {
            futureResults.add(completionService.submit(new IRuleWrapper(rule, tile, pos)));
        }

        result = true;
        expectedResults = threadPoolSize;
        
        while (expectedResults > 0) {
            try {
                // take() blocks until a result is available
                result &= completionService.take().get();
                expectedResults--;
            } catch (Exception e) {
                // TODO error handling
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Checks if placing a tile breaks any of the implicit rules activated via
     * config.properties. If a rule is broken the tile cannot be placed, in this
     * case the check is stopped immediately.
     * 
     * @see IRuleChecker#checkExplicitRules(ess.data.Composite, ess.data.Tile,
     *      ess.data.Position)
     */
    @Override
    public synchronized boolean checkImplicitRules(Composite composite, Tile tile, Position pos) {
        for (IRule rule : ruleSet.getImplicitRules()) {
            if (!rule.check(tile, pos)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the composite is filled completely.
     * 
     * @see IRuleChecker#checkEndConditions(ess.data.Composite, ess.data.Tile,
     *      ess.data.Position)
     */
    @Override
    public synchronized boolean checkEndConditions(Composite composite, Tile tile, Position pos) {
        for (IRule rule : ruleSet.getEndConditions()) {
            if (!rule.check(tile, pos)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        ExecutorService pool = (ExecutorService) executor;
        pool.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                pool.awaitTermination(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}
