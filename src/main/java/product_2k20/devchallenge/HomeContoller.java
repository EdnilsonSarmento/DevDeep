package product_2k20.devchallenge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Ednilson Luis ALfredo Sarmento
 * **/

@Controller
public class HomeContoller {
    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
