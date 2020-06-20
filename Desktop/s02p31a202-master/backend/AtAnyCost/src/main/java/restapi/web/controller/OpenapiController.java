package restapi.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import restapi.persistence.model.Interview;
import restapi.persistence.model.Recruit;
import restapi.persistence.service.InterviewService;
import restapi.web.dto.InterviewDto;

@RestController
@CrossOrigin
public class OpenapiController {
	
	private List<Recruit> recruits=new ArrayList<Recruit>();

	@GetMapping("/jobapi")
	public List<Recruit> RecruitList() throws Exception{
		BufferedReader br = null;
		String urlstr = "http://openapi.work.go.kr/opi/opi/opia/wantedApi.do?"+
		"authKey=WNKARL3YYEWF5JKCKGRHD2VR1HK&callTp=L&returnType=XML&"+
		"startPage=1&display=100&occupation=024";
        URL url = new URL(urlstr);
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        urlconnection.setRequestMethod("GET");
        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
        String line=br.readLine();
        System.out.println(line);
        
        int idx=0;
        
        while(recruits.size()<100) {
        	Recruit recruit=new Recruit();
        	
        	recruit.setIndex(idx++);
        	
        	int begin=line.indexOf("company")+8;
            int end=line.indexOf("/company")-1;
            String company=line.substring(begin, end);
            recruit.setCompany(company);
            
            begin=line.indexOf("title")+6;
            end=line.indexOf("/title")-1;
            String position=line.substring(begin, end);
            recruit.setPosition(position);
            
            begin=line.indexOf("closeDt")+8;
            end=line.indexOf("/closeDt")-1;
            String dueDate=line.substring(begin, end);
         
            dueDate=dueDate.replace("채용시까지  ", "");
            String realdueDate="20"+dueDate;
            
            recruit.setDueDate(realdueDate);

            begin=line.indexOf("wantedInfoUrl")+14;
            end=line.indexOf("/wantedInfoUrl")-1;
            String link=line.substring(begin, end);
            recruit.setLink(link);
            
            line=line.substring(end+10);
            recruits.add(recruit);
        }
		
		return recruits;
	}
	
}
