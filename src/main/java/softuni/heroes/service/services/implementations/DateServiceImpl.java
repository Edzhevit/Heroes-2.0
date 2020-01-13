package softuni.heroes.service.services.implementations;

import org.springframework.stereotype.Service;
import softuni.heroes.service.services.DateService;

import java.util.Date;

@Service
public class DateServiceImpl implements DateService {

    @Override
    public Date currentDate() {
        return new Date();
    }
}
