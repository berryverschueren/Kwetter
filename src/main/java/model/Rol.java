package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
@Entity
@Table(name="t_rol")
public class Rol {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titel", nullable = false, unique = true)
    private String titel;

    @ManyToMany
    @JoinTable(name="t_kwetteraar_rol"
            , joinColumns = @JoinColumn(name = "rol_titel", referencedColumnName = "titel")
            , inverseJoinColumns = @JoinColumn(name = "kwetteraar_profielNaam", referencedColumnName = "profielNaam")
    )
    private List<Kwetteraar> kwetteraars;

    public Rol() {
        kwetteraars = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<Kwetteraar> getKwetteraars() {
        return kwetteraars;
    }

    public void setKwetteraars(List<Kwetteraar> kwetteraars) {
        this.kwetteraars = kwetteraars;
    }

    public void addKwetteraar(Kwetteraar kwetteraar) {
        if (kwetteraar != null && kwetteraars != null && !kwetteraars.contains(kwetteraar)) {
            kwetteraars.add(kwetteraar);
            if (!kwetteraar.getRollen().contains(this))
                kwetteraar.addRol(this);
        }
    }
}
