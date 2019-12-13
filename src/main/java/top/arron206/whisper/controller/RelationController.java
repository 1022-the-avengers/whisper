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
    public GeneralResponse deleteRelationship(@RequestParam("hostId") int hostId,
                                          @RequestParam("customId") int customId) {
        this.relationshipService.deleteRelationShip(hostId, customId);
        return new GeneralResponse("好友关系解除成功", null,
                "",
                "",
                "",
                "");
    }

    @RequestMapping(value = "/relationship", method = RequestMethod.GET)
    public GeneralResponse getRelationship(@RequestParam("hostId") int hostId) {
        Map<String, List<UserInformation>> relationships = this.relationshipService.getRelationShips(hostId);
        return new GeneralResponse("好友列表获取成功", relationships,
                "",
                "",
                "",
                "");
    }
}
