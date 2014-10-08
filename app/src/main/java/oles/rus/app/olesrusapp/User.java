package oles.rus.app.olesrusapp;

/**
 * Created by borimino on 10/2/14.
 */
public class User
{
    private int userId;
    private String name;

    public User(int userId)
    {
        this.userId = userId;
        this.name = "Test Testesen";
    }

    public User(int userId, String name)
    {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getName()
    {
        return name;
    }
}
