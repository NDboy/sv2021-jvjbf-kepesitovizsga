package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recorders")
@AllArgsConstructor
public class RecorderController {

    private RecorderService recorderService;

    @GetMapping
    public List<RecorderShortDto> listRecorders() {
        return recorderService.listRecorders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto saveRecorder(@Valid @RequestBody RecorderCreateCommand command) {
        return recorderService.saveRecorder(command);
    }




}
