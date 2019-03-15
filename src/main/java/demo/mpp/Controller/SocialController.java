package demo.mpp.Controller;

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

    @RequestMapping(value= "/{user_id}",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getPostsByUserId(@PathVariable("user_id") Integer userId) {
        List<PostEntity> postEntityList = postService.getPostListWithFullDetail(userId);
        if (postEntityList == null) {
            return new ResponseEntity("Can not find any post of user " + userId);
        } else {
            return new ResponseEntity<>(postEntityList);
        }
    }
}
