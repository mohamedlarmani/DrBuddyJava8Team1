package com.develhope.drbuddy;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Person;
import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.repository.PersonRepository;
import it.pasqualecavallo.studentsmaterial.authorization_framework.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.pasqualecavallo.studentsmaterial.authorization_framework.dao.UserDao;
import java.util.Arrays;
import java.util.Optional;


@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public UserDetails getUserByUsername(String email) {
        Optional<? extends Person> oPerson = personRepository.findByEmail(email);
        if ((oPerson = personRepository.findByEmail(email)).isPresent()) {
            UserDetails userDetails = new UserDetails();
            Person person = oPerson.get();
            userDetails.setUsername(person.getEmail());
            userDetails.setPassword(person.getPassword());
            if(person instanceof Patient){
                userDetails.setRoles(Arrays.asList("ROLE_PATIENT"));
            }else if (person instanceof Doctor){
                userDetails.setRoles(Arrays.asList("ROLE_DOCTOR"));
            }else if (person instanceof Secretary){
                userDetails.setRoles(Arrays.asList("ROLE_SECRETARY"));
            }return userDetails;
        }  else{
            return null;
        }

    }
}