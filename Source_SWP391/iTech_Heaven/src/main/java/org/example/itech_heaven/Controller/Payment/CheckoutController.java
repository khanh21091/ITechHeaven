package org.example.itech_heaven.Controller.Payment;

import lombok.RequiredArgsConstructor;
import org.example.itech_heaven.Entity.TypeDevice;
import org.example.itech_heaven.Entity.User;
import org.example.itech_heaven.Service.DeviceTypeSerivce;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final DeviceTypeSerivce deviceTypeSerivce;

    @GetMapping
    public String getFormCheckout(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<TypeDevice> devices = deviceTypeSerivce.getAllTypeDevices();
        model.addAttribute("typeDevices", devices);
        model.addAttribute("user", user);
        return "checkout";
    }


}
