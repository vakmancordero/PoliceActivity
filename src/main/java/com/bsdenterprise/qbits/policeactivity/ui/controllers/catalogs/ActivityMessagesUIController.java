package com.bsdenterprise.qbits.policeactivity.ui.controllers.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ui/catalogs/messages/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityMessagesUIController {

    @GetMapping
    public String index(Model model) {
        return "/catalogs/messages/index";
    }

}
