package top.arron206.whisper.service;

import top.arron206.whisper.entity.Impression;
import top.arron206.whisper.vo.ImpressionMessage;

import java.util.List;

public interface ImpressionService {
    void saveImpression(ImpressionMessage impressionMessage);

    List<String> getImpressions(int id);
}
