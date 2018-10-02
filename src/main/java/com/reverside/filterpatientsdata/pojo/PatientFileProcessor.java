
package com.reverside.filterpatientsdata.pojo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author William
 */
public class PatientFileProcessor {
   
    private LocalDate testDate;
    private File file;
    private List<Patient> patientList = new ArrayList<>();
    Calendar calendarInstanceTestDate = Calendar.getInstance();
    
    Patient patientPojo ;
    public PatientFileProcessor() {
    }
    
    private void readPatientData(String file) throws FileNotFoundException, IOException{
        List<String> patientRecords = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String record = "";
        while((record = br.readLine()) != null){
            if(!record.equals("Name|Gender|BirthDate")){
            String [] data = record.split("\\|");
            patientRecords.add(record);
            patientList.add(new Patient(data[0], data[1],data[2]));
            }
        }
        br.close();
    }
   
    public List<Patient> getPatientOverEighteen(String file) throws IOException{
        readPatientData(file);
        List<Patient> patientsOverEighteen = new ArrayList<>();
        for(Patient patient: patientList ) {
           if(calculateAge(patient.getDob())>=18){
               patientsOverEighteen.add(patient);
           } 
        } 
        return patientsOverEighteen;
    }
   
    public int calculateAge(String dob)
    {
        String [] yearMonthDAY = dob.split("\\-");
        String month = (yearMonthDAY[1].charAt(0) == '0') ? yearMonthDAY[1].replace("0", "") : yearMonthDAY[1] ;
        String day = (yearMonthDAY[2].charAt(0) == '0') ? yearMonthDAY[2].replace("0", "") : yearMonthDAY[2] ;
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(yearMonthDAY[0]),Integer.parseInt(yearMonthDAY[1]), Integer.parseInt(yearMonthDAY[2]));   
        return Period.between(dateOfBirth , currentDate).getYears();   
    }
   
    public void writeAccetablePatientsToFile(File sortedPatientsFile,List<Patient> patients) throws IOException{
       patientPojo = new Patient();
       StringBuilder str = new StringBuilder();
       for(Patient patiant : patients ){
           str.append(patiant.getName() + "|" + patiant.getGender() + "|" + patiant.getDob() + System.lineSeparator());
       } 
       Files.write(Paths.get(sortedPatientsFile.getAbsolutePath()), str.toString().getBytes());
       System.out.println(str.toString());
       Collections.sort(patients,patientPojo.SortByName);
    }  
}
