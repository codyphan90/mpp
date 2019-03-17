package demo.mpp.Controller;

import demo.mpp.Entity.CommentEntity;
import demo.mpp.Entity.EmotionEntity;
import demo.mpp.Entity.PostEntity;
import demo.mpp.Response.ResponseEntity;
import demo.mpp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/social")
public class SocialController {
    @Autowired
    private PostService postService;

    @RequestMapping(value= "/post/{user_name}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getPostsByUserId(@PathVariable("user_name") String useName) {
        List<PostEntity> postEntityList = postService.getPostListWithFullDetail(useName);
        if (postEntityList == null) {
            return new ResponseEntity("Can not find any post of user " + useName);
        } else {
            return new ResponseEntity<>(postEntityList);
        }
    }

    @RequestMapping(value = "/post/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity createPost(@RequestBody PostEntity request) {
        PostEntity postEntity = postService.createPost(request);
        if (postEntity.getPostId() != null) return new ResponseEntity<>(postEntity);
        return new ResponseEntity("Can not create Post for user " + request.getUserName());
    }

    @RequestMapping(value = "/comment/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity createComment(@RequestBody CommentEntity request) {
        CommentEntity commentEntity = postService.createComment(request);
        if (commentEntity.getPostId() != null) return new ResponseEntity<>(commentEntity);
        return new ResponseEntity("Can not create comment for user " + request.getUserName());
    }

    @RequestMapping(value = "/reaction/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity createReaction(@RequestBody EmotionEntity request) {
        EmotionEntity emotionEntity = postService.createEmotion(request);
        if (emotionEntity.getPostId() != null) return new ResponseEntity<>(emotionEntity);
        return new ResponseEntity("Can not create reaction for user " + request.getUserName());
    }

    @RequestMapping(value = "/newfeed/{user_name}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getNewFeed(@PathVariable("user_name") String userName) {
        List<PostEntity> postEntityList = postService.getNewFeed(userName);
        if (postEntityList == null) {
            return new ResponseEntity("Can not find any new feed of user " + userName);
        } else {
            return new ResponseEntity<>(postEntityList);
        }
    }


}
