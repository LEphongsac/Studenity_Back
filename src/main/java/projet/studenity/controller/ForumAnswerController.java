package projet.studenity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.studenity.model.Answer;
import projet.studenity.model.Forum;
import projet.studenity.service.ForumAnswerService;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/forum")
public class ForumAnswerController {
    @Autowired
    private ForumAnswerService forumAnswerService;

    @PostMapping(value="/deleteForum")
    public boolean deleteForum(@RequestBody Forum forum){
        return forumAnswerService.deleteForum(forum);
    }

    @PostMapping(value="/deleteAnswer")
    public boolean deleteAnswer(@RequestBody Answer answer){
        return forumAnswerService.deleteAnswer(answer);
    }

    @PostMapping(value="/createForum")
    public boolean addForum(@RequestBody Forum forum){
        return forumAnswerService.addForum(forum);
    }

    @PostMapping(value="/addAnswerToForum")
    public boolean addAnswerToForum(@RequestBody Answer answer){
        return forumAnswerService.addAnswerToForum(answer);
    }

    @PostMapping(value="/updateForum")
    public boolean updateForum(@RequestBody Forum forum){
        return forumAnswerService.updateForum(forum);
    }

    @PostMapping(value="/updateAnswer")
    public boolean updateAnswer(@RequestBody Answer answer){
        return forumAnswerService.updateAnswer(answer);
    }

    @GetMapping(value="/list")
    public List<Forum> getAllForums(){
        return forumAnswerService.getAllForums();
    }

    @GetMapping(value="listAnswerByForum/{idForum}")
    public List<Answer> listAnswerByForum(@PathVariable("idForum")  int idForum) {
        return forumAnswerService.listAnswerByForum(idForum);
    }
    @GetMapping(value="detail/{idForum}")
    public Forum findForumById(@PathVariable ("idForum")  int idForum) {
        return forumAnswerService.findForumById(idForum);
    }

    @GetMapping(value="/{keyword}")
    public List<Forum> findForumByKeyword(@PathVariable("keyword")  String keyword) {
        return forumAnswerService.findForumByKeyword(keyword);
    }

    @GetMapping(value="/test")
    public boolean test() {
        Answer answer = new Answer();
        answer.setIdForum(1);
        answer.setIdUser(4);
        answer.setAnswer("test");
        return forumAnswerService.addAnswerToForum(answer);
    }
}