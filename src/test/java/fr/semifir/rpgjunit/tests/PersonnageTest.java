package fr.semifir.rpgjunit.tests;

import fr.semifir.rpgjunit.models.Personnage;
import fr.semifir.rpgjunit.repositories.PersonnageRepository;
import fr.semifir.rpgjunit.services.impl.PersonnageService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class PersonnageTest {

    private List<Personnage> personnages = new ArrayList<Personnage>();

    private AutoCloseable closeable;

    @Mock
    private PersonnageRepository personnageRepository;
    @InjectMocks
    private PersonnageService personnageService;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        personnages.add(new Personnage("id1", "Titi", 100));
        personnages.add(new Personnage("id2", "Tutu", 100));
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Création d'une instance de personnage")
    public void creationPersonnage() {
        Personnage p = new Personnage("customId", "Toto", 100);
        assertTrue(p instanceof Personnage);
    }

    @Test
    @DisplayName("Insertion personnage")
    public void insert() {
        Personnage personnage = new Personnage("", "Toto", 100);

        when(personnageRepository.save(any(Personnage.class))).thenAnswer(i -> {
            Personnage p = i.getArgument(0);
            p.setId("myCustomId");
            return p;
        });

        Personnage p = personnageService.create(personnage);
        assertNotNull(p);
    }

    @Test
    @DisplayName("Insertion personnage sans nom")
    public void insertSansNom() {
        Personnage personnage = new Personnage("", "", 100);

        when(personnageRepository.save(any(Personnage.class))).thenAnswer(i -> {
            Personnage p = i.getArgument(0);
            p.setId("myCustomId");
            return p;
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Personnage p = personnageService.create(personnage);
        });
    }

    @Test
    @DisplayName("Récupérer personnage par id")
    public void findById() {
        String id = "id1";

        when(personnageRepository.findById(any(String.class))).thenAnswer(i -> {
            Optional<Personnage> returnedPersonnage = Optional.empty();
            for(Personnage p : personnages) {
                if(p.getId().equals(id)) {
                    returnedPersonnage = Optional.of(p);
                }
            }
            return returnedPersonnage;
        });

        Personnage p = personnageService.findById(id);

        assertNotNull(p);
    }

    @Test
    @DisplayName("Récupérer personnage par id introuvable")
    public void findByIdNotFound() {
        String id = "id3";

        when(personnageRepository.findById(any(String.class))).thenAnswer(i -> {
            Optional<Personnage> returnedPersonnage = Optional.empty();
            for(Personnage p : personnages) {
                if(p.getId().equals(id)) {
                    returnedPersonnage = Optional.of(p);
                }
            }
            return returnedPersonnage;
        });

        assertThrows(NullPointerException.class, () -> {
            Personnage p = personnageService.findById(id);
        });
    }

    @Test
    @DisplayName("Récupérer tous les personnages")
    public void findAll() {
        when(personnageRepository.findAll()).thenReturn(personnages);

        List<Personnage> returnedList = personnageService.findAll();

        assertTrue(returnedList.size() == 2);
    }
}
