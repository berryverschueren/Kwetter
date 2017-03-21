package dao.implementations.jpa;

import dao.interfaces.jpa.LocatieDAO;
import model.Locatie;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import logger.Logger;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Default
public class LocatieDAOImpJPA extends GenericDaoImpJPA<Locatie> implements LocatieDAO {


    public LocatieDAOImpJPA() {
        // Empty constructor for dependency injection purposes.
    }

    @Override
    public Locatie getByPlaatsnaam(String plaatsnaam) {
        if (plaatsnaam == null || plaatsnaam.isEmpty())
            return null;

        try {
            return (Locatie) em.createQuery("select l from Locatie l where l.plaatsnaam = '" + plaatsnaam + "'").getSingleResult();
        }
        catch (Exception x) {
            Logger.log(x);
            return null;
        }
    }
}
