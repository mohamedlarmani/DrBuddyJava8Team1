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

    /**
     Retrieves the UserDetails object associated with the specified email address.
     The UserDetails object contains the username, password, and role(s) of the user.
     @param email the email address of the user
     @return the UserDetails object of the user, or null if no user was found with the specified email
     */
    @Override
    public UserDetails getUserByUsername(String email) {
        Optional<Patient> oPatient;
        Optional<Doctor> oDoctor;
        Optional<Secretary> oSecretary;
        UserDetails userDetails = new UserDetails();
        if ((oPatient = patientRepository.findByEmail(email)).isPresent()) {
            Patient patient = oPatient.get();
            userDetails.setUsername(patient.getEmail());
            userDetails.setPassword(patient.getPassword());
            userDetails.setRoles(Arrays.asList("ROLE_USER"));
            userDetails.setUserId(Long.valueOf(patient.getId()));
            return userDetails;
        } else if((oDoctor = doctorRepository.findByEmail(email)).isPresent()){
            Doctor doctor = oDoctor.get();
            userDetails.setUsername(doctor.getEmail());
            userDetails.setPassword(doctor.getPassword());
            userDetails.setRoles(Arrays.asList("ROLE_DOCTOR"));
            userDetails.setUserId(Long.valueOf(doctor.getId()));
            return userDetails;
        } else if((oSecretary = secretaryRepository.findByEmail(email)).isPresent()) {
            Secretary secretary = oSecretary.get();
            userDetails.setUsername(secretary.getEmail());
            userDetails.setPassword(secretary.getPassword());
            userDetails.setRoles(Arrays.asList("ROLE_SECRETARY"));
            userDetails.setUserId(Long.valueOf(secretary.getId()));
            return userDetails;
        } else{
            return null;
        }

    }
}