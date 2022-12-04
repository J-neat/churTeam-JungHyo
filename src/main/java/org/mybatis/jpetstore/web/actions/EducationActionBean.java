package org.mybatis.jpetstore.web.actions;

import com.mysql.cj.Session;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Example;
import org.mybatis.jpetstore.domain.Question;
import org.mybatis.jpetstore.domain.TestResult;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.CatalogService;
import org.mybatis.jpetstore.service.EducationService;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionScope
public class EducationActionBean extends AbstractActionBean{
    private static final String MAIN = "/WEB-INF/jsp/education/LifeEducation.jsp";
    private static final String VIEW_TEST = "/WEB-INF/jsp/education/Test.jsp";
    private static final String VIEW_RESULT ="/WEB-INF/jsp/education/Result.jsp";
    private static final String VIEW_CHOICE ="/WEB-INF/jsp/education/ChoiceAnimal.jsp";

    @SpringBean
    private transient EducationService educationService;

    private List<Example> exampleList;
    private List<Question> questionList;
    private TestResult testResult  = new TestResult();
    private String type;
    private Example example;
    private Question question;
    private String point1;
    private String point2;
    private String point3;
    private String point4;
    private String point5;
    private String point6;
    private String point7;
    private String tName;

    public String gettName() {
        return tName;
    }
    public void settName(String tName) {
        this.tName = tName;
    }

    public String getPoint1() {
        return point1;
    }
    public void setPoint1(String point1) {
        this.point1 = point1;
    }
    public String getPoint2() {
        return point2;
    }
    public void setPoint2(String point2) {
        this.point2 = point2;
    }
    public String getPoint3() {
        return point3;
    }
    public void setPoint3(String point3) { this.point3 = point3; }
    public String getPoint4() {
        return point4;
    }
    public void setPoint4(String point4) {
        this.point4 = point4;
    }
    public String getPoint5() {
        return point5;
    }
    public void setPoint5(String point5) { this.point5 = point5; }
    public String getPoint6() {
        return point6;
    }
    public void setPoint6(String point6) {
        this.point6 = point6;
    }
    public String getPoint7() {
        return point7;
    }
    public void setPoint7(String point7) {
        this.point7 = point7;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Example> getExampleList() {
        return exampleList;
    }

    public void setExampleList(List<Example> exampleList) {
        this.exampleList = exampleList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    @DefaultHandler
    public ForwardResolution viewMain() {
        return new ForwardResolution(MAIN);
    }

    public ForwardResolution viewLifeEducation() {
        return new ForwardResolution(MAIN);
    }

    /**
     * View Question.
     *
     * @return the forward resolution
     */
    public ForwardResolution viewTest() {
        System.out.println("type = "+type);
        questionList = educationService.getQuestionList(type);
        exampleList =educationService.getExceptionList(type);
        return new ForwardResolution(VIEW_TEST);
    }
    public ForwardResolution viewResult(){


        int totalPoint =0;
//        testResultList = educationService.getTestResultList();
        /*tName을 testResult테이블의 userId에 삽입*/
        System.out.println("아이디 = "+tName);

        totalPoint += Integer.parseInt(point1);
        totalPoint += Integer.parseInt(point2);
        totalPoint += Integer.parseInt(point3);
        totalPoint += Integer.parseInt(point4);
        totalPoint += Integer.parseInt(point5);
        totalPoint += Integer.parseInt(point6);
        totalPoint += Integer.parseInt(point7);
        System.out.println("totalPoint = "+totalPoint);
        testResult.setUserId(tName);


        int flag = 0;
        if((type == "ET") && (totalPoint >= 35)) flag = 1;
        else if ((type != "ET") && (totalPoint >25)) flag = 1;
        switch (type) {
            case "ET":
                testResult.setEt_result(flag);
                break;
            case "DG":
                testResult.setDog_result(flag);
                break;
            case "CT":
                testResult.setCat_result(flag);
                break;
            case "FI":
                testResult.setFish_result(flag);
                break;
            case "BD":
                testResult.setBird_result(flag);
                break;
            case "RT":
                testResult.setRep_result(flag);
                break;
        }
        educationService.updatePoint(testResult);
        HttpSession s = context.getRequest().getSession();
        s.setAttribute("educationBean", this);
        return new ForwardResolution(VIEW_RESULT);
    }


    /**
     * 동물 선택 화면
     * */

    public ForwardResolution viewChoice(){
        return new ForwardResolution(VIEW_CHOICE);
    }

    public void clear(){
        exampleList = null;
        questionList = null;
        testResult = null;
    }

}
