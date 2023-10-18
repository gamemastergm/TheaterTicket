package ucsal.edu.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ucsal.edu.web.model.Titular;
import ucsal.edu.web.repository.TitularRepository;
import ucsal.edu.web.service.TitularService;

@Controller
@RequestMapping(value = "/client")
public class TitularController {
	
	@Autowired
	private TitularRepository titularRepository;
	
	@Autowired
    private TitularService titularService;
	
	@GetMapping("/insert")
    public String insertRandomUsers() {
        titularService.inserirUsuariosAleatorios(22000000);
        return "redirect:/";
    }


	@GetMapping("/list")
	public String listClientes(Model model) {
		Optional<Titular> titular = titularRepository.findById(1L);
		model.addAttribute("titulares", titular.get());
		return "/cliente";
	}

	@GetMapping("/in")
	public String inCliente(Model model, @Param(value = "id") Long id) {
		Optional<Titular> titular = titularRepository.findById(id);
		if (titular.isPresent()) {
			model.addAttribute("titulares", titular.get());
		}
		return "/cliente";
	}

	@GetMapping("/login")
	public String login(Model model) {
		Titular titular = new Titular();
		model.addAttribute("titulares", titular);
		return "/login";
	}

	@GetMapping("/form")
	public String form(Model model, @Param(value = "id") Long id) {
		Titular titular = new Titular();
		if (id != null) {
			Optional<Titular> op = titularRepository.findById(id);
			if (op.isPresent()) {
				titular = op.get();
			}

		}
		model.addAttribute("titulares", titular);
		return "/register";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Titular titular, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(a -> System.out.print(a));
			model.addAttribute("titulares", titularRepository.findAll());
			return "clienteform";
		}
		titularRepository.save(titular);
		return "index";
	}

	@GetMapping("/delete")
	public String delete(Long id) {
		titularRepository.deleteById(id);
		return "redirect:/cliente/list";
	}

}
