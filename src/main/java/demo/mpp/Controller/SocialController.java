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

    @RequestMapping(value= "/post/{user_name}",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getPostsByUserId(@PathVariable("user_name") String useName) {
        List<PostEntity> postEntityList = postService.getPostListWithFullDetail(useName);
        if (postEntityList == null) {
            return new ResponseEntity("Can not find any post of user " + useName);
        } else {
            return new ResponseEntity<>(postEntityList);
        }
    }
}
