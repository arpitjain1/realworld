@filterbyapi
Feature: Filter tags by API

  @filterbyapi-test1
  Scenario Outline: Verify filtering by API using "<tag>" valid tag with limit
    Given I call api to verify filtering of "<tag>" tag that is "valid" with "10" limit
    @filterbyapi-test1-HuManIty
    Examples:
      |tag|
      |HuManIty|
    @filterbyapi-test1-Gandhi
    Examples:
      |tag|
      |Gandhi|

  @filterbyapi-test2
  Scenario Outline: Verify filtering by API using "<tag>" valid tag without limit
    Given I call api to verify filtering of "<tag>" tag that is "valid" with "no" limit
    @filterbyapi-test2-HITLER
    Examples:
      |tag|
      |HITLER|
    @filterbyapi-test2-BlackLivesMatter
    Examples:
      |tag|
      |BlackLivesMatter|

  @filterbyapi-test3
  Scenario Outline: Verify filtering by API using "<tag>" invalid tag with limit
    Given I call api to verify filtering of "<tag>" tag that is "invalid" with "10" limit
    @filterbyapi-test3-OutOfWorld
    Examples:
      |tag|
      |OutOfWorld|

  @filterbyapi-test4
  Scenario Outline: Verify filtering by API using "<tag>" invalid tag without limit
    Given I call api to verify filtering of "<tag>" tag that is "invalid" with "no" limit
    @filterbyapi-test3-EOT
    Examples:
      |tag|
      |EOT|