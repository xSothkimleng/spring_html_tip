package com.htmlTips.controllers;

import com.htmlTips.models.Bookmark;
import com.htmlTips.models.Tip;
import com.htmlTips.repositories.BookmarkRepository;
import com.htmlTips.repositories.TipRepository;
import com.htmlTips.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class bookmarkController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;

    // list bookmark
    @GetMapping("/bookmark")
    public String getBookmarkPage(Model model,
                                  HttpSession session) {
        Long userId = (Long)session.getAttribute("currentUserId");
        if(userId == null){
            return "/user/login";
        }else{
            Iterable<Bookmark> bookmarks = bookmarkRepository.findAll();
            List<Tip> htmlTips = new ArrayList<>();
            for(Bookmark bookmark : bookmarks){
                if(bookmark.getUser_id().equals(userId)){
                    Tip tip = tipRepository.findById(bookmark.getTip_id()).orElseThrow();
                    htmlTips.add(tip);
                }
            }
            model.addAttribute("htmlTips", htmlTips);
            return "/bookmark/list";
        }
    }

    // add bookmark
    @PostMapping("/bookmark/add-bookmark/{tipId}")
    public String addBookmark(HttpSession  session,
                              @PathVariable(value = "tipId") Long tipId){
        Long userId = (Long)session.getAttribute("currentUserId");
        Bookmark bookmark = new Bookmark(userId,tipId);
        bookmarkRepository.save(bookmark);
        return "redirect:/";
    }

    // delete bookmark
    @PostMapping("/delete-bookmark/{tipId}")
    public String deleteBookmark(@PathVariable(value = "tipId") Long tipId){
        Iterable<Bookmark> bookmarks = bookmarkRepository.findAll();
        for(Bookmark bookmark : bookmarks){
            if(bookmark.getTip_id().equals(tipId)){
                Long id = bookmark.getId();
                Bookmark bMark = bookmarkRepository.findById(id).orElseThrow();
                bookmarkRepository.delete(bMark);
                break;
            }
        }

        return "redirect:/";
    }
}
