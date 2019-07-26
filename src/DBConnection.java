
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DBConnection {
    //create the connection strings
    
    String host="jdbc:mysql://localhost:3306/csc210";
    String user="root";
    String pass="";
    
    //Declare connection object
    
   Connection conn;
   
   //Initialize connection object
   
   public Connection getConnection(){
   
       try{
       
           conn=DriverManager.getConnection(host,user,pass);
           JOptionPane.showMessageDialog(null,"Connection Succesful");
           return conn;
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null,ex.getMessage());
           return null;
       }
      
   }
   

//Insert New Record
public void insertStudent(String studid,String studname,String studg){

String q="INSERT into student(studentid,studentname,gender) VALUES(?,?,?)";

int k=0;

try{
 PreparedStatement st=getConnection().prepareStatement(q);


st.setString(1,studid);
st.setString(2,studname);
st.setString(3,studg);

if(st.executeUpdate()>k){
    
    JOptionPane.showMessageDialog(null,studid+"Registered Succesfully");
}
}

catch(SQLException ex){
    
    JOptionPane.showMessageDialog(null,ex.getMessage());
}
}

///Update Records

public void updateStudent(String stdid,String stdname,String stdgd)
{
    String q="UPDATE student SET studentname=?,gender=? WHERE studentid=?";

    try{
        int k=0;
        
        PreparedStatement st=getConnection().prepareStatement(q);
       st.setString(1, stdname);
       st.setString(2, stdgd);
       st.setString(3, stdid);
    
       if (st.executeUpdate()>k)
       {
           JOptionPane.showMessageDialog(null,stdid+"student record Updated");
       }
    }
    catch(SQLException x)
    {
        JOptionPane.showMessageDialog(null, x.getMessage());
    }
}
///Delete Records

public void deleteStudent(String stdid)
{
    String q="DELETE FROM student WHERE studentid=?";
    
    try{
        int k=0;
        
        PreparedStatement st=getConnection().prepareStatement(q);
        
        st.setString(1, stdid);
        
        if(st.executeUpdate()>k)
        {
            JOptionPane.showMessageDialog(null,stdid+"student record Deleted");
        }
    }
    catch(SQLException x)
    {
        JOptionPane.showMessageDialog(null, x.getMessage());
    }
}

//Retrieve Records
public ResultSet getStudents()
{
    String q="SELECT * FROM student";
    try{
        Statement st=getConnection().createStatement();
        ResultSet rs=st.executeQuery(q);
        return rs;
    }
    catch(SQLException x)
    {
        JOptionPane.showMessageDialog(null, x.getMessage());
        return null;
    }
    }
}


   