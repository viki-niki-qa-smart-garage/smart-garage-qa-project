\# Smart Garage Test Plan

Prepared by:
Viktoria Spasova
Nikolay Vlasenko

TABLE OF CONTENTS
1.0 Introduction
2.0 Objectives and Tasks
 2.1 Objectives
 2.2 Tasks
3.0 Scope
4.0 Testing Strategy
 4.1 Unit Testing
 4.2 System and Integration Testing
 4.3 Performance Testing
 4.4 Smoke Testing
 4.5 Automated Regression Testing
 4.6 Acceptance Testing
5.0 Hardware Requirements
6.0 Environment Requirements
 6.1 Main Frame
 6.2 Workstation
7.0 Test Schedule
8.0 Control Procedures
9.0 Features to Be Tested
10.0 Features Not to Be Tested
11.0 Entry Criteria
12.0 Resources/Roles & Responsibilities
13.0 Schedules
14.0 Significantly Impacted Departments (SIDs)
15.0 Dependencies
16.0 Risks/Assumptions
17.0 Tools
18.0 Exit Criteria
19.0 Approvals
________________________________________
1.0 INTRODUCTION
Smart Garage is a web application designed for auto repair shop owners to manage day-to-day operations efficiently. It serves both employees and customers, providing functionality for vehicle management, service tracking, report generation, email notifications, and loyalty management. The purpose of this test plan is to ensure that all features meet functional, technical, and business requirements through structured manual and automated testing.
________________________________________
2.0 OBJECTIVES AND TASKS
2.1 Objectives
•	Ensure all functional requirements are correctly implemented.
•	Validate input, authentication, and authorization mechanisms.
•	Verify filtering, sorting, report generation, PDF creation, currency conversion, and email notifications.
•	Confirm REST API endpoints are functional and documented via Swagger.
•	Identify defects and report them for resolution.
2.2 Tasks
•	Design detailed manual test cases for all functional modules.
•	Execute test cases and document results.
•	Conduct system and integration testing for workflows like customer registration → service → report generation → email.
•	Perform smoke testing for core features.
•	Run automated regression tests on critical paths.
•	Prepare test summary and defect reports.
________________________________________
3.0 SCOPE
In Scope:
•	Public, private, and administrative application parts.
•	Customer and employee workflows (vehicle management, service management, visits, reports).
•	REST API endpoints with Swagger documentation.
•	Validations: usernames, passwords, email, phone, license plates, VIN, service prices.
•	PDF generation, email notifications, loyalty program, visit rating, remote repair requests.
Out of Scope:
•	Optional features not implemented, like continuous integration setup, hosting backend online, or Easter eggs, unless implemented.
________________________________________
4.0 TESTING STRATEGY
4.1 Unit Testing
•	Focus on input validation, business rules, and exception handling.
•	Target at least 80% code coverage in the service layer with BDD-style unit tests.
4.2 System and Integration Testing
•	Test full workflows: customer registration → vehicle/service creation → PDF report generation → emails.
•	Validate API integration, database operations, and third-party services (currency conversion).
4.3 Performance Testing
•	Measure response times for login, filtering, PDF generation, report fetching, and API calls.
•	Verify system handles multiple concurrent users.
4.4 Smoke Testing
•	Ensure core functionality works: login, user registration, service addition, PDF/email generation.
4.5 Automated Regression Testing
•	Automate critical paths: login, service creation, report generation, and email notifications.
•	Run after each release to confirm no regressions.
4.6 Acceptance Testing
•	Verify features meet business expectations.
•	Execute high-priority test scenarios from a user perspective.
________________________________________
5.0 HARDWARE REQUIREMENTS
•	Server: 8GB RAM, 4-core CPU, 250GB SSD
•	Database server (MariaDB)
•	Workstations: 8GB RAM, dual-core CPU, 100GB HDD
________________________________________
6.0 ENVIRONMENT REQUIREMENTS
6.1 Main Frame
•	Web server hosting the application
•	Database server (MariaDB)
•	Email server for notifications
6.2 Workstation
•	Browsers: Chrome, Firefox, Edge
•	Test tools: Selenium (for automation), Postman (API testing), PDF viewer
________________________________________
7.0 TEST SCHEDULE
•	Test Case Design: 3–4 days
•	Manual Test Execution: 5–6 days
•	Smoke Testing: 1 day
•	Regression Testing: 2 days
•	Test Report & Documentation: 1–2 days
________________________________________
8.0 CONTROL PROCEDURES
•	Track defects in Jira.
•	Review test cases before execution.
•	Maintain version control for test artifacts.
________________________________________
9.0 FEATURES TO BE TESTED
Public Part
•	Login with valid/invalid credentials
•	Forgotten password functionality
•	Anonymous access to public pages
Customer Private Part
•	Service list and filtering by vehicle/date
•	Detailed visit reports
•	PDF report generation (single and multiple visits)
•	Currency selection for reports
•	Password change
•	Service status indicator
•	Visit rating
Employee Administrative Part
•	Vehicle management: CRUD, filtering, sorting
•	Service management: CRUD, filtering, sorting
•	Customer management: CRUD, filtering, sorting
•	Visit creation for new/existing customers
•	PDF report generation and email notifications
•	Password reset emails
•	Remote repair workflow
•	Loyalty program discounts
•	Spare parts selection and repair time estimation
•	Service visit calendar constraints
Job Application Workflow
•	User registration before applying
•	Form submission and email notifications to applicant/HR
•	Application status updates in admin panel
REST API
•	CRUD operations for Users, Vehicles, Services
•	Search and filter endpoints
•	Swagger documentation verification
________________________________________
10.0 FEATURES NOT TO BE TESTED
•	Optional features not implemented (CI/CD, backend hosting online, Easter eggs).
•	Non-critical UI elements unrelated to workflows.
________________________________________
11.0 ENTRY CRITERIA
•	Application deployed in test environment.
•	Test data prepared for customers, vehicles, services.
•	REST API deployed with Swagger documentation.
________________________________________
12.0 RESOURCES / ROLES & RESPONSIBILITIES
•	Viktoria Spasova: Test case design, manual testing, defect reporting
•	Nikolay Vlasenko: Test execution, smoke testing, regression testing
________________________________________
13.0 SCHEDULES
•	Week 1: Test case creation, environment setup
•	Week 2: Manual testing, defect logging
•	Week 3: Regression testing, acceptance testing, final report
________________________________________
14.0 SIGNIFICANTLY IMPACTED DEPARTMENTS (SIDs)
•	IT / Development Team
•	Auto repair shop administration
•	Customer support
•	HR (for job applications)
________________________________________
15.0 DEPENDENCIES
•	Database server availability
•	Email server functional
•	Third-party API access (currency conversion, car models)
•	REST API deployed and documented
________________________________________
16.0 RISKS / ASSUMPTIONS
•	Risk: Email or PDF failures due to network or server issues
•	Risk: Currency conversion service may be unavailable
•	Assumption: Test data accurately represents production scenarios
________________________________________
17.0 TOOLS
•	Jira – Defect tracking, test management
•	Selenium – Automated regression tests
•	Postman – REST API testing
•	Browser developer tools – UI inspection
•	PDF viewer – Report verification
________________________________________
18.0 EXIT CRITERIA
•	All high-priority test cases executed and documented
•	All critical defects resolved or logged with workarounds
•	Acceptance tests approved by stakeholders
________________________________________
19.0 APPROVALS
Role	Name	Signature	Date
QA Tester	Viktoria Spasova	___________	_______
QA Tester	Nikolay Vlasenko	___________	_______


