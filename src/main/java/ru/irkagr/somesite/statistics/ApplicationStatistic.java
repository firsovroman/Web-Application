package ru.irkagr.somesite.statistics;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.LongAdder;

@Component
public class ApplicationStatistic {

    private LocalDateTime countersResetTime;

    private LongAdder counterGreeting;
    private LongAdder counterMain;
    private LongAdder counterRegistration;
    private LongAdder counterForum;
    private LongAdder counterHotels;


    public ApplicationStatistic() {
        this.countersResetTime = LocalDateTime.now();

        this.counterGreeting = new LongAdder();
        this.counterMain = new LongAdder();
        this.counterRegistration = new LongAdder();
        this.counterForum = new LongAdder();
        this.counterHotels = new LongAdder();
    }

    public void reset() {
        counterGreeting.reset();
        counterMain.reset();
        counterRegistration.reset();
        counterForum.reset();
        counterHotels.reset();

        countersResetTime = LocalDateTime.now();
    }

    public LocalDateTime getCountersResetTime() {
        return countersResetTime;
    }

    public LongAdder getCounterGreeting() {
        return counterGreeting;
    }

    public LongAdder getCounterMain() {
        return counterMain;
    }

    public LongAdder getCounterRegistration() {
        return counterRegistration;
    }

    public LongAdder getCounterForum() {
        return counterForum;
    }

    public LongAdder getCounterHotels() {
        return counterHotels;
    }

    @Override
    public String toString() {
        return "ApplicationStatistic {" + "\n" +
                "countersResetTime=" + countersResetTime + "\n" +
                ", counterGreeting=" + counterGreeting + "\n" +
                ", counterMain=" + counterMain + "\n" +
                ", counterRegistration=" + counterRegistration + "\n" +
                ", counterForum=" + counterForum + "\n" +
                ", counterHotels=" + counterHotels + "\n" +
                '}';
    }
}
