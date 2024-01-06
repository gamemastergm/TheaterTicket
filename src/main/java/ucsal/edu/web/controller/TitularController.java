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
import ucsal.edu.web.service.CartaoCreditoService;
import ucsal.edu.web.service.TitularService;

@Controller
@RequestMapping(value = "/client")
public class TitularController {
	
	@Autowired
	private TitularRepository titularRepository;
	
	@Autowired
    private TitularService titularService;
	
	@Autowired
    private CartaoCreditoService cartaoCreditoService;
	
	@GetMapping("/insertUser")
    public String insertRandomUsers() {
        titularService.inserirUsuariosAleatorios(25000);
        return "redirect:/";
    }
	
	@GetMapping("/insertCart")
    public String insertCartRandomUsers() {
		cartaoCreditoService.inserirCartaoAleatorios(50000);
        return "redirect:/";
    }
	
	@GetMapping("/filtroNascimento")
	public String filtroNascimento() {
	    System.out.println("---------------------------filtroNascimento---------------------------");
	    titularService.filtroNascimento("1980-02-01", "1980-02-30");
	    System.out.println("---------------------------filtroNascimento---------------------------");
	    return "redirect:/";
	}
	
	@GetMapping("/filtroNome")
    public String filtroNome() {
		System.out.println("---------------------------filtroNome---------------------------");
		titularService.filtroNome("Felipe");
		System.out.println("---------------------------filtroNome---------------------------");
        return "redirect:/";
    }
	
	@GetMapping("/filtroRole")
    public String filtroRole() {
		System.out.println("---------------------------filtroRole---------------------------");
		titularService.filtroRole("TITULAR");
		System.out.println("---------------------------filtroRole---------------------------");
        return "redirect:/";
    }
	
	@GetMapping("/filtroQuantidadeCartoes")
    public String filtroQuantidadeCartoes() {
		System.out.println("---------------------------filtroQuantidadeCartoes---------------------------");
		titularService.filtroQuantidadeCartoes(4);
		System.out.println("---------------------------filtroQuantidadeCartoes---------------------------");
        return "redirect:/";
    }
	
    @GetMapping("/filtroNomeCartao")
    public String filtroNomeCartao() {
        System.out.println("---------------------------filtroNomeCartao---------------------------");
        cartaoCreditoService.filtroNomeCartao("Felipe");
        System.out.println("---------------------------filtroNomeCartao---------------------------");
        return "redirect:/";
    }

    @GetMapping("/filtroLimiteCartao")
    public String filtroLimiteCartao() {
        System.out.println("---------------------------filtroLimiteCartao---------------------------");
        cartaoCreditoService.filtroLimiteCartao(1000, 5000);
        System.out.println("---------------------------filtroLimiteCartao---------------------------");
        return "redirect:/";
    }

    @GetMapping("/filtroValidadeCartao")
    public String filtroValidadeCartao() {
        System.out.println("---------------------------filtroValidadeCartao---------------------------");
        cartaoCreditoService.filtroValidadeCartao("1980-01", "1980-02");
        System.out.println("---------------------------filtroValidadeCartao---------------------------");
        return "redirect:/";
    }
    
    @GetMapping("/filtroStatusCartao")
    public String filtroStatusCartao() {
        System.out.println("---------------------------filtroStatusCartao---------------------------");
        cartaoCreditoService.filtroStatusCartao("DESBLOQUEADO");
        System.out.println("---------------------------filtroStatusCartao---------------------------");
        return "redirect:/";
    }
	
	@GetMapping("/card")
    public String cartao() {
        return "/cartao";
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
			return "clientform";
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
