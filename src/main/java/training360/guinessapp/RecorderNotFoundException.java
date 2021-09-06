package training360.guinessapp;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class RecorderNotFoundException extends AbstractThrowableProblem {

    public RecorderNotFoundException(Long id) {
        super(
                URI.create("recorders/not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Not found with id '%d'", id));
    }
}
