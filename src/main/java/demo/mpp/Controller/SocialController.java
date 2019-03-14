package demo.mpp.Controller;

import demo.mpp.Response.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/social")
public class SocialController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity createPost() {
        return null;
    }
}
