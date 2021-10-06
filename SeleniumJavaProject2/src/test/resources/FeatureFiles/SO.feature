Feature: Test SO

@Test
  Scenario Outline: So test
    Given User enters app url
    When User clicks "<UserName>" and "<Password>"
    Then User says hello

    Examples: 
      | UserName | Password |
      | abc1234  | xyz1234  |
      | abc123   | xyz123   |
