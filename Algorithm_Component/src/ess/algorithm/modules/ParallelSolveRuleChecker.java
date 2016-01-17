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

public class ParallelSolveRuleChecker implements IRuleChecker {

    private IRuleSet ruleSet;
    private Executor executor;
    private CompletionService<Boolean> completionService;
    private final int threadPoolSize;

    private class IRuleCallableWrapper implements Callable<Boolean> {

        private Tile tile;
        private Position pos;
        private IRule rule;

        IRuleCallableWrapper(IRule rule, Tile tile, Position pos) {
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
     * Instantiates a ParallelSolveRuleChecker.
     * 
     * @param composite
     *            the composite
     * @throws PropertyException
     *             if the config.properties file cannot be read or if it
     *             contains invalid parameters
     */
    public ParallelSolveRuleChecker(Composite composite) throws PropertyException {
        ruleSet = new SolveRuleSet(composite);
        threadPoolSize = ruleSet.getExplicitRules().size();
        executor = Executors.newFixedThreadPool(threadPoolSize);
        completionService = new ExecutorCompletionService<Boolean>(executor);
    }
    

    @Override
    public synchronized boolean checkExplicitRules(Composite c, Tile tile, Position pos) {
        ArrayList<Future<Boolean>> futureResults = new ArrayList<>();
        for (IRule rule : ruleSet.getExplicitRules()) {
            futureResults.add(completionService.submit(new IRuleCallableWrapper(rule, tile, pos)));
        }

        int expectedResults = threadPoolSize;
        boolean result = true;
        try {
            while (expectedResults > 0) {
                try {
                    // take() blocks until a result is available
                    result &= completionService.take().get();
                    expectedResults--;
//                    if (!result) {
//                        break;
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
//            for (Future<Boolean> f : futureResults) {
//                f.cancel(true);
//            }
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
        System.out.println("Finishing..");
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
