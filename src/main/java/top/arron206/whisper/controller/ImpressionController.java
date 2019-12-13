package top.arron206.whisper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.arron206.whisper.entity.Impression;
import top.arron206.whisper.service.ImpressionService;
import top.arron206.whisper.vo.GeneralResponse;
import top.arron206.whisper.vo.ImpressionMessage;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class ImpressionController {
    @Autowired
    ImpressionService impressionService;

    @RequestMapping(value = "/impression", method = RequestMethod.POST)
    public GeneralResponse saveImpression(@RequestBody ImpressionMessage impressionMessage) {
        this.impressionService.saveImpression(impressionMessage);
        return new GeneralResponse("好友印象更新成功", null,
                "",
                "",
                "",
                "");
    }

    @RequestMapping(value = "/impression", method = RequestMethod.GET)
    public GeneralResponse getImpression(@RequestParam int id) {
        List<String> contents = this.impressionService.getImpressions(id);
        return new GeneralResponse("好友印象列表获取成功", contents,
                "",
                "",
                "",
                "");
    }
}
