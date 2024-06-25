package estiam.projets.immataeronef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ConstructeurController {

    @Autowired
    private ImmatService immatService;

    /**
     * @RestController : Indique que cette classe est un contrôleur Spring qui gère les requêtes HTTP.
     * @GetMapping("/constructeurs") : Indique que cette méthode doit être appelée lorsque l'URL /constructeurs
     * est accédée via une requête GET.
     * getConstructeurs : Utilise ImmatService pour obtenir le compte des constructeurs et retourne une liste de
     * ConstructeurDTO, filtrée pour ne garder que les constructeurs ayant plus d'un appareil.
     */
    @GetMapping("/constructeurs")
    public List<ConstructeurDTO> getConstructeurs() {
        Map<String, Long> constructeurCount = immatService.getConstructeurCount();
        return constructeurCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> new ConstructeurDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
