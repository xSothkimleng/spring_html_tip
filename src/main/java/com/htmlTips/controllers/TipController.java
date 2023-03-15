package com.htmlTips.controllers;

import com.htmlTips.models.Tip;
import com.htmlTips.repositories.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TipController {

    @Autowired
    private TipRepository tipRepository;

    @GetMapping("/")
    public String homepage(Model model){
        Iterable<Tip> tips = tipRepository.findAll();
        model.addAttribute("htmlTips", tips);
        return "index";
    }

    // create tip
    @GetMapping("/create-tip")
    public String createTipPage(){return "create-tip";}

    @PostMapping("/create-tip")
    public String createTip(@RequestParam String title,
                            @RequestParam String description,
                            @RequestParam String exampleHtmlEscape){
        Tip tip = new Tip(title,description,exampleHtmlEscape);
        tipRepository.save(tip);
        return "redirect:/";
    }

    // edit tip
    @GetMapping("/update-tip/{id}")
    public String getUpdateTipPage(Model model,
                            @PathVariable(value = "id") Long id){
        Tip tip = tipRepository.findById(id).orElseThrow();
        model.addAttribute("tip",tip);
        return "/edit-tip";
    }

    @PostMapping("/update-tip")
    public String updateTip(Tip currentTip){
        tipRepository.save(currentTip);
        return "redirect:/";
    }

    // delete tip
    @PostMapping("/delete-tip/{id}")
    public String deleteTip(@PathVariable(value = "id") Long id){
        if(!tipRepository.existsById(id)){return "redirect:/";}
        Tip tip = tipRepository.findById(id).orElseThrow();
        tipRepository.delete(tip);
        return "redirect:/";
    }

    // tip detail page
    @GetMapping("/tip-detail/{id}")
    public String getTipDetailPage(@PathVariable(value = "id") Long id,
                                   Model model){
        if(!tipRepository.existsById(id)){return "redirect:/";}
        Tip tip = tipRepository.findById(id).orElseThrow();
        model.addAttribute("tip",tip);
        return "/tip-detail";
    }
}
