package survey.demo.Package;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class SampleController {

        @RequestMapping(method = RequestMethod.GET)
        public String create()
        {
            return "This is sample";
        }
}
