package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecorderService {

    private RecorderRepository recorderRepository;

    private ModelMapper modelMapper;

    public List<RecorderShortDto> listRecorders() {
        Type targetListType = new TypeToken<List<RecorderShortDto>>() {}.getType();

        List<Recorder> recorders = recorderRepository.listRecordersByCharacters();
        return modelMapper.map(recorders, targetListType);
    }

    public RecorderDto saveRecorder(RecorderCreateCommand command) {
        Recorder recorder = new Recorder(command.getName(), command.getDateOfBirth());
        recorderRepository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }


}
