package com.alex.unijourneybackend.modules.notification.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import com.alex.unijourneybackend.modules.user.domain.model.User;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "NOTIFICATIONS",
    schema = "communication",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_notification_event_user",
        columnNames = {"event_key", "user_id"}
    )
)
@Access(AccessType.FIELD)
public class Notification implements Serializable {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String message;

    @Column(name = "event_key", nullable = false)
    private String eventKey;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private boolean read; // true se lo studente ha già accettato l’esito



    // =========================
    // Constructors
    // =========================
    protected Notification() { /* no args constructor  */}

    public static Notification create(User user, String type, String message, String eventKey, int expiresInDays) {
        Notification n = new Notification();
        n.user = Objects.requireNonNull(user);
        n.type = Objects.requireNonNull(type);
        n.message = Objects.requireNonNull(message);
        n.eventKey = Objects.requireNonNull(eventKey);
        n.createdAt = LocalDateTime.now(ZoneId.systemDefault());
        n.expiresAt = LocalDateTime.now(ZoneId.systemDefault()).plusDays(expiresInDays);
        n.read = false;
        return n;
    }


    // =========================
    // Getters
    // =========================
    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getType() { return type; }
    public String getMessage() { return message; }
    public String getEventKey() { return eventKey; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public boolean isRead() { return read; }


    // =========================
    // Setters (domain command)
    // =========================
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setType(String type) { this.type = type; }
    public void setMessage(String message) { this.message = message; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public void markAsRead() { this.read = true; }
    public void markAsUnread() { this.read = false;}


}
