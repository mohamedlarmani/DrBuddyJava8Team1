package com.develhope.drbuddy.roles;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Person;
import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.repository.DoctorRepository;
import com.develhope.drbuddy.repository.PatientRepository;
import com.develhope.drbuddy.repository.SecretaryRepository;
import it.pasqualecavallo.studentsmaterial.authorization_framework.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.pasqualecavallo.studentsmaterial.authorization_framework.dao.UserDao;
import java.util.Arrays;
import java.util.Optional;


@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Override
    public UserDetails getUserByUsername(String email) {
        Optional<Patient> oPatient;
        Optional<Doctor> oDoctor;
        Optional<Secretary> oSecretary;
        if ((oPatient = patientRepository.findByEmail(email)).isPresent()) {
            UserDetails userDetails = new UserDetails();
            Patient patient = oPatient.get();
            userDetails.setUsername(patient.getEmail());
            userDetails.setPassword(patient.getPassword());
            userDetails.setRoles(Arrays.asList("ROLE_USER"));
            return userDetails;
        } else if((oDoctor = doctorRepository.findByEmail(email)).isPresent()){
            UserDetails userDetails = new UserDetails();
            Doctor doctor = oDoctor.get();
            userDetails.setUsername(doctor.getEmail());
            userDetails.setPassword(doctor.getPassword());
            userDetails.setRoles(Arrays.asList("ROLE_DOCTOR"));
            return userDetails;
        } else if((oSecretary = secretaryRepository.findByEmail(email)).isPresent()) {
            UserDetails userDetails = new UserDetails();
            Secretary secretary = oSecretary.get();
            userDetails.setUsername(secretary.getEmail());
            userDetails.setPassword(secretary.getPassword());
            userDetails.setRoles(Arrays.asList("ROLE_SECRETARY"));
            return userDetails;
        } else{
            return null;
        }

    }
}