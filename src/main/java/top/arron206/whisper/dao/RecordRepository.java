package top.arron206.whisper.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import top.arron206.whisper.entity.Record;


public interface RecordRepository extends JpaRepository<Record, Integer>, RecordExtendRepository{
}
