package fun.falco.microbenchmarks;

import java.util.Arrays;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class StringToDimensionBenchmark {

    private static final String[] DATA = {
        "512",
        "1920x1080"
    };

    @Benchmark
    public void alwaysParse(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map(StringToDimension::alwaysParse).toArray());
    }

    @Benchmark
    public void parseConditionally(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map(StringToDimension::parseConditionally).toArray());
    }
}
