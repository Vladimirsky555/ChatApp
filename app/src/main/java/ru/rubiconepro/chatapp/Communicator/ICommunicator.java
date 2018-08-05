package ru.rubiconepro.chatapp.Communicator;

import ru.rubiconepro.chatapp.Model.IUser;

public interface ICommunicator {
    void sendMessage(IUser user, String message);
    void subscribe(IResiver reciver);
    void unsubscribe(IResiver reciver);

    void stopConnector();
}
