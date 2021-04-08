package Github.Issues.collector;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class GitHubIssues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			GitHub github = GitHub.connectAnonymously();
			var ghRepo = github.getRepository("soot-oss/soot");
			System.out.println("Repo Intialized");
			List<GHIssue> sootIssues = ghRepo.getIssues(GHIssueState.ALL);
			System.out.println("repo got connected");
			 FileWriter writer = new FileWriter("issues.csv");
	            writer.append("Id, Title, Body Text, comments");
	            writer.append("\n");

//	            for (GHIssue issue :sootIssues) {
//	                writer.append(String.valueOf(issue.getNumber()) + ",");
//	                writer.append(issue.getTitle() + ",");
//	                writer.append(issue.getUser().getLogin() + ",");
//	                if (issue.getAssignee() != null) {
//	                    writer.append(issue.getAssignee().getName() + ",");
//	                } else {
//	                    writer.append(" ,");
//	                }
//	                if (issue.getMilestone() != null) {
//	                    writer.append(issue.getMilestone().getTitle() + ",");
//	                } else {
//	                    writer.append(" ,");
//	                }
//	                writer.append(issue.getState() + ",");
//	                writer.append(issue.getBody() + ",");
//	                writer.append("\n");
//	                if(issue.getComments().size() != 0)
//	                	writer.append(issue.getComments().toString());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
//	                	
//	                writer.append("\n");
//	                
//	            }
	           System.out.println("File Writing Started");
	            sootIssues.stream().forEach(i -> {
	            	try {
						writer.append(String.valueOf(i.getNumber()) + ",");
						writer.append(i.getTitle() + ",");
						writer.append(i.getBody() + ",");
						if(i.getComments().size() != 0)
		                	writer.append(i.getComments().toString());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
                	
		                writer.append("\n");
						
						 
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            });
	            
	            
	            writer.flush();
	            writer.close();
	           
		
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
