package me.piebridge;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@Outcome(id = {"0, 0", "0, 2", "1, 0"}, expect = Expect.ACCEPTABLE, desc = "Normal outcome")
@Outcome(id = {"1, 2"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "Abnormal outcome")
@State
public class JlsMemoryModel {

    private int a = 0;
    private int b = 0;

    @Actor
    public void thread1(II_Result r) {
        r.r2 = a;
        b = 1;
    }

    @Actor
    public void thread2(II_Result r) {
        r.r1 = b;
        a = 2;
    }

}
