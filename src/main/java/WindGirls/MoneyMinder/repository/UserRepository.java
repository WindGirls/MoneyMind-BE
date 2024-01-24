package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
