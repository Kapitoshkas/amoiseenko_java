package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("fc4807caf5d07b4375000700b7703197f42964cf");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Kapitoshkas", "amoiseenko_java")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {

      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
