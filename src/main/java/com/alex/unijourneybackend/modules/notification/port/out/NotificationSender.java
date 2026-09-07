package com.alex.unijourneybackend.modules.notification.port.out;

public interface NotificationSender {

    /**
     * Sends a notification to the specified user.
     * @param username the username of the user to notify
     * @param subject the subject of the notification
     * @param message the message of the notification
     */
    void send(String username, String subject, String message);


}
