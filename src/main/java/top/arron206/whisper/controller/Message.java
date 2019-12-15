package top.arron206.whisper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.arron206.whisper.dao.RecordRepository;
import top.arron206.whisper.dto.*;
import top.arron206.whisper.entity.User;
import top.arron206.whisper.service.*;
import top.arron206.whisper.vo.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping(value = "/verification/message")
public class Message {

    @Autowired
    UserService userService;

    @Autowired
    UserMessage userMessage;

    @Autowired
    RecordRepository recordRepository;

    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public InfoMessage getIndex(@RequestParam Integer customer){
        User user = userService.setUser();
        if(!(customer==null||customer==-1)){
            userMessage.readMessage(user.getId(),customer);
        }
        Map<Object, Object> unreadMessages = userMessage.getAllUnread(user.getId());
        Map<Object, Object> unreadNum = userMessage.getAllUnreadNum(user.getId());
        LinkMessage linkMessage = new LinkMessage(
                "",
                "",
                "",
                "");
        return new InfoMessage("获取成功", unreadMessages, unreadNum, new UserInformation(
                user.getId(), user.getNickname(), user.getPic(), user.getGender(), user.getAge()
        ), linkMessage);
    }

    @RequestMapping(value = "/history", method = {RequestMethod.GET})
    public HistoryMessage getHistory(@RequestParam Integer customer, @RequestParam Integer page){
        User user = userService.setUser();
        userMessage.readMessage(user.getId(),customer);
        List<RecordDto> records = recordRepository.getChatRecord(user.getId(), customer, page);
        List<Map<String, String>> chatMessages = new ArrayList<>();
        for(RecordDto recordDto : records){
            if(recordDto.getSenderId()==user.getId()){
                chatMessages.add(new HashMap<String, String>(){{
                        put("my", recordDto.getContent());
                    }});
            }else{
                chatMessages.add(new HashMap<String, String>(){{
                    put("he", recordDto.getContent());
                }});
            }
        }
        LinkMessage linkMessage = new LinkMessage(
                "",
                "",
                "",
                "");
        return new HistoryMessage("获取成功", chatMessages, linkMessage);
    }

    @RequestMapping(value = "/history/download", method = {RequestMethod.GET})
    public ResponseEntity<FileSystemResource> download(@RequestParam Integer customer){
        User user = userService.setUser();
        List<RecordDto> records = recordRepository.getChatRecord(user.getId(), customer, -1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        try {
            File file = new File("history/"+user.getId()+"with"+customer+".txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            if(!file.exists()) {
                file.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(writer);
            for(RecordDto record: records){
                out.write("at "+ record.getTime()+"|"+record.getSenderId()+"-->"+record.getRecipientId()+"="+record.getContent()+"\r\n");
            }
            out.flush(); // 把缓存区内容压入文件
            out.close();
            return ResponseEntity.ok().headers(headers) .contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")) .body(new FileSystemResource(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("history/"+user.getId()+"with"+customer+".txt");
        return ResponseEntity.ok().headers(headers) .contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")) .body(new FileSystemResource(file));
    }

    @RequestMapping(value = "/history", method = {RequestMethod.DELETE})
    public StatusMessage readMessage(@RequestParam Integer customer){
        User user = userService.setUser();
        userMessage.readMessage(user.getId(), customer);
        List<RecordDto> records = recordRepository.getChatRecord(user.getId(), customer, -1);
        LinkMessage linkMessage = new LinkMessage(
                "",
                "",
                "",
                "");
        if(records.size()==0){
            return new StatusMessage("删除成功", linkMessage);
        }
        recordRepository.deleteRecord(user.getId(), customer);
        return new StatusMessage("删除成功", linkMessage);
    }
}
