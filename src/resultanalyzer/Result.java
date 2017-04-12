/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resultanalyzer;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author suhail
 */
public class Result {
    private final SimpleStringProperty registerNo;
    private final SimpleStringProperty subjectOne;
    private final SimpleStringProperty subjectTwo;
    private final SimpleStringProperty subjectThree;
    private final SimpleStringProperty subjectFour;
    private final SimpleStringProperty subjectFive;
    private final SimpleStringProperty subjectSix;
    private final SimpleStringProperty subjectSeven;
    private final SimpleStringProperty subjectEight;
    private final SimpleStringProperty subjectNine;
    private final SimpleStringProperty subjectTen;
    private final SimpleStringProperty subjectEleven;
    private final SimpleStringProperty subjectTwelve;
    
    
    
    Result(String regno,String sub1,String sub2,String sub3,String sub4,String sub5,String sub6,String sub7,String sub8,String sub9,String sub10,String sub11,String sub12){
        this.registerNo=new SimpleStringProperty(regno);
        this.subjectOne=new SimpleStringProperty(sub1);
        this.subjectTwo=new SimpleStringProperty(sub2);
        this.subjectThree=new SimpleStringProperty(sub3);
        this.subjectFour=new SimpleStringProperty(sub4);
        this.subjectFive=new SimpleStringProperty(sub5);
        this.subjectSix=new SimpleStringProperty(sub6);
        this.subjectSeven=new SimpleStringProperty(sub7);
        this.subjectEight=new SimpleStringProperty(sub8);
        this.subjectNine=new SimpleStringProperty(sub9);
        this.subjectTen=new SimpleStringProperty(sub10);
        this.subjectEleven=new SimpleStringProperty(sub11);
        this.subjectTwelve=new SimpleStringProperty(sub12);
    }
    public String getRegisterNo(){
        return registerNo.get();
    }
    public String getSubjectOne(){
        return subjectOne.get();
    }
    public String getSubjectTwo(){
        return subjectTwo.get();
    }
    public String getSubjectThree(){
        return subjectThree.get();
    }
    public String getSubjectFour(){
        return subjectFour.get();
    }
    public String getSubjectFive(){
        return subjectFive.get();
    }
    public String getSubjectSix(){
        return subjectSix.get();
    }
    public String getSubjectSeven(){
        return subjectSeven.get();
    }
    public String getSubjectEight(){
        return subjectEight.get();
    }
    public String getSubjectNine(){
        return subjectNine.get();
    }
    public String getSubjectTen(){
        return subjectTen.get();
    }
    public String getSubjectEleven(){
        return subjectEleven.get();
    }
    public String getSubjectTwelve(){
        return subjectTwelve.get();
    }
    
    
}
