package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class WorldRecordService {

    private ModelMapper modelMapper;

    private WorldRecordRepository worldRecordRepository;

    private RecorderRepository recorderRepository;

    public WorldRecordDto saveWorldRecord(WorldRecordCreateCommand command) {
        Recorder recorder = recorderRepository.findById(command.getRecorderId())
                .orElseThrow(() -> new RecorderNotFoundException(command.getRecorderId()));
        WorldRecord worldRecord = new WorldRecord(command.getDescription(), command.getValue(), command.getUnitOfMeasure(), command.getDateOfRecord(), recorder);
        worldRecordRepository.save(worldRecord);
        return new WorldRecordDto(worldRecord.getId(), worldRecord.getDescription(), worldRecord.getValue(), worldRecord.getUnitOfMeasure(), worldRecord.getDateOfRecord(), recorder.getName());
    }

//    public List<WorldRecordDto> listWorldRecords() {
//        Type targetListType = new TypeToken<List<WorldRecordDto>>() {}.getType();
//        return modelMapper.map(worldRecordRepository.findAll(), targetListType);
//    }


}
