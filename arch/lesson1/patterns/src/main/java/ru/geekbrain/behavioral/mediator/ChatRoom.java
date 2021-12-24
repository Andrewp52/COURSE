package ru.geekbrain.behavioral.mediator;

public class ChatRoom implements ChatRoomMediator{
    @Override
    public void showMessage(User user, String message) {
        System.out.printf("%s: %s\r\n", user.getName(), message);
    }
}
