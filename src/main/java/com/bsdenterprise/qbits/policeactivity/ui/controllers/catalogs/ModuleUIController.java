package com.bsdenterprise.qbits.policeactivity.ui.controllers.catalogs;

import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleInDTO;
import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import com.bsdenterprise.qbits.policeactivity.service.ModuleService;
import com.bsdenterprise.qbits.policeactivity.util.ConvertUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ui/catalogs/modules/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ModuleUIController {

    private final ModuleService moduleService;

    @GetMapping
    public String index(Model model) {

        try {

            List<ModuleOutDTO> modules = moduleService.findAll(ModuleOutDTO.class);

            model.addAttribute("modules", modules);

            return "/catalogs/module/index";

        } catch (Exception ex) {

            log.error("Method index - Error!", ex);

            return "/error";
        }

    }

    @GetMapping(value = "/create")
    public String create(final ModuleInDTO moduleInDTO, Model model) {

        try {

            return "/catalogs/module/create";

        } catch (Exception ex) {

            log.error("Method create - Error!", ex);

            return "/error";
        }

    }

    @PostMapping("/create")
    public String confirmCreate(@Valid @ModelAttribute final ModuleInDTO moduleInDTO, final BindingResult bindingResult) {

        try {

            if (bindingResult.hasErrors()) {
                return "/catalogs/module/create";
            }

            moduleService.createModule(moduleInDTO);

            return "redirect:/ui/catalogs/modules/";

        } catch (Exception ex) {

            log.error("Method confirmCreate - Error!", ex);

            return "/error";
        }

    }

    @GetMapping("/{id}")
    public String update(final ModuleInDTO moduleInDTO, Model model, @PathVariable Long id) {

        try {

            ModuleOutDTO moduleOutDTO = moduleService.findById(id, ModuleOutDTO.class);

            convertUtils().map(moduleOutDTO, moduleInDTO);

            model.addAttribute("moduleId", id);

            return "/catalogs/module/edit";

        } catch (Exception ex) {

            log.error("Method update - Error!", ex);

            return "/error";
        }
    }

    @PutMapping("/{id}")
    public String confirmEdit(@Valid @ModelAttribute ModuleInDTO moduleInDTO,
                              @PathVariable long id, BindingResult bindingResult, ModelMap model) {

        try {

            if (bindingResult.hasErrors()) {
                return "/catalogs/module/create";
            }

            moduleService.updateModule(id, moduleInDTO);

            return "redirect:/ui/catalogs/modules/";

        } catch (Exception ex) {

            log.error("Method confirmEdit - Error!", ex);

            return "/error";
        }

    }

    private ConvertUtils convertUtils() {
        return this.moduleService.getConvertUtils();
    }

}
