package me.piebridge;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@Outcome(id = {".*"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"1, 1"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Abnormal outcome")
@State
public class Reorder8234Variant {

    private int x = 0;
    private int y = 0;

    @Actor
    public void process0(II_Result r) {
        r.r2 = y;
        x = 1;
    }

    @Actor
    public void process1(II_Result r) {
        r.r1 = x;
        y = 1;
    }

}
