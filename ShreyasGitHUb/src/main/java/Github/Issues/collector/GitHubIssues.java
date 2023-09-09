package Github.Issues.collector;

import java.io.FileWriter;
import java.io.IOException;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.util.logging.Logger;

public class GitHubIssues {

    Logger log = Logger.getLogger(GitHubIssues.class.getName());

    public static void main(String[] args) {

        try {
            GitHub github = GitHub.connectAnonymously();
            var ghRepo = github.getRepository("soot-oss/soot");
            log.info("Repo Intialized");
            List<GHIssue> sootIssues = ghRepo.getIssues(GHIssueState.ALL);
            log.info("repo got connected");
            FileWriter writer = new FileWriter("issues.csv");
            writer.append("Id, Title, Body Text, comments");
            writer.append("\n");

            log.info("File Writing Started");
            sootIssues.stream().forEach(i -> {
                try {
                    writer.append(String.valueOf(i.getNumber()) + ",");
                    writer.append(i.getTitle() + ",");
                    writer.append(i.getBody() + ",");
                    if (i.getComments().size() != 0) writer.append(i.getComments().toString());

                    writer.append("\n");


                } catch (IOException e) {
                    e.printStackTrace();
                }

            });


            writer.flush();
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
