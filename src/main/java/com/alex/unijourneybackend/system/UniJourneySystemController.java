package com.alex.unijourneybackend.system;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/api/v1/system")
public class UniJourneySystemController {

    @GetMapping
    public ModelAndView system() {
        return new ModelAndView("user_admin/system/system");
    }

    @GetMapping("/restart")
    public ResponseEntity<String> restartApp() {
        UniJourneySystemManager.restartWithScript();
        return ResponseEntity.ok("Restart triggered. Check logs for progress.");
    }

    @GetMapping("/shutdown")
    public ResponseEntity<String> shutdownApp() {
        UniJourneySystemManager.shutdown();
        return ResponseEntity.ok("Shutdown triggered. Application will exit.");
    }


}

