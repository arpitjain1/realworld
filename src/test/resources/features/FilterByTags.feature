@filterbytag
Feature: Test filtering by tags

  @filterbytag-test1
  Scenario Outline: Filter by "<tag>" valid tag
    Given I filter the page by "<tag>" tag that is "valid"
    And I verify for all occurrences of "<tag>" tag on the page
    And I verify for "<tag>" tag on the feed title
    @filterbytag-test1-Gandhi
    Examples:
      |tag|
      |Gandhi|
    @filterbytag-test1-SIDA
    Examples:
      |tag|
      |SIDA|
    @filterbytag-test1-test
    Examples:
      |tag|
      |test|
    @filterbytag-test1-butt
    Examples:
      |tag|
      |butt|

  @filterbytag-test2
  Scenario Outline: Filter by "<tag>" invalid tag
    Given I filter the page by "<tag>" tag that is "invalid"
    @filterbytag-test2-invalid
      Examples:
        |tag|
        |invalid|
    @filterbytag-test2-BLACKMAGIC
      Examples:
        |tag|
        |BLACKMAGIC|

  @filterbytag-test3
  Scenario: Repeated filter by same tag
    Given I filter the page by "HuManIty" tag that is "valid"
    And I verify for all occurrences of "HuManIty" tag on the page
    And I verify for "HuManIty" tag on the feed title
    Then I filter the page by "HuManIty" tag that is "valid"
    And I verify for all occurrences of "HuManIty" tag on the page
    And I verify for "HuManIty" tag on the feed title

  @filterbytag-test4
  Scenario: Repeated filter by different tags
    Given I filter the page by "dragons" tag that is "valid"
    And I verify for all occurrences of "dragons" tag on the page
    And I verify for "dragons" tag on the feed title
    Then I filter the page by "HITLER" tag that is "valid"
    And I verify for all occurrences of "HITLER" tag on the page
    And I verify for "HITLER" tag on the feed title

  @filterbytag-test5
  Scenario Outline: Filter by all occurrences of a "<tag>" and validate
    Given I filter the page by "<tag>" tags and verify for all occurrences
  @filterbytag-test5-BlackLivesMatter
    Examples:
      |tag|
      |BlackLivesMatter|
  @filterbytag-test5-HuManIty
    Examples:
      |tag|
      |HuManIty|

  @filterbytag-test6
  Scenario: Filter by all occurrences of all the tags
    Given I filter the page by all tags and verify for all occurrences
