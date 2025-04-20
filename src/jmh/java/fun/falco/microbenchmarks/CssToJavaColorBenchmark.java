package fun.falco.microbenchmarks;

import java.util.Arrays;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class CssToJavaColorBenchmark {

    private static final String[] DATA = {
        "#FFF",
        "#FFFFFFFF"
    };

    @Benchmark
    public void allLengths(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map(CssToJavaColor::allLengths).toArray());
    }

    @Benchmark
    public void regexToDuplicateChars(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map(CssToJavaColor::regexToDuplicateChars).toArray());
    }
}
