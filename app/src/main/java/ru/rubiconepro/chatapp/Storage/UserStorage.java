package ru.rubiconepro.chatapp.Storage;

import ru.rubiconepro.chatapp.Model.IUser;

public class UserStorage implements IStorage <IUser> {
    @Override
    public void storeElement(IUser element) throws Exception {

    }

    @Override
    public void deleteElement(IUser element) throws Exception {

    }

    @Override
    public void updateElement(IUser element) throws Exception {

    }

    @Override
    public IUser getElementByID(Integer id) {
        return null;
    }

    @Override
    public IUser getElementByField(String field, String value) {
        return null;
    }
}
