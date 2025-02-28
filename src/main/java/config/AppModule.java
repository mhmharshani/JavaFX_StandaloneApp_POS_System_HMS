package config;

import com.google.inject.AbstractModule;
import repository.custom.PatientDao;
import repository.custom.impl.PatientDaoImpl;
import service.custom.PatientService;
import service.custom.impl.PatientServiceImpl;

public class AppModule extends AbstractModule {

    //Dependency Injection using Google Library

    @Override
    protected void configure(){
        bind(PatientService.class).to(PatientServiceImpl.class);
        bind(PatientDao.class).to(PatientDaoImpl.class);
    }
}
