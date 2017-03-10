package model;

import javax.persistence.*;

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

    public Rol() {}

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
}
