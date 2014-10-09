package oles.rus.app.olesrusapp;

/**
 * Created by borimino on 10/2/14.
 */
public class User
{
    private int userId;
    private String name;
    private int avatar;

    public User(int userId)
    {
        this.userId = userId;
        this.name = "Test Testesen";
        avatar = 0;
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

    public int getAvatar()
    {
        return avatar;
    }

    public void setAvatar(int avatar)
    {
        this.avatar = avatar;
    }

    public String getName()
    {
        return name;
    }
}
