package fun.falco.microbenchmarks;

import java.util.Arrays;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class MatchHexPrefixBenchmark {

    private static final String[] DATA = {
        "0xa3",
        "0Xa3",
        "Hello, World!",
    };

    @Benchmark
    public void lowercaseString(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map(MatchHexPrefix::lowercaseString).toArray());
    }

    @Benchmark
    public void lowercasePrefix(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map(MatchHexPrefix::lowercasePrefix).toArray());
    }

    @Benchmark
    public void regex(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map(MatchHexPrefix::regex).toArray());
    }
}
