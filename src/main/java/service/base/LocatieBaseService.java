package service.base;

import dao.interfaces.LocatieDAO;
import dao.implementations.LocatieDAOImp;
import model.Locatie;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class LocatieBaseService {
    private LocatieDAO locatieDao = new LocatieDAOImp();

    public List<Locatie> getLocaties() {
        return locatieDao.getAll();
    }

    public Locatie getLocatie(long id) {
        return locatieDao.get(id);
    }

    public Locatie insertLocatie(String plaatsnaam) {
        if (getExactlyMatchingLocatie(plaatsnaam) != null)
            return getExactlyMatchingLocatie(plaatsnaam);

        Locatie locatie = new Locatie();
        locatie.setPlaatsNaam(plaatsnaam);
        setGeolocation(locatie, plaatsnaam);
        return locatieDao.save(locatie);
    }

    public Locatie updateLocatie(long id, String plaatsnaam) {
        Locatie locatie = getLocatie(id);
        locatie.setPlaatsNaam(plaatsnaam);
        setGeolocation(locatie, plaatsnaam);
        return locatieDao.save(locatie);
    }

    public boolean deleteLocatie(long id) {
        return locatieDao.delete(id);
    }

    public Locatie getExactlyMatchingLocatie(String plaatsnaam) {
        return locatieDao.getByPlaatsnaam(plaatsnaam);
    }

    public void setGeolocation(Locatie locatie, String plaatsnaam) {
        try {
            try {
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address=\"" + URLEncoder.encode(plaatsnaam, "UTF-8") + "\"";
                JSONObject jsonObject = JsonReader.readJsonFromUrl(url);
                String jsonString = jsonObject.toString();
                String lonStart = "location\":{\"lng\":";
                String latStart = ",\"lat\":";
                String endStart = "}";
                int lonPoint = jsonString.indexOf(lonStart);
                jsonString = jsonString.substring(lonPoint + lonStart.length());
                int latPoint = jsonString.indexOf(latStart);
                String lonValue = jsonString.substring(0, latPoint);
                jsonString = jsonString.substring(latPoint + latStart.length());
                int endPoint = jsonString.indexOf(endStart);
                String latValue = jsonString.substring(0, endPoint);
                double lat = Double.parseDouble(latValue);
                double lon = Double.parseDouble(lonValue);
                locatie.setLatitude(lat);
                locatie.setLongitude(lon);
            } catch (Exception x) {
                System.out.println(x);
            }
        } catch (Exception x) {

        }
    }

}
