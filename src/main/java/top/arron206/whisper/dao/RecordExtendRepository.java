package top.arron206.whisper.dao;

import top.arron206.whisper.dto.RecordDto;
import top.arron206.whisper.entity.User;

import java.util.List;

public interface RecordExtendRepository {
    List<RecordDto> getChatRecord(int host, int customer, int page);
    void deleteRecord(Integer sender, Integer recipient);
}
