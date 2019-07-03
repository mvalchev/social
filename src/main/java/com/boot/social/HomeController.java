package com.boot.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.boot.social.api.facebook.Facebook;

@Controller
public class HomeController
{
	private Facebook facebook;

	@Autowired
	public HomeController(Facebook facebook)
	{
		this.facebook = facebook;
	}

	@GetMapping("/")
	public String home(Model model)
	{
		System.out.println("TESTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		model.addAttribute("profile", facebook.getProfile());
		model.addAttribute("feed", facebook.getFeed());
		return "home";
	}
}
