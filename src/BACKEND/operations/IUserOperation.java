package BACKEND.operations;

import BACKEND.models.*;

public interface IUserOperation {

    public boolean register(User user);
    public User authenticate(String username,String password);
    public boolean changeInformation(User user);
    public boolean logout();

    public User getById(int id);

}
