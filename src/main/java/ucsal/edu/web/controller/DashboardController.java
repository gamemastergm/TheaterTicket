package ucsal.edu.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ucsal.edu.web.service.ChartApplication;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

    private final ChartApplication chartApplication;

    public DashboardController(ChartApplication chartApplication) {
        this.chartApplication = chartApplication;
    }

    @GetMapping("/run")
    public String run() {
        chartApplication.launchApp(new String[]{});
        return "redirect:/"; 
    }
}
