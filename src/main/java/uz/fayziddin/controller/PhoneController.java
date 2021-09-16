package uz.fayziddin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.fayziddin.entity.Phone;
import uz.fayziddin.repository.PhoneRepository;

import java.util.Optional;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    PhoneRepository phoneRepository;

    @GetMapping
    public String getPhonePage(Model model) {
        model.addAttribute("phones", phoneRepository.findAll());
        return "phone";
    }

    @GetMapping("/addPage")
    public String addPage() {
        return "addPage";
    }

    @PostMapping("/add")
    public String savePhone(@RequestParam Integer id,
                            @RequestParam String model,
                            @RequestParam Integer price,
                            Model data) {
        phoneRepository.save(new Phone(id, model, price));
        data.addAttribute("phones", phoneRepository.findAll());
        return "phone";
    }

    @GetMapping("/editPage/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Phone> byId = phoneRepository.findById(id);
        System.out.println(byId);
        Phone phone = byId.get();
        model.addAttribute("aPhone", phone);
        return "editPage";
    }

    @PostMapping("/update")
    public String updatePhone(@RequestParam Integer id,
                              @RequestParam String model,
                              @RequestParam Integer price,
                              Model data) {
        phoneRepository.save(new Phone(id, model, price));
        data.addAttribute("phones", phoneRepository.findAll());
        return "phone";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable Integer id, Model model) {
        phoneRepository.deleteById(id);
        model.addAttribute("phones", phoneRepository.findAll());
        return "phone";
    }

    @PostMapping("/search")
    public String search(@RequestParam String word, Model model) {
        model.addAttribute("phones", phoneRepository.findAllByModelContainingIgnoreCase(word));
        return "phone";
    }
}
