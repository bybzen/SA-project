package shop.models;

public class Owner {

    private String username;
    private String password;

    public Owner (){
        this.username = "admin2009";
        this.password = "maxmotives";
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}
