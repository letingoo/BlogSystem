package autoCompletion.controller;

import autoCompletion.service.AutoCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by BASA on 2017/4/4.
 */

@Controller
public class AutoCompletionController {


    @Autowired
    private AutoCompletionService service;


    @RequestMapping("/autoCompletion")
    @ResponseBody
    public List<String> autoCompletion(String word) {

        return service.autoCompletion(word);

    }

}
