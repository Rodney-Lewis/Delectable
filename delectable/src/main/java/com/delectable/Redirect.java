package com.delectable;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Redirect implements ErrorController {

    private final String errorPath = "/error";

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = errorPath)
    public String error() {
        return "forward:/index.html";
    }
}
