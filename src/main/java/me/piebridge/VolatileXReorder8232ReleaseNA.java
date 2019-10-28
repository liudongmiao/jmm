package me.piebridge;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@Outcome(id = {"0, 0", "0, 1", "1, 1"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"1, 0"}, expect = Expect.FORBIDDEN, desc = "Abnormal outcome")
@State
public class VolatileXReorder8232ReleaseNA {

    private volatile int x = 0;
    private int y = 0;

    @Actor
    public void process0(II_Result r) {
        // can reorder? NO
        x = 1;
        y = 1;
    }

    @Actor
    public void process1(II_Result r) {
        r.r1 = y;
        synchronized (this) {
            r.r2 = x;
        }
    }

}
