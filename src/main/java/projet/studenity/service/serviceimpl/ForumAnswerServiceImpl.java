package projet.studenity.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import projet.studenity.model.*;
import projet.studenity.repository.AnswerRepository;
import projet.studenity.repository.ForumRepository;
import projet.studenity.repository.NotificationRepository;
import projet.studenity.service.ForumAnswerService;
import projet.studenity.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForumAnswerServiceImpl implements ForumAnswerService {
    @Autowired
    private ForumRepository forumRepo;

    @Autowired
    AnswerRepository answerRepo;

    @Autowired
    UserService userService;

    @Autowired
    NotificationRepository notiRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Forum findForumById(int idForum) {
        List<Forum> forums = forumRepo.findAll();
        for(Forum forum: forums){
            if(forum.getId()==idForum){
                return forum;
            }
        }
        return null;
    }

    @Override
    public Answer findAnswerById(int idAnswer) {
        List<Answer> answers = answerRepo.findAll();
        for(Answer answer: answers){
            if(answer.getId()==idAnswer){
                return answer;
            }
        }
        return null;
    }

    @Override
    public boolean addForum(Forum forum) {
        try {
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            forum.setStartDate(date);
            forumRepo.save(forum);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addAnswerToForum(Answer answer) {
        try {
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            answer.setStartDate(date);
            if(answer.getAnswer().equalsIgnoreCase("")){return false;}
            answerRepo.save(answer);

            //Envoie noti quand y a qqn reponse au forum
            Forum forum = findForumById(answer.getIdForum());
            User user = userService.findUserById(answer.getIdUser());
            Notification noti = new Notification();
            noti.setIdUser(forum.getIdUser());
            noti.setTypeNoti(2); // 1: Cours / 2: Forum
            noti.setMessage(user.getFirstName() + " a répondu à votre question");
            //stocker id forum pour afficher si utilisateur clique sur noti
            noti.setIdTempo(forum.getId());
            notiRepo.save(noti);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteForum(Forum forum) {
        try {
            forumRepo.delete(forum);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAnswer(Answer answer) {
        try {
            answerRepo.delete(answer);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateForum(Forum forum) {
        try {
            forumRepo.save(forum);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateAnswer(Answer answer) {
        try {
            answerRepo.save(answer);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Forum> getAllForums() {
        //List<Forum> forums = forumRepo.findAll();
        return jdbcTemplate.query("select * from Forum order by start_date desc ", new ForumRowMapper());
        //return forumRepo.findAll();
    }

    private final class ForumRowMapper implements RowMapper<Forum> {
        @Override
        public Forum mapRow(ResultSet rs, int rowNum) throws SQLException {
            Forum forum = new Forum();
            forum.setId(rs.getInt("id_forum"));
            forum.setIdUser(rs.getInt("id_user"));
            forum.setTitle(rs.getString("title"));
            forum.setDescription(rs.getString("description"));
            forum.setStartDate(rs.getDate("start_date"));
            return forum;
        }
    }

    @Override
    public List<Answer> listAnswerByForum(int idForum) {
        List<Answer> answers = jdbcTemplate.query("select * from Answer order by start_date desc ", new AnswerRowMapper());
        List<Answer> answerList = new ArrayList<>();
        for(Answer answer: answers){
            if(answer.getIdForum()==idForum)
            answerList.add(answer);
        }
        return answerList;
    }

    private final class AnswerRowMapper implements RowMapper<Answer> {
        @Override
        public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Answer answer = new Answer();
            answer.setId(rs.getInt("id_answer"));
            answer.setIdUser(rs.getInt("id_user"));
            answer.setIdForum(rs.getInt("id_forum"));
            answer.setAnswer(rs.getString("answer"));
            answer.setStartDate(rs.getDate("start_date"));
            return answer;
        }
    }

    @Override
    public List<Forum> findForumByKeyword(String keyword) {
        List<Forum> forumList = getAllForums();
        List<Forum> forumResult = new ArrayList<>();
        for (Forum forum : forumList) {
            String[] forumKeyword = forum.getTitle().split(" ");
            for (int i = 0; i < forumKeyword.length; i++) {
                if (forumKeyword[i].contains(keyword)) {
                    forumResult.add(forum);
                }
            }
        }
        return forumResult;
    }
}
