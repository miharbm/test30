package web.test30.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.test30.model.User;
import web.test30.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, transactionManager = "transactionManager")
public class UserService {

    private final UserRepository userDao;

    @Autowired
    public UserService(UserRepository userDao) {

        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findOne(int id) {
        Optional<User> foundUser =  userDao.findById(id );
        return foundUser.orElse( null );
    }

    public Optional<User> findOneByEmail(String email) {
        return userDao.findUserByEmail( email );
    }

    public Optional<User> findOneByAddress(String address) {

        Optional<User> user =  userDao.findUserByAddress( address );
        System.out.println("АААААААА" + user.isPresent());
        return user;
    }


    @Transactional(transactionManager = "transactionManager")
    public void save(User User) {
        userDao.save( User );
    }

    @Transactional(transactionManager = "transactionManager")
    public void update(Long id, User updatedUser) {
        updatedUser.setId( id );
        userDao.save( updatedUser );
    }

    @Transactional(transactionManager = "transactionManager")
    public void delete(int id) {
        userDao.deleteById( id );
    }

}
