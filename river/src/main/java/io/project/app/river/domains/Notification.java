package io.project.app.river.domains;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author armena
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "notification")
public class Notification implements Serializable {

    @Id
    private String id;
    
    private Long receiverId;
    
    private String status;
    
    private String message;

    ////@Indexed(name = "ephemeralEventDate", expireAfterSeconds = 600)
    private LocalDateTime eventDate = LocalDateTime.now();

}
