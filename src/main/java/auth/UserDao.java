package auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, Long> { //no implementation necessary

  /**
   * This method will find an User instance in the database by its name.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
    public User findByName(String name);

    public User findByProviderIP(String providerIP);

}