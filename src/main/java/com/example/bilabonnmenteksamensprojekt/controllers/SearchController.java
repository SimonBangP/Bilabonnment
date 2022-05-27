package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.system.Search;
import com.example.bilabonnmenteksamensprojekt.services.SearchService;
import com.example.bilabonnmenteksamensprojekt.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    UserAuthenticationService authenticationService;

    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public String search(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            model.addAttribute("userFirstname", (String)session.getAttribute("userFirstname"));
            model.addAttribute("userLastnameChar", ((String)session.getAttribute("userLastname")).charAt(0));
            return "search";
        }
        else {
            return "redirect:/?location=search";
        }
    }

    @GetMapping("/search/{searchQuery}")
    public String getSearchResults(HttpSession session, Model model, @PathVariable String searchQuery) {
         if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
                List<Search> searchResults = searchService.getSearchResults(searchQuery);
                model.addAttribute("searchResults", searchResults);
                return "searchResults";
       }
        else {
            return "redirect:/?location=search/" + searchQuery;
        }
    }

}
