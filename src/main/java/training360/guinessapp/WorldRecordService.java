package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training360.guinessapp.dto.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
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

    @Transactional
    public BeatWorldRecordDto beatWorldRecord(long id, BeatWorldRecordCommand command) {
        WorldRecord worldRecord = worldRecordRepository.findById(id)
                .orElseThrow(() -> new WorldRecordNotFoundException(id));
        Recorder newRecorder = recorderRepository.findById(command.getRecorderId())
                .orElseThrow(() -> new RecorderNotFoundException(command.getRecorderId()));
        Double difference = Math.abs(command.getNewValue() - worldRecord.getValue());

        if(command.getNewValue() < worldRecord.getValue()) {
            throw new InvalidNewRecordValueException(command.getNewValue(), worldRecord.getValue());
        }
        String oldRecorderName = worldRecord.getRecorder().getName();
        Double oldRecordValue = worldRecord.getValue();

        worldRecord.setRecorder(newRecorder);
        worldRecord.setDateOfRecord(LocalDate.now());
        worldRecord.setValue(command.getNewValue());

        return new BeatWorldRecordDto(worldRecord.getDescription(), worldRecord.getUnitOfMeasure(), oldRecorderName, oldRecordValue, newRecorder.getName(), command.getNewValue(), difference);
    }



}
