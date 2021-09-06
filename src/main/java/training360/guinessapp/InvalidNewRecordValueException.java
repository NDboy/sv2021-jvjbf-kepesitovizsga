package training360.guinessapp;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidNewRecordValueException extends AbstractThrowableProblem {

    public InvalidNewRecordValueException(Double newValue, Double oldValue) {
        super(
                URI.create("worldrecords/bad-request"),
                "Can not beat",
                Status.BAD_REQUEST,
                String.format("The new record, %f must be higher than the previous %f", newValue, oldValue));
    }
}
