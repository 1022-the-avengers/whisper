package top.arron206.whisper.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.arron206.whisper.dto.RecordDto;
import top.arron206.whisper.entity.Record;
import top.arron206.whisper.entity.User;

import java.util.List;


public interface RecordRepository extends JpaRepository<Record, Integer>, RecordExtendRepository{

}
