package telegram.bot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telegram.bot.model.MessageForBot;

import java.util.List;

@Repository
public interface MessageForBotRepo extends JpaRepository<MessageForBot, Long> {

    @Query("select t from MessageForBot t where lower(t.city) like ?1")
    MessageForBot findByCity(String city);

    List<MessageForBot> findAll();
}
