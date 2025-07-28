package fun.falco.microbenchmarks;

import java.time.DayOfWeek;
import java.util.Arrays;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class EnumStringToConstBenchmark {

    private static final Object[][] DATA = {
        new Object[] {Enum.class, "java.util.concurrent.TimeUnit.NANOSECONDS"},
        new Object[] {DayOfWeek.class, "java.time.DayOfWeek#MONDAY"},
        new Object[] {DayOfWeek.class, "MONDAY"},
    };

    @Benchmark
    public void usingRegex(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map((arr) -> {
            return EnumStringToConst.usingRegex((Class<Enum<?>>) arr[0], (String) arr[1]);
        }).toArray());
    }

    @Benchmark
    public void stringParsing(final Blackhole blackhole) {
        blackhole.consume(Arrays.stream(DATA).map((arr) -> {
            return EnumStringToConst.stringParsing((Class<Enum<?>>) arr[0], (String) arr[1]);
        }).toArray());
    }
}
