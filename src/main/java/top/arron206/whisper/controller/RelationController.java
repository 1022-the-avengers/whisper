package top.arron206.whisper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.arron206.whisper.dto.UserInformation;
import top.arron206.whisper.service.RelationshipService;
import top.arron206.whisper.vo.GeneralResponse;
import top.arron206.whisper.vo.RelationshipMessage;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/verification/user")
public class RelationController {
    @Autowired
    RelationshipService relationshipService;

    @RequestMapping(value = "/relationship", method = RequestMethod.POST)
    public GeneralResponse saveRelationship(@RequestBody RelationshipMessage relationshipMessage) {
        this.relationshipService.saveRelationShip(relationshipMessage);
        return new GeneralResponse("好友关系更新成功", null,
                "",
                "",
                "",
                "");
    }

    @RequestMapping(value = "/relationship", method = RequestMethod.DELETE)
    public GeneralResponse deleteRelationship(@RequestParam("customId") int customId) {
        this.relationshipService.deleteRelationShip(customId);
        return new GeneralResponse("好友关系解除成功", null,
                "",
                "",
                "",
                "");
    }

    @RequestMapping(value = "/relationships", method = RequestMethod.GET)
    public GeneralResponse getRelationships() {
        Map<String, List<UserInformation>> relationships = this.relationshipService.getRelationShips();
        return new GeneralResponse("好友列表获取成功", relationships,
                "",
                "",
                "",
                "");
    }
}
