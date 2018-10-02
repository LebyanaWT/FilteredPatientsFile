
import com.reverside.filterpatientsdata.pojo.Patient;
import com.reverside.filterpatientsdata.pojo.PatientFileProcessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *
 * @author William
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException,FileNotFoundException, ParseException {
        
        List<Patient> patientdetails;
        try{
            String unsortedTextfile = "UnsortedPatients.txt";
            File sortedTextfile = new File("SortedPatients.txt");
            PatientFileProcessor processesObj = new PatientFileProcessor();
            patientdetails = processesObj.getPatientOverEighteen(unsortedTextfile);
            processesObj.writeAccetablePatientsToFile(sortedTextfile, patientdetails);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }            
    }
    
}
