package task.manager.secure.controller;

// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import task.manager.secure.service.ExamApiService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller 
public class ExamController {
    private final ExamApiService examApiService;
    public ExamController(ExamApiService examApiService) {
        this.examApiService = examApiService;
    }
    @GetMapping("/exam")
    public String showExamPage() {
        return "securelaunch";
    }
    
    @PostMapping("/exam")
    public String startExam(@RequestParam String clientId, @RequestParam String templateCode, Model model) {
        String token = examApiService.getToken(clientId, templateCode);
        model.addAttribute("clientId", clientId);
        model.addAttribute("templateCode", templateCode);
        model.addAttribute("token", token);
        return "launchsecurebrowser";
    }
    
}
