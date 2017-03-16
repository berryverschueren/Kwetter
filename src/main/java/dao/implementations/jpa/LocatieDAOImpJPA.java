package dao.implementations.jpa;

import dao.interfaces.jpa.LocatieDAO;
import model.Locatie;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.*;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class LocatieDAOImpJPA extends GenericDaoImpJPA<Locatie> implements LocatieDAO {


    public LocatieDAOImpJPA() {}

    @Override
    public Locatie getByPlaatsnaam(String plaatsnaam) {
        if (plaatsnaam == null || plaatsnaam.isEmpty())
            return null;

        try {
            return (Locatie) em.createQuery("select l from Locatie l where l.plaatsnaam = '" + plaatsnaam + "'").getSingleResult();
        }
        catch (Exception x) {
            return null;
        }
    }
}
